package com.report.board.entity;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Getter
@Entity
@NoArgsConstructor
@DynamicInsert
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEM_NO")
	private int MEMNO;
	
	@Column(name = "MEM_ID", unique = true)
	private String MEMID;
	
	@Column(name = "MEM_PW")
	private String MEMPW;
	
	@Column(name = "MEM_NAME")
	private String MEMNAME;
	
	private String SALARY;
	
	private String PHONE;
	
	private String EMAIL;
	
	private String ADDRESS;
	
	private String BIRTHDATE;
	
	private String POSITION;
	
	private String GRADE;
	
	private String DEPARTMENT;
	
	@Column(name = "IS_USE")
	@ColumnDefault("Y")
	private String ISUSE;
	
	@Column(name = "MEM_AUTH")
	@ColumnDefault("USER")
	private String MEMAUTH;
	
	private String JOINDATE;
	
	private String LEAVEDATE;
	
	@CreationTimestamp
	@Column(name = "REG_DATETIME")
	private String REGDATETIME;
	
	@UpdateTimestamp
	@Column(name = "MOD_DATETIME")
	private String MODDATETIME;
	
	@Builder
	public Member(int MEMNO, String MEMID, String MEMPW, String MEMNAME, 
			String BIRTHDATE, String EMAIL, String PHONE, String SALARY, String ADDRESS,
			String POSITION, String GRADE, String DEPARTMENT, String JOINDATE, String LEAVEDATE, String ISUSE, String MEMAUTH) {
		this.MEMNO = MEMNO;
		this.MEMID = MEMID;
		this.MEMPW = MEMPW;
		this.MEMNAME = MEMNAME;
		this.BIRTHDATE = BIRTHDATE;
		this.EMAIL= EMAIL;
		this.PHONE = PHONE;
		this.SALARY = SALARY;
		this.ADDRESS = ADDRESS;
		this.POSITION = POSITION;
		this.GRADE = GRADE;
		this.DEPARTMENT = DEPARTMENT;
		this.JOINDATE = JOINDATE;
		this.LEAVEDATE = LEAVEDATE;
		this.ISUSE = ISUSE;
		this.MEMAUTH = MEMAUTH;
	}
	
}

