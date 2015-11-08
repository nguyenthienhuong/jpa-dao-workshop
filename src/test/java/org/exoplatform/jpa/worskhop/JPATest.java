package org.exoplatform.jpa.worskhop;

import javax.persistence.EntityManager;

import org.exoplatform.commons.persistence.impl.EntityManagerService;
import org.exoplatform.container.StandaloneContainer;
import org.junit.After;
import org.junit.Before;

import org.exoplatform.container.ExoContainerContext;

import java.net.MalformedURLException;


/**
 *
 */
public class JPATest {

  protected EntityManagerService entityManagerService;
  protected EntityManager entityManager;
  private StandaloneContainer container;

  @Before
  public void setup() throws Exception {
    // init container
    container = StandaloneContainer.getInstance();

    // create entity manager
    entityManagerService = container.getComponentInstanceOfType(EntityManagerService.class);
    entityManagerService.startRequest(container);
    entityManager = entityManagerService.getEntityManager();
  }

  @After
  public void teardown() {
    // close entity manager
    if(entityManagerService != null) {
      entityManagerService.endRequest(container);
    }
    container.stop();
  }
}
