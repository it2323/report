package com.report.board.dto;

import java.util.Optional;

import com.report.board.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberDTO {
	
	private int MEMNO;
	private String MEMID;
	private String MEMNAME;
	private String MEMPW;
	private String SALARY;
	private String ADDRESS;
	private String BIRTHDATE;
	private String EMAIL;
	private String PHONE;
	private String POSITION;
	private String GRADE;
	private String DEPARTMENT;
	private String JOINDATE;
	private String LEAVEDATE;
	private String REGDATETIME;
	private String MODDATETIME;
	private String ISUSE;
	private String MEMAUTH;
	
	public Member toEntity() {
		Member build = Member.builder()
				.MEMNO(MEMNO)
				.MEMID(MEMID)
				.MEMPW(MEMPW)
				.MEMNAME(MEMNAME)
				.BIRTHDATE(BIRTHDATE)
				.EMAIL(EMAIL)
				.PHONE(PHONE)
				.ADDRESS(ADDRESS)
				.JOINDATE(JOINDATE)
				.LEAVEDATE(LEAVEDATE)
				.SALARY(SALARY)
				.POSITION(POSITION)
				.GRADE(GRADE)
				.DEPARTMENT(DEPARTMENT)
				.ISUSE(ISUSE)
				.MEMAUTH(MEMAUTH)
				.build();
		return build;
	}
	
	public Member toDelete() {
		Member build = Member.builder()
				.MEMNO(MEMNO)
				.ISUSE(ISUSE)
				.build();
		return build;
	}
	
	public MemberDTO toMemberDTO(Member member) {
		MemberDTO build = MemberDTO.builder()
				.MEMNO(MEMNO)
				.MEMID(MEMID)
				.MEMPW(MEMPW)
				.MEMNAME(MEMNAME)
				.BIRTHDATE(BIRTHDATE)
				.EMAIL(EMAIL)
				.PHONE(PHONE)
				.build();
		return build;
	}
	
	@Builder
	public MemberDTO(int MEMNO, String MEMID, String MEMPW, String MEMNAME, 
			String BIRTHDATE, String EMAIL, String PHONE, String REGDATETIME, String MODDATETIME, 
			String SALARY, String ADDRESS, String POSITION, String GRADE, 
			String DEPARTMENT, String JOINDATE, String LEAVEDATE, String ISUSE, String MEMAUTH) {
		this.MEMNO = MEMNO;
		this.MEMID = MEMID;
		this.MEMPW = MEMPW;
		this.MEMNAME = MEMNAME;
		this.BIRTHDATE = BIRTHDATE;
		this.EMAIL = EMAIL;
		this.PHONE = PHONE;
		this.SALARY = SALARY;
		this.ADDRESS = ADDRESS;
		this.POSITION = POSITION;
		this.GRADE = GRADE;
		this.DEPARTMENT = DEPARTMENT;
		this.JOINDATE = JOINDATE;
		this.LEAVEDATE = LEAVEDATE;
		this.REGDATETIME = REGDATETIME;
		this.MODDATETIME = MODDATETIME;
		this.ISUSE = ISUSE;
		this.MEMAUTH = MEMAUTH;
	}


}
