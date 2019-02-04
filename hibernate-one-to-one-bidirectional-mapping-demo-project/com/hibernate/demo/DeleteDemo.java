package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = sessionFactory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();
			Instructor instructor = session.get(Instructor.class, 2);
			if (instructor != null) {
				System.out.println(instructor);
				// Note: will ALSO delete associated "details" object
				// because of CascadeType.ALL
				session.delete(instructor);
			}
			// commit transaction
			session.getTransaction().commit();
		} finally {
			sessionFactory.close();
		}
	}

}
