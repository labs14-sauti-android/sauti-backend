package com.lambdaschool.starthere.repository;

import com.lambdaschool.starthere.models.Comment;
import com.lambdaschool.starthere.models.User;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository <Comment, Long> {


}
