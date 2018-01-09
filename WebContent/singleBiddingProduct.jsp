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
	
	<script>
		function addPrice(){
			document.singleBiddingProductForm.action = "addPriceServlet";
			document.singleBiddingProductForm.submit();
			alert("加價成功");
		}
	
		function directlyBuy(){
			document.singleBiddingProductForm.action = "directlyBuyServlet";
			document.singleBiddingProductForm.submit();
			
		}
	</script>
	
</head>

<%

	String userAccount = (String)session.getAttribute("userId");
	int bidId = Integer.parseInt(request.getParameter("bidId"));
	
	biddingItem item = search.searchSingleBid(bidId);

%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 結標後要秀出已結標，立即加價Button關閉點擊 -->
	<!-- 內容區 -->
	<div id="singleBiddingProduct_content" class="content">
	    <div class="content_container">
	        <div id="singleBiddingProduct_content_top" class="left">
	            <form id="singleBiddingProductForm" name="singleBiddingProductForm" class="center" action="">
	            	<input type="hidden" name="bidId" value="<%=item.getBidId()%>">
	                <input type="hidden" name="priceNow" value="<%=item.getPriceNow()%>">
	                <input type="hidden" name="priceIncrease" value="<%=item.getPriceIncrease()%>">
	                <input type="hidden" name="priceMax" value="<%=item.getPriceMax()%>">
	
	                <div class="single_img left"><img src="./images/content-01.jpg"></div>
	                <div class="single_field left">
	                	<div class="form-group col-md-10">
	                        <p>販售人：<a href="memberProfile.jsp?userId=<%=item.getSellerId()%>" title="<%=item.getSellerId() %>"><%=item.getSellerId() %></a></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>商品：<%=item.getName() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>結標時間： <%=item.getEndTime() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>起始金額： $<%=item.getPriceMin() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>目前最高金額： $<%=item.getPriceNow() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>每次加價金額： $<%=item.getPriceIncrease() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>直購金額： $<%=item.getPriceMax() %></p>
	                    </div>
	                    <% if( userAccount != null && !userAccount.equals("null") && !userAccount.equals("") && !userAccount.equals(item.getSellerId()) && !item.getStatus().equals("Result") && !item.getStatus().equals("UnderTheShelf") && !item.getStatus().equals("Cancel") && !item.getStatus().equals("End")) {%>
	                    <div class="form-group col-md-10">
	                        <button type="button" class="btn btn-danger" onclick="addPrice()">立即加價</button>
	                    </div>
	                    <%} %>
	                    <%if(item.getStatus().equals("Result") && !item.getStatus().equals("UnderTheShelf") && !item.getStatus().equals("Cancel") && !item.getStatus().equals("End")){ %>
		                    <div class="form-group col-md-10">
		                        <p class="outOfNumber">＊ 已結標</p>
		                        <p class="outOfNumber">＊ 結標價錢： $<%=item.getPriceNow() %></p>
		                    </div>
	                    <%} %>
	
	
	                </div>
	
	            </form>
	        </div>
	        <div id="singleBiddingProduct_content_bottom" class="left">
	            <nav class="nav nav-tabs" id="myTab" role="tablist">
	                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">商品詳細說明</a>
	                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">問與答</a>
	            </nav>
	            <div class="tab-content" id="nav-tabContent" style="margin-top: 1rem;">
	                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	                    <%=item.getDescription() %>
	                </div>
	                <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
	                   <ul id="faq_list">
		                	<li>
			                    <div>
			                    	<p>問： ?????</p>
			                    	<p>答： ?????</p>
			                    </div>
			                    <div>問答人</div>
		                    </li>
		                    <li>
			                    <div>
			                    	<p>問： ?????</p>
			                    	<p>答： ?????</p>
			                    </div>
			                    <div>問答人</div>
		                    </li>
	                    </ul>
	                    <form id="faqForm" name="faqForm">
	                    <div class="form-group col-md-8 center">
	                    		<label for="buyer_area">我要提問</label>
	                			<textarea name="buyer_area" class="form-control" id="buyer_area" rows="5"></textarea>
	                    </div>
	                    <div class="col-md-6 text_center center" style="margin-top: 10px;">
			                <button type="submit" class="btn btn-primary ">確認送出</button>
			            </div>
			            </form>
	                </div>
	            </div>
	        </div>
	
	    </div>
	
	
	
	
	</div>




    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>