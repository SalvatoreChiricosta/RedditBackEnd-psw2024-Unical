package com.reddit.redditbe.repositories;

import com.reddit.redditbe.models.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByCommunityName(String community);
}
