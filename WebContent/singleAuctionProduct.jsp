<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="search.*" %>
<%@ page import="buyer.*" %>
<%@ page import="java.util.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>
	
	<script>
		function buy(){
			document.singleAuctionProductForm.action = "buyServlet";
			document.singleAuctionProductForm.submit();
		}
	
		function addShoppingCart(){
			document.singleAuctionProductForm.action = "addShoppingCartServlet";
			document.singleAuctionProductForm.submit();
			alert("加入購物車");
		}
	</script>
	
</head>

<%

	String userAccount = (String)session.getAttribute("userId");
	int auctionId = Integer.parseInt(request.getParameter("auctionId"));
	
	auctionItem item = search.searchSingleAuction(auctionId);
	ArrayList<evaluationItem> evaluationItems = buyer.getProductEvaluation(auctionId); 

%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 數量歸0要秀出已售完，立即購買、加入購物車Button關閉點擊 -->
	<!-- 內容區 -->
	<div id="singleAuctionProduct_content" class="content">
	    <div class="content_container">
	        <div id="singleAuctionProduct_content_top" class="left">
	            <form id="singleAuctionProductForm" name="singleAuctionProductForm" class="center">
					
					<input type="hidden" name="auctionId" value="<%=item.getAuctionId()%>">
	                <input type="hidden" name="auctionName" value="<%=item.getName()%>">
	                <input type="hidden" name="productPrice" value="<%=item.getPrice()%>">
	                <input type="hidden" name="sellerId" value="<%=item.getSellerId()%>">
	
	                <div class="single_img left"><img src="./images/content-01.jpg"></div>
	                <div class="single_field left">
	                	<div class="form-group col-md-10">
	                        <p>販售人：<a href="memberProfile.jsp?userId=<%=item.getSellerId()%>" title="<%=item.getSellerId() %>"><%=item.getSellerId() %></a></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>商品：<%=item.getName() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>優惠價： $<%=item.getPrice() %></p>
	                    </div>
	                    <div class="form-group col-md-10">
	                        <p>剩餘數量： <%=item.getNumberOfProduct() %></p>
	                    </div>
	                    <% if(userAccount != null && !userAccount.equals("null") && !userAccount.equals("") && !userAccount.equals(item.getSellerId()) && !item.getStatus().equals("UnderTheShelf") && !item.getStatus().equals("End")) {%>
	                    <div class="form-group  col-md-6">
	                        <label for="amount">購買數量</label>
	                        <select class="form-control" id="amount" name="amount">
	                        	<%if(item.getNumberOfProduct() > 10){ %>
		                            <option value="1">1</option>
		                            <option value="2">2</option>
		                            <option value="3">3</option>
		                            <option value="4">4</option>
		                            <option value="5">5</option>
		                            <option value="6">6</option>
		                            <option value="7">7</option>
		                            <option value="8">8</option>
		                            <option value="9">9</option>
		                            <option value="10">10</option>
	                            <%}else {
	                            	for( int i = 0; i < item.getNumberOfProduct(); i++){
	                            %>
	                            	<option value="<%=i+1%>"><%=i+1%></option>
	                            <%
	                            	}
	                            }%>
	                        </select>
	                    </div>
	                    <%if(!item.getStatus().equals("UnderTheShelf") && !item.getStatus().equals("End")) %>
	                    <div class="form-group col-md-10">
	                        <button type="button" class="btn btn-warning" onclick="addShoppingCart()">加入購物車</button>
	                    </div>
	                    <%} %>
	                    <%if(item.getNumberOfProduct() <= 0){ %>
	                    <div class="form-group col-md-10">
	                        <p class="outOfNumber">＊ 已售完</p>
	                    </div>
	                    <%} %>
	                    <%if(item.getStatus().equals("UnderTheShelf")){ %>
	                    <div class="form-group col-md-10">
	                        <p class="underTheShelf">＊ 已下架</p>
	                    </div>
	                    <%} %>
	
	                </div>
	            </form>
	        </div>
	        <div id="singleAuctionProduct_content_bottom" class="left">
	            <nav class="nav nav-tabs" id="myTab" role="tablist">
	                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">商品詳細說明</a>
	                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">問與答</a>
	                <a class="nav-item nav-link" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">最新評價</a>
	            </nav>
	            <div class="tab-content" id="nav-tabContent" style="margin-top: 1rem;">
	            	<!-- 商品詳細說明 -->
	                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	                   <%=item.getDescription() %>
	                </div>
	                <!-- 問與答 -->
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
	                <!-- 最新評價 -->
	                <div class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
	                    <ul id="evaluation_list">
		                	<li>
			                    <div>
			                    	<p>評價</p>
			                    </div>
			                    <div><p>評價人</p></div>
		                    </li>
		                    <%
		                    for( int i = 0; i < evaluationItems.size(); i++){
		                    	evaluationItem evaluationitem = evaluationItems.get(i);
		                    %>
		                    <li>
			                    <div>
			                    	<p>
			                    	<%
			                    		if(evaluationitem.getEvaluation() == 1)
			                    			out.println("★");
			                    		else if(evaluationitem.getEvaluation() == 2)
			                    			out.println("★★");
			                    		else if(evaluationitem.getEvaluation() == 3)
			                    			out.println("★★★");
			                    		else if(evaluationitem.getEvaluation() == 4)
			                    			out.println("★★★★");
			                    		else if(evaluationitem.getEvaluation() == 5)
			                    			out.println("★★★★★");
			                    	%>
			                    	</p>
			                    </div>
			                    <div><p><%=evaluationitem.getBuyerId()%></p></div>
		                    </li>
		                    <%} %>
	                    </ul>
	                </div>
	            </div>
	        </div>
	
	    </div>
	
	
	
	
	</div>




    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>