package com.jdbc.springweb;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jdbc.dao.BoardDAO;
import com.jdbc.util.MyPage;


public class BoardServlet extends HttpServlet{

//	private static final long serialVersionUID = 1L;
//	final String U8 = "UTF-8";
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
////		request.setCharacterEncoding("UTF-8");
//		String cp = request.getContextPath();	//프로젝트 path까지 들어옴. 이 프로젝트는 study까지
//		String uri = request.getRequestURI();	//현재 들어와 있는 실행되고 있는 지점?까지라고 해야되나
//
//
//		Connection conn = DBConn.getConnection();
//		BoardDAO dao = new BoardDAO(conn);
//
//		MyPage myPage = new MyPage();
//
//		String url;
//
//		if(uri.indexOf("created.do") != -1) {
//			
//			HttpSession session = request.getSession();
//			
//			CustomInfo info = (CustomInfo)session.getAttribute("customInfo");
//			
//			if (info == null) {
//				url = "/member/login.jsp";
//				myForward(request, response, url);
//				return;
//			} 
//			
//			
//			
//			
//			url = "/boardTest/created.jsp";
//			myForward(request, response, url);
//
//		}
//		else if(uri.indexOf("created_ok.do") != -1) {
//			BoardDTO dto = new BoardDTO();
//			int maxNum = dao.getMaxNum();
//
//			dto.setNum(maxNum + 1);
//			dto.setSubject(request.getParameter("subject"));
//			dto.setName(request.getParameter("name"));
//			dto.setEmail(request.getParameter("email"));
//			dto.setPwd(request.getParameter("pwd"));
//			dto.setContent(request.getParameter("content"));
//			dto.setIpAddr(request.getRemoteAddr());
//
//			dao.insertData(dto);
//
//			url = cp + "/bbs/list.do";
//
//			response.sendRedirect(url);
//		}
//		else if(uri.indexOf("list.do") != -1) {
//
//			String pageNum = request.getParameter("pageNum");
//
//			int currentPage = 1;
//
//			if (pageNum != null) {
//				currentPage = Integer.parseInt(pageNum);
//			}
//
//			String searchKey = request.getParameter("searchKey");
//			String searchValue = request.getParameter("searchValue");
//
//			if (searchValue == null) {
//				searchKey = "subject";
//				searchValue = "";
//			}
//			else {
//				if (request.getMethod().equalsIgnoreCase("GET")) {
//					searchValue = URLDecoder.decode(searchValue, "UTF-8");
//				}
//			}
//
//			int dataCount = dao.getDataCount(searchKey, searchValue);
//			int numPerPage = 5;
//			int totalPage = myPage.getPageCount(numPerPage, dataCount);
//
//			if (currentPage > totalPage) {
//				currentPage = totalPage;
//			}
//
//			int start = (currentPage - 1) * numPerPage + 1;
//			int end = currentPage * numPerPage;
//
//			List<BoardDTO> lists = dao.getLists(start, end, searchKey, searchValue);
//
//			String param = "";
//			if (searchValue != null && !searchValue.equals("")) {
//				param = "searchKey=" + searchKey;
//				param += "&searchValue=" + URLEncoder.encode(searchValue, "UTF-8");
//			}
//
//			String listUrl = cp + "/bbs/list.do";
//			if(!param.equals("")) {
//				listUrl += "?" + param;
//			}
//
//			String pageIndexList = myPage.pageIndexList(currentPage, totalPage, listUrl);
//
//			String articleUrl = cp + "/bbs/article.do?pageNum=" + currentPage;
//
//			if (!param.equals("")) {
//				articleUrl += "&" + param;
//			}
//
//			//포워딩할 데이터
//			request.setAttribute("lists", lists);
//			request.setAttribute("pageIndexList", pageIndexList);
//			request.setAttribute("articleUrl", articleUrl);
//			request.setAttribute("dataCount", dataCount);
//
//			url = "/boardTest/list.jsp";
//			myForward(request, response, url);
//		}
//		else if(uri.indexOf("article.do") != -1) {
//
//			int num = Integer.parseInt(request.getParameter("num"));
//
//			String pageNum = request.getParameter("pageNum");
//
//			String searchKey = request.getParameter("searchKey");
//			String searchValue = request.getParameter("searchValue");
//
//			if (searchValue != null && !searchValue.equals("")) {
//				searchValue = URLDecoder.decode(searchValue, "UTF-8");
//			}
//
//			dao.updateHitCount(num);
//
//			BoardDTO dto = dao.getReadData(num);
//
//			if(dto == null) {
//				url = cp + "/bbs/list.do";
//				response.sendRedirect(url);
//			}
//
//			int lineSu = dto.getContent().split("\n").length;
//
//			dto.setContent(dto.getContent().replaceAll("\r", "<br/>"));
//
//			//문자열 크기가 35가 안되면 오류나서 일단 보류
//			//dto.setContent(dto.getContent().substring(0, 35) + "<br/>" + dto.getContent().substring(35));
//
//
//			String param = "pageNum=" + pageNum;
//
//			if (searchValue != null && !searchValue.equals("")) {
//				param += "&searchKey=" + searchKey;
//				param += "&searchValue=" + URLEncoder.encode(searchValue, U8);
//			}
//
//			request.setAttribute("dto", dto);
//			request.setAttribute("params", param);
//			request.setAttribute("lineSu", lineSu);
//			request.setAttribute("pageNum", pageNum);
//
//			url = "/boardTest/article.jsp";
//			myForward(request, response, url);
//		}
//		else if (uri.indexOf("updated.do") != -1) {
//
//			int num = Integer.parseInt(request.getParameter("num"));
//
//			String pageNum = request.getParameter("pageNum");
//
//			String searchKey = request.getParameter("searchKey");
//			String searchValue = request.getParameter("searchValue");
//
//			if (searchValue != null && !searchValue.equals("")) {
//				searchValue = URLDecoder.decode(searchValue, "UTF-8");
//			}
//			
//			BoardDTO dto = dao.getReadData(num);
//
//			if(dto == null) {
//				url = cp + "/bbs/list.do";
//				response.sendRedirect(url);
//			}
//			
//			String param = "pageNum=" + pageNum;
//
//			if (searchValue != null && !searchValue.equals("")) {
//				param += "&searchKey=" + searchKey;
//				param += "&searchValue" + URLEncoder.encode(searchValue, U8);
//			}
//			
//			request.setAttribute("dto", dto);
//			request.setAttribute("pageNum", pageNum);
//			request.setAttribute("params", param);
//			request.setAttribute("searchKey", searchKey);
//			request.setAttribute("searchValue", searchValue);
//
//			url = "/boardTest/updated.jsp";
//			myForward(request, response, url);
//		}
//		else if(uri.indexOf("updated_ok.do") != -1) {
//	
//			int num = Integer.parseInt(request.getParameter("num"));
//
//			String pageNum = request.getParameter("pageNum");
//
//			String searchKey = request.getParameter("searchKey");
//			String searchValue = request.getParameter("searchValue");
//
//			if (searchValue != null && !searchValue.equals("")) {
//				searchValue = URLDecoder.decode(searchValue, "UTF-8");
//			}
//
//			BoardDTO dto = new BoardDTO();
//			
//			dto.setNum(Integer.parseInt(request.getParameter("num")));
//			dto.setSubject(request.getParameter("subject"));
//			dto.setName(request.getParameter("name"));
//			dto.setEmail(request.getParameter("email"));
//			dto.setPwd(request.getParameter("pwd"));
//			dto.setContent(request.getParameter("content"));
//
//			dao.updateData(dto);
//
//			String param = "pageNum=" + pageNum;
//
//			if (searchValue != null && !searchValue.equals("")) {
//				param += "&searchKey=" + searchKey;
//				param += "&searchValue=" + URLEncoder.encode(searchValue, U8);
//			}
//
//			url = cp + "/bbs/list.do?" + param;
//
//			response.sendRedirect(url);
//		}
//		else if(uri.indexOf("deleted_ok.do") != -1) {
//			
//			int num = Integer.parseInt(request.getParameter("num"));
//
//			String pageNum = request.getParameter("pageNum");
//
//			String searchKey = request.getParameter("searchKey");
//			String searchValue = request.getParameter("searchValue");
//
//			if (searchValue != null && !searchValue.equals("")) {
//				searchValue = URLDecoder.decode(searchValue, "UTF-8");
//			}
//
//			dao.deletedData(num);
//
//			String param = "pageNum=" + pageNum;
//
//			if (searchValue != null && !searchValue.equals("")) {
//				param += "&searchKey=" + searchKey;
//				param += "&searchValue=" + URLEncoder.encode(searchValue, U8);
//			}
//
//			url = cp + "/bbs/list.do?" + param;
//
//			response.sendRedirect(url);
//		}
//	}
//
//	protected void myForward(HttpServletRequest request, HttpServletResponse response, String pUrl) throws ServletException, IOException{
//		RequestDispatcher rd = request.getRequestDispatcher(pUrl);
//		rd.forward(request, response);
//	}
}



































