package org.dsa.services.commentsservice.mapper;

import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

  public Comment mapCreateCommentRequestToEntity(CreateCommentRequest request) {
    return Comment.builder().textContent(request.textContent()).build();
  }

  public CommentResponse mapToCommentResponse(Comment comment) {
    return CommentResponse.builder().textContent(comment.getTextContent()).build();
  }
}
