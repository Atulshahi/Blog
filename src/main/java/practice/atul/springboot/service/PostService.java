package practice.atul.springboot.service;

import java.util.List;

import practice.atul.springboot.payload.PostDto;
import practice.atul.springboot.payload.PostResponse;

public interface PostService {
	
	PostDto createPost(PostDto postDto);
	
	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);
	
	PostDto getPostById(Long id);
	
	PostDto updatePost(PostDto postDto, Long id);
	
	String deletePostById(Long id);

}
