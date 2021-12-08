package springbootfacebookclone.service;

import springbootfacebookclone.DTO.PostMapper;
import springbootfacebookclone.model.Person;
import springbootfacebookclone.model.Post;
import java.util.List;

public interface PostService {
    boolean createPost(Long userId, Post post);
    List<PostMapper> getPost(Person currentUser);
}
