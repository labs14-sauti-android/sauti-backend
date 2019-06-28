package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.repository.*;
import com.lambdaschool.starthere.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;

@Transactional
@Component
public class SeedData implements CommandLineRunner {

    UserRepository userrepos;
    RoleRepository rolerepos;
    TypeRepository typerepos;
    QuestionRepository questionrepos;
    CommentRepository commentrepos;


    public SeedData(UserRepository userrepos, RoleRepository rolerepos, TypeRepository typerepos, QuestionRepository questionrepos, CommentRepository commentrepos) {
        this.userrepos = userrepos;
        this.rolerepos = rolerepos;
        this.typerepos = typerepos;
        this.questionrepos = questionrepos;
        this.commentrepos = commentrepos;
    }

    @Override
    public void run(String[] args) throws Exception {

        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        rolerepos.save(r1);
        rolerepos.save(r2);
        rolerepos.save(r3);

        Type t1 = new Type("mentor");
        Type t2 = new Type("entrepreneur");

        typerepos.save(t1);
        typerepos.save(t2);

        ArrayList<UserRoles> adminUserRoles = new ArrayList<>();
        adminUserRoles.add(new UserRoles(new User(), r1));
        adminUserRoles.add(new UserRoles(new User(), r2));
        adminUserRoles.add(new UserRoles(new User(), r3));
        ArrayList<UserTypes> adminUserTypes = new ArrayList<>();
        adminUserTypes.add(new UserTypes(new User(), t1));
        adminUserTypes.add(new UserTypes(new User(), t2));
        User u1 = new User("admin", "password", "phoneNumber", "industrytype", adminUserTypes, adminUserRoles);
//        u1.getQuestions().add(new Question("What criteria are required to apply to Y Combinator?", u1));
//        u1.getQuestions().add(new Question("When should we develop inhouse versus outsourcing?", u1));
        userrepos.save(u1);

//                ArrayList<Question> quests = new ArrayList<>()
        Question q1 = new Question("help", u1);
        questionrepos.save(q1);
        commentrepos.save(new Comment("this is my comment", q1));
    }

//    @Autowired
//    TypeService typeService;
//
//    @Autowired
//    RoleService roleService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    QuestionService questionService;
//
//    @Autowired
//    CommentService commentService;
//
//
//    @Override
//    public void run(String[] args) throws Exception
//    {
//
//        Type t1 = new Type("mentor");
//        Type t2 = new Type("entrepreneur");
//
//        typeService.save(t1);
//        typeService.save(t2);
//
//        Role r1 = new Role("admin");
//        Role r2 = new Role("user");
//        Role r3 = new Role("data");
//
//        roleService.save(r1);
//        roleService.save(r2);
//        roleService.save(r3);
//
//        // admin, data, user
//        ArrayList<UserRoles> adminUserRoles = new ArrayList<>();
//        adminUserRoles.add(new UserRoles(new User(), r1));
//        adminUserRoles.add(new UserRoles(new User(), r2));
//        adminUserRoles.add(new UserRoles(new User(), r3));
//        ArrayList<UserTypes> adminUserTypes = new ArrayList<>();
//        adminUserTypes.add(new UserTypes(new User(), t1));
//        adminUserTypes.add(new UserTypes(new User(), t2));
//        User u1 = new User("admin", "password", "phoneNumber", "industrytype", adminUserTypes, adminUserRoles);
//        u1.getQuestions().add(new Question("What criteria are required to apply to Y Combinator?", u1));
//        u1.getQuestions().add(new Question("When should we develop inhouse versus outsourcing?", u1));
//        userService.save(u1);
//
//        // data, user
//        ArrayList<UserRoles> datas = new ArrayList<>();
//        datas.add(new UserRoles(new User(), r3));
//        datas.add(new UserRoles(new User(), r2));
//        ArrayList<UserTypes> dataUserTypes = new ArrayList<>();
//        dataUserTypes.add(new UserTypes(new User(), t1));
//        dataUserTypes.add(new UserTypes(new User(), t2));
//        User u2 = new User("cinnamon", "1234567", "phoneNumber", "industrytype", dataUserTypes, datas);
//        userService.save(u2);
//
//        // user
//        ArrayList<UserRoles> users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        ArrayList<UserTypes> userUT = new ArrayList<>();
//        userUT.add(new UserTypes(new User(), t1));
//        userUT.add(new UserTypes(new User(), t2));
//        User u3 = new User("Jane Doe", "ILuvM4th", "phoneNumber", "industrytype", userUT, users);
//        u3.getQuestions().add(new Question("How do get Angel funding?", u3));
//        u3.getQuestions().add(new Question("What should I look for in a cofounder?", u3));
//        u3.getQuestions().add(new Question("When do I need to bring on a CFO?", u3));
//        userService.save(u3);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        ArrayList<UserTypes> uu = new ArrayList<>();
//        uu.add(new UserTypes(new User(), t2));
//        User u4 = new User("Barn Barn", "whynot", "phoneNumber", "industrytype", uu, users);
//        userService.save(u4);
//
//        users = new ArrayList<>();
//        users.add(new UserRoles(new User(), r2));
//        ArrayList<UserTypes> ut = new ArrayList<>();
//        ut.add(new UserTypes(new User(), t2));
//        User u5 = new User("Jane Smith", "because", "phoneNumber", "industrytype", ut, users);
//        Question q10 = new Question("When do I get to sleep?",  u5);
//        q10.getComments().add(new Comment ("comments", q10));
////        u5.getQuestions().add(new Question("When do I get to sleep?",  u5));
////        u5.getQuestions().get(0).getComments().add(new Comment ("comments", u5.getQuestions().get(0)));
//        u5.getQuestions().add(q10);
//        userService.save(u5);
//
////        commentService.save(new Comment( "comments", u5.getQuestions().get(0)));
//
//
////        ArrayList<String> comments = new ArrayList<>();
////        comments.add("Great comment wish I had an answer.");
////
////        q10.setComments(comments);
//    }
}