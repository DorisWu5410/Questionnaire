package com.doriswu.questionnaireapi.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "questions")
public class Question {

    @Id
    private int id;
    private String type;
    private String content;

//    @ManyToMany(mappedBy = "answeredQuestion")
//    private Set<User> userSet;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Answer> answerlist;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Option> optionList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Answer> getAnswerlist() {
        return answerlist;
    }

    public void setAnswerlist(List<Answer> answerlist) {
        this.answerlist = answerlist;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
    }
}
