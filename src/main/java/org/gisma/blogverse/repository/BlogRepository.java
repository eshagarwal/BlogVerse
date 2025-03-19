package org.gisma.blogverse.repository;

import org.gisma.blogverse.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Aggregation;

import java.time.LocalDateTime;
import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    @Query(value = "{}", fields = "{ 'category' : 1 }")
    List<Blog> findCategoryBy();

    List<Blog> findByCategory(String category);

    List<Blog> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Aggregation(pipeline = {
            "{ $addFields: { comments: { $ifNull: ['$comments', []] } } }",
            "{ $project: { title: 1, category: 1, author: 1, createdAt: 1, comments: 1, commentCount: { $size: '$comments' } } }",
            "{ $sort: { commentCount: -1 } }",
            "{ $limit: 5 }"
    })
    List<Blog> findTop5ByMostComments();

    List<Blog> findByAuthor(String author);

    void deleteByAuthor(String author);
}
