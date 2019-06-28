package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Comment;

import java.util.List;

public interface CommentService
{
    List<Comment> findAll();

    Comment findCommentById(long id);

    List<Comment> findByQuestionId(String questionsid);


    Comment save(Comment comment);
}
