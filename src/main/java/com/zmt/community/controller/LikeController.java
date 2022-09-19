package com.zmt.community.controller;

import com.zmt.community.entity.Event;
import com.zmt.community.entity.User;
import com.zmt.community.event.EventProducer;
import com.zmt.community.service.LikeService;
import com.zmt.community.util.CommunityConstant;
import com.zmt.community.util.CommunityUtil;
import com.zmt.community.util.HostHolder;
import com.zmt.community.util.RedisKeyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理点赞的相关请求
 */
@Controller
public class LikeController implements CommunityConstant {

    //点赞的业务层
    @Autowired
    private LikeService likeService;

    //当前用户
    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private EventProducer eventProducer;


    /**
     * 完成当前用户点赞、取消点赞请求，异步请求
     * @param entityType 实体类型
     * @param entityId 实体id
     * @param  entityUserId 实体作者id
     * @return JSON对象
     */
    @RequestMapping(path = "/like", method = RequestMethod.POST)
    @ResponseBody
    public String like(int entityType, int entityId, int entityUserId, int postId) {
        User user = hostHolder.getUser();

        // 点赞
        likeService.like(user.getId(), entityType, entityId, entityUserId);

        // 数量
        long likeCount = likeService.findEntityLikeCount(entityType, entityId);
        // 状态
        int likeStatus = likeService.findEntityLikeStatus(user.getId(), entityType, entityId);
        // 返回的结果
        Map<String, Object> map = new HashMap<>();
        map.put("likeCount", likeCount);
        map.put("likeStatus", likeStatus);

        // 触发点赞事件
        if (likeStatus == 1) {
            Event event = new Event()
                    .setTopic(TOPIC_LIKE)
                    .setUserId(hostHolder.getUser().getId())
                    .setEntityType(entityType)
                    .setEntityId(entityId)
                    .setEntityUserId(entityUserId)
                    .setData("postId", postId);
            //加postId是因为通知里要有链接跳转到被点赞的帖子内，即使是用户的回复被点赞了也要跳转到这个帖子上
            eventProducer.fireEvent(event);
        }

        return CommunityUtil.getJSONString(0, null, map);
    }

}
