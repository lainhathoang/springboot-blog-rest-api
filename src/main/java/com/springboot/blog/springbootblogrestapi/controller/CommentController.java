package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.payload.CommentDto;
import com.springboot.blog.springbootblogrestapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {
    final private CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentID) {
        return ResponseEntity.ok(commentService.getCommentById(postId, commentID));
    }

    @PutMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentID, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(postId, commentID, commentDto);
        return ResponseEntity.ok(updatedComment);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> updateComment(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentID) {
        commentService.deleteComment(postId, commentID);
        return ResponseEntity.ok("Comment entity deleted successfully");
    }
}