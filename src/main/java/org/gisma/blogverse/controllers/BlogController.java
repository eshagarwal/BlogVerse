package org.gisma.blogverse.controllers;

import org.gisma.blogverse.exceptions.InvalidDateProvidedException;
import org.gisma.blogverse.models.Blog;
import org.gisma.blogverse.models.Comment;
import org.gisma.blogverse.services.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
    public ResponseEntity<Object> getBlogById(@PathVariable String id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        if (blog.isPresent()) {
            return ResponseEntity.ok(blog.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist.");
    }

    // Get all the available categories
    @GetMapping("/categories")
    public ResponseEntity<Object> getAllCategories() {
        List<String> categories = blogService.getAllCategories();
        if (categories.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found");
        }
        return ResponseEntity.ok(categories);
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
    public ResponseEntity<Object> getRecentBlogs() {
        List<Blog> blogs = blogService.getRecentBlogs();
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found");
        }
        return ResponseEntity.ok(blogs);
    }

    // Fetch blogs within a given date range
    @GetMapping("/daterange")
    public ResponseEntity<Object> getBlogsByDateRange(@RequestParam("start") String start, @RequestParam("end") String end) {
        try {
            LocalDateTime startDate = LocalDateTime.parse(start);
            LocalDateTime endDate = LocalDateTime.parse(end);

            if (endDate.isAfter(LocalDateTime.now())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("End date cannot be in the future.");
            }
            if (startDate.isAfter(endDate)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start date cannot be after end date.");
            }

            List<Blog> blogs = blogService.getBlogsByDateRange(startDate, endDate);

            if (blogs.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found within the given date range.");
            }

            return ResponseEntity.ok(blogs);
        } catch (DateTimeParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Provided date was in incorrect format.");
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
        boolean isCommentAdded = blogService.addComment(id, comment);
        if (!isCommentAdded) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist.");
        }
        return ResponseEntity.ok("Comment added successfully!");
    }

    // Get all comments for a blog
    @GetMapping("/{id}/comments")
    public ResponseEntity<?> getComments(@PathVariable String id) {
        List<Comment> comments = blogService.getCommentsByBlogId(id);
        if (comments == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Blog with ID " + id + " does not exist.");
        }
        if (comments.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No comments found for blog with ID " + id);
        }
        return ResponseEntity.ok(comments);
    }

    // Get the most commented blogs
    @GetMapping("/most-commented")
    public ResponseEntity<Object> getMostCommentedBlogs() {
        List<Blog> blogs = blogService.getMostCommentedBlogs();
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found");
        }
        return ResponseEntity.ok(blogs);
    }

    // Get blogs by author
    @GetMapping("/author/{authorName}")
    public ResponseEntity<?> getBlogsByAuthor(@PathVariable String authorName) {
        List<Blog> blogs = blogService.getBlogsByAuthor(authorName);
        if (blogs == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author does not exist.");
        }
        if (blogs.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No blogs found for author " + authorName);
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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Author does not exist or has no blogs.");
        }
    }

}