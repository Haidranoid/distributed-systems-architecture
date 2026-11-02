package org.dsa.services.commentsservice.repository;

import org.dsa.services.commentsservice.entity.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ReactiveCrudRepository<Comment, Long> {}
