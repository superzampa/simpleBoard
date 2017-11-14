package com.anoki.simpleBoard.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.anoki.simpleBoard.models.Tag;

@Repository
@Transactional
public interface TagDao extends CrudRepository<Tag, Integer> {

	/**
	* This method will find an User instance in the database by its email.
	* Note that this method is not implemented and its working code will be
	* automagically generated from its signature by Spring Data JPA.
	*/
	//public Tag findByEmail(String email);
	  
	public List<Tag> findAll();
	
	public Tag findByName(String name);

	@Modifying
    @Query(value = "insert into tags (name) VALUES (:name)", nativeQuery = true)
    @Transactional
    public void addTag(@Param("name") String name);
	
	//public void mergeByName(String name);
	
  
} // class TagDao

