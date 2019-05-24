package com.espressoshock.drinkle.daoLayer;

import javax.persistence.*;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/********* K:key, E:entity         */
public class JPADao<K, E> implements  IDao<K, E> {
    protected Class<E> type;

    /*********  inject EntityManager         */
    @PersistenceContext
    protected EntityManager entityManager;

    public JPADao(EntityManager entityManager) {
        /********* =GET ENTITY CLASS WITH REFLECTION       */
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.type = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
        /********* END =GET ENTITY CLASS WITH REFLECTION       */
        this.entityManager = entityManager;
    }




    @Override
    public void save(E entity) {
        EntityTransaction entityTransaction = null;
        try{
            entityTransaction = this.entityManager.getTransaction();
            entityTransaction.begin();
            this.entityManager.persist(entity);
            entityTransaction.commit();
        } catch (RuntimeException e){
            //rollback changes //***********
            if(entityTransaction != null)
                entityTransaction.rollback();
            //print exception //***********
            e.printStackTrace();
        } finally {
            this.entityManager.close();
        }
    }

    @Override
    public void update(K k, E entity) {
        EntityTransaction entityTransaction = null;
        E tmp = null;
        try{
            entityTransaction = this.entityManager.getTransaction();
            entityTransaction.begin();
            tmp = this.entityManager.find(this.type, k);
            tmp = entity;
            this.entityManager.persist(tmp);
            entityTransaction.commit();
        } catch (NoResultException ex){
            ex.printStackTrace();
        } finally{
            this.entityManager.close();
        }
    }
    public void updateMerge(E entity){
        EntityTransaction entityTransaction = null;
        E tmp = null;
        try{
            entityTransaction = this.entityManager.getTransaction();
            entityTransaction.begin();
            this.entityManager.merge(entity);
            this.entityManager.getTransaction().commit();
            this.entityManager.persist(entity);

        } catch (NoResultException ex){
            ex.printStackTrace();
        } finally{
            this.entityManager.close();
        }
    }

    @Override
    public void remove(K k,E entity) {
        EntityTransaction entityTransaction = null;
        E tmp = null;
        try{
            entityTransaction = this.entityManager.getTransaction();
            entityTransaction.begin();

            this.entityManager.remove(this.entityManager.find(this.type, k));

            this.entityManager.persist(tmp);
            entityTransaction.commit();
        } catch (NoResultException ex){
            ex.printStackTrace();
        } finally{
            this.entityManager.close();
        }
    }

    @Override
    public E getByKey(K id) {
        EntityTransaction entityTransaction = null;
        E tmp = null;
        try{
            entityTransaction = this.entityManager.getTransaction();
            entityTransaction.begin();
            tmp = this.entityManager.find(type, id);
        } catch (RuntimeException e){
            //rollback changes //***********
            if(entityTransaction != null)
                entityTransaction.rollback();
            //print exception //***********
            e.printStackTrace();
        } finally {
            this.entityManager.close();
        }
        return tmp;
    }

    @Override
    public List<E> getAll() {
        EntityTransaction entityTransaction = null;
        String query = "SELECT e FROM"+this.type.getName()+" WHERE 1";
        TypedQuery<E>typedQuery = this.entityManager.createQuery(query, this.type);
        List<E>es = null;
        try{
            es = typedQuery.getResultList();
        } catch (NoResultException ex){
            ex.printStackTrace();
        } finally{
            this.entityManager.close();
        }
        return es;
    }
}
