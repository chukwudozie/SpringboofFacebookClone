package springbootfacebookclone.service.serviceImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.repository.PersonRepository;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    PersonRepository repository;

    @InjectMocks
    PersonServiceImpl personService;

    private Person person;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setFirstname("FirstName");
        person.setLastname("Lastname");
        person.setPassword("1234");
        person.setEmail("person@gmail.com");
        person.setGender("Male");
        person.setDob("1994-07-02");
    }

    @Test
    void createUser() {

        //Mock User Repository
    when(repository.save(any(Person.class))).thenReturn(person);

    // call the method to be tested
        boolean registrationState = personService.createUser(person);

        //Make Assertions:
        assertTrue(registrationState);
        verify(repository,times(1)).save(any(Person.class));
    }

    @Test
    void getUser() {

        //        mock User Repository
        when(repository.findPersonByEmail(anyString())).thenReturn(person);

//        Call Method to be Tested
        Person personTest = repository.findPersonByEmail("mail@mail.com");
        personService.getUser(person.getEmail(), person.getPassword());

//        Make Assertion
        assertNotNull(personTest);
        assertEquals("person@gmail.com",personTest.getEmail());
        verify(repository,times(1)).findPersonByEmail(person.getEmail());
    }

}