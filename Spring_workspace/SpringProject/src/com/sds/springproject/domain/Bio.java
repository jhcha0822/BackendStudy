package com.sds.springproject.domain;

import lombok.Data;

@Data
public class Bio {
	private int bio_idx;
	private String blood;
	private float height;
	private float weight;	
	//부모의 정보 
	private Member member;
	public int getBio_idx() {
		return bio_idx;
	}
	public void setBio_idx(int bio_idx) {
		this.bio_idx = bio_idx;
	}
	public String getBlood() {
		return blood;
	}
	public void setBlood(String blood) {
		this.blood = blood;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
