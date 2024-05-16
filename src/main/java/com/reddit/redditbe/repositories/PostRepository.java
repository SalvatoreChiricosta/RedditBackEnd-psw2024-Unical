package com.reddit.redditbe.repositories;

import com.reddit.redditbe.models.Community;
import com.reddit.redditbe.models.Post;
import com.reddit.redditbe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByCommunity(Community community);

    List<Post> findByUser(User user);

    List<Post> findAllByOrderByPostDateDesc();

    List<Post> findAllByOrderByPostVoteDesc();

}

