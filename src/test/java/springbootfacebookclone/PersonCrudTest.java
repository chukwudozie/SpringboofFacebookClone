package springbootfacebookclone;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootfacebookclone.controller.PersonController;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.repository.PersonRepository;
import springbootfacebookclone.service.serviceImplementation.PersonServiceImpl;
import springbootfacebookclone.utils.PasswordHashing;

@SpringBootTest
class PersonCrudTest {

    private PersonServiceImpl personService;
    private PersonRepository personRepository;
    private PersonController personController;

    @Autowired
    public PersonCrudTest(PersonServiceImpl personService, PersonRepository personRepository) {
        this.personService = personService;
        this.personRepository = personRepository;
    }

    Person person = null;

    String password = PasswordHashing.encryptPassword("1234");

    @Test
    void UserCrud(){

//        Create New User And Check If the Fields are in the database
        person = new Person("emeka","Okonkwo",
                password,"mekus@gmail.com","1997-03-03","Male");
       boolean success =  personService.createUser(person);
        Assertions.assertTrue(success);
        Assertions.assertNotNull(person.getEmail());
        Assertions.assertEquals(person.getFirstname(),"emeka");
        Assertions.assertEquals(person.getLastname(),"Okonkwo");


//        Test for  deleting User from the database

        boolean delete = personService.deleteUser(person.getEmail());
        Assertions.assertTrue(delete);

        // Reading a user: To check if the user is still in the Database
      Assertions.assertNull(personService.getUser(person.getEmail(),person.getPassword()));

        


    }
}
