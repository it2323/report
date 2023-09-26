                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     package com.report.board.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.report.board.dto.MemberDTO;
import com.report.board.entity.Board;
import com.report.board.entity.Member;
import com.report.board.entity.Type;
import com.report.board.repository.MemberRepository;
import com.report.board.repository.TypeRepository;

import jakarta.transaction.Transactional;


@Service
public class MemberService {
	
	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	private TypeRepository typeRepository;
	
	//회원가입
	@Transactional
	public Integer saveMember(MemberDTO memberDTO) {
		return memberRepository.save(memberDTO.toEntity()).getMEMNO();
	}
	
	@Transactional
	public Integer deleteMember(MemberDTO memberDTO) {
		return memberRepository.save(memberDTO.toDelete()).getMEMNO();
	}
	
	//회원 리스트
	public Page<Member> memberList(Pageable pageable) {	
		return memberRepository.findAll(pageable);
	}
	
	//성명 검색조회
	public Page<Member> memberNameSearch(String SearchKeyword, Pageable pageable){
		return memberRepository.findByMEMNAMEContaining(SearchKeyword, pageable);
	}
	//아이디 검색조회
	public Page<Member> memberIdSearch(String SearchKeyword, Pageable pageable){
		return memberRepository.findByMEMIDContaining(SearchKeyword, pageable);
	}
	//부서 검색조회
	public Page<Member> memberDepartmentSearch(String SearchKeyword, Pageable pageable){
		return memberRepository.findByDEPARTMENTContaining(SearchKeyword, pageable);
	}
	//직급 검색조회
	public Page<Member> memberGradeSearch(String SearchKeyword, Pageable pageable){
		return memberRepository.findByGRADEContaining(SearchKeyword, pageable);
	}
	//직책 검색조회
	public Page<Member> memberPositionSearch(String SearchKeyword, Pageable pageable){
		return memberRepository.findByPOSITIONContaining(SearchKeyword, pageable);
	}
	//전화번호 검색조회
	public Page<Member> memberPhoneSearch(String SearchKeyword, Pageable pageable){
		return memberRepository.findByPHONEContaining(SearchKeyword, pageable);
	}
	
	
	//회원 상세조회
	public Member memberView(Integer id) {
		return memberRepository.findById(id).get();
	}
	
	//로그인 체크
	public MemberDTO loginCheck(MemberDTO memberDTO) {
		Optional<Member> byMemberID = memberRepository.findByMEMID(memberDTO.getMEMID());
		if(byMemberID.isPresent()) {
			
			Member member = byMemberID.get();
			if(member.getMEMPW().equals(memberDTO.getMEMPW())) {
				
				member.getMEMNO();
				
				MemberDTO dto = memberDTO.toMemberDTO(member);
				
				System.out.println(dto);
				return dto;
			} else {
				
				return null;
			}
			
		} else {
			
			return null;
		}
		
		
	}
	
	//분류 전체 데이터 조회
	public List<Type> TypeList(String PNAME) {
		
		return typeRepository.findByPNAME(PNAME);
	}
	
}
