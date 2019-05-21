package com.espressoshock.drinkle.daoLayer.entities;

import com.espressoshock.drinkle.daoLayer.JPADao;
import com.espressoshock.drinkle.models.Person;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class JPAPersonDao extends JPADao<String, Person> implements PersonDao {
    public JPAPersonDao(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<Person> getByFirstName(String firstName) {
        Query query = entityManager.createQuery("SELECT e FROM"+type.getName()+" where first_name LIKE :firstName");
        query.setParameter("firstName", firstName);
        return (List<Person>) query.getResultList();
    }
}
