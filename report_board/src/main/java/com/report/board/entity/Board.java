package com.report.board.entity;

import java.util.List;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name="report")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REP_NO")
	private int REPNO;
	
	@Column(name = "REP_NAME")
	private String REPNAME;
	
	@Column(name = "REP_CONTENT")
	private String REPCONTENT;
	
	@Column(name = "MAIN_CATEGORY")
	private String MAINCATEGORY;
	
	@Column(name = "SUB_CATEGORY")
	private String SUBCATEGORY;
	/*
	@ColumnDefault("Y")
	@Column(name = "IS_USE")
	private String ISUSE;
	*/
	
	@Column(name = "REG_MEM_NO")
	private String REGMEMNO;
	
	@CreationTimestamp
	@Column(name = "REG_DATETIME")
	private String REGDATETIME;
	
	@UpdateTimestamp
	@Column(name = "MOD_DATETIME")
	private String MODDATETIME;
	
	@Builder
	public Board(int REPNO, String REPNAME, String REPCONTENT, String MAINCATEGORY, String SUBCATEGORY) {
		this.REPNO = REPNO;
		this.REPNAME = REPNAME;
		this.REPCONTENT = REPCONTENT;
		this.MAINCATEGORY = MAINCATEGORY;
		this.SUBCATEGORY = SUBCATEGORY;
	}
}

