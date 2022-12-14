package com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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


public class BoardDAO2 {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)  throws Exception{ //의존성 주입
		this.jdbcTemplate = jdbcTemplate;
	}

	Connection conn = null;

	//num 의 최대값
	public int getMaxNum() {

		int maxNum = 0;
		StringBuffer sql= new StringBuffer(100);

		sql.append("select nvl(max(num),0) from board");

		maxNum = jdbcTemplate.queryForInt(sql.toString());

		return maxNum;
	}


	//입력

	public void insertData(BoardDTO dto) {

		StringBuffer sql = new StringBuffer();

		sql.append("insert into board (num,name,pwd,email,subject,")
		.append("content,ipAddr,hitCount,created )")
		.append("values(?,?,?,?,?,?,?,0,sysdate)");

		jdbcTemplate.update(sql.toString(), dto.getNum(), dto.getName(), dto.getPwd(), dto.getEmail(), dto.getSubject(), dto.getContent(), dto.getIpAddr());

	}

	//전체데이터 가져오기
	public List<BoardDTO> getLists(int start, int end, String searchKey, String searchValue){

		StringBuffer sql = new StringBuffer(500);

		searchValue = "%" + searchValue + "%";

		sql.append("select * from (");
		sql.append("select rownum rnum, data.* from (");
		sql.append("select num,name,subject,hitCount,");
		sql.append("to_char(created,'yyyy-mm-dd') created ");
		//sql+= "from board order by num desc) data) ";

		sql.append("from board where "+ searchKey + " like ? order by num desc) data) ");
		sql.append("where rnum>=? and rnum<=?");

		List<BoardDTO> lists = jdbcTemplate.query(sql.toString(),new Object[] {searchValue, start, end}, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

				BoardDTO dto = new BoardDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setSubject(rs.getString("subject"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getString("created"));
				return dto;
			}

		});

		return lists;
	}


	//전체데이터의 갯수

	public int getDataCount(String searchKey, String searchValue) {

		int dataCount = 0;

		StringBuilder sql = new StringBuilder(200);

		searchValue = "%" + searchValue + "%";

		sql.append("select nvl(count(*),0) from board ");
		sql.append("where "+searchKey+" like ?");

		dataCount = jdbcTemplate.queryForInt(sql.toString(), searchValue);

		return dataCount;

	}

	//num으로 한개의 데이터 가져오기

	public BoardDTO getReadData(int num) {

		StringBuilder sql = new StringBuilder(100);

		sql.append("select num,name,pwd,email,subject,content,");
		sql.append("ipAddr,hitCount,created from board where num=?");


		BoardDTO dtoOne = jdbcTemplate.queryForObject(sql.toString(), new RowMapper<BoardDTO>() {
			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();

				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setPwd(rs.getString("pwd"));
				dto.setEmail(rs.getString("email"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setIpAddr(rs.getString("ipAddr"));
				dto.setHitCount(rs.getInt("hitCount"));
				dto.setCreated(rs.getString("created"));
				return dto;
			}
		}, num);

		return dtoOne;
	}

	//조회수 증가
	public void updateHitCount(int num) {

		StringBuilder sql = new StringBuilder();
		sql.append("update board set hitCount = hitCount+1 where num=?");
		jdbcTemplate.update(sql.toString(), num);

	}

	//수정

	public void updateData(BoardDTO dto) {

		StringBuilder sql = new StringBuilder();
		sql.append("update board set name=?, pwd=?, email=?, subject=?, content=? where num=?");

		jdbcTemplate.update(sql.toString(), dto.getName(), dto.getPwd(), dto.getEmail(), 
				dto.getSubject(), dto.getContent(), dto.getNum());

	}

	//삭제

	public void deletedData(int num) {
		StringBuffer sql = new StringBuffer(100);
		sql.append("delete board where num=?");

		jdbcTemplate.update(sql.toString(), num);
	}


}
