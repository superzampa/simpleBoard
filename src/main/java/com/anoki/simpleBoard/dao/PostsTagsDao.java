package com.anoki.simpleBoard.dao;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anoki.simpleBoard.models.Post;
import com.anoki.simpleBoard.models.Tag;

@Repository
@Transactional
public interface PostsTagsDao extends JpaRepository<Post, Integer>{
	
	public List<Tag> findByIdPost(int idPost);
	
	@Modifying
    @Query(value = "insert into posts_tags (id_post,id_tag) VALUES (:id_post,:id_tag)", nativeQuery = true)
    @Transactional
    public void addPostTag(@Param("id_post")int idPost, @Param("id_tag") int idTag);

	@Modifying
    @Query(value = "insert into posts_tags (id_post,id_tag) VALUES (:id_post,:id_tag)", nativeQuery = true)
    @Transactional
	public int findByName(String name);
	
}