package com.report.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.report.board.dto.BoardDTO;
import com.report.board.entity.Board;
import com.report.board.entity.Type;
import com.report.board.repository.BoardRepository;
import com.report.board.repository.TypeRepository;

import jakarta.transaction.Transactional;

@Service
public class BoardService {
	private BoardRepository boardRepository;
	@Autowired
	private TypeRepository typeRepository;
	
	public BoardService(BoardRepository boardRepository) {
		this.boardRepository = boardRepository;
	}
	
	//보고서 저장
	@Transactional
	public Integer saveReport(BoardDTO boardDTO) {
		return boardRepository.save(boardDTO.toEntity()).getREPNO();
	}
	
	//보고서 전체 데이터 조회
	public Page<Board> boardList(Pageable pageable) {	
		return boardRepository.findAll(pageable);
	}
	
	//분류 전체 데이터 조회
	public List<Type> TypeList(String PNAME) {
		
		return typeRepository.findByPNAME(PNAME);
	}
	
	//제목 검색조회
	public Page<Board> boardTitleSearch(String searchKeyword, Pageable pageable) {
		return boardRepository.findByREPNAMEContaining(searchKeyword, pageable);
	}
	//등록자 검색조회
	public Page<Board> boardRegNameSearch(String searchKeyword, Pageable pageable) {
		return boardRepository.findByREGMEMNOContaining(searchKeyword, pageable);
	}
	//대분류 검색조회
	public Page<Board> boardMainTypeSerach(String searchKeyword, Pageable pageable){
		return boardRepository.findByMAINCATEGORYContaining(searchKeyword, pageable);
	}
	//중분류 검색조회
	public Page<Board> boardSubTypeSerach(String searchKeyword, Pageable pageable){
		return boardRepository.findBySUBCATEGORYContaining(searchKeyword, pageable);
	}
	
	//보고서 상세 조회
	public Board boardView(Integer id) {
		return boardRepository.findById(id).get();
	}
	
	//보고서 삭제
	public void boardDelete(Integer id) {
		
		boardRepository.deleteById(id);
	}
}
