package practice.atul.springboot.service.impl;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import practice.atul.springboot.entity.Post;
import practice.atul.springboot.exception.ResourceNotFoundException;
import practice.atul.springboot.payload.PostDto;
import practice.atul.springboot.payload.PostResponse;
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
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
		
		//Sorting in Ascending order by Default
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		//Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());  In Descending order

		
		Page<Post> posts = postRepository.findAll(pageable);
		
		List<Post> listOfPosts = posts.getContent();
		List<PostDto> content = listOfPosts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
	    
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(posts.getNumber());
		postResponse.setPageSize(posts.getSize());
		postResponse.setTotalElements(posts.getTotalElements());
		postResponse.setTotalPages(posts.getTotalPages());
		postResponse.setLast(posts.isLast());
		
		return postResponse;
	}



	@Override
	public PostDto getPostById(Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		return mapToDto(post);
	}



	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		
		post.setTittle(postDto.getTittle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());
		
		Post updatedPost = postRepository.save(post);
		return mapToDto(updatedPost);
	}



	@Override
	public String deletePostById(Long id) {
		// TODO Auto-generated method stub
		Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post", "id", id));
		
		postRepository.delete(post);
		
		return "Deleted Successfully";
	}



	
	
	

}
