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
public class User {
   private int id;
   private String username;
   private String password;
   private String salt;
   private String email;
   private int type;
   private int status;
   private String activationCode;
   private String headerUrl;
   private Date createTime;
}
