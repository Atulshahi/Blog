package practice.atul.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import practice.atul.springboot.entity.Post;
import practice.atul.springboot.payload.PostDto;
import practice.atul.springboot.repository.PostRepository;
import practice.atul.springboot.service.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	private PostRepository postRepository;
	
	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}



	@Override
	public PostDto createPost(PostDto postDto) {
		//convert DTO to entity
//		Post post=new Post();
//		post.setTittle(postDto.getTittle());
//		post.setDescription(postDto.getDescription());
//		post.setContent(postDto.getContent());
		Post post=mapToEntity(postDto);
		
		Post newPost= postRepository.save(post);
		
		PostDto postResponse = mapToDto(newPost);
		
		//convert entity to DTO
//		PostDto postResponse = new PostDto();
//		postResponse.setTittle(newPost.getTittle());
//		postResponse.setDescription(newPost.getDescription());
//		postResponse.setContent(newPost.getContent());
		
		return postResponse;
	}
	
	//convert to entity to DTO
	private PostDto mapToDto(Post post) {
		PostDto postDto = new PostDto();
		postDto.setId(post.getId());
		postDto.setTittle(post.getTittle());
		postDto.setDescription(post.getDescription());
		postDto.setContent(post.getContent());
		
		return postDto;
		
	}
	
	private Post mapToEntity(PostDto postDto) {
		Post post = new Post();
		post.setId(postDto.getId());
		post.setTittle(postDto.getTittle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		return post;
	}



	@Override
	public List<PostDto> getAllPosts() {
		List<Post> posts = postRepository.findAll();
		return posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	}

}
