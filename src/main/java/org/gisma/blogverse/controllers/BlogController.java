package org.gisma.blogverse.controllers;

import org.gisma.blogverse.models.Blog;
import org.gisma.blogverse.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    // Create a new blog
    @PostMapping
    public ResponseEntity<String> createBlog(@RequestBody Blog blog) {
        if (blog.getTitle() == null || blog.getTitle().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Title cannot be empty.");
        }
        if (blog.getContent() == null || blog.getContent().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Content cannot be empty.");
        }
        if (blog.getAuthor() == null || blog.getAuthor().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Author cannot be empty.");
        }
        if (blog.getCategory() == null || blog.getCategory().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Category cannot be empty.");
        }
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.status(HttpStatus.CREATED).body("Blog created successfully with ID: " + createdBlog.getId());
    }

    // Retrieve all blogs
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // Fetch a specific blog by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable String id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            return ResponseEntity.ok(blog.get()); // Return the blog object if found
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist."); // Return error message if not found
        }
    }

    // Fetch all available categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = blogService.getAllCategories();
        return categories.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(categories);
    }

    // Fetch blogs by category
    @GetMapping("/category/{category-name}")
    public ResponseEntity<Object> getBlogsByCategory(@PathVariable("category-name") String category) {
        List<Blog> blogs = blogService.getBlogsByCategory(category);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category name does not exist.");
        }
        return ResponseEntity.ok(blogs);
    }

    // Update a blog by ID
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable String id, @RequestBody Blog blogDetails) {
        Blog updatedBlog = blogService.updateBlog(id, blogDetails);
        if (updatedBlog == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist.");
        }
        return ResponseEntity.ok("Blog updated successfully: " + updatedBlog.getId());
    }

    // Delete a blog by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable String id) {
        boolean isDeleted = blogService.deleteBlogById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Blog " + id + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist");
        }
    }
}