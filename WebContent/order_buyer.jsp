<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="buyer.*" %>
<%@ page import="java.util.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<%
	String buyerId = (String)session.getAttribute("userId");
	ArrayList<orderItem> orderItems = buyer.getOrder(buyerId);
	
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
	
	<script>
		function cancelOrder()
	    {
	        // 判斷checkbox有沒有被勾選
	        if($("input[name='checkbox']:checked").length == 0){
	            alert("Please choice the CheckBox.")
	            return;
	        }
	        else{
	            document.order_buyerForm.action = "cancelOrderServlet";
	            document.order_buyerForm.submit();
	        }
	
	    }
		
	</script>
	
    <!-- 內容區 -->
	<div id="order_buyer_content" class="content">
	    <div class="topic">商品訂單(買家)</div>
	    <div class="content_container">
	        <!-- 這邊form表單傳遞參數你們要自己寫，看要怎麼把所有商品的參數都傳過去 -->
	        <form id="order_buyerForm" name="order_buyerForm" class="center">
	        
	        	<input type="hidden" name="category" value="buyer">
	        	
	            <div class="content_list center">
	                <!-- 標題class用list_head，商品用list_list -->
	                <div class="css_tr list_head">
	                    <div class="css_hd list_number"></div>
	                    <div class="css_hd list_title">訂單內容</div>
	                    <div class="css_hd list_total">總價</div>
	                    <div class="css_hd list_status">狀態</div>
	                </div>
	                <%
	    			for( int i = 0; i < orderItems.size(); i++) {
	    				orderItem item = orderItems.get(i);
	        		%>
	                <div class="css_tr list_list">
	                    <!-- 按圖片連結到單一商品頁面 -->
	                    <div class="css_hd list_number text_center vertical_middle">
	                        <!-- value放訂單編號 -->
	                        <%if(!item.getStatus().equals("End") && !item.getStatus().equals("Cancel")){ %>
	                        <input name='checkbox' type="checkbox" value="<%=item.getOrderId()%>">
	                        <%} %>
	                    </div>
	                    <div class="css_hd list_title"><%=item.getDescription()%></div>
	                    <div class="css_hd list_total"><%=item.getTotalPrice()%></div>
	                    <div class="css_hd list_status">
	                    <%
	                   		String status = item.getStatus();
	                   		if (status.equals("Ing")){
	                   			out.println("進行中");
	                   		}
	                   		else if (status.equals("Move")) {
	                   			out.println("移交中");
	                   		}
	                   		else if (status.equals("UnderTheShelf")) {
	                   			out.println("已下架");
	                   		}
	                   		else if (status.equals("Cancel")){
	                   			out.println("取消交易");
	                   		}
	                   		else if (status.equals("End")){
	                   			out.println("完成交易");
                   			}
	               		%>
	                    </div>
	                </div>
	                <%} %>
	            </div>
	            <div class="content_settlement center text_center">
	                <div class="col-md-6 center">
	                    <button type="button" class="btn btn-primary" onclick="cancelOrder()">取消訂單</button>
	                </div>
	            </div>
	        </form>
	
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>