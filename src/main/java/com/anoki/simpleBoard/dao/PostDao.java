package com.anoki.simpleBoard.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	public Post findByIdPost(int idPost);

    @Transactional
    public Long deleteByIdPost(Integer idPost);
    
	@Modifying
    @Query(value = "update posts set hidden = 'Y', hidden_by = :idUser where id_post = :idPost", nativeQuery = true)
    @Transactional
    public void hidePost(@Param("idUser") int idUser, @Param("idPost") int idPost);
    
    @Query(value = "select distinct p.id_post from posts p join users u on u.id_user = p.id_user \r\n" + 
    		"left join posts_tags pt on pt.id_post = p.id_post \r\n" + 
    		"where 1=1\r\n" + 
    		"and (:idTag = 0 or :idTag = pt.id_tag)\r\n" + 
    		"and (:idUser = 0 or :idUser = u.id_user)\r\n" + 
    		"and (:text = \"\" or p.text like :text)", nativeQuery = true)
	public List<Integer> searchPosts(@Param("idUser") int idUser, @Param("text") String text,@Param("idTag") int idTag);

    
  
} // class PostDao

