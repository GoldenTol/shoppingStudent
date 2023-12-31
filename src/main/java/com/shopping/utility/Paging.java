package com.shopping.utility;

public class Paging {
   //페이징을 위한 클래스
   private int totalCount = 0; //테이블에 들어있는 총 행의 개수
   private int totalPage = 0; //전체 페이지 수
   
   private int pageNumber = 0; //현재 보고있는 페이지 번호
   private int pageSize = 0; //한 페이지에 보여줄 행의 개수
   private int beginRow = 0; //한 페이지에 보여지는 시작 랭킹 번호
   private int endRow = 0; //한 페이지에 보여지는 끝 랭킹 번호
   
   private int pageCount = 10; //하단 증간에 보여지는 페이지 링크 개수
   private int beginPage = 0; //하단에 보여지는 페이지 링크 중에 가장 앞 페이지
   private int endPage = 0; //하단에 보여지는 페이지 링크 중에 가장 뒷 페이지
   
   private String url = "";// 게시물 보여주는 페이지 (예시: boList)
   private String pagingHtml = ""; //하단에 이전, 페이지 번호 ,다음 의 링크를 저장하고 있는 문자열
   private String pagingStatus = ""; //상단 우측의 현재 페이지 정보 ex)20페이지 중 2페이지, 2/20
   
   private String mode = ""; //검색 모드(예시: 작성자, 글제목 등등)
   private String keyword = ""; //검색할 단어
   
   private String flowParameter = ""; // 페이지 이동시 같이 수반되는 파라미터 리스트
   
   public int getPageNumber() {return pageNumber;}
   public int getPageSize() {return pageSize;}
   public int getBeginRow() {return beginRow;}
   public int getEndRow() {return endRow;}
   public String getPagingHtml() {return pagingHtml;}
   public String getPagingStatus() {return pagingStatus;}
   public String getMode() {return mode;}
   public String getKeyword() {return keyword;}
   public String getFlowParameter() {return flowParameter;}

   @Override
   public String toString() {
	   return "Paging [totalCount=" + totalCount + ", totalPage=" + totalPage + ", pageNumber=" + pageNumber
			   + ", pageSize=" + pageSize + ", beginRow=" + beginRow + ", endRow=" + endRow + ", pageCount="
			   + pageCount + ", beginPage=" + beginPage + ", endPage=" + endPage + ", url=" + url + ", pagingHtml="
			   + pagingHtml + ", pagingStatus=" + pagingStatus + ", mode=" + mode + ", keyword=" + keyword + "]";
   }
   
   public Paging(String _pageNumber, String _pageSize, int totalCount, String url, String mode, String keyword, boolean isGrid) {
	   /* get 방식의 주소 형식의 파라미터 값은 String으로 넘어온다. 
		이때 값이 제대로 넘어왔는지 확인한다. */
	   // isGrid=true이면 상품 목록 보기, false이면 일반 형식으로 보기
	   
      if(_pageNumber==null||_pageNumber.equals("null")||_pageNumber.equals("")) {
         _pageNumber = "1";
      }
      this.pageNumber = Integer.parseInt(_pageNumber);
      
      if(_pageSize==null||_pageSize.equals("null")||_pageSize.equals("")) {
    	  if(isGrid==true) {
    		  _pageSize="6"; // 6은 2행 3열의 격자(grid) 구조
    	  }else {
    		  _pageSize="10";
    	  }
      }
      this.pageSize = Integer.parseInt(_pageSize);
      
      
      this.totalCount = totalCount;
      this.url = url;
      this.mode = mode;
      this.keyword = keyword;
      
      double _totalPage = Math.ceil((double)totalCount/pageSize);
      this.totalPage = (int)_totalPage;
      
      this.beginRow = (this.pageNumber-1) * this.pageSize + 1;
      this.endRow = this.pageNumber * this.pageSize;
      
      this.beginPage = (this.pageNumber-1)/this.pageCount * this.pageCount + 1;
      this.endPage = this.beginPage + this.pageCount -1;
      
      if(endRow > totalCount) {
         endRow = totalCount;
      }
      
      if(endPage > totalPage) {
         endPage = totalPage;
      }
      
      this.pagingStatus = "총 " + totalCount + "건[" + pageNumber + "/" + totalPage + "]";
      this.pagingHtml = this.getMakePagingHtml();
      
      this.flowParameter = "";
      this.flowParameter += "&pageNumber=" + this.pageNumber;
      this.flowParameter += "&pageSize=" + this.pageSize;
      this.flowParameter += "&mode=" + this.mode;
      this.flowParameter += "&keyword=" + this.keyword;
      
   }


   private String getMakePagingHtml() {
      String html = "";
      
      html += "<ul class='pagination justify-content-center'>" ;
      
      if(this.pageNumber <= this.pageCount) {
         //'맨 처음'과 '이전' 항목이 존재하지 않는 영역
      }else {
         html += this.makeLiTag(String.valueOf(1), "맨처음");
         html += this.makeLiTag(String.valueOf(beginPage-1), "이전");
      }
      
      for (int i = beginPage; i <= endPage; i++) {
         if(i==pageNumber) { // 활성화는 'active'("ative"로 하고 싶으면 역슬래쉬를 붙여야 한다.)
        	 html += "<li class='page-item active'><a class='page-link'><b><font color='white'>";
        	 html += i;
        	 html += "</font></a></b></li>";
         }else {
        	 html += this.makeLiTag(String.valueOf(i), String.valueOf(i));
         }
      }
      
      if(this.pageNumber >= (totalPage/pageCount*pageCount + 1)) {
         //'맨 끝'과 '다음' 항목이 존재하지 않는 영역
      }else {
    	  html += this.makeLiTag(String.valueOf(endPage+1), "다음");
    	  html += this.makeLiTag(String.valueOf(totalPage), "맨끝");
      }
      html+= "</ul>";
      return html;
   }

   private String makeLiTag(String strPageNumber, String strLabel) {
	   String result = "";
	   
	// class="page-link"
	   
	   result += "<li class='page-item'>";
	   result += "<a class='page-link' href='";
	   result += this.url;
	   result += "&pageNumber="+strPageNumber;
	   result += "&pageSize="+this.pageSize;
	   result += "&mode="+this.mode;
	   result += "&keyword="+this.keyword;
	   result += "'>"+strLabel;
	   result += "</a></li>";
	   
	   return result;
   }
}