package practice.atul.springboot.service.impl;

import org.springframework.stereotype.Service;

import practice.atul.springboot.entity.Comment;
import practice.atul.springboot.entity.Post;
import practice.atul.springboot.exception.ResourceNotFoundException;
import practice.atul.springboot.payload.CommentDto;
import practice.atul.springboot.repository.CommentRepository;
import practice.atul.springboot.repository.PostRepository;
import practice.atul.springboot.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	
	public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository;
	}
	
	
	
	private CommentDto mapToDto(Comment comment) {
		CommentDto commentDto = new CommentDto();
		commentDto.setId(comment.getId());
		commentDto.setName(comment.getName());
		commentDto.setEmail(comment.getEmail());
		commentDto.setBody(comment.getBody());
		return commentDto;
	}
	
	private Comment mapToEntity(CommentDto commentDto) {
		Comment comment = new Comment();
		comment.setId(commentDto.getId());
		comment.setName(commentDto.getName());
		comment.setEmail(commentDto.getEmail());
		comment.setBody(commentDto.getEmail());
		return comment;
	}

	@Override
	public CommentDto createComment(Long postId, CommentDto commentDto) {
		Comment comment = mapToEntity(commentDto);
		
		Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "id", postId));
		
		//Set post to comment entity
		comment.setPost(post);
		
		Comment newComment = commentRepository.save(comment);
		
		return mapToDto(newComment);
		//return null;
	}

}
