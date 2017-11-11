package com.anoki.simpleBoard.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoki.simpleBoard.models.Post;

@Repository
@Transactional
public interface PostDao extends JpaRepository<Post, Integer> {

	/**
	* This method will find an User instance in the database by its email.
	* Note that this method is not implemented and its working code will be
	* automagically generated from its signature by Spring Data JPA.
	*/
	//public Post findByEmail(String email);
	  
	public List<Post> findAll();

  
} // class PostDao

