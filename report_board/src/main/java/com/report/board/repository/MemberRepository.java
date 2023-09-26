package com.report.board.repository;

import org.springframework.stereotype.Repository;

import com.report.board.entity.Member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


@Repository

public interface MemberRepository extends JpaRepository<Member, Integer>{
	Page<Member> findByMEMNAMEContaining(String SearchKeyword, Pageable pageable);
	Page<Member> findByMEMIDContaining(String SearchKeyword, Pageable pageable);
	Page<Member> findByDEPARTMENTContaining(String SearchKeyword, Pageable pageable);
	Page<Member> findByGRADEContaining(String SearchKeyword, Pageable pageable);
	Page<Member> findByPOSITIONContaining(String SearchKeyword, Pageable pageable);
	Page<Member> findByPHONEContaining(String SearchKeyword, Pageable pageable);
	
	//Optional<Member> findByMEMID(String MEMID);
	
	@Query("select MEM_NO, MEM_ID, MEM_NAME, MEM_PW, SALARY, ADDRESS, BIRTHDATE, EMAIL, PHONE, POSITION,"
			+ " GRADE, DEPARTMENT, JOINDATE, LEAVEDATE, REG_DATETIME, MOD_DATETIME"
			+ "from member where MEM_ID = :MEM_ID")
	Optional<Member> findByMEMID(@Param("MEM_ID") String MEM_ID);
}
