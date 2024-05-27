package com.example.testTomcat.api;

import com.example.testTomcat.model.Person;
import com.example.testTomcat.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {
    private final PersonService personService;
    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }
    @PostMapping
    public void addPerson(@RequestBody Person person){
        personService.addPerson(person);
        System.out.println("controller post!");

    }
   @GetMapping
    public List<Person> selectAllPeople(){
       return personService.selectAllPeople();
    }
    /* @GetMapping
        public void selectAllPerson(){
        System.out.println(" controller get First person: " + personService.selectPerson());

        personService.selectPerson();
    }*/
    @GetMapping(path = "{id}")
    public Person getPersonById(@PathVariable("id") UUID id){
        return personService.getPersonById(id)
                .orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public int deletePersonById(@PathVariable("id") UUID id){
        return personService.deletePerson(id);
    }
    @PutMapping(path = "{id}")
    public int updatePerson(@PathVariable("id") UUID id, @RequestBody Person personToUpdate){
        return personService.updatePerson(id, personToUpdate);

    }

}
