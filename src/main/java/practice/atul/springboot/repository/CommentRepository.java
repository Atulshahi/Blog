package practice.atul.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.atul.springboot.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
