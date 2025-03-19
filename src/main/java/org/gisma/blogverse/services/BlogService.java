package org.gisma.blogverse.services;

import org.gisma.blogverse.models.Blog;
import org.gisma.blogverse.models.Comment;
import org.gisma.blogverse.repository.BlogRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

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
        List<Blog> blogs = blogRepository.findCategoryBy();
        return blogs
                .stream()
                .map(b -> b.getCategory())
                .distinct()
                .toList();
    }

    public List<Blog> getBlogsByCategory(String category) {
        return blogRepository.findByCategory(category);
    }

    public Blog updateBlog(String id, Blog blogDetails) {
        Optional<Blog> existingBlog = blogRepository.findById(id);
        if (existingBlog.isPresent()) {
            Blog blog = existingBlog.get();

            if (blogDetails.getTitle() != null)
                blog.setTitle(blogDetails.getTitle());

            if (blogDetails.getContent() != null)
                blog.setContent(blogDetails.getContent());

            if (blogDetails.getAuthor() != null)
                blog.setAuthor(blogDetails.getAuthor());

            if (blogDetails.getCategory() != null)
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

    public List<Blog> getRecentBlogs() {
        return blogRepository
                .findAll(Sort.by(Sort.Order.desc("createdAt")))
                .stream()
                .limit(5)
                .toList();
    }

    public List<Blog> getBlogsByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return blogRepository.findByCreatedAtBetween(startDate, endDate);
    }

    public boolean addComment(String blogId, Comment comment) {
        Optional<Blog> blogOptional = blogRepository.findById(blogId);
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            blog.getComments().add(comment);
            blogRepository.save(blog);
            return true;
        } else {
            return false;
        }
    }

    public List<Comment> getCommentsByBlogId(String blogId) {
        Optional<Blog> blog = blogRepository.findById(blogId);
        return blog.map(b -> b.getComments()).orElse(null);
    }

    public List<Blog> getMostCommentedBlogs() {
        return blogRepository.findTop5ByMostComments();
    }

    public List<Blog> getBlogsByAuthor(String author) {
        return blogRepository.findByAuthor(author);
    }

    public boolean deleteBlogsByAuthor(String authorName) {
        List<Blog> blogs = blogRepository.findByAuthor(authorName);
        if (blogs.isEmpty()) {
            return false;
        }
        blogRepository.deleteByAuthor(authorName);
        return true;
    }
}