package com.zmt.community.entity;

import lombok.*;

import java.util.Date;

/**
 * @author zmt
 */
@NoArgsConstructor
@ToString
@Setter
@Getter
@AllArgsConstructor
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private int status;
    private Date expired;
}
