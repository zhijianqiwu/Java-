package com.zmt.community.service;

import com.zmt.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

/**
 *  实现点赞相关业务
 * @author zmt
 */
@Service
public class LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 实现点赞、取消点赞业务
     * 由于该业务需要更改两个数据，所以采用事务
     * @param userId 执行用户
     * @param entityType 被点赞的实体类型
     * @param entityId  被点赞的实体id
     * @param entityUserId 实体作者，为了更新该作者的点赞数
     */
    public void like(int userId, int entityType, int entityId, int entityUserId) {
        redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                //获取实体点赞信息key
                String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
                //获取用户获赞数的key
                String userLikeKey = RedisKeyUtil.getUserLikeKey(entityUserId);

                //判断该用户是否点赞
                boolean isMember = operations.opsForSet().isMember(entityLikeKey, userId);
                //开启事务
                operations.multi();

                if (isMember) {
                    //如果点赞了就取消点赞，即从集合删除该用户id
                    operations.opsForSet().remove(entityLikeKey, userId);
                    //作者获赞数减小
                    operations.opsForValue().decrement(userLikeKey);
                } else {
                    //没点赞就点赞
                    operations.opsForSet().add(entityLikeKey, userId);
                    //用户获赞数增加
                    operations.opsForValue().increment(userLikeKey);
                }
                //事务提交
                return operations.exec();
            }
        });
    }

    /**
     * 查询某实体点赞的数量
     * @param entityType 实体类型
     * @param entityId 实体id
     * @return 点赞数
     */
    public long findEntityLikeCount(int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().size(entityLikeKey);
    }

    /**
     *  查询某人对某实体的点赞状态，页面上才能实现已赞显示已赞，没有点赞过显示赞
     * @param userId 用户id
     * @param entityType 实体类型
     * @param entityId 实体id
     * @return 点赞状态 1：点赞 0：没有点赞 后续可以拓展踩：-1
     */
    public int findEntityLikeStatus(int userId, int entityType, int entityId) {
        String entityLikeKey = RedisKeyUtil.getEntityLikeKey(entityType, entityId);
        return redisTemplate.opsForSet().isMember(entityLikeKey, userId) ? 1 : 0;
    }

    /**
     * // 查询某个用户获得的赞
     * @param userId 用户id
     * @return 获赞数
     */
    public int findUserLikeCount(int userId) {
        String userLikeKey = RedisKeyUtil.getUserLikeKey(userId);
        Integer count = (Integer) redisTemplate.opsForValue().get(userLikeKey);
        return count == null ? 0 : count;
    }

}
