package com.report.board.dto;

import com.report.board.entity.Type;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TypeDTO {
	
	private String TYPECD;
	private String TYPENAME;
	private String PNAME;
	private int DEPTH;
	private int ORDERNO;
	private String DESCRIPTION;
	
	public Type toEntity() {
		Type build = Type.builder()
				.TYPECD(TYPECD)
				.TYPENAME(TYPENAME)
				.PNAME(PNAME)
				.DEPTH(DEPTH)
				.ORDERNO(ORDERNO)
				.DESCRIPTION(DESCRIPTION)
				.build();
			return build;
	}
	
	@Builder
	public TypeDTO(String TYPECD, String TYPENAME, String PNAME, int DEPTH, int ORDERNO, String DESCRIPTION) {
		this.TYPECD = TYPECD;
		this.TYPENAME = TYPENAME;
		this.PNAME = PNAME;
		this.DEPTH = DEPTH;
		this.ORDERNO = ORDERNO;
		this.DESCRIPTION = DESCRIPTION;
	}
}
