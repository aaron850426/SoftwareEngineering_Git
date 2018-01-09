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

        function checkForm()
        {
            // 判斷checkbox有沒有被勾選
            if($("input[name='checkBox']:checked").length == 0){
                alert("Please choice the CheckBox.")
                return;
            }
            else{
            	document.biddingProductOverview_sellerForm.action = "underTheShelfServlet";
            	document.biddingProductOverview_sellerForm.submit();
            }

        }
        
        function checkForm2()
        {
            // 判斷checkbox有沒有被勾選
            if($("input[name='checkBox']:checked").length == 0){
                alert("Please choice the CheckBox.")
                return;
            }
            else{
            	document.biddingProductOverview_sellerForm.action = "resultServlet";
            	document.biddingProductOverview_sellerForm.submit();
            }

        }

    </script>
	
</head>

<%
	String sellerId = (String)session.getAttribute("userId");;
	
	ArrayList<biddingItem> biddingItems = new ArrayList<biddingItem>();
	
	biddingItems = record.searchBiddingBySellerId(sellerId);
%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />

    <!-- 內容區 -->
    <div id="biddingProductOverview_seller_content" class="content">
	    <div class="topic">競標商品概況(我是賣家)</div>
	    <div class="content_container">
	        <!-- 這邊form表單傳遞參數你們要自己寫，看要怎麼把所有商品的參數都傳過去 -->
	        <form id="biddingProductOverview_sellerForm" name="biddingProductOverview_sellerForm" class="center">
	        
	        	<input type="hidden" name="category" value="bid">
	        	
	            <div class="content_list center">
	                <!-- 標題class用list_head，商品用list_list -->
	                <div class="css_tr list_head">
	                    <div class="css_hd list_number"></div>
	                    <div class="css_hd list_title">競標商品名稱</div>
	                    <div class="css_hd list_price">現在金額</div>
	                    <div class="css_hd list_price">直購價</div>
	                    <div class="css_hd list_status">狀態</div>
	                    <!-- 已結標、未結標、下架、取消交易、已移交 -->
	                </div>
	                
	                <%
	    			for( int i = 0; i < biddingItems.size(); i++) {
	    				biddingItem item = biddingItems.get(i);	
		        	%> 
	                <div class="css_tr list_list">
	                    <!-- 按圖片連結到單一商品頁面 -->
	                    <div class="css_hd list_number text_center vertical_middle">
	                        <!-- value放訂單編號 -->
	                        <%if(item.getStatus().equals("Ing")){ %>
	                        <input name='checkBox' type="checkbox" value=<%=item.getBidId()%> >
	                        <%} %>
	                    </div>
	                    <div class="css_hd list_title"><a href="singleBiddingProduct.jsp?bidId=<%=item.getBidId()%>"
	                    	title="<%=item.getBidId()%>"><%=item.getName()%></a></div>
	                    <div class="css_hd list_price"><%=item.getPriceNow()%></div>
	                    <div class="css_hd list_price"><%=item.getPriceMax()%></div>
	                    <div class="css_hd list_status">
						<%
	                   		String status = item.getStatus();
	                   		if (status.equals("Ing")){
	                   			out.println("進行中");
	                   		}
	                   		else if (status.equals("Result")) {
	                   			out.println("已結標");
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
	                <%}%>
				</div>
	            <div class="content_settlement center text_center">
	                <div class="col-md-6 center">
	                    <button type="button" class="btn btn-primary" onclick="checkForm()">下架商品</button>
	                    <button type="button" class="btn btn-primary" onclick="checkForm2()">商品結標</button>
	                </div>
	            </div>
	        </form>
	    </div>
	</div>

    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>