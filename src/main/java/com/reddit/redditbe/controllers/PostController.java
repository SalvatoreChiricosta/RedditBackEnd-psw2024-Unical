package com.reddit.redditbe.controllers;

import com.reddit.redditbe.models.Community;
import com.reddit.redditbe.models.Post;
import com.reddit.redditbe.models.User;
import com.reddit.redditbe.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/user")
    public ResponseEntity<List<Post>> getAllPostsByUser(User user){
        List<Post> posts = postService.getAllPostByUser(user);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/community")
    public ResponseEntity<List<Post>> getAllPostsByCommunity(Community comm){
        List<Post> posts = postService.getAllPostsByCommunity(comm);
        return ResponseEntity.ok(posts);
    }
    @GetMapping("/date")
    public ResponseEntity<List<Post>> getAllPostsOrderByDateDesc() {
        List<Post> posts = postService.getAllPostsOrderByDateDesc();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/votes")
    public ResponseEntity<List<Post>> getAllPostsOrderByVoteDesc() {
        List<Post> posts = postService.getAllPostsOrderByVoteDesc();
        return ResponseEntity.ok(posts);
    }

    // Endpoint per ottenere tutti i post
    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // Endpoint per ottenere un post per ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        if (post != null) {
            return ResponseEntity.ok(post);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint per creare un nuovo post
    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post createdPost = postService.createPost(post);
        return ResponseEntity.ok(createdPost);
    }

    // Endpoint per aggiornare un post esistente
    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post postDetails) {
        Post updatedPost = postService.updatePost(id, postDetails);
        if (updatedPost != null) {
            return ResponseEntity.ok(updatedPost);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint per eliminare un post
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        boolean deleted = postService.deletePost(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
