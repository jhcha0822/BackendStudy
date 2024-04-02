package com.sds.model2app.domain;

import lombok.Data;

// DTO
// lombok으로 getter, setter generate

@Data
public class Board {
	private int board_idx;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int hit;
}
