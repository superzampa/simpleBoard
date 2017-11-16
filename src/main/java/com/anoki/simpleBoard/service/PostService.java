package com.anoki.simpleBoard.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anoki.simpleBoard.dao.PostDao;
import com.anoki.simpleBoard.models.Post;

@Service("PostService")
public class PostService{
	
	private PostDao postDao;
	
	public List<Post> findAll() {
		return postDao.findAll();
	}
	
	public void addPost(String author, String text) {
	//	User user = userDao.getByName(author);
	//	Post post = new Post(user, text);
	//	postDao.save(post);
	}
	
	public List<Post> searchPosts(int idUser, String text, int idTag) {
		List<Integer> tmp = postDao.searchPosts(idUser, "%" + text + "%", idTag);
		List<Post> tmp1 = new ArrayList<Post>();
		for(Integer i : tmp) {
			tmp1.add(postDao.findByIdPost(i));
		}
		return tmp1;
		//return postDao.searchPosts(idUser, text, idTag);
	}
	
	public void addPost(Post post) {
		postDao.save(post);
	}
	
	public void deletePost(Post post) {
		postDao.delete(post);
	}
	
	public void deletePostByIdPost(int idPost) {
		postDao.deleteByIdPost(idPost);
	}
	
	public Post findByIdPost(int idPost) {
		return postDao.findByIdPost(idPost);
	}
	
	public void hidePost(int idPost, int idUser) {
		postDao.hidePost(idPost, idUser);
	}
	
	@Autowired
	public void setPostDao(PostDao postDao) {
		this.postDao = postDao;
	}
}