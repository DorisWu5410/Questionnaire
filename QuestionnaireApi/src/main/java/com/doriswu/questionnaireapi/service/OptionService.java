package com.doriswu.questionnaireapi.service;

import com.doriswu.questionnaireapi.dao.OptionDao;
import com.doriswu.questionnaireapi.entity.Option;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {
    @Autowired
    private OptionDao optionDao;

    public void saveOption(Option option){
        optionDao.saveOption(option);
    }


}
