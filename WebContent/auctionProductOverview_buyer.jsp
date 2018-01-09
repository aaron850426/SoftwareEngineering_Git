<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="record.*" %>
<%@ page import="java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<%
	String buyerId = (String)session.getAttribute("userId");;
	
	ArrayList<checkoutAuctionItem> checkoutAuctionItems = new ArrayList<checkoutAuctionItem>();
	
	checkoutAuctionItems = record.searchAuctionByBuyerId(buyerId);	
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="auctionProductOverview_buyer_content" class="content">
    <div class="topic">拍賣商品概況(我是買家)</div>
    <div class="content_container">
        <!-- 這邊form表單傳遞參數你們要自己寫，看要怎麼把所有商品的參數都傳過去 -->
        <form id="auctionProductOverview_buyerForm" name="auctionProductOverview_buyerForm" class="center" action="">
            <div class="content_list center">
                <!-- 標題class用list_head，商品用list_list -->
                <div class="css_tr list_head">
                    <div class="css_hd list_img">&nbsp;</div>
                    <div class="css_hd list_title">拍賣商品名稱</div>
                    <div class="css_hd list_number">數量</div>
                    <div class="css_hd list_price">單價</div>
                    <div class="css_hd list_total">小計</div>
                    <div class="css_hd list_status">狀態</div>
                    <!-- 已完成交易、交易中、移交中、取消交易 -->
                </div>
                <%
    			for( int i = 0; i < checkoutAuctionItems.size(); i++) {
    				checkoutAuctionItem item = checkoutAuctionItems.get(i);
	        	%> 
                <div class="css_tr list_list">
                    <!-- 按圖片連結到單一拍賣商品頁面 -->
                    <div class="css_hd list_img"><img src="./images/content-01.jpg"></div>
                    <div class="css_hd list_title"><a href="singleAuctionProduct.jsp?auctionId=<%=item.getAuctionId()%>"
	                    	title="<%=item.getName()%>"><%=item.getName()%></a></div>
                    <div class="css_hd list_number"><%=item.getNumberOfProduct()%></div>
                    <div class="css_hd list_price"><%=item.getPrice()%></div>
                    <div class="css_hd list_total"><%=item.getNumberOfProduct()*item.getPrice() %></div>
                    <div class="css_hd list_status">
                    	<%
	                   		String status = item.getStatus();
	                   		if (status.equals("Ing")){
	               		%>
	                   		等待商家確認商品
	               		<%	
	                		} else if (status.equals("Move")) {		                    			
	               		%>
	           		                移交中
	               		<%
	               			} else if (status.equals("End")) {
	              		%>	
	              			完成交易
	              		<%
	              			} else if (status.equals("Cancel")){
	             		%>
	             			取消交易
	             		<%  } %>
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