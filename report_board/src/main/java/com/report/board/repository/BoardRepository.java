package com.report.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.report.board.entity.Board;

import jakarta.transaction.Transactional;
import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	Page<Board> findByREPNAMEContaining(String searchKeyword, Pageable pageable);
	Page<Board> findByMAINCATEGORYContaining(String searchKeyword, Pageable pageable);
	Page<Board> findBySUBCATEGORYContaining(String searchKeyword, Pageable pageable);
	Page<Board> findByREGMEMNOContaining(String searchKeyword, Pageable pageable);
	
	
	//@Query("select report.*, type.TYPE_NAME from report "
	//		+ "join type on report.MAIN_CATEGORY = type.TYPE_CD where report.REP_NO = :REPNO")
	//Optional<Board> findByREPNO(@Param("REPNO") Integer REPNO);
}