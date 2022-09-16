package com.doriswu.questionnaireapi.dao;

import com.doriswu.questionnaireapi.entity.Answer;
import com.doriswu.questionnaireapi.entity.Question;
import com.doriswu.questionnaireapi.entity.User;
import com.doriswu.questionnaireapi.service.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionDao {
    @Autowired
    private SessionFactory sessionFactory;


    public void postQuestion(Question question){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(question);
            session.getTransaction().commit();
        }
        catch (Exception ex){
            ex.printStackTrace();
            if(session != null){
                session.getTransaction().rollback();
            }
        }
        finally {
            if(session != null){
                session.close();
            }
        }
    }




    public Question getQuestion(int questionId){
        Question question = null;
        try(Session session = sessionFactory.openSession()){
            question = session.get(Question.class, questionId);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return question;
    }

    public List<Integer> getAllQuestionId(){
        Session session = null;
        List<Integer> questionIdList = new ArrayList<>();
        try{
            session = sessionFactory.openSession();
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Question> criteria = builder.createQuery(Question.class);
            criteria.from(Question.class);
            List<Question> questionList = session.createQuery(criteria).getResultList();
            for(Question q: questionList){
                questionIdList.add(q.getId());
            }
            return questionIdList;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        } finally{
            if(session != null){
                session.close();
            }
        }
        return null;
    }


}
