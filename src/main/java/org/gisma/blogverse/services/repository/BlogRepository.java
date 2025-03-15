package org.gisma.blogverse.services.repository;

import org.gisma.blogverse.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Aggregation;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    // Get all available categories
    @Query(value = "{}", fields = "{ 'category' : 1 }")  // fetch the category field only
    List<Blog> findCategoryBy();

    // Get blogs by category
    List<Blog> findByCategory(String category);

    // Get blogs by createdAt between the given start and end LocalDateTime
    List<Blog> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    // Get the most commented blogs
    @Aggregation(pipeline = {
            "{ $addFields: { comments: { $ifNull: ['$comments', []] } } }",
            "{ $project: { title: 1, category: 1, author: 1, createdAt: 1, comments: 1, commentCount: { $size: '$comments' } } }",
            "{ $sort: { commentCount: -1 } }",
            "{ $limit: 5 }"
    })
    List<Blog> findTop5ByMostComments();

    // Get blogs by author
    List<Blog> findByAuthor(String author);

    // Delete blogs by author
    void deleteByAuthor(String author);
}
