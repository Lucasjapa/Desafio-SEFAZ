package com.sefaz.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.sefaz.model.Phone;
import com.sefaz.model.User;
import com.sefaz.util.HibernateUtil;
import com.sefaz.util.PhoneEnum;

public class PhoneDao {

	// -------------SAVE PHONE------------------
	public void save(Phone phone) {
		Transaction transaction = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
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

	// -------------VALIDATE NEW PHONE--------------
	public PhoneEnum validateInsertPhone(String ddd, String number, String type) {
		PhoneEnum valido = PhoneEnum.SAVE;
		if (validatePhoneData(ddd, number, type)) {
			valido = PhoneEnum.INVALID_DATA;
		} else if (validateNumber(ddd, number)) {
			valido = PhoneEnum.NUMBER_EXIST;
		}
		return valido;
	}
	// ------------------------------------------

	// -------------VALIDATE UPDATE PHONE--------------
	public PhoneEnum validateUpdatePhone(int userId, String ddd, String number, String type) {
		PhoneEnum valido = PhoneEnum.UPDATE;
		if (validatePhoneData(ddd, number, type)) {
			valido = PhoneEnum.INVALID_DATA;
		} else if (!validateUpdateNumber(userId, ddd, number)) {
			if (validateNumber(ddd, number)) {
				valido = PhoneEnum.NUMBER_EXIST;
			}
		}
		return valido;
	}
	// ------------------------------------------

	// --------------VALIDATE PHONE EXIST-------
	public boolean validateNumber(String ddd, String number) {

		Transaction transaction = null;
		Phone phone = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			phone = (Phone) session.createQuery("FROM Phone WHERE number = :number").setParameter("number", number)
					.uniqueResult();

			if (phone != null && phone.getDdd().equals(ddd) && phone.getNumber().equals(number)) {
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

	// --------------VALIDATE PHONE EXIST (UPDATE)-------
	public boolean validateUpdateNumber(int userId, String ddd, String number) {

		Transaction transaction = null;
		Phone phone = null;

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			transaction = session.beginTransaction();
			phone = (Phone) session.createQuery("FROM Phone WHERE number = :number").setParameter("number", number)
					.uniqueResult();

			if (phone != null && phone.getDdd().equals(ddd) && phone.getNumber().equals(number)
					&& phone.getUser().getId() == userId) {
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

	// -------------VALIDATE PHONE DATA--------------
	public boolean validatePhoneData(String ddd, String number, String type) {
		boolean valido = false;

		if (ddd == null || ddd.equals("")) {
			valido = true;
		} else if (number == null || number.equals("")) {
			valido = true;
		} else if (type == null || type.equals("")
				|| (!type.equalsIgnoreCase("residential") && !type.equalsIgnoreCase("cell phone"))) {
			valido = true;
		} else if (!number.matches("\\d+") || !ddd.matches("\\d+")) {
			valido = true;
		} else if (Integer.parseInt(ddd) <= 0 || Integer.parseInt(ddd) >= 100 || ddd.length() > 3
				|| number.length() != 9) {
			valido = true;
		}
		return valido;
	}
	// ------------------------------------------
}
