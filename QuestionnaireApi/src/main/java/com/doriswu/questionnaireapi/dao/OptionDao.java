package com.doriswu.questionnaireapi.dao;

import com.doriswu.questionnaireapi.entity.Option;
import com.doriswu.questionnaireapi.entity.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void saveOption(Option option){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(option);
            session.getTransaction().commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            if(session != null){
                session.getTransaction().rollback();       // assures atomicity
            }
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }

    public Option getOptionByQAndContext(int questionId, String content){
        List<Option> optionList = getOptionByQuestionId(questionId);
        for(Option o: optionList){
            String c = o.getContent();
            if(content.equals(c)){
                return o;
            }
        }
        return null;
    }


    public List<Option> getOptionByQuestionId(int questionId){
        try (Session session = sessionFactory.openSession()) {
            Question question = session.get(Question.class, questionId);
            if (question != null) {
                return question.getOptionList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
