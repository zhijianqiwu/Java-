package com.zmt.community.dao;

import com.zmt.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 登录凭证的mapper接口，已过期，优化后将登录凭证存放在Redis而不是Mysql
 */
@Mapper
@Deprecated
public interface DiscussPostMapper {
    /**
     * 获取帖子
     * @param userId 0：忽略条件
     * @param offset 偏移
     * @param limit  数据条数
     * @return  帖子集合
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param注解用于给参数取别名,
    // 如果只有一个参数,并且在<if>里使用,则必须加别名.

    /**
     * 获取帖子总数
     * @param userId 0：忽略条件
     * @return 帖子总数
     */
    int selectDiscussPostRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);


    /**
     * 获取帖子
     * @param id 主键
     * @return 帖子
     */
    DiscussPost selectDiscussPostById(int id);

    //更新帖子评论数
    int updateCommentCount(int id, int commentCount);

    //更新帖子类型
    int updateType(int id, int type);

    //更新帖子状态
    int updateStatus(int id, int status);

    //更新帖子分数
    int updateScore(int id, double score);
}
