package practice.atul.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import practice.atul.springboot.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
