package com.dh.repository;

import com.dh.domain.Notification;
import com.dh.domain.NotificationType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {

    Optional<Notification> findById(String id);

    Notification save(Notification notification);

    void deleteById(String id);

    @Query("{ 'type': ?0, 'commentId':?1 }")
    Optional<Notification> findByTypeAndCommentId(NotificationType type, Long commentId);

    @Query("{ 'type': ?0, 'postId':?1 }")
    Optional<Notification> findByTypeAndPostId(NotificationType type, Long postId);

    @Query("{ 'type': ?0, 'userId': ?1, 'followerId':?2 }")
    Optional<Notification> findByTypeAndUserIdAndFollowerId(NotificationType type, Long postId, Long FollowerId);

    Slice<Notification> findAllByUserIdOrderByOccurredAtDesc(long Id, Pageable pageable);

    Slice<Notification> findAllByUserIdAndOccurredAtLessThanOrderByOccurredAtDesc(long Id, Instant occurredAt, Pageable pageable);
}
