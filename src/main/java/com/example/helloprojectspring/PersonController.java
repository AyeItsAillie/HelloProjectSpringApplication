package com.example.helloprojectspring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;



@RestController
@RequestMapping("/person")
public class PersonController {

    private final List<Person> persons = new ArrayList<>();

    public PersonController() {
        persons.add(new Person(
                1,
                "John",
                "Johnson",
                20
        ));
        persons.add(new Person(
                2,
                "Bob",
                "George",
                30
        ));
        persons.add(new Person(
                3,
                "Bobby",
                "Personman",
                9
        ));
    }

    @GetMapping
    public List<Person> getPersons() {
        return persons;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPerson(@PathVariable int id){
        for (Person person : persons) {
            if (person.getId() ==id){
                return ResponseEntity.ok(persons);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Person> addPerson(@RequestBody Person person) {
        int newId = persons.size() + 1;
        person.setId(newId);
        persons.add(person);
        return ResponseEntity.status(201).body(person);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        for (Person person : persons) {
            if (person.getId() == id){
                persons.remove(person);
                return ResponseEntity.status(204).build();

            }
        }

        return ResponseEntity.notFound().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable int id,
                                               @RequestBody Person updatedPerson) {
        for(Person person :persons) {
            if (person.getId() == id) {
                person.setFirstName(updatedPerson.getFirstName());
                person.setLast_name(updatedPerson.getLast_name());
                person.setAge((int) updatedPerson.getAge());

                return ResponseEntity.ok(person);
            }
        }
        return ResponseEntity.notFound().build();
    }

}
