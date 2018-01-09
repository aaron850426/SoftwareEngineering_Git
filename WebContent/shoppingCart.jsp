<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="buyer.*" %>
<%@ page import="discount.*" %>
<%@ page import="java.util.*" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<%

	String userId = (String)session.getAttribute("userId");
	
	ArrayList<shoppingCartItem> shoppingCartItems = buyer.getInfoShoppingCart(userId);
	
	ArrayList<discountItem> discountItems = discount.getInfoDiscount(userId);

%>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
	<script>
		function deleteShoppingCartItem(shoppingCartId,deleteUserId){
			document.shoppingCartForm.deleteShoppingCartId.value = shoppingCartId;
			document.shoppingCartForm.deleteUserId.value = deleteUserId;
			document.shoppingCartForm.action = "deleteShoppingCartServlet";
			document.shoppingCartForm.submit();
			
			alert("刪除購物車物品");
		}
		function checkout(userId){
			
			$.ajax({
	            url: "useDiscountServlet",
				type: "POST",
	            data: "userId=" + userId + "&discountId=" + $('#discountId').val(),
	            success : function(response){

                    //用彈出視窗顯示Ajax的回傳值
                    console.log("優惠券更新成功");

                },
                error : function(response){
                	//alert( response.responseText );
                	console.log("優惠券更新失敗");
                }
	            
	        });
			
			document.shoppingCartForm.action = "checkoutServlet";
			document.shoppingCartForm.submit();
		}
		
		function changeDiscount(discountId, discountPrice, totalPrice) {
			document.shoppingCartForm.discountId.value = discountId;
			document.shoppingCartForm.discountPrice.value = discountPrice;
			document.shoppingCartForm.totalPrice.value = Math.round(totalPrice * discountPrice);
			document.getElementById("total").innerHTML = "<s>$ " + totalPrice + "</s>　<span style='color:red;'>$ " + Math.round(totalPrice * discountPrice) + "</span>";
		}
	</script>
	
    <!-- 內容區 -->
    <div id="shoppingCart_content" class="content">
    <div class="topic">購物車</div>
    <div class="content_container">
        <form id="shoppingCartForm" name="shoppingCartForm" class="center" action="">
        	
        	<!-- 儲存要刪除的購物車品項資訊 -->
        	<input type="hidden" name="deleteShoppingCartId" id="deleteShoppingCartId">
        	<input type="hidden" name="deleteUserId" id="deleteUserId">
        	
        	<!-- 優惠券資訊 -->
        	<input type="hidden" name="discountId" id="discountId">
        	<input type="hidden" name="discountPrice" id="discountPrice">
        
        
            <div class="content_list center">
                <!-- 標題class用list_head，商品用list_list -->
                <div class="css_tr list_head">
                    <div class="css_hd list_img">&nbsp;</div>
                    <div class="css_hd list_title">商品名稱</div>
                    <div class="css_hd list_number">數量</div>
                    <div class="css_hd list_price">單價</div>
                    <div class="css_hd list_total">小計</div>
                    <div class="css_hd list_delete">刪除</div>
                </div>
                
               	<%
               	int number = 0;
               	int totalPrice = 0;
               	if(shoppingCartItems.size() > 0){
               		for( int i = 0; i < shoppingCartItems.size(); i++){
               			shoppingCartItem item = shoppingCartItems.get(i);
               			number += item.getNumberOfProduct();
               			totalPrice += item.getTotal();
               	%>
	               		<div class="css_tr list_list">
		                   <!-- 按名稱連結到單一商品頁面 -->
		                   <div class="css_hd list_img"><img src="./images/content-01.jpg"></div>
		                   <div class="css_hd list_title"><a href="singleAuctionProduct.jsp?auctionId=<%=item.getAuctionId() %>" title="<%=item.getAuctionName() %>"><%=item.getAuctionName() %></a></div>
		                   <div class="css_hd list_number"><%=item.getNumberOfProduct() %></div>
		                   <div class="css_hd list_price"><%=item.getPrice() %></div>
		                   <div class="css_hd list_total"><%=item.getTotal() %></div>
		                   <div class="css_hd list_delete">
		                       <!-- 網址要存購物車ID -->
		                       <a href="javascript:void(0);" onclick="deleteShoppingCartItem('<%=item.getShoppingCartId()%>','<%=item.getUserId()%>')" title="刪除"><img src="./images/trashCan.png"></a>
		                   </div>
		               	</div>
               	<%	}
               	} 
               	%>
                
            </div>
            <%if(totalPrice > 0 && discountItems.size() > 0) {%>
            <div class="content_list center">
               <!-- 標題class用list_head，商品用list_list -->
               <div class="css_tr list_head">
               	<div class="css_hd list_number"></div>
                   <div class="css_hd list_title">優惠券名稱</div>
                   <div class="css_hd list_status">狀態</div>
                   <!-- 未使用、已使用-->
               </div>
               <%
               	for(int i = 0; i < discountItems.size(); i++){
               		discountItem item = discountItems.get(i);
               		couponItem couponItemNow = new couponItem();
               		couponItemNow = discount.getInfoCoupon(item.getCouponId());
               %>		
               		<div class="css_tr list_list">
               			<div class="css_hd list_number">
               				<%if(item.getStatus().equals("UnUsed")) {%>
               				<input type="radio" name="discount" onclick="changeDiscount(<%=item.getDiscountId()%>,<%=couponItemNow.getDiscountPrice()%>,<%=totalPrice%>)">
               				<%} %>
               			</div>
	                    <div class="css_hd list_title"><%= couponItemNow.getName() %></div>
	                    <div class="css_hd list_status">
	                    <%
	                      if(item.getStatus().equals("Used")) 
	                          out.println("已使用");
	                      else if(item.getStatus().equals("UnUsed"))
	                          out.println("<span style='color: #bd2130;'>未使用</span>");
                         %>
	                    </div>
                	</div>
                <% }
               %>                
        	</div>
        	<%} %>
            
            <div class="content_settlement center text_center">
                <div class="col-md-6 center">
                    小計金額 (共 <%=number %> 件)
                </div>
                <div id="total" class="col-md-6 center content_settlement_price">
                    $ <%=totalPrice %>
                </div>
                
                <!-- 存總共價錢 -->
                <input type="hidden" name="totalPrice" id="totalPrice" value="<%=totalPrice%>">
                
                <%if(totalPrice > 0) {%>
                <div class="col-md-6 center">
                    <input type="button" class="btn btn-primary" value="結帳" onclick="checkout('<%=userId%>')">
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