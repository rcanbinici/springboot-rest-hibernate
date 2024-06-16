package com.rcanbinici.spring.hibernate.api.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rcanbinici.spring.hibernate.api.model.Person;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Repository
@Transactional
public class PersonDao {

	@Autowired
	private SessionFactory factory;

	public void savePerson(Person person) {
		getSession().persist(person);
	}

	public List<Person> getPersons() {
		
		// create Criteria
        CriteriaQuery<Person> criteriaQuery = getSession().getCriteriaBuilder().createQuery(Person.class);
        criteriaQuery.from(Person.class);

        List<Person> contacts = getSession().createQuery(criteriaQuery).getResultList();
        getSession().close();

        return contacts;
        
	}

	private Session getSession() {
		Session session = factory.getCurrentSession();
		if (session == null) {
			session = factory.openSession();
		}
		return session;
	}

}