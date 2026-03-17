package org.example.service;

import org.example.models.Comment;

public class CommentDAO {

    private org.example.dao.CommentDAO commentDAO;

    public CommentDAO(){
        this.commentDAO = new org.example.dao.CommentDAO();
    }


    public void save(Comment comment){
        commentDAO.save(comment);
    }
}
