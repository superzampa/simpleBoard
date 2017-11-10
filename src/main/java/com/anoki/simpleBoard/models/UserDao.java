package com.anoki.simpleBoard.models;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class UserDao {
  
  // An EntityManager will be automatically injected from entityManagerFactory
  // setup on DatabaseConfig class.
  @PersistenceContext
  private EntityManager entityManager;
	
  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * Save the user in the database.
   */
  public void create(User user) {
    entityManager.persist(user);
    return;
  }
  
  /**
   * Delete the user from the database.
   */
  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }
  
  /**
   * Return all the users stored in the database.
   */
  
  @SuppressWarnings("unchecked")
  public List<User> findAll() {
    return entityManager.createQuery("select * from users").getResultList();
  }
  
  
  /**
   * Return the user having the passed email.
   */
  public User getByEmail(String email) {
    return (User) entityManager.createQuery(
        "from USER where EMAIL = :email")
        .setParameter("EMAIL", email)
        .getSingleResult();
  }

  /**
   * Return the user having the passed id.
   */
  //public User getById(long id) {
  //  return entityManager.find(User.class, id);
  //}

  /**
   * Update the passed user in the database.
   */
  public void update(User user) {
    entityManager.merge(user);
    return;
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------
  
} // class UserDao