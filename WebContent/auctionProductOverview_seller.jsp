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
	
	<script>

        function checkForm(form)
        {
            // 判斷checkbox有沒有被勾選
            if($("input[name='checkBox']:checked").length == 0){
                alert("Please choice the CheckBox.");
                return;
            }
            else{
            	form.action = "underTheShelfServlet";
            	form.submit();
            }

        }

    </script>
	
</head>

<%
	String sellerId = (String)session.getAttribute("userId");;
	
	ArrayList<auctionItem> auctionItems = new ArrayList<auctionItem>();
	
	auctionItems = record.searchAuctionBySellerId(sellerId);	
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="auctionProductOverview_seller_content" class="content">
    <div class="topic">拍賣商品概況(我是賣家)</div>
    <div class="content_container">
        <form id="auctionProductOverview_sellerForm" name="auctionProductOverview_sellerForm" class="center" onsubmit="checkForm(this);">
        
        	<input type="hidden" name="category" value="auction">
        	
            <div class="content_list center">
                <!-- 標題class用list_head，商品用list_list -->
                <div class="css_tr list_head">
                    <div class="css_hd list_number"></div>
                    <div class="css_hd list_title">拍賣商品名稱</div>
                    <div class="css_hd list_price">單價</div>
                    <div class="css_hd list_price">剩餘數量</div>
                    <div class="css_hd list_status">狀態</div>
                    <!-- 有庫存、無庫存 -->
                </div>
                
                <%
    			for( int i = 0; i < auctionItems.size(); i++) {
    				auctionItem item = auctionItems.get(i);	
	        	%>            	
	                <div class="css_tr list_list">
	                    <!-- 按圖片連結到單一商品頁面 -->
	                    <div class="css_hd list_number text_center vertical_middle">
	                    	<%if(!item.getStatus().equals("UnderTheShelf")) {%>
	                        <input name='checkBox' type="checkbox" value="<%=item.getAuctionId()%>">
	                        <%} %>
	                    </div>
	                    <div class="css_hd list_title"><a href="singleAuctionProduct.jsp?auctionId=<%=item.getAuctionId()%>"
	                    	title="<%=item.getName()%>"><%=item.getName()%></a></div>
	                    <div class="css_hd list_price"><%=item.getPrice()%></div>
	                    <div class="css_hd list_price"><%=item.getNumberOfProduct()%></div>
	                    <div class="css_hd list_status">
	                   	<%
	                   		String status = item.getStatus();
	                   		if (status.equals("Ing")){
	               		%>
	                   			有庫存
	               		<%	
	                		} else if (status.equals("End")) {		                    			
	               		%>
	           		                無庫存
	               		<%
	               			} else if (status.equals("UnderTheShelf")) {
	              		%>	
	              			下架中
	              		<%
	              			}
	             		%>
	               			
	                   	</div>
	                </div>
	        	<%}%>
                
                
            </div>
            <div class="content_settlement center text_center">
                <div class="col-md-6 center">
                    <button type="submit" class="btn btn-primary ">下架商品</button>
                </div>
            </div>
        </form>

    </div>




</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>