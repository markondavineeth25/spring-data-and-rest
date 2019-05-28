package com.guitar.db;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.guitar.db.model.Model;
import com.guitar.db.repository.ModelJpaRepository;
import com.guitar.db.repository.ModelRepository;

@ContextConfiguration(locations={"classpath:com/guitar/db/applicationTests-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class ModelPersistenceTests {
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private ModelJpaRepository modelJpaRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Test
	@Transactional
	public void testSaveAndGetAndDelete() throws Exception {
		Model m = new Model();
		m.setFrets(10);
		m.setName("Test Model");
		m.setPrice(BigDecimal.valueOf(55L));
		m.setWoodType("Maple");
		m.setYearFirstMade(new Date());
		m = modelRepository.create(m);
		
		// clear the persistence context so we don't return the previously cached location object
		// this is a test only thing and normally doesn't need to be done in prod code
		entityManager.clear();

		Model otherModel = modelRepository.find(m.getId());
		assertEquals("Test Model", otherModel.getName());
		assertEquals(10, otherModel.getFrets());
		
		//delete BC location now
		modelRepository.delete(otherModel);
	}

	@Test
	public void testGetModelsInPriceRange() throws Exception {
		//List<Model> mods = modelRepository.getModelsInPriceRange(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));
		
		// moved to Spring data jpa using findBy ... 
		List<Model> mods = modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L));
		assertEquals(4, mods.size());
	}

	@Test
	public void testGetModelsByPriceRangeAndWoodType() throws Exception {
		
		// normal jpa 
		//List<Model> mods = modelRepository.getModelsByPriceRangeAndWoodType(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "Maple");

		// Spring data jpa 
		//List<Model> mods = modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqualAndWoodTypeLike(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%");
		
		// Spring data jpa -- @Query annotation
		List<Model> mods = modelJpaRepository.queryByPriceRangeAndWoodType(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "%Maple%");
		
		assertEquals(3, mods.size());
	}
	
	@Test
	public void queryByPriceRange() throws Exception {
		List<Model> mods = modelJpaRepository.queryByPriceRange(BigDecimal.valueOf(1000L), BigDecimal.valueOf(2000L), "Maple");
		System.out.println(mods);
		assertEquals(3, mods.size());
	}

	@Test
	public void testGetModelsByType() throws Exception {
		
		// normal jpa 
		//List<Model> mods = modelRepository.getModelsByType("Electric");
		
		// Spring data jpa - named query 
		List<Model> mods = modelJpaRepository.findAllModelsByType("Electric");
		assertEquals(4, mods.size());
	}
	
	@Test
	public void testGetModelsByModelTypeName() throws Exception {
		List<Model> mods = modelJpaRepository.findByModelTypeNameIn("Electric");
		System.out.println(mods);
	}
}