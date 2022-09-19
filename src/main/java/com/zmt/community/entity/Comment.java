package com.zmt.community.entity;

import lombok.*;

import java.util.Date;

/**
 * 评论类
 * @author zmt
 */
@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Comment {
    private int id;
    private int userId;
    private int entityType;
    private int entityId;
    private int targetId;
    private String content;
    private int status;
    private Date createTime;
}
