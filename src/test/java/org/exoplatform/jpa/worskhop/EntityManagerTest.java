package org.exoplatform.jpa.worskhop;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 *
 */
public class EntityManagerTest {

  private EntityManagerFactory entityManagerFactory;
  private EntityManager entityManager;

  @Before
  public void setupTest() {
    entityManagerFactory = Persistence.createEntityManagerFactory("my-pu");
    entityManager = entityManagerFactory.createEntityManager();
  }

  @After
  public void teardown() {
    if(entityManager != null) {
      entityManager.close();
    }
    if(entityManagerFactory != null) {
      entityManagerFactory.close();
    }
  }

  @Test
  public void testEntityManager()  {
    assertNotNull(entityManager);
  }

}
