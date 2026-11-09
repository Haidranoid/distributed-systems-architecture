package org.dsa.services.commentsservice.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.request.UpdateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Tag(name = "CommentsController")
@RequestMapping("/api/v1/comments")
public class CommentsController {

  private final CommentService commentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Mono<CommentResponse> create(
      @Valid @RequestBody CreateCommentRequest createCommentRequest) {
    return commentService.persist(createCommentRequest);
  }

  @GetMapping
  public Flux<CommentResponse> readAll() {
    return commentService.getAll();
  }

  @GetMapping("/{id}")
  public Mono<CommentResponse> readById(@PathVariable Long id) {
    return commentService.getById(id);
  }

  @PatchMapping("/{id}")
  public Mono<CommentResponse> update(
      @PathVariable Long id, @Valid @RequestBody UpdateCommentRequest updateCommentRequest) {
    return commentService.modify(id, updateCommentRequest);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public Mono<Void> delete(@PathVariable Long id) {
    return commentService.remove(id);
  }
}
