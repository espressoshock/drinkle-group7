package com.espressoshock.drinkle.daoLayer.entities;

import com.espressoshock.drinkle.daoLayer.JPADao;

import javax.persistence.EntityManager;

public class JPAAAccountDal extends JPADao<String, AccountDAL> implements AccountDalDao {
    public JPAAAccountDal(EntityManager entityManager) {
        super(entityManager);
    }
}
