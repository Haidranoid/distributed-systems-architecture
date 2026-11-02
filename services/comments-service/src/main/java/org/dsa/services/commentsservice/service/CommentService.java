package org.dsa.services.commentsservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.services.commentsservice.dto.request.CreateCommentRequest;
import org.dsa.services.commentsservice.dto.request.UpdateCommentRequest;
import org.dsa.services.commentsservice.dto.response.CommentResponse;
import org.dsa.services.commentsservice.repository.CommentRepository;
import org.dsa.shared.core.contracts.CrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentService implements CrudService<Long, CommentResponse, CreateCommentRequest, UpdateCommentRequest> {

    private final CommentRepository commentRepository;

    @Override
    public CommentResponse create(CreateCommentRequest createCommentRequest) {
        return null;
    }

    @Override
    public CommentResponse findById(Long aLong) {
        return null;
    }

    @Override
    public List<CommentResponse> findAll() {
        return List.of();
    }

    @Override
    public CommentResponse update(Long aLong, UpdateCommentRequest updateCommentRequest) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }
}
