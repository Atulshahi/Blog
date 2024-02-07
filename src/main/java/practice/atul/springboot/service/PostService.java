package practice.atul.springboot.service;

import java.util.List;

import practice.atul.springboot.payload.PostDto;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	
	List<PostDto> getAllPosts();

}
