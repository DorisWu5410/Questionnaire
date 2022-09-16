package com.doriswu.questionnaireapi.dao;

import com.doriswu.questionnaireapi.entity.Authorities;
import com.doriswu.questionnaireapi.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void signup(User user){
        Authorities authorities = new Authorities();
        authorities.setAuthorities("ROLE_USER");
        authorities.setUsername(user.getUsername());

        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(authorities);
            session.save(user);
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

    public User getUser(String username){
        User user = null;
        try(Session session = sessionFactory.openSession()){
            user = session.get(User.class, username);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
}
