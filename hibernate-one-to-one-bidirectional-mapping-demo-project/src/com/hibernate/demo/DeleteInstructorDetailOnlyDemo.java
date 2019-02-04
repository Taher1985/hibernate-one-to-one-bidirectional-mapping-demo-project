package com.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.hibernate.entity.Instructor;
import com.hibernate.entity.InstructorDetail;
import com.hibernate.entity.InstructorDetailOnly;
import com.hibernate.entity.InstructorOnly;

public class DeleteInstructorDetailOnlyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(InstructorOnly.class)
				.addAnnotatedClass(InstructorDetailOnly.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// start a transaction
			session.beginTransaction();
			// get the instructor detail object
			int id = 7;
			InstructorDetailOnly instructorDetail = session.get(InstructorDetailOnly.class, id);
			// print the instructor detail
			System.out.println("tempInstructorDetail: " + instructorDetail);
			// print the associated instructor
			System.out.println("the associated instructor: " + instructorDetail.getInstructor());
			// now let's delete the instructor detail
			System.out.println("Deleting tempInstructorDetail: " + instructorDetail);

			// remove the associated object reference
			// break bi-directional link
			instructorDetail.getInstructor().setInstructorDetail(null);
			session.delete(instructorDetail);
			// commit transaction
			session.getTransaction().commit();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			session.close();
			factory.close();
		}
	}

}
