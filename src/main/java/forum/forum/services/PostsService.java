package forum.forum.services;

import forum.forum.dtos.request.CreatePostDTO;
import forum.forum.dtos.response.PostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.mappers.PostMapper;
import forum.forum.repositories.PostsRepository;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostsService {

  private final PostsRepository postsRepository;
  private final UsersRepository usersRepository;
  private final PostMapper postMapper;

  public List<PostDTO> getPosts() {
    return postMapper.PostsEntityToPostDTO(postsRepository.findAll());
  }

  public PostDTO create(@RequestBody CreatePostDTO data) {
    var post = postMapper.CreatePostDTOToPostsEntity(data);
    post.setUsers(usersRepository.findById(data.user_id()).orElseThrow());
    return postMapper.PostsEntityToPostDTO(postsRepository.save(post));
  }

  @PatchMapping("/{id}")
  public PostsEntity update(@PathVariable Long id, @RequestBody CreatePostDTO data) {
    var post = postsRepository.findById(id).orElseThrow();
    post.setTitle(data.title());
    post.setBody(data.body());
    return postsRepository.save(post);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    postsRepository.deleteById(id);
  }
}
