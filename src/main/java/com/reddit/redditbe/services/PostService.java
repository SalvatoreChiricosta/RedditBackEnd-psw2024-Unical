package com.reddit.redditbe.services;

import com.reddit.redditbe.models.Community;
import com.reddit.redditbe.models.Post;
import com.reddit.redditbe.models.User;
import com.reddit.redditbe.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    // Restituisce tutti i post
    @Transactional(readOnly = true)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Post> getAllPostsByCommunity(Community com){return postRepository.findAllByCommunity(com);}

    @Transactional(readOnly = true)
    public List<Post> getAllPostByUser(User user){return postRepository.findByUser(user);}

    // Restituisce tutti i post ordinati per data in ordine decrescente
    @Transactional(readOnly = true)
    public List<Post> getAllPostsOrderByDateDesc() {
        return postRepository.findAllByOrderByPostDateDesc();
    }

    // Restituisce tutti i post ordinati per voti in ordine decrescente
    @Transactional(readOnly = true)
    public List<Post> getAllPostsOrderByVoteDesc() {
        return postRepository.findAllByOrderByPostVoteDesc();
    }

    // Restituisce un singolo post per ID
    @Transactional(readOnly = true)
    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null); // Restituisce null se il post non è trovato
    }

    // Crea un nuovo post
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    // Aggiorna un post esistente
    public Post updatePost(Long id, Post postDetails) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setPostDate(postDetails.getPostDate());
            post.setPostVote(postDetails.getPostVote());
            post.setCommunity(postDetails.getCommunity());
            post.setUser(postDetails.getUser());
            post.setPostDescription(postDetails.getPostDescription());
            post.setPostTitle(postDetails.getPostTitle());
            return postRepository.save(post);
        } else {
            return null; // Restituisce null se il post non è trovato
        }
    }

    // Elimina un post
    public boolean deletePost(Long id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true; // Restituisce true se l'eliminazione è avvenuta con successo
        } else {
            return false; // Restituisce false se il post non è trovato
        }
    }
}
