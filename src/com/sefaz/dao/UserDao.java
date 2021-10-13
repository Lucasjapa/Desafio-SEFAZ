package com.sefaz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sefaz.model.User;
import com.sefaz.util.HibernateUtil;

public class UserDao {

	// -------------SAVE USER------------------
	public void save(User newUser) {
		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			// check-in email exist
			user = (User) session.createQuery("FROM User WHERE email = :email").setParameter("email", newUser.getEmail())
	                .uniqueResult();
			if(user == null) {
				session.save(newUser);
				transaction.commit();
			}else {
				
			}
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	// ------------------------------------------

	// -------------UPDATE USER------------------
	public void update(User user) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	// ------------------------------------------

	// -------------DELETE USER------------------
	public void delete(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			User user = session.get(User.class, id);
			if (user != null) {
				session.delete(user);
				System.out.println("User is deleted!!!");
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	// ------------------------------------------

	// -------------List ALL USER------------------
	
	public List<User> getAllUser() {
		Transaction transaction = null;
		List<User> listOfUser = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			listOfUser = session.createQuery("from User").getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return listOfUser;
	}
	// ------------------------------------------

	// -------------List USER------------------
	public User getUser(int id) {

		Transaction transaction = null;
		User user = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// get an user object
			user = session.get(User.class, id);
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return user;
	}
	// ------------------------------------------
	
	// -------------VALIDATE USER------------------
	public boolean validate(String email, String password) {

        Transaction transaction = null;
        User user = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User WHERE email = :email").setParameter("email", email)
                .uniqueResult();

            if (user != null && user.getPassword().equals(password)) {
                return true;
            }
    
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
            	return false;
            }
            e.printStackTrace();
        }
        return false;
    }
	// ------------------------------------------
	
	//--------------VALIDATE EMAIL-------
	public boolean validateEmail(String email) {

        Transaction transaction = null;
        User user = null;
        
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
    
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User WHERE email = :email").setParameter("email", email)
                .uniqueResult();

            if (user != null && user.getEmail().equals(email)) {
                return false;
            }
    
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
            	return false;
            }
            e.printStackTrace();
        }
        return true;
    }
	// ------------------------------------------
}
