package com.sds.testapp.model.board;

import java.util.List;
import java.util.Map;

import com.sds.testapp.domain.Board;

public interface BoardService {
	
	public List selectBySearch(String title);
	public List selectAll(Map map);
	public Board select(int board_idx);
	public int getTotalCount();
	public void insert(Board board); // DAO가 int를 반환하기에 여기서 exception 발생 -> void형
	public void update(Board board);
	public void delete(Board board);	
	
}
