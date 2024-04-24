package com.sds.testapp.model.board;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sds.testapp.domain.Board;

// Mapper 어노테이션을 통해 아래의 인터페이스 객체는 Mybatis의 id와 연결됨
@Mapper
public interface MybatisBoardDAO {
	
	public List selectBySearch(String title);
	public List selectAll(Map map);
	public Board select(int board_idx);
	public int getTotalCount();
	public int insert(Board board); // sqlSessionTemplate 사용 X -> int 반환 불가
	public int update(Board board);
	public int delete(Board board);
	
}
