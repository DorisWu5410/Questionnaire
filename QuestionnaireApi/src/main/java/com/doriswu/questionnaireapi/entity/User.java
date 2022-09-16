package com.doriswu.questionnaireapi.entity;


import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String username;
    private String password;

    private boolean enable;



    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answerList;



    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
//    public Set<Question> getAnsweredQuestion() {
//        return answeredQuestion;
//    }
//
//    public void setAnsweredQuestion(Set<Question> answeredQuestion) {
//        this.answeredQuestion = answeredQuestion;
//    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
