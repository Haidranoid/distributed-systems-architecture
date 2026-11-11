package org.dsa.services.commentsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.request.UpdateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.entity.Comment;
import org.dsa.services.commentsservice.exception.CommentNotFoundException;
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

  public Mono<CommentResponse> persist(CreateCommentRequest createCommentRequest) {
    Comment comment = commentMapper.mapCreateCommentRequestToEntity(createCommentRequest);

    return commentRepository
        .save(comment)
        .map(commentMapper::mapToCommentResponse)
        .doOnSuccess(response -> log.debug("Comment created: {}", response));
  }

  public Mono<CommentResponse> getById(Long id) {
    return commentRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new CommentNotFoundException(id)))
        .map(commentMapper::mapToCommentResponse)
        .doOnSuccess(response -> log.debug("Comment retrieved: {}", response));
  }

  public Flux<CommentResponse> getAll() {
    return commentRepository
        .findAll()
        .map(commentMapper::mapToCommentResponse)
        .doOnComplete(() -> log.debug("All comments retrieved"));
  }

  public Mono<CommentResponse> modify(Long id, UpdateCommentRequest updateCommentRequest) {
    return commentRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new CommentNotFoundException(id)))
        .flatMap(comment -> {
          comment.setTextContent(updateCommentRequest.textContent());
          return commentRepository.save(comment);
        })
        .map(commentMapper::mapToCommentResponse)
        .doOnSuccess(response -> log.debug("Comment updated: {}", response));
  }

  public Mono<Void> remove(Long id) {
    return commentRepository
        .findById(id)
        .switchIfEmpty(Mono.error(new CommentNotFoundException(id)))
        .flatMap(commentRepository::delete)
        .doOnSuccess(ignored -> log.debug("Comment deleted: {}", id));
  }
}
