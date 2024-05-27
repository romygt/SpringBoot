package com.example.testTomcat.service;

import com.example.testTomcat.dao.PersonDao;
import com.example.testTomcat.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class PersonService {
    private final PersonDao personDao;
   @Autowired
    public PersonService(@Qualifier("postgres") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person){
        System.out.println("server post!");
        return personDao.insertPerson(person);

    }
    public List<Person> selectAllPeople(){
       return personDao.selectAllPeople();
    }
    public String selectPerson(){
        System.out.println(" server get First person: " + personDao.selectPerson());

        return personDao.selectPerson();
    }
    public Optional<Person> getPersonById(UUID id){
       return personDao.selectPersonById(id);
    }
    public int deletePerson(UUID id){
       return personDao.deletPersonById(id);
    }
    public int updatePerson(UUID id, Person newPerson){
       return personDao.updatePersonById(id, newPerson);
    }
}
