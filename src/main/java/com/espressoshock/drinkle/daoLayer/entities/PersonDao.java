package com.espressoshock.drinkle.daoLayer.entities;

import com.espressoshock.drinkle.daoLayer.IDao;
import com.espressoshock.drinkle.models.Person;

import java.util.List;

public interface PersonDao extends IDao<String, Person> {
    List<Person>getByFirstName(String name);
}
