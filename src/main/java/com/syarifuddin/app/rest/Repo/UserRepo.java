package com.syarifuddin.app.rest.Repo;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.syarifuddin.app.rest.Models.User;

public interface UserRepo extends JpaRepository<User, Long>{


	@Query("SELECT u FROM User u WHERE u.age BETWEEN :minAge AND :maxAge")
	List<User> findUsersByAgeBetween(@Param("minAge") int minAge, @Param("maxAge") int maxAge);
	
	@Query("select u from User u where u.age > :minAge")
	List<User> findUsersByAgeMoreThan(@Param("minAge") int minAge);
	
	@Query("select u from User u where u.joinDate between :startDate and :endDate")
	List<User> findUserByDateJoinRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}
