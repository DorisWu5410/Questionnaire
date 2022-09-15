package com.doriswu.questionnaireapi.entity;


import javax.persistence.*;

import java.util.*;

@Entity
public class User {
    @Id
    private String username;
    private String password;

//    @ManyToMany
//    @JoinTable(
//            name = "user_answered_q",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "question_id")
//    )
//    private Set<Question> answeredQuestion;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answerList;


//    public Set<Question> getAnsweredQuestion() {
//        return answeredQuestion;
//    }
//
//    public void setAnsweredQuestion(Set<Question> answeredQuestion) {
//        this.answeredQuestion = answeredQuestion;
//    }

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
