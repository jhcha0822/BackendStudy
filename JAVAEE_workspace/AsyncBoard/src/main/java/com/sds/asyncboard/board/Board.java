package com.sds.asyncboard.board;

import lombok.Data;

// DTO
@Data
public class Board {
	private int board_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
