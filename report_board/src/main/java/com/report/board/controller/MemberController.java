package com.report.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.report.board.dto.BoardDTO;
import com.report.board.dto.MemberDTO;
import com.report.board.entity.Board;
import com.report.board.entity.Member;
import com.report.board.entity.Type;
import com.report.board.service.BoardService;
import com.report.board.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;


@Controller
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	//로그인 폼
	@GetMapping("/member/login")
	public String loginForm() {
		
		return "Login";
	}
	
	//로그인
	@PostMapping("/member/loginPro")
	public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
		MemberDTO loginResult = memberService.loginCheck(memberDTO);
		
		if(loginResult != null) {
			
			session.setAttribute("memberLogin", loginResult);
			return "redirect:/";
		} else {
	
			return "redirect:/member/login";
		}
	}
	
	//로그아웃
	@GetMapping("/member/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		
		return "redirect:/";
	}
	
	//회원가입 폼
	@GetMapping("/member/new")
	public String newMemberForm(Model model, String PNAME) {
		//분류
		List<Type> grade = new ArrayList<>();
		grade = memberService.TypeList(PNAME = "직급");
		
		List<Type> position = new ArrayList<>();
		position = memberService.TypeList(PNAME = "직책");
		
		List<Type> department = new ArrayList<>();
		department = memberService.TypeList(PNAME = "부서");
		
		model.addAttribute("grade", grade);
		model.addAttribute("position", position);
		model.addAttribute("department", department);
		
		return "NewMember";
	}
	
	//회원가입
	@PostMapping("/member/newPro")
	public String newMember(MemberDTO memberDTO, @RequestParam String grade,@RequestParam String position,@RequestParam String department) {
		
		memberDTO.setGRADE(grade);
		memberDTO.setPOSITION(position);
		memberDTO.setDEPARTMENT(department);
		
		memberService.saveMember(memberDTO);
		
		return "redirect:/member/login";
	}
	
	//회원 리스트
	@GetMapping("/member/list")
	public String list(Model model, @PageableDefault(page = 0, size = 10, sort = "MEMNO", direction = Direction.DESC) Pageable pageable,
			String searchKeyword, String searchType) {
		
		Page<Member> list = null;
		
		//검색
		if(searchKeyword == null) {
			list = memberService.memberList(pageable);
		} else {
			switch(searchType) {
			case "name":list = memberService.memberNameSearch(searchKeyword, pageable); break;
			case "id":list = memberService.memberIdSearch(searchKeyword, pageable); break;
			case "department":list = memberService.memberDepartmentSearch(searchKeyword, pageable); break;
			case "grade":list = memberService.memberGradeSearch(searchKeyword, pageable); break;
			case "position":list = memberService.memberPositionSearch(searchKeyword, pageable); break;
			case "phone":list = memberService.memberPhoneSearch(searchKeyword, pageable); break;
			}
		}
		
		//리스트 페이징
		int nowPage = list.getPageable().getPageNumber() + 1;
		int startPage = Math.max(nowPage - 4, 1);
		int endPage = Math.min(nowPage + 5, list.getTotalPages());
		
		model.addAttribute("list", list);
		model.addAttribute("nowPage", nowPage);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "MemberList";
	}

	
	//회원 상세조회
	@GetMapping("/member/view")
	public String view(Model model, Integer id) {
		model.addAttribute("member", memberService.memberView(id));
		
		return "MemberView";
	}
	
	//회원 수정폼
	@GetMapping("/member/modify/{id}")
	public String modify(Model model, @PathVariable("id") Integer id, String PNAME) {
		//분류
		List<Type> grade = new ArrayList<>();
		grade = memberService.TypeList(PNAME = "직급");
		
		List<Type> position = new ArrayList<>();
		position = memberService.TypeList(PNAME = "직책");
		
		List<Type> department = new ArrayList<>();
		department = memberService.TypeList(PNAME = "부서");
		
		model.addAttribute("grade", grade);
		model.addAttribute("position", position);
		model.addAttribute("department", department);
		
		model.addAttribute("member", memberService.memberView(id));
		
		return "MemberModify";
	}
	
	//회원 수정
	@PostMapping("member/update/{id}")
	public String update(MemberDTO memberDTO,@PathVariable("id") Integer id, @RequestParam String grade, @RequestParam String position, @RequestParam String department ) {
		Member Membertemp = memberService.memberView(id);
		MemberDTO membertemp = memberDTO.toMemberDTO(Membertemp);
		
		membertemp.setMEMNO(id);
		membertemp.setMEMNAME(memberDTO.getMEMNAME());
		membertemp.setBIRTHDATE(memberDTO.getBIRTHDATE());
		membertemp.setEMAIL(memberDTO.getEMAIL());
		membertemp.setPHONE(memberDTO.getPHONE());
		membertemp.setADDRESS(memberDTO.getADDRESS());
		membertemp.setDEPARTMENT(department);
		membertemp.setGRADE(grade);
		membertemp.setPOSITION(position);
		membertemp.setSALARY(memberDTO.getSALARY());
		membertemp.setISUSE("Y");
		membertemp.setMEMAUTH("USER");
		
		System.out.println(membertemp);
		
		memberService.saveMember(membertemp);
		
		return "redirect:/member/list";
	}
	
	//회원탈퇴
	@GetMapping("member/delete/{id}")
	public String delete(MemberDTO memberDTO, @PathVariable("id") Integer id) {
		Member Membertemp = memberService.memberView(id);
		MemberDTO membertemp = memberDTO.toMemberDTO(Membertemp);
		
		membertemp.setMEMNO(id);
		membertemp.setISUSE("N");
		
		System.out.println(membertemp);
		
		memberService.deleteMember(membertemp);
		
		return "redirect:/member/list";
		
	}
}
