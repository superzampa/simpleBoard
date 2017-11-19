package com.anoki.simpleBoard.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



/**
 * An entity User composed by three fields (id, email, name).
 * The Entity annotation indicates that this class is a JPA entity.
 * The Table annotation specifies the name for the table in the db.
 */
@Entity
@Table(name = "posts")
public class Post {

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
  // An autogenerated id (unique for each user in the db)
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idPost;
  
  @NotNull
  @ManyToOne(targetEntity=User.class, fetch=FetchType.LAZY)
  @JoinColumn(name="idUser")
  private User user;
  
  @NotNull
  private String text;

  
  private String hidden;
  
  
  private String hiddenBy;

  
  private String deleted;
  

  @ManyToMany(fetch=FetchType.EAGER)
  @JoinTable(name = "posts_tags", joinColumns  = @JoinColumn(name="idPost", referencedColumnName = "idPost"),  
  inverseJoinColumns = @JoinColumn(name = "idTag", referencedColumnName = "idTag"))
  private List<Tag> listTag;
  
 
  
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public Post() {}
  
  public Post(User user, String text) {
    this.user =  user;
    this.text = text;
  }
  
  public Post(User user) {
	  this.user = user;
  }

  
  // Getter and setter methods
  
  public Post(int idPost, User user, String text, String hidden, String hiddenBy, String deleted) {
	this.idPost = idPost;
	this.user = user;
	this.text = text;
	this.hidden = hidden;
	this.hiddenBy = hiddenBy;
	this.deleted = deleted;
  }


  public int getIdPost() {
    return idPost;
  }

  public void setIdPost(int idPost) {
    this.idPost = idPost;
  }
  
  public void setUser(User user) {
	this.user = user;
  }
  
  public String getText() {
	return text;
  }

  public void setText(String text) {
    this.text = text;
  }  

  public String getHidden() {
    return hidden;
  }
  
  public void setHidden(String hidden) {
	this.hidden = hidden;
  }
  
  public String getHiddenBy() {
    return hiddenBy;
  }
  
  public void setHiddenBy(String hiddenBy) {
	this.hiddenBy = hiddenBy;
  }
  
  public String getDeleted() {
	return deleted;
  }
  
  public void setDeleted(String deleted) {
	this.deleted = deleted;
  }
  
  public User getUser() {
	return user;
  }
  
  public List<Tag> getListTag() {
	  return listTag;
  }
  
  public void setListTag(List<Tag> listTag) {
	  this.listTag = listTag;
  }
  
} // class Post


