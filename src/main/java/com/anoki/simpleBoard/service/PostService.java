package com.anoki.simpleBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anoki.simpleBoard.models.User;
import com.anoki.simpleBoard.dao.PostDao;
import com.anoki.simpleBoard.dao.UserDao;
import com.anoki.simpleBoard.models.Post;

@Service("PostService")
public class PostService{
	
	@Autowired
	private PostDao postDao;
	private UserDao userDao;
	
	
	public List<Post> findAll() {
		return postDao.findAll();
	}
	
	public void addPost(String author, String text) {
	//	User user = userDao.getByName(author);
	//	Post post = new Post(user, text);
	//	postDao.save(post);
	}
	
	public void addPost(Post post) {
		postDao.save(post);
	}
	
	public void deletePost(Post post) {
		postDao.delete(post);
	}
}