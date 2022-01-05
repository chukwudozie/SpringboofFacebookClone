package springbootfacebookclone.service.serviceImplementation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.model.Post;
import springbootfacebookclone.repository.PersonRepository;
import springbootfacebookclone.repository.PostRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    PersonRepository repository;
    PostRepository postRepository;

    @InjectMocks
    PersonServiceImpl personService;
    PostServiceImpl postService;

    private Person person;
    private Post post;

    @BeforeEach
    void setUp() {
        person = new Person();
        person.setFirstname("FirstName");
        person.setLastname("Lastname");
        person.setPassword("1234");
        person.setEmail("person@gmail.com");
        person.setGender("Male");
        person.setDob("1994-07-02");

        post = new Post();
        post.setPerson(person);
        post.setChecker("ACTIVE");
        post.setTitle("post Title");
        post.setBody("Post body");
    }

    @Test
    void createPost() {
        //mock personRepository
        when(repository.findById(anyLong())).thenReturn(Optional.of(person));
        //mock postRepository
        when(postRepository.save(any(Post.class))).thenReturn(post);
        boolean result = postService.createPost(1L, post);
        assertTrue(result);
//        personServiceImpl.getUser("snowshaddy@gmail.com", person.getPassword());
        verify(postRepository, times(1)).save(any(Post.class));
        verify(repository, times(1)).findById(anyLong());


    }

    /*
        void createPost() {
        //mock personRepository
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(person));
        //mock postRepository
        when(postRepository.save(any(Post.class))).thenReturn(post);
        boolean result = PostServiceImpl.createPost(1L, post);
        assertTrue(result);
//        personServiceImpl.getUser("snowshaddy@gmail.com", person.getPassword());
        verify(postRepository, times(1)).save(any(Post.class));
        verify(personRepository, times(1)).findById(anyLong());
//        verify(personRepository, times(1)).save(any(Person.class));
    }
    @Test
    void editPost() {
        when(postRepository.save(any(Post.class))).thenReturn(post);
        when(postRepository.findById(anyLong())).thenReturn(Optional.of(post));
        boolean status = PostServiceImpl.editPost(person,1L, "get started", "we are getting started");
        assertTrue(status);
        verify(postRepository, times(1)).save(any(Post.class));
        verify(postRepository, times(1)).findById(anyLong());
    }
     */

    @Test
    void getPostById() {
    }

    @Test
    void getPost() {
    }

    @Test
    void editPost() {
    }

    @Test
    void deletePost() {
    }
}