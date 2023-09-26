package com.report.board.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.report.board.entity.Type;
import java.util.List;


@Repository
public interface TypeRepository extends JpaRepository<Type, String>{
	
	List<Type> findByPNAME(String PNAME);
	
}
