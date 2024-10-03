package com.blog.services;

import com.blog.exceptions.CommentNotFoundException;
import com.blog.exceptions.PostNotFoundException;
import com.blog.models.Comment;
import com.blog.models.Post;
import com.blog.repositories.CommentRepository;
import com.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    // Create a new Comment
    public Comment createComment(Long postId, Comment comment) {  // Accept postId as a parameter
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post with id " + postId + " not found"));
        comment.setPost(post);  // Associate the comment with the post
        return commentRepository.save(comment);
    }

    // Get all comments by Post ID
    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    // Get Comment by ID
    public Optional<Comment> getCommentById(Long commentId) {
        return commentRepository.findById(commentId);
    }

    // Update existing Comment
    public Comment updateComment(Long commentId, Comment updatedComment) {  // Accept commentId as a parameter
        Comment existingComment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment with id " + commentId + " not found"));
        existingComment.setContent(updatedComment.getContent());  // Use the correct method to get content
        return commentRepository.save(existingComment);
    }

    // Delete Comment by ID
    public void deleteComment(Long commentId) {
        if (!commentRepository.existsById(commentId)) {
            throw new CommentNotFoundException("Comment with id " + commentId + " not found");
        }
        commentRepository.deleteById(commentId);
    }
}
