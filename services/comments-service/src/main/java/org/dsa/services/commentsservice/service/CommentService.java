package org.dsa.services.commentsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.request.UpdateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.mapper.CommentMapper;
import org.dsa.services.commentsservice.repository.CommentRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
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

  public Flux<CommentResponse> findAll() {
    return commentRepository.findAll().map(commentMapper::toResponse);
  }

  public Mono<CommentResponse> update(Long id, UpdateCommentRequest request) {
    return commentRepository.findById(id).map(commentMapper::toResponse);
  }

  public void delete(Long id) {}
}
