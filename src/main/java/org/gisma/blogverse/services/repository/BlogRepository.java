package org.gisma.blogverse.services.repository;

import org.gisma.blogverse.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    // Fetch all available categories
    @Query(value = "{}", fields = "{ 'category' : 1 }")  // fetch the category field only
    List<Blog> findCategoryBy();

    // Fetch blogs by category
    List<Blog> findByCategory(String category);

    // Fetch blogs by createdAt between the given start and end LocalDateTime
    List<Blog> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);
}
