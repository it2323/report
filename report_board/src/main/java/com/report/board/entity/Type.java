package com.report.board.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Type {
	@Id
	@Column(name = "TYPE_CD")
	private String TYPECD;
	
	@Column(name = "TYPE_NAME")
	private String TYPENAME;
	
	@Column(name = "P_NAME")
	private String PNAME;
	
	private int DEPTH;
	
	@Column(name = "ORDER_NO")
	private int ORDERNO;

	private String DESCRIPTION;

	@Builder
	public Type(String TYPECD, String TYPENAME, String PNAME, int DEPTH, int ORDERNO, String DESCRIPTION) {
		this.TYPECD = TYPECD;
		this.TYPENAME = TYPENAME;
		this.PNAME = PNAME;
		this.DEPTH = DEPTH;
		this.ORDERNO = ORDERNO;
		this.DESCRIPTION = DESCRIPTION;
	}
	
}
