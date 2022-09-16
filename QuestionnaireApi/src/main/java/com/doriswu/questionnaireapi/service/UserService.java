package com.doriswu.questionnaireapi.service;

import com.doriswu.questionnaireapi.dao.UserDao;
import com.doriswu.questionnaireapi.entity.Answer;
import com.doriswu.questionnaireapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public void signUp(User user){
        List<Answer> answerList = new ArrayList<>();
        user.setAnswerList(answerList);
        user.setEnable(true);
        userDao.signup(user);
    }

    public User getUser(String username){
        return userDao.getUser(username);
    }


}
