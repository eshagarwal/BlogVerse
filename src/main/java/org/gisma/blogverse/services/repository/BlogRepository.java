package org.gisma.blogverse.services.repository;

import org.gisma.blogverse.models.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface BlogRepository extends MongoRepository<Blog, String> {
    List<Blog> findByCategory(String category);
}
