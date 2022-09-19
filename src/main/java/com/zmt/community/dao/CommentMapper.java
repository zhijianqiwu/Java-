package com.zmt.community.dao;

import com.zmt.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    //获取某个实体所有的评论
    List<Comment> selectCommentsByEntity(int entityType, int entityId, int offset, int limit);

    //查找某个实体被评论数
    int selectCountByEntity(int entityType, int entityId);

    //增加评论
    int insertComment(Comment comment);

    //查找评论
    Comment selectCommentById(int id);
}
