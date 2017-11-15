package com.anoki.simpleBoard.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoki.simpleBoard.models.User;

@Repository
@Transactional
//public class UserDao {
  
  /*
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
	
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  public void create(User user) {
    entityManager.persist(user);
    return;
  }
  
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
  
  @SuppressWarnings("unchecked")
  public List<User> findAll() {
    return entityManager.createQuery("select * from users").getResultList();
  }
  
  
  public User getByEmail(String email) {
    return (User) entityManager.createQuery(
        "from USER where EMAIL = :email")
        .setParameter("EMAIL", email)
        .getSingleResult();
  }
  
  public User getByName(String name) {
	    return (User) entityManager.createQuery(
	        "from USER where NAME = :name")
	        .setParameter("name", name)
	        .getSingleResult();
	  }
  
  public User getByUsername(String username) {
	    return (User) entityManager.createQuery(
	        "from USER where username = :username")
	        .setParameter("name", username)
	        .getSingleResult();
	  }


  //public User getById(long id) {
  //  return entityManager.find(User.class, id);
  //}

  public void update(User user) {
    entityManager.merge(user);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
   */
	
public interface UserDao extends JpaRepository<User, Integer> {

	//public Post findByEmail(String email);
	  
	public List<User> findAll();
  
} // class UserDao