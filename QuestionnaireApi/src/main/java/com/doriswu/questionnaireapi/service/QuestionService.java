package com.doriswu.questionnaireapi.service;

import com.doriswu.questionnaireapi.dao.OptionDao;
import com.doriswu.questionnaireapi.dao.QuestionDao;
import com.doriswu.questionnaireapi.entity.Answer;
import com.doriswu.questionnaireapi.entity.Option;
import com.doriswu.questionnaireapi.entity.Question;
import com.doriswu.questionnaireapi.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private UserService userService;

    public void postQuestion(Question question, Map<String, String> optionContents) throws Exception{
        List<Option> optionList = new ArrayList<>();

        // instantiate options
        for(Map.Entry<String, String> entry : optionContents.entrySet()){
            String correct = entry.getValue();
            Option option = new Option();
            option.setContent(entry.getKey());
            option.setQuestion(question);
            option.setAnswerList(new ArrayList<Answer>());

            String type = question.getType();
            if(question.getType().equals("trivia")){          // question has correct answer or not
                if(correct.equals("true") || correct.equals("True") || correct.equals("TURE")){
                    option.setCorrect(true);
                }
                else{
                    option.setCorrect(false);
                }
            }

            else{
                option.setCorrect(true);
            }
            optionList.add(option);
        }

        question.setOptionList(optionList);

        try{
            questionDao.postQuestion(question);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public int getNotAnsweredQuestion(){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);

        List<Answer> answerList = user.getAnswerList();
        List<Integer> allQuestionId = questionDao.getAllQuestionId();
        for(int i: allQuestionId){
            boolean flag = true;
            for(Answer a: answerList){
                if(i == a.getQuestionId()){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return i;
            }
        }
        // all question answered
        return -1;
    }


    public Question getQuestion(int questionId){
        Question q = null;
        try{
            q = questionDao.getQuestion(questionId);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return q;
    }

    public List<Integer> getAllQuestionId(){
        return questionDao.getAllQuestionId();
    }


}
