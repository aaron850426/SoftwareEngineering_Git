<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-TW">
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AS拍</title>

	<%@ include file="include/css_js.jsp" %>

</head>

<!-- 要記得寫jstl的error回傳(insert fail , 值重複) -->
<!-- 需寫判斷直購價是否為為起始價+加價區間倍數 -->

<body>
	<!-- 上方選單 -->
	<jsp:include page="include/topLink.jsp" flush="true" />
	
	
	<script>
		var isPass = true;
        var isCheckImageType = true;  //是否檢查圖片副檔名
        var isCheckPriceMinMoreThanPriceMax = true; // 是否檢查起始價大於直購價
        var isCheckMultiple = true; // 是否檢查直購價為起始價+加價區間倍數
        var errorMessage = ""; // 錯誤訊息

        // Math.round(a); 四捨五入
        function check(form)
        {
            var f = form;
            
            // 檢查圖檔副檔名
            var re = /\.(png|jpg|gif)$/i;  //允許的圖片副檔名
            if ( isCheckImageType && !re.test(f.pic.value) ) {
            	errorMessage += "＊只允許上傳PNG,JPG或GIF影像檔\n"
            	isPass = false;
                //alert("只允許上傳PNG,JPG或GIF影像檔");
                //return;
            }
            // 檢查起始價是否大於直購價
            var priceMin = f.priceMin.value;
            var priceMax = f.priceMax.value;
            var priceIncrease = f.priceIncrease.value;
            if( isCheckPriceMinMoreThanPriceMax && (priceMin > priceMax) ) {
            	errorMessage += "＊起始價不可大於直購價\n";
            	isPass = false;
            }
            // 檢查直購價是否為起始價+加價區間倍數
            var re2 = /^[0-9]*[1-9][0-9]*$/; // 是否為整數
            if( isCheckMultiple && !re2.test( (priceMax-priceMin)/priceIncrease ) ) {
            	errorMessage += "＊直購價不等於起始價+加價區間倍數\n";
            	isPass = false;
            }
            
            // 去掉錯誤訊息最後一個換行字元
            if( !errorMessage.replace(/(^s*)|(s*$)/g, "") == 0) {
            	errorMessage = errorMessage.Substring(0, errorMessage.Length - 2 );
            }
            
            // 是否有錯誤訊息
            if(!isPass) {
            	alert( errorMessage );
            	return;
            } else {
            	form.action = "publishBiddingProductServlet";
                form.submit();
            }
            
        }



        /*
        //這裡控制要檢查的項目，true表示要檢查，false表示不檢查
        var isCheckImageType = true;  //是否檢查圖片副檔名
        var isCheckImageWidth = true;  //是否檢查圖片寬度
        var isCheckImageHeight = true;  //是否檢查圖片高度
        var isCheckImageSize = true;  //是否檢查圖片檔案大小

        var ImageSizeLimit = 100000;  //上傳上限，單位:byte
        var ImageWidthLimit = 1200;  //圖片寬度上限
        var ImageHeightLimit = 1000;  //圖片高度上限

        function checkFile() {
            var f = document.FileForm;
            var re = /\.(jpg|gif)$/i;  //允許的圖片副檔名
            if (isCheckImageType && !re.test(f.file1.value)) {
                alert("只允許上傳JPG或GIF影像檔");
            } else {
                var img = new Image();
                img.onload = checkImage;
                img.src = f.file1.value;
            }
        }
        function checkImage() {
            if (isCheckImageWidth && this.width > ImageWidthLimit) {
                showMessage('寬度','px',this.width,ImageWidthLimit);
            } else if (isCheckImageHeight && this.height > ImageHeightLimit) {
                showMessage('高度','px',this.height,ImageHeightLimit);
            } else if (isCheckImageSize && this.fileSize > ImageSizeLimit) {
                showMessage('檔案大小','kb',this.fileSize/1000,ImageSizeLimit/1000);
            } else {
                document.FileForm.submit();
            }
        }
        function showMessage(kind,unit,real,limit) {
            var msg = "您所選擇的圖片kind為 real unit\n超過了上傳上限 limit unit\n不允許上傳！"
            alert(msg.replace(/kind/,kind).replace(/unit/g,unit).replace(/real/,real).replace(/limit/,limit));
        }
        * */
    </script>
	
    <!-- 內容區 -->
    <div id="publishBiddingProduct_content" class="content">
	    <div class="topic">刊登競標商品</div>
	    <div class="content_container">
	        <form id="publishBiddingProductForm" name="publishBiddingProductForm" class="center" onsubmit="check(this)">
	
	            <div class="form-group col-md-6">
	                <label class="col-form-label" for="title">商品標題</label>
	                <input type="text" class="form-control" id="title" name="title" placeholder="輸入商品標題" required>
	            </div>
	            <div class="form-group col-md-6">
	                <label class="col-form-label" for="priceMin">商品開始價格</label>
	                <input type="text" id="priceMin" name="priceMin" class="form-control" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="輸入商品起標價格" required>
	            </div>
	            <div class="form-group col-md-6">
	                <label class="col-form-label" for="priceMax">商品直購價格</label>
	                <input type="text" id="priceMax" name="priceMax" class="form-control" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="輸入商品直購價格" required>
	            </div>
	            <div class="form-group col-md-6">
	                <label class="col-form-label" for="priceIncrease">商品加價區間</label>
	                <input type="text" id="priceIncrease" name="priceIncrease" class="form-control" onkeyup="value=value.replace(/[^\d]/g,'')" placeholder="輸入商品加價區間" required>
	            </div>
	            <div class="form-group col-md-6">
	                <label class="col-form-label" for="endTime">截止時間</label>
	                <input type="date" id="endTime" name="endTime" class="form-control" required>
	            </div>
	            <div class="form-group col-md-6">
	                <label class="col-form-label" for="category">商品分類</label>
	                <select id="category" class="form-control" name="category">
	                    <option value="Digit" selected>3C數位</option>
                        <option value="Appliances">家電</option>
                        <option value="Food">食品</option>
                        <option value="Apparel">服飾</option>
                        <option value="MakeUps">美妝</option>
                        <option value="Life">生活</option>
	                </select>
	            </div>
	            <div class="form-group col-md-6">
	                <label for="pic">上傳圖片</label>
	                <input type="file" name="pic" class="form-control-file" id="pic" required>
	            </div>
	            <div class="form-group col-md-6">
	                <label for="description">商品說明</label>
	                <textarea name="description" class="form-control" id="description" rows="5"></textarea>
	            </div>
	            <div class="col-md-6 text_center">
	                <button type="submit" class="btn btn-primary ">確認送出</button>
	            </div>
	        </form>
	
	    </div>
	
	
	
	
	</div>





    <!-- 網站資訊 -->
    <%@ include file="include/footer.jsp" %>
</body>
</html>