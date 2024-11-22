package sg.edu.nus.iss.ssf_15l.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import sg.edu.nus.iss.ssf_15l.model.Person;
import sg.edu.nus.iss.ssf_15l.repository.ListRepository;

@Service
public class PersonService {
    private final ListRepository personRepository;
    private List<Person> persons;

    public PersonService(ListRepository listRepository) {
        this.personRepository = listRepository;
        this.persons = new ArrayList<>();
    }

    public void addPerson(String redisKey, Person person) {
        personRepository.rightPush(redisKey, person.toString());
    }
    
    public List<Person> findAll(String redisKey) {
        List<Object> listObjects = personRepository.getList(redisKey);
        
        List<Person> personData = new ArrayList<>();
        for (Object data : listObjects) {
            personData.add(new Person(data.toString()));
        }

        return personData;
    }

    public void delete(String redisKey, String id) {
        personRepository.delete2(redisKey, id);
    }

}
