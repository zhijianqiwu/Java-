package com.zmt.community.entity;

import lombok.*;

import java.util.Date;

/**
 * 消息类 和他人的会话信息
 * @author zmt
 */
@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Message {
    private int id;
    private int fromId;
    private int toId;
    private String conversationId;
    private String content;
    private int status;//0 未读、 1 已读 2 删除
    private Date createTime;
}
