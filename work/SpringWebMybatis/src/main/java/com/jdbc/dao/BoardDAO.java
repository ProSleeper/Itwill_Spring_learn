package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionTemplate;

import com.jdbc.dto.BoardDTO;

//CREATE TABLE BOARD
//(NUM NUMBER (9) NOT NULL,
//NAME VARCHAR2(20) NOT NULL,
//PWD VARCHAR2(10) NOT NULL,
//EMAIL VARCHAR2(50),
//SUBJECT VARCHAR2(50) NOT NULL,
//CONTENT VARCHAR2(4000) NOT NULL,
//IPADDR VARCHAR2 (20),
//HITCOUNT NUMBER(9),
//CREATED DATE,
//CONSTRAINT PK_BOARD_NUM PRIMARY KEY(NUM));

public class BoardDAO {

	private SqlSessionTemplate sessionTemplate;
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate)  throws Exception{ //의존성 주입
		this.sessionTemplate = sessionTemplate;
	}

	//num 의 최대값
	public int getMaxNum() {

		int maxNum =0;
		maxNum = sessionTemplate.selectOne("com.boardMapper.maxNum");

		return maxNum;
	}

	//입력
	public void insertData(BoardDTO dto) {
		sessionTemplate.insert("com.boardMapper.insertData", dto);

	}

	//전체데이터 가져오기
	public List<BoardDTO> getLists(int start, int end, String searchKey, String searchValue){

		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("start", start);
		params.put("end", end);
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		List<BoardDTO> lists = sessionTemplate.selectList("com.boardMapper.getLists", params);
		
		return lists;
	}

	//전체데이터의 갯수

	public int getDataCount(String searchKey, String searchValue) {
		int dataCount =0;
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("searchKey", searchKey);
		params.put("searchValue", searchValue);
		
		dataCount = sessionTemplate.selectOne("com.boardMapper.getDataCount", params);
		
		return dataCount;
	}

	//num으로 한개의 데이터 가져오기

	public BoardDTO getReadData(int num) {

		return sessionTemplate.selectOne("com.boardMapper.getReadData", num);
	}

	//조회수 증가
	public void updateHitCount(int num) {

		sessionTemplate.update("com.boardMapper.updateHitCount", num);
	}

	//수정
	public void updateData(BoardDTO dto) {
		sessionTemplate.update("com.boardMapper.updateData", dto);
	}

	//삭제
	public void deletedData(int num) {

		sessionTemplate.delete("com.boardMapper.deleteData", num);
	}


}
