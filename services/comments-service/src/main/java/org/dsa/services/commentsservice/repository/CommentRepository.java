package org.dsa.services.commentsservice.repository;

import java.util.List;
import org.dsa.services.commentsservice.entity.Comment;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends ReactiveCrudRepository<Comment, Long> {
  List<Comment> id(Long id);
}
