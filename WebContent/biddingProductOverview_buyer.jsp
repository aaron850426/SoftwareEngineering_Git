<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="record.*" %>
<%@ page import="java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<%
	String buyerId = (String)session.getAttribute("userId");;
	
	ArrayList<biddingItem> biddingItems = new ArrayList<biddingItem>();
	
	biddingItems = record.searchBiddingByBuyerId(buyerId);
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="biddingProductOverview_buyer_content" class="content">
	    <div class="topic">競標商品概況(我是買家)</div>
	    <div class="content_container">
	        <!-- 這邊form表單傳遞參數你們要自己寫，看要怎麼把所有商品的參數都傳過去 -->
	        <form id="biddingProductOverview_buyerForm" name="biddingProductOverview_buyerForm" class="center" action="">
	            <div class="content_list center">
	                <!-- 標題class用list_head，商品用list_list -->
	                <div class="css_tr list_head">
	                    <div class="css_hd list_img">&nbsp;</div>
	                    <div class="css_hd list_title">競標商品名稱</div>
	                    <div class="css_hd list_price">現在金額</div>
	                    <div class="css_hd list_total">直購價</div>
	                    <div class="css_hd list_status">狀態</div>
	                    <!-- 已結標、未結標、下架、取消交易、已移交 -->
	                </div>
	                <%
	    			for( int i = 0; i < biddingItems.size(); i++) {
	    				biddingItem item = biddingItems.get(i);	
		        	%>
	                <div class="css_tr list_list">
	                    <!-- 按圖片連結到單一競標商品頁面 -->
	                    <div class="css_hd list_img"><img src="./images/content-01.jpg"></div>
	                    <div class="css_hd list_title"><a href="singleAuctionProduct.jsp?auctionId=<%=item.getBidId()%>"
	                    	title="<%=item.getBidId()%>"><%=item.getName()%></a></div>
	                    <div class="css_hd list_price"><%=item.getPriceNow()%></div>
	                    <div class="css_hd list_total"><%=item.getPriceMax()%></div>
	                    <div class="css_hd list_status">
	                    <%
	                      if(item.getStatus().equals("Ing")) 
	                          out.println("進行中");
	                      else if(item.getStatus().equals("Result"))
	                          out.println("已結標");
	                      else if(item.getStatus().equals("UnderTheShelf"))
	                          out.println("已下架");
	                      else if(item.getStatus().equals("Cancel"))
	                          out.println("取消交易");
	                      else if(item.getStatus().equals("End"))
	                          out.println("已移交");
	                                %>
	                    </div>
	                </div>
	                <%} %>
	            </div>
	        </form>
	
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>