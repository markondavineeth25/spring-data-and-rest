package com.guitar.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.guitar.db.model.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long>{
	
	List<Location> findByStateLike(String sn);
	
	List<Location> findByStateOrCountry(String sn, String cn);
	List<Location> findByStateAndCountry(String sn, String cn);
	
	@Query("select l from Location l where l.state like ?1%")
	List<Location> queryByStatte(String statte);
}
