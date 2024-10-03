package com.blog.services;

import com.blog.models.Post;
import com.blog.repositories.PostRepository; // Make sure this is imported
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    // Create a new Post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // Get Post by ID
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    // Update existing Post
    public Post updatePost(Long id, Post post) {
        return postRepository.save(post); // This should handle updates
    }

    // Delete Post by ID
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
