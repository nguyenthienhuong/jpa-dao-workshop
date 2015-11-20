package org.exoplatform.jpa.worskhop;

import java.sql.Connection;
import java.sql.DriverManager;


import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import org.exoplatform.container.PortalContainer;
import org.exoplatform.jpa.workshop.dao.SuperHeroHandler;
import org.junit.After;
import org.junit.Before;


/**
 *
 */
public abstract class JPATest {

	private Connection conn;
	private Liquibase liquibase;

	protected SuperHeroHandler superHeroDAO;



	@Before
	public void setup() throws Exception {
		// Init Liquibase
		Class.forName("org.hsqldb.jdbcDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:workshop", "sa", "");
		Database database = DatabaseFactory
				.getInstance()
				.findCorrectDatabaseImplementation(new JdbcConnection(conn));
		liquibase = new Liquibase(
				"db/changelogs/superhero.db.changelog-1.0.0.xml",
				new ClassLoaderResourceAccessor(), database);
		liquibase.update((String) null);

		//Init DAO
		superHeroDAO = PortalContainer.getInstance().getComponentInstanceOfType(SuperHeroHandler.class);
	}

	@After
	public void teardown() throws Exception {
		// Close DB
		liquibase.rollback(1000, null);
		conn.close();
	}
}
