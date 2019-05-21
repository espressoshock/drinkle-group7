package com.espressoshock.drinkle.daoLayer.entities;

import com.espressoshock.drinkle.daoLayer.JPADao;
import com.espressoshock.drinkle.models.PrivateAccount;

import javax.persistence.EntityManager;

public class JPAPrivateAccountDao extends JPADao<String, PrivateAccount> implements PrivateAccountDao{
    public JPAPrivateAccountDao(EntityManager entityManager) {
        super(entityManager);
    }
}
