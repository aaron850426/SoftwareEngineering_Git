<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="search.*" %>
<%@ page import="java.util.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<%
	String intpage1 = (String) request.getParameter("page");
	if (intpage1 == null || intpage1.equals("null") || intpage1.equals("") || !intpage1.matches("[0-9]*"))
	    intpage1 = "1";
	int intpage = Integer.parseInt(intpage1);
	
	// 每頁顯示的資料量
	int pagesize = 16;
	
	// 資料從第幾筆到第幾筆
	int startpage = (intpage - 1) * pagesize + 1;
    int endpage = startpage + pagesize - 1;
	
	ArrayList<auctionItem> auctionItems = new ArrayList<auctionItem>();
	
	auctionItems = search.searchAuctions("", "", startpage, endpage, "");
	
	
	
	// 所有資料的量
	int rcount = search.searchAuctionsForPage("","","");

    //計算總頁數
    int maxpage = 0;
    int datasize = pagesize;
    if (datasize > rcount && rcount > 0)
        datasize = rcount;

    //取餘數
    maxpage = rcount % datasize;
    if (maxpage == 0)
        maxpage = rcount / datasize;
    else
        maxpage = (rcount / datasize) + 1;
    
	
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
	<div id="shoppingMall_content" class="content">
	    <div class="topic">拍賣商品</div>
	    <div class="content_container">
	    	<form id="shoppingMallForm" name="shoppingMallForm">
	    		<!-- 存頁碼的(第1,2,3...頁) -->
	    		<input type="hidden" name="page">
	    		
		        <!-- 商品區塊 -->
		        <div class="content_container00">
		        <%
		        if( auctionItems.size() > 0 ) {
		        	for( int i = 0; i < auctionItems.size(); i++) {
	    				auctionItem item = auctionItems.get(i);
	    		%>
	    				<div class="product">
		        			<a href="singleAuctionProduct.jsp?auctionId=<%=item.getAuctionId()%>" title="<%=item.getName()%>">
				                <img src="./images/content-01.jpg">
				                <p>
				                    <span><%=item.getName()%></span><br>
				                    <span>NT.<%=item.getPrice()%></span><br>
				                    <span style="color: #464a4e;">已賣出數量: <%=item.getOriginalNumber() - item.getNumberOfProduct()%></span>
				                </p>
			                </a>
		            	</div>
	    		<%
		        	}
		        }
		        %>
		        </div>
		
		        <!-- 頁數區塊 -->
		        <script language="javascript">
		            function list(newpage) {
		                document.shoppingMallForm.page.value = newpage;
		                document.shoppingMallForm.submit();
		            }
		        </script>
				
		        <div class="page">
	                <ul>
	                    <!-- 上一頁的class要加上 "previousPage" -->
	                    <%if( maxpage != 1 && intpage != 1){ %>
	                    <li><a href="javascript:list(<%=intpage-1 %>)" class="previousPage" title="上一頁"> ＜ </a></li>
	                    <%} %>
	                    <!-- 現在所在的頁數class要加上 "focusPage", 所有頁數的class加上 "pageview" -->
	                    <%for(int i = 1; i <= maxpage; i++){ 
	                    	if(i == intpage){
	                    %>
	                    	<li class="pageview"><a href="javascript:list(<%=i%>)" class="focusPage" title="<%="第" + i + "頁"%>"> <%=i%> </a></li>
	                    <%	
	                    	continue;
	                    	}
	                    %>
	                    	<li class="pageview"><a href="javascript:list(<%=i%>)" title="<%="第" + i + "頁"%>"> <%=i%> </a></li>
	                    <%} %>
	                    <!-- 下一頁的class要加上 "nextPage" -->
	                    <%if( maxpage != 1 && intpage != maxpage){ %>
	                    <li><a href="javascript:list(<%=intpage+1 %>)" class="nextPage" title="下一頁"> ＞ </a></li>
	                    <%} %>
	                </ul>
	                <p align="center">總比數： <%=rcount %> 筆</p>
	            </div>
			</form>
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>