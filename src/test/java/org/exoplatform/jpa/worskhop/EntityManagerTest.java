package org.exoplatform.jpa.worskhop;

import static org.junit.Assert.*;

import org.junit.Test;


/**
 *
 */
public class EntityManagerTest extends JPATest {

  @Test
  public void testEntityManager()  {
    assertNotNull(entityManager);
  }

}
