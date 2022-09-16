package com.doriswu.questionnaireapi.service;

import com.doriswu.questionnaireapi.dao.AnswerDao;
import com.doriswu.questionnaireapi.dao.OptionDao;
import com.doriswu.questionnaireapi.entity.Answer;
import com.doriswu.questionnaireapi.entity.Option;
import com.doriswu.questionnaireapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerDao answerDao;

    @Autowired
    private OptionDao optionDao;

    @Autowired
    private UserService userService;

    public Answer submitAnswer(Answer answer, List<String> selected){
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        String username = loggedInUser.getName();
        User user = userService.getUser(username);
        answer.setUser(user);

        List<Option> optionList = new ArrayList<>();
        for(String s: selected){
            Option option = optionDao.getOptionByQAndContext(answer.getQuestionId(), s);
            if(option == null){
                return null;
            }
            optionList.add(option);
        }
        answer.setOptionList(optionList);
        answerDao.submitAnswer(answer);
        return answer;
    }

}
