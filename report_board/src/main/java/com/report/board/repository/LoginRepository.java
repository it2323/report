package com.report.board.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.report.board.entity.Member;

@Repository
public interface LoginRepository extends CrudRepository<Member, Integer> {
	
}
