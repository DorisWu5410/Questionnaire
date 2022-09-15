package com.doriswu.questionnaireapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.ManyToMany;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Option {
    @Id
    private int id;
    private int questionId;
    private String content;

    @ManyToOne
    @JsonIgnore
    private Question question;

    @ManyToMany(mappedBy = "optionList")
    private List<Answer> answerList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }
}
