package springbootfacebookclone;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import springbootfacebookclone.controller.PersonController;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.model.Post;
import springbootfacebookclone.repository.PostRepository;
import springbootfacebookclone.service.serviceImplementation.PersonServiceImpl;
import springbootfacebookclone.service.serviceImplementation.PostServiceImpl;
import springbootfacebookclone.utils.PasswordHashing;

@SpringBootTest
class PostCrudTest {

    private PostRepository postRepository;
    private PostServiceImpl postService;
    private PersonServiceImpl personService;
    private PersonController personController;

    @Autowired
    public PostCrudTest(PostRepository postRepository, PostServiceImpl postService,
                        PersonServiceImpl personService,PersonController personController) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.personService = personService;
        this.personController = personController;
    }

    Post post;
    Person user;
    String password = PasswordHashing.encryptPassword("1234");

    @Test
    void createPost(){

        // create a user that will create a post
       user =  new Person("emeka","Okonkwo",
                password,"mekus@gmail.com","1997-03-03","Male");
       personService.createUser(user);


        post = new Post(17L,"Test1","TestBody1","/images/myPics.png","ACTIVE",user,null,null);
        boolean success = postService.createPost(user.getId(), post);
        Assertions.assertTrue(success);
        Assertions.assertEquals(user,personService.getUser(user.getEmail(), user.getPassword()));
        Assertions.assertEquals("Test1",post.getTitle());
        Assertions.assertEquals(user,post.getPerson());
        Assertions.assertNotNull(post.getTitle());
        Assertions.assertNotNull(post.getBody());

        // Edit the Post created
        postService.editPost(user,post.getPostId(),"Test1 Updated", "TestBody1 Updated");
        Assertions.assertEquals("Test1 Updated",post.getTitle());
        Assertions.assertEquals("TestBody1 Updated",post.getBody());

        //Delete Post
        postService.deletePost(post.getPostId(), user.getId());
        Assertions.assertNull(post);


    }
}
