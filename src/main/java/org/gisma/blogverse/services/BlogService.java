package org.gisma.blogverse.services;

import org.gisma.blogverse.models.Blog;
import org.gisma.blogverse.services.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> getBlogById(String id) {
        return blogRepository.findById(id);
    }

    public List<String> getAllCategories() {
        // get all blogs with the category field
        List<Blog> blogs = blogRepository.findCategoryBy();

        // Extract the category names from the Blog objects and return only values
        return blogs.stream()
                .map(Blog::getCategory)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Blog> getBlogsByCategory(String category) {
        return blogRepository.findByCategory(category);
    }

    public Blog updateBlog(String id, Blog blogDetails) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog blog = existingBlog.get();
            blog.setTitle(blogDetails.getTitle());
            blog.setContent(blogDetails.getContent());
            blog.setAuthor(blogDetails.getAuthor());
            blog.setCategory(blogDetails.getCategory());
            return blogRepository.save(blog);
        }
        return null;
    }

    public boolean deleteBlogById(String id) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            blogRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Fetch the 5 most recent blogs
    public List<Blog> getRecentBlogs() {
        return blogRepository.findAll(Sort.by(Sort.Order.desc("createdAt"))).stream().limit(5).collect(Collectors.toList());
    }
}