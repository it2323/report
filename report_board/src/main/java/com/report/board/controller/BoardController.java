package com.report.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.report.board.dto.BoardDTO;
import com.report.board.entity.Board;
import com.report.board.entity.Type;
import com.report.board.repository.BoardRepository;
import com.report.board.service.BoardService;

@Controller
public class BoardController {
	private BoardService boardService;
	
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	//홈
	@GetMapping("/")
	public String home() {
		
		return "Home";
	}
	
	//보고서 리스트
	@GetMapping("/board/list")
	public String list(Model model, @PageableDefault(page = 0, size = 10, sort = "REPNO", direction = Direction.DESC) Pageable pageable
			, String searchKeyword, String searchType) {
		
		Page<Board> list = null;
		
		//검색
		if(searchKeyword == null) {
			list = boardService.boardList(pageable);
		} else {
			switch(searchType) {
			case "title":list = boardService.boardTitleSearch(searchKeyword, pageable); break;
			case "name":list = boardService.boardTitleSearch(searchKeyword, pageable); break;
			case "main":list = boardService.boardMainTypeSerach(searchKeyword, pageable); break;
			case "sub":list = boardService.boardSubTypeSerach(searchKeyword, pageable); break;
			}
		}
		
		//리스트 페이징
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 4, list.getTotalPages());
		
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "BoardList";
	}
	
	//보고서 작성폼
	@GetMapping("/board/write")
	public String writeForm(Model model, String PNAME) {
		//분류
		List<Type> maincategory = new ArrayList<>();
		maincategory = boardService.TypeList(PNAME = "대분류");
		
		List<Type> subcategory = new ArrayList<>();
		subcategory = boardService.TypeList(PNAME = "중분류");
		
		model.addAttribute("maincategory", maincategory);
		model.addAttribute("subcategory", subcategory);
		
		return "BoardWrite";
	}
	
	//보고서 작성
	@PostMapping("/board/writePro")
	public String write(BoardDTO boardDTO, @RequestParam String main, @RequestParam String sub) {
		boardDTO.setMAINCATEGORY(main);
		boardDTO.setSUBCATEGORY(sub);
		
		boardService.saveReport(boardDTO);
		return "redirect:/board/list";
	}
	
	//보고서 상세조회
	@GetMapping("/board/view")
	public String view(Model model, Integer id) {
		model.addAttribute("board", boardService.boardView(id));

		return "BoardView";
	}
	
	//보고서 수정폼
	@GetMapping("/board/modify/{id}")
	public String modify(Model model, @PathVariable("id") Integer id, String PNAME) {
		//분류
		List<Type> maincategory = new ArrayList<>();
		maincategory = boardService.TypeList(PNAME = "대분류");
		
		List<Type> subcategory = new ArrayList<>();
		subcategory = boardService.TypeList(PNAME = "중분류");
		
		model.addAttribute("maincategory", maincategory);
		model.addAttribute("subcategory", subcategory);
		
		model.addAttribute("board", boardService.boardView(id));
		
		return "BoardModify";
	}
	
	//보고서 수정
	@PostMapping("/board/update/{id}")
	public String update(BoardDTO boardDTO,@PathVariable("id") Integer id, @RequestParam String main, @RequestParam String sub) {
		Board Boardtemp = boardService.boardView(id);
		BoardDTO boardtemp = boardDTO.toBoardDTO(Boardtemp);
		
		boardtemp.setREPNO(id);
		boardtemp.setREPNAME(boardDTO.getREPNAME());
		boardtemp.setREPCONTENT(boardDTO.getREPCONTENT());
		boardtemp.setMAINCATEGORY(main);
		boardtemp.setSUBCATEGORY(sub);
		
		System.out.println(boardtemp);
		
		boardService.saveReport(boardtemp);
		
		return "redirect:/board/list";
	}
	
	//보고서 삭제
	@GetMapping("/board/delete")
	public String delete(Integer id) {
		//임시
		//사용여부를 N으로 변경하도록 작성
		boardService.boardDelete(id);
		
		return "redirect:/board/list";
	}
}
