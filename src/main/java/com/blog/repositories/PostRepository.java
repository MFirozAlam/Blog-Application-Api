package com.blog.repositories;



import com.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // Additional custom query methods can be added here, for example:

    List<Post> findByTitleContaining(String title);

    Optional<Post> findByIdAndTitle(Long id, String title);
}
