package com.espressoshock.drinkle.daoLayer.entities;

import com.espressoshock.drinkle.daoLayer.JPADao;
import com.espressoshock.drinkle.models.Account;

import javax.persistence.EntityManager;

public class JPAAccountDao extends JPADao<String, Account> implements AccountDao {
    public JPAAccountDao(EntityManager entityManager) {
        super(entityManager);
    }

}
