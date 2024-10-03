package com.blog.repositories;

import com.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Custom query method to find all comments by Post ID
    List<Comment> findByPostId(Long postId);

    // Custom query method to find a comment by its ID and associated Post ID
    Optional<Comment> findByIdAndPostId(Long commentId, Long postId);
}
