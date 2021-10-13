package com.sefaz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sefaz.model.Phone;
import com.sefaz.util.HibernateUtil;

public class PhoneDao {

	// -------------SAVE PHONE------------------
	public void save(Phone phone) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			// save
			session.save(phone);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	// --------------------------------------------

	// -------------UPDATE PHONE------------------
	public void update(Phone phone) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			session.update(phone);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	// --------------------------------------------

	// -------------DELETE PHONE------------------
	public void delete(int id) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			Phone phone = session.get(Phone.class, id);
			if (phone != null) {
				session.delete(phone);
				System.out.println("Phone is deleted!!!");
			}
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	// --------------------------------------------

	// -----------------Get a Phone----------------
	public Phone getPhone(int id) {

		Transaction transaction = null;
		Phone phone = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			phone = session.get(Phone.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return phone;
	}
	// --------------------------------------------

	// -------------List All Phone------------------
	public List<Phone> getByUserId(int userId) {

		Transaction transaction = null;
		List<Phone> phones = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			phones = session.createQuery("from Phone WHERE user_id = :user_id").setParameter("user_id", userId)
					.getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return phones;
	}
	// ------------------------------------------

	// -------------VALIDATE PHONE--------------
	public boolean validatePhone(String ddd, String number) {
		boolean valido = true;
		if (!number.matches("\\d+") || !ddd.matches("\\d+")) {
			valido = false;
		}else if( Integer.parseInt(ddd) <= 0 || Integer.parseInt(ddd) >= 100 || ddd.length() > 3 || number.length() != 9) {
			valido = false;
		}
		return valido;
	}
}
