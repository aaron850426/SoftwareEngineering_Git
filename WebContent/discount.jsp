<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ page import="product.*" %>
<%@ page import="discount.*" %>
<%@ page import="java.util.*" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<%

	String userId = (String)session.getAttribute("userId");
	
	ArrayList<discountItem> discountItems = discount.getInfoDiscount(userId);

%>

	<script>
		function luckyDraw(userId){
			
			$.ajax({
	            url: "luckyDrawServlet",
				type: "POST",
	            data: "userId=" + userId,
	            async: false,
	            success : function(response){

                    //用彈出視窗顯示Ajax的回傳值
                    alert("恭喜獲得: " + response);
                    location.reload() 

                },
                error : function(response){
                	//alert( response.responseText );
                	alert("抽獎失敗，請重新嘗試。");
                }
	            
	        });
		}
		/*
		function readone(id, datatablename, filetablename, mserno, detailno, readnumbertable, language, subject, flag) {
	        $.ajax({
	            url: "ap/updatenumber_list.jsp",
	            type: "POST",
	            data: "menuid=" + id + "&datatablename=" + datatablename + "&filetablename=" + filetablename + "&dataserno=" + mserno + "&detailno=" + detailno + "&readnumbertable=" + readnumbertable + "&language=" + language + "&subject=" + subject + "&flag=" + flag
	        });
	    }*/
	</script>

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
    <!-- 內容區 -->
	<div id="discount_content" class="content">
	    <div class="topic">優惠券</div>
	    <div class="content_container">
	    	
	    	<div class="tab-content" id="nav-tabContent" style="margin-top: 1rem;">
	                <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
	                    <!-------- 現有之優惠券 -------->
	                    <form id="discountForm" name="discountForm" class="center">
				            <div class="content_list center">
				                <!-- 標題class用list_head，商品用list_list -->
				                <div class="css_tr list_head">
				                    <div class="css_hd list_title">優惠券名稱</div>
				                    <div class="css_hd list_status">狀態</div>
				                    <!-- 未使用、已使用-->
				                </div>
				                <%
				                if(discountItems.size() > 0){
				                	for(int i = 0; i < discountItems.size(); i++){
				                		discountItem item = discountItems.get(i);
				                		couponItem couponItemNow = new couponItem();
				                		couponItemNow = discount.getInfoCoupon(item.getCouponId());
				                %>		
				                		<div class="css_tr list_list">
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
				                }
				                %>                
				         	   </div>
				         	   
				
				        </form>
				        <div class="content_settlement center text_center">
			                <div class="col-md-6 center">
			                    <button class="btn btn-primary" onclick="luckyDraw('<%=userId%>')">抽優惠券</button>
			                </div>
			            </div>
				        
	                </div>
	            </div>
	
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>