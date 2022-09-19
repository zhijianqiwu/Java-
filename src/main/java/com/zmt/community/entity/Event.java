package com.zmt.community.entity;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装事件的实体，即使用kafka发送消息的消息
 */

@NoArgsConstructor
@ToString
@Getter
public class Event {

    //主题
    private String topic;
    //事件发送者
    private int userId;
    //实体类型
    private int entityType;
    //实体id
    private int entityId;
    //实体拥有者
    private int entityUserId;
    //事件对象要具有通用性，在其他业务可能有特殊的字段，后续拓展用map封装即可
    private Map<String, Object> data = new HashMap<>();


    //为了多次调用set方法让其返回对象，避免有些事件某个属性为null需要重载多个构造器
    public Event setTopic(String topic) {
        this.topic = topic;
        return this;
    }


    public Event setUserId(int userId) {
        this.userId = userId;
        return this;
    }


    public Event setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }


    public Event setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }


    public Event setEntityUserId(int entityUserId) {
        this.entityUserId = entityUserId;
        return this;
    }


    public Event setData(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

}
