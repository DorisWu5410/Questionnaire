package com.doriswu.questionnaireapi.controller;

import com.doriswu.questionnaireapi.entity.Option;
import com.doriswu.questionnaireapi.entity.Question;
import com.doriswu.questionnaireapi.service.OptionService;
import com.doriswu.questionnaireapi.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;



    @RequestMapping(value = "/post-question", method = RequestMethod.POST)
    public void postQuestion(@RequestBody Param p, HttpServletResponse response){
        Question question = p.getQuestion();
        Map<String, String> optionContents = p.getOptionContent();

        try {
            questionService.postQuestion(question, optionContents);
            response.setStatus(200);
        }
        catch(Exception ex){
            ex.printStackTrace();
            response.setStatus(400);
        }

    }

    @RequestMapping(value = "/question", method = RequestMethod.GET)
    @ResponseBody
    public Question getQuestion(HttpServletResponse response) throws Exception{
        int questionId = questionService.getNotAnsweredQuestion();
        if(questionId == -1){
            response.setStatus(400);
            response.getOutputStream()
                    .println("you have answered all the questions!");
        }
        else{
            response.setStatus(200);
            return questionService.getQuestion(questionId);
        }
        return null;
    }
}

class Param{
    private Question question;
    private Map<String, String> optionContent;          // {"content": true}

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Map<String, String> getOptionContent() {
        return optionContent;
    }

    public void setOptionContent(Map<String, String> optionContent) {
        this.optionContent = optionContent;
    }
}
