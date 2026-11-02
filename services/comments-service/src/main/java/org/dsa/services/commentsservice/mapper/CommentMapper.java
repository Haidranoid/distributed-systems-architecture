package org.dsa.services.commentsservice.mapper;

import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.entity.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment toCommentEntity(CreateCommentRequest createCommentRequest) {
        return Comment.builder()
                .textContent(createCommentRequest.textContent())
                .build();
    }

    public CommentResponse toCommentResponse(Comment commentEntity) {
        return CommentResponse.builder()
                .id(commentEntity.getId())
                .textContent(commentEntity.getTextContent())
                .build();
    }
}
