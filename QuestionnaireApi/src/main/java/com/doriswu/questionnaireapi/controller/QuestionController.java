package com.doriswu.questionnaireapi.controller;

import com.doriswu.questionnaireapi.entity.Answer;
import com.doriswu.questionnaireapi.entity.Option;
import com.doriswu.questionnaireapi.entity.Question;
import com.doriswu.questionnaireapi.service.AnswerService;
import com.doriswu.questionnaireapi.service.OptionService;
import com.doriswu.questionnaireapi.service.QuestionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;



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

    // submit answer
    @RequestMapping(value = "/question/{questionId}/submit-answer", method = RequestMethod.POST)
    @ResponseBody
    public boolean answerQuestion(@PathVariable("questionId") int questionId, @RequestBody Param2 p, HttpServletResponse response) throws Exception{
        Question question = questionService.getQuestion(questionId);
        if(question == null){
            response.setStatus(400);
            response.getOutputStream()
                    .println("invalid questionId");
            return false;
        }

        Answer answer = p.getAnswer();
        List<String> selected = p.getSelected();
        answer.setQuestionId(questionId);

        Answer newAnswer = answerService.submitAnswer(answer, selected);

        // invalid options
        if(newAnswer == null){
            response.setStatus(400);
            response.getOutputStream()
                    .println("contains invalid options");
            return false;
        }

        //check correct or not
        else{
            response.setStatus(200);
            List<Option> optionList= newAnswer.getOptionList();
            if(!question.getType().equals("trivia")){
                return true;
            }
            for(Option option: optionList){
                if(!option.isCorrect()){
                    return false;
                }
            }
            return true;
        }
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

class Param2{
    private Answer answer;

    private List<String> selected;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
    }
}


