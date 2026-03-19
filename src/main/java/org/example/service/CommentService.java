package org.example.service;

import org.example.dao.CommentDAO;
import org.example.models.Comment;
import org.example.models.Task;
import org.example.supportEnum.Status;

import java.util.List;

public class CommentService {

    private CommentDAO commentDAO;

    public CommentService(){
        this.commentDAO = new org.example.dao.CommentDAO();
    }


    public void save(Comment comment){
        commentDAO.save(comment);
    }
    public Comment findById(Long id) {
        return commentDAO.findById(id).orElseThrow(() -> new RuntimeException("Comment not found"));
    }
    public void deleteById(Long id){
        commentDAO.deleteById(id);
    }
    public List<Comment> findAllCommentByIdTask(Long id) {
        List<Comment> result = commentDAO.findAllCommentByIdTask(id);

        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("Not found");
        }
    }
    public List<Comment> findAllCommentByIdUser(Long id) {
        List<Comment> result = commentDAO.findAllCommentByIdUser(id);

        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("Not found");
        }
    }
    public List<Comment> findAllCommentByIdTaskOrderByCreated(Long id) {
        List<Comment> result = commentDAO.findAllCommentByIdTaskOrderByCreated(id);

        if (!result.isEmpty()) {
            return result;
        } else {
            throw new RuntimeException("Not found");
        }
    }

}
