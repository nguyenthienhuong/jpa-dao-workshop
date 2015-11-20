package org.exoplatform.jpa.worskhop;

import org.exoplatform.jpa.workshop.entities.SuperHero;
import org.junit.Test;
import static org.junit.Assert.*;

public class BaseDAOTest extends JPATest {

	@Test
	public void testCreateSuperHero() throws Exception{
		
		SuperHero superHero = new SuperHero();
		superHero.setName("Spiderman");
		superHero.setCivilName("Spiderman 2015");
		superHero.setPicture("picture-spiderman".getBytes());
		superHero = superHeroDAO.create(superHero);

		assertNotNull(superHero);
	
		assertEquals("Spiderman", superHero.getName());
	}
	
	@Test
	public void testDeleteSuperHero() throws Exception {
		
		
	}
}
