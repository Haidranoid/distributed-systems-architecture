package org.dsa.services.commentsservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.request.UpdateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.mapper.CommentMapper;
import org.dsa.services.commentsservice.repository.CommentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final CommentMapper commentMapper;

  public CommentResponse create(CreateCommentRequest request) {
    return null;
  }

  public Mono<CommentResponse> findById(Long id) {
    return commentRepository.findById(id).map(commentMapper::toResponse);
  }

  public List<CommentResponse> findAll() {
    return List.of();
  }

  public CommentResponse update(Long id, UpdateCommentRequest request) {
    return null;
  }

  public void delete(Long id) {}
}
