package com.doriswu.questionnaireapi.dao;

import com.doriswu.questionnaireapi.entity.Answer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDao {

    @Autowired
    private SessionFactory sessionFactory;


    public void submitAnswer(Answer answer){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(answer);
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


}
