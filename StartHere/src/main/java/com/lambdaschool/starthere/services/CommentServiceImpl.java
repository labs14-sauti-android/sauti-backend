package com.lambdaschool.starthere.services;


import com.lambdaschool.starthere.exceptions.ResourceNotFoundException;
import com.lambdaschool.starthere.models.Comment;
import com.lambdaschool.starthere.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentrepos;
    private List<Comment> comments;

    @Override
    public List<Comment> findAll()
    {
        List<Comment> clist = new ArrayList<>();
        commentrepos.findAll().iterator().forEachRemaining(clist::add);
        return clist;
    }

    @Override
    public Comment findCommentById(long id)
    {
        return commentrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Long.toString(id)));
    }


    @Override
    public List<Comment> findByQuestionId(String questionsid) {

        return comments;
    }


    @Transactional
    @Override
    public Comment save(Comment comment)
    {

        Comment saveComment =  commentrepos.save(comment);
//        smsSender.sendSms(new SmsRequest("919-438-9115", "MentorMe has sent you a new question " + saveQuestion.getQuestion()));
        return saveComment;
    }

}

