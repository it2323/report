package com.report.board.dto;

import com.report.board.entity.Board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDTO {

	private int REPNO;
	private String REPNAME;
	private String REPCONTENT;
	private String MAINCATEGORY;
	private String SUBCATEGORY;
	private String REGMEMNO;
	private String REGDATETIME;
	private String MODDATETIME;
	
	public Board toEntity() {
		Board build = Board.builder()
				.REPNO(REPNO)
				.REPNAME(REPNAME)
				.REPCONTENT(REPCONTENT)
				.MAINCATEGORY(MAINCATEGORY)
				.SUBCATEGORY(SUBCATEGORY)
				.build();
		return build;
	}
	
	public BoardDTO toBoardDTO(Board boardtemp) {
		BoardDTO build = BoardDTO.builder()
				.REPNO(REPNO)
				.REPNAME(REPNAME)
				.REPCONTENT(REPCONTENT)
				.build();
		return build;
	}
	
	@Builder
	public BoardDTO(int REPNO, String REPNAME, String REPCONTENT,String MAINCATEGORY, String SUBCATEGORY, String REGMEMNO, String REGDATETIME, String MODDATETIME) {
		this.REPNO = REPNO;
		this.REPNAME = REPNAME;
		this.REPCONTENT = REPCONTENT;
		this.MAINCATEGORY = MAINCATEGORY;
		this.SUBCATEGORY = SUBCATEGORY;
		this.REGMEMNO = REGMEMNO;
		this.REGDATETIME = REGDATETIME;
		this.MODDATETIME = MODDATETIME;
	}
}
