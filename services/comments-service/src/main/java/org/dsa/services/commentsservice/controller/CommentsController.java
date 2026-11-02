package org.dsa.services.commentsservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.request.UpdateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@Tag(name = "CommentsController")
@RequestMapping("/api/v1/comments")
@RestController
@RequiredArgsConstructor
public class CommentsController {

  private final CommentService commentService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<CommentResponse> createComment(
      @Valid @RequestBody CreateCommentRequest request) {
    log.info("createComment request: {}", request);

    var response = commentService.create(request);

    log.info("createComment response: {}", request);
    return ResponseEntity.created(URI.create("/api/v1/comments")).body(response);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  @Operation(summary = "Get all comments")
  public ResponseEntity<List<CommentResponse>> getAllComments() {
    log.info("getAllComments request: {}", "/api/v1/comments");

    var responses = commentService.findAll();

    log.info("getAllComments response: [{}]", "...");
    return ResponseEntity.ok(responses);
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get a comment by id")
  public Mono<ResponseEntity<CommentResponse>> getCommentById(@PathVariable Long id) {
    return commentService
        .findById(id)
        .doFirst(() -> log.info("getCommentById request: {}", id))
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build())
        .doOnNext(response -> log.info("getCommentById response: {}", response));
  }

  @PatchMapping("/{id}")
  @ResponseStatus(HttpStatus.ACCEPTED)
  @Operation(summary = "Update an comment by id")
  public ResponseEntity<CommentResponse> updateComment(
      @PathVariable Long id, @RequestBody UpdateCommentRequest request) {
    log.info("updateComment request: {}", request);

    var response = commentService.update(id, request);

    log.info("updateComment response: {}", response);
    return ResponseEntity.accepted().body(response);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  @Operation(summary = "Delete an comment by id")
  public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
    log.info("deleteComment request: {}", id);

    commentService.delete(id);

    log.info("deleteComment response: {}", id);
    return ResponseEntity.noContent().build();
  }
}
