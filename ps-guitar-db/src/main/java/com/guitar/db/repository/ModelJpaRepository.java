package com.guitar.db.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.guitar.db.model.Model;

public interface ModelJpaRepository extends JpaRepository<Model, Long> {
	
	List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqual(BigDecimal lowest, BigDecimal highest);
	
	List<Model> findByModelTypeNameIn(String modeltypeName);
	
	List<Model> findByPriceGreaterThanEqualAndPriceLessThanEqualAndWoodTypeLike(BigDecimal lowest, BigDecimal highest, String woodType);
	
	@Query("select m from Model m where m.price >= :lowest and m.price <= :highest and m.woodType like :wood")
	List<Model> queryByPriceRangeAndWoodType(@Param("lowest") BigDecimal lowest,
											@Param("highest") BigDecimal highest,
											@Param("wood") String woodType);
	
	@Query("select m from Model m where m.price>=?1 and m.price<=?2 and m.woodType like %?3%")
	List<Model> queryByPriceRange(BigDecimal lowest, 
								BigDecimal highest, 
								String wood);
	
	List<Model> findAllModelsByType(@Param("name") String name);
	
}
