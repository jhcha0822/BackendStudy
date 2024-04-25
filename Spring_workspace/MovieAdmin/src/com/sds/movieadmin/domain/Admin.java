package com.sds.movieadmin.domain;

import lombok.Data;

@Data
public class Admin {
	private int admin_idx;
	private String admin_id;
	private String admin_pw;
	private String admin_name;
}
