package com.example.testTomcat.dao;

import com.example.testTomcat.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao{
    private static List<Person> DB = new ArrayList<>();
    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName()));
        System.out.println("dao post!");
        Person firstPerson = DB.get(0);
        System.out.println("First person: " + firstPerson.getName());
        System.out.println("First person: " + firstPerson.getId());


        return 52;
    }

    @Override
    public List<Person> selectAllPeople() {
        //  DB.add(new Person('ed35a41b-9996-4e92-b30d-4aaef95a9432', "romy"));
        Person firstPerson = DB.get(0);
        System.out.println(" dao get First person: " + firstPerson.getName());
        return DB;
    }

    @Override
    public String selectPerson() {
        System.out.println(" dao get First person: " + DB.get(0).getName());
        return DB.get(0).getName();
    }

    @Override
    public Optional<Person> selectPersonById(UUID id) {
        return DB.stream()
                .filter(person -> person.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deletPersonById(UUID id) {
       Optional<Person> personMaybe = selectPersonById(id);
       if (personMaybe.isEmpty()){
           return 0;
       }
        DB.remove(personMaybe.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return selectPersonById(id)
                .map(p -> {
                    int indexOfPersonDelete = DB.indexOf(p);
                    if (indexOfPersonDelete >= 0){
                        DB.set(indexOfPersonDelete, new Person(id, person.getName()));
                        return 1;
                    }
                    return  0;
                })
                .orElse(0);
    }
}
