package ar.edu.unlp.lifia.ratatoullie.dao.impl;

import org.springframework.stereotype.Repository;

import ar.edu.unlp.lifia.ratatoullie.dao.CommentDao;
import ar.edu.unlp.lifia.ratatoullie.model.Comment;

@Repository
public class CommentDaoImpl extends GenericDaoImpl<Comment> implements CommentDao {

	public CommentDaoImpl() {
		super(Comment.class);		
	}
}
