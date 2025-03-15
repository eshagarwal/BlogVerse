package org.gisma.blogverse.controllers;

import org.gisma.blogverse.models.Blog;
import org.gisma.blogverse.models.Comment;
import org.gisma.blogverse.services.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Add a new blog
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

    // Get all blogs
    @GetMapping
    public List<Blog> getAllBlogs() {
        return blogService.getAllBlogs();
    }

    // Get a blog by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getBlogById(@PathVariable String id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            return ResponseEntity.ok(blog.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist.");
        }
    }

    // Get all the available categories
    @GetMapping("/categories")
    public ResponseEntity<List<String>> getAllCategories() {
        List<String> categories = blogService.getAllCategories();
        return categories.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(categories);
    }

    // Get blogs by category
    @GetMapping("/category/{category-name}")
    public ResponseEntity<Object> getBlogsByCategory(@PathVariable("category-name") String category) {
        List<Blog> blogs = blogService.getBlogsByCategory(category);
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category name does not exist.");
        }
        return ResponseEntity.ok(blogs);
    }

    // Get the 5 most recent blogs
    @GetMapping("/recent")
    public ResponseEntity<List<Blog>> getRecentBlogs() {
        List<Blog> blogs = blogService.getRecentBlogs();
        return blogs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(blogs);
    }

    // Fetch blogs within a given date range
    @GetMapping("/daterange")
    public ResponseEntity<List<Blog>> getBlogsByDateRange(@RequestParam("start") String start, @RequestParam("end") String end) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);

            List<Blog> blogs = blogService.getBlogsByDateRange(startDate, endDate);

            return blogs.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(blogs);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
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

    // Add a comment
    @PostMapping("/{id}/comments")
    public ResponseEntity<String> addComment(@PathVariable String id, @RequestBody Comment comment) {
        try {
            blogService.addComment(id, comment);
            return ResponseEntity.ok("Comment added successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // Get all comments for a blog
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable String id) {
        List<Comment> comments = blogService.getCommentsByBlogId(id);
        if (comments == null || comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("No comments on this blog.");
        }
        return ResponseEntity.ok(comments);
    }

    // Get the most commented blogs
    @GetMapping("/most-commented")
    public ResponseEntity<List<Blog>> getMostCommentedBlogs() {
        List<Blog> blogs = blogService.getMostCommentedBlogs();
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());
        }
        return ResponseEntity.ok(blogs);
    }

    // Get blogs by author
    @GetMapping("/author/{authorName}")
    public ResponseEntity<?> getBlogsByAuthor(@PathVariable String authorName) {
        List<Blog> blogs = blogService.getBlogsByAuthor(authorName);
        if (blogs == null || blogs.isEmpty()) {
            return ResponseEntity.status(404).body("Author does not exist or has no blogs.");
        }
        return ResponseEntity.ok(blogs);
    }

    // Delete blogs by author
    @DeleteMapping("/author/{authorName}")
    public ResponseEntity<String> deleteBlogsByAuthor(@PathVariable String authorName) {
        boolean deleted = blogService.deleteBlogsByAuthor(authorName);

        if (deleted) {
            return ResponseEntity.ok("All blogs by " + authorName + " have been deleted.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Author does not exist or has no blogs.");
        }
    }

}