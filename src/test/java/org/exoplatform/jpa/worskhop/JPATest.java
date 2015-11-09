package org.exoplatform.jpa.worskhop;

import javax.persistence.EntityManager;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.exoplatform.commons.persistence.impl.EntityManagerService;
import org.exoplatform.container.StandaloneContainer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 */
public class JPATest {

  protected EntityManagerService entityManagerService;
  protected EntityManager entityManager;
  private StandaloneContainer container;

  private Connection conn;
  private Liquibase liquibase;

  @Before
  public void setup() throws Exception {
    // Init Liquibase
    Class.forName("org.hsqldb.jdbcDriver");
    conn = DriverManager.getConnection("jdbc:hsqldb:mem:db1", "sa", "");
    Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(conn));
    liquibase = new Liquibase("db/changelogs/superhero.db.changelog-1.0.0.xml", new ClassLoaderResourceAccessor(), database);
    liquibase.update((String) null);

    // init container
    container = StandaloneContainer.getInstance();

    // create entity manager
    entityManagerService = container.getComponentInstanceOfType(EntityManagerService.class);
    entityManagerService.startRequest(container);
    entityManager = entityManagerService.getEntityManager();
  }

  @After
  public void teardown() throws Exception {
    // close entity manager
    if(entityManagerService != null) {
      entityManagerService.endRequest(container);
    }
    container.stop();
    // Close DB
    liquibase.rollback(1000, null);
    conn.close();
  }
}
