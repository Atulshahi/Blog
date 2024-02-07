package practice.atul.springboot.service;

import practice.atul.springboot.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(Long postId, CommentDto commentDto);

}
