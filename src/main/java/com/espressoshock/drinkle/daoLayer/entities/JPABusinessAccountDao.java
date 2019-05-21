package com.espressoshock.drinkle.daoLayer.entities;

import com.espressoshock.drinkle.daoLayer.JPADao;
import com.espressoshock.drinkle.models.BusinessAccount;

import javax.persistence.EntityManager;

public class JPABusinessAccountDao extends JPADao<String, BusinessAccount> implements BusinessAccountDao{
    public JPABusinessAccountDao(EntityManager entityManager) {
        super(entityManager);
    }
}
