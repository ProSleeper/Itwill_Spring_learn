package com.spring.board.util;

import org.springframework.stereotype.Service;

@Service
public class MyPage {
	// 페이지 수 구하기
	public int getPageCount(int numPerPage, int dataCount) {
		// numperpage 페이지에 보여질 글의 개수
		// dataCount가 글의 총 개수

		int pageCount = 0;

		pageCount = dataCount / numPerPage;

		if ((dataCount % numPerPage) != 0) {
			pageCount++;
		}
		
		if(dataCount == 0) {
			pageCount = 1;
		}
		return pageCount;
	}
	// 페이징 처리 메소드
	public String pageIndexList(int currentPage, int totalPage, String listUrl) {
		
//		System.out.println(currentPage);
//		System.out.println(totalPage);
		// currentPage가 내가 보고 싶어서 누른 값

		// ◀이전 6 7 8 9 10 다음▶
		int numPerBlock = 5; // 보여줄 페이지의 수
		int currentPageSetup; // 이전을 눌렀을 때 보여줄 값(이전 버튼을 누르면 이동해야 될 페이지 번호)
		int page; // 이게 실제 페이지?

		// 최종적으로 출력되는 내용물이 String이기 때문에
		// 계속해서 String을 더해주는데 String을 사용하기보다는 StringBuffer를 사용하는 것이 빠르고 효율적이다.
		StringBuffer sb = new StringBuffer();

		if (currentPage == 0 || totalPage == 0) { // 1개의 게시글도 없다면 아무것도 안해도 되므로 바로 빈값 반환
			return "";
		}

		// 돌아가는 주소
		// list.jsp
		// list.jsp?searchKey=subject&searchValue=하이요

		//이미 값이 존재한다면 &를 붙여서 원하는 값을 누적해서 출력이 가능하도록 함
		if (listUrl.indexOf("?") != -1) {
			// list.jsp?searchKey=subject&searchValue=111&pageNum=3
			listUrl = listUrl + "&";
		} else {
			//현재 값이 아무것도 없다면
			listUrl = listUrl + "?";
			// list.jsp?pageNum3
		}

		// ◀ 이전 6 7 8 9 10 다음 ▶
		currentPageSetup = (currentPage / numPerBlock) * numPerBlock;

		// currentPageSetup = ((currentPage % numPerBlock) / numPerBlock) * numPerBlock;
		// //이렇게 하면 안되나?

		if (currentPage % numPerBlock == 0) {
			currentPageSetup = currentPageSetup - numPerBlock;
		}

		
		// ◀ 이전 버튼 만드는 조건
		if (totalPage > numPerBlock && currentPageSetup > 0) {
			sb.append("<a href=\"" + listUrl + "pageNum=" + currentPageSetup + "\">◀이전</a>&nbsp;");
			// <a href="list.jsp?pageNum=5">◀이전</a>&nbsp;
		}

		// ◀이전 6 7 8 9 10 다음▶
		// 바로가기 페이지
		
		// 6 7 8 9 10 을 만들어주는 조건
		page = currentPageSetup + 1;

		while (page <= totalPage && page <= (currentPageSetup + numPerBlock)) {

			// 내가 누른 페이지가 같은 페이지라면 색만 변경해줌.
			if (page == currentPage) {
				sb.append("<font color=\"Fuchsia\">" + page + "</font>&nbsp;");
				// font color = "Fuchisa">9</font>&nbsp;
			} else {
				sb.append("<a href = \"" + listUrl + "pageNum=" + page + "\">" + page + "</a>&nbsp;");
				// <a href="list.jsp?pageNum=2">2</a>&nbsp;
			}
			page++;
		}

		// ◀이전 6 7 8 9 10 다음▶
		// ◀이전 11 12
		
		// 다음▶ 버튼 만들어주는 조건
		if (totalPage - currentPageSetup > numPerBlock) {

			sb.append("<a href = \"" + listUrl + "pageNum=" + page + "\">다음▶</a>&nbsp;");
			// <a href="list.jsp?pageNum=11">다음▶</a>&nbsp;

		}

		return sb.toString();
	}
	
	public String pageIndexList(int currentPage, int totalPage, int pNPB, String listUrl) {return null;}
}
