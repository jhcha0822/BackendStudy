package com.sds.mall.model.member;

import java.util.List;

import com.sds.mall.domain.MemberDetail;

public interface MemberDetailDAO {
	public void insert(MemberDetail memberDetail);
	public List selectAll();
	public MemberDetail select(int member_detail_idx);
	public void update(MemberDetail memberDetail);
	public void delete(MemberDetail memberDetail);
}
