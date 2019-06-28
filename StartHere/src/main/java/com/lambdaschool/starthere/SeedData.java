package com.lambdaschool.starthere;

import com.lambdaschool.starthere.models.*;
import com.lambdaschool.starthere.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


//@Transactional
//@Component
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
}