package com.anoki.simpleBoard.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tags")
public class Tag {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idTag;
  
  @NotNull
  @Column(unique=true)
  private String name;
  
  @ManyToMany(mappedBy = "listTag")
  private List<Post> posts;
  
  public Tag() {}
  
  public int getIdTag() {
	  return idTag;
  }
  
  public void setIdTag(int idTag) {
	  this.idTag = idTag;
  }
  
  public String getName() {
	  return name;
  }
  
  public void setName(String name) {
	  this.name = name;
  }
  
  public List<Post> getPosts() {
      return posts;
  }
  
  public void setPosts(List<Post> posts) {
	  this.posts = posts;
  }


}