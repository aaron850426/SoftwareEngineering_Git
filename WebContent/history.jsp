<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>


<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
    <!-- 內容區 -->
	<div id="history_content" class="content">
	    <div class="topic">歷史紀錄(None)</div>
	    <div class="content_container">
	            <div class="content_list center">
	                <!-- 標題class用list_head，商品用list_list -->
	                <div class="css_tr list_head">
	                    <div class="css_hd list_img">&nbsp;</div>
	                    <div class="css_hd list_title">商品名稱</div>
	                    <div class="css_hd list_price">單價</div>
	                    <div class="css_hd list_delete">刪除</div>
	                </div>
	                <div class="css_tr list_list">
	                    <!-- 按商品名稱連結到單一商品頁面 -->
	                    <div class="css_hd list_img"><img src="./images/content-01.jpg"></div>
	                    <div class="css_hd list_title"><a href="#" title="商品1">商品1</a></div>
	                    <div class="css_hd list_price">100</div>
	                    <div class="css_hd list_delete">
	                        <!-- 網址要存歷史紀錄ID -->
	                        <a href="#" title="刪除"><img src="./images/trashCan.png"></a>
	                    </div>
	                </div>
	                <div class="css_tr list_list">
	                    <div class="css_hd list_img"><img src="./images/content-01.jpg"></div>
	                    <div class="css_hd list_title"><a href="#" title="商品2">商品2</a></div>
	                    <div class="css_hd list_price">200</div>
	                    <div class="css_hd list_delete">
	                        <a href="#" title="刪除"><img src="./images/trashCan.png"></a>
	                    </div>
	                </div>
	            </div>
	
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>