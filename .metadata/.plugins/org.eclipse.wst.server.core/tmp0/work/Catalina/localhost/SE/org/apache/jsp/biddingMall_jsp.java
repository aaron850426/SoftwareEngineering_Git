/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.23
 * Generated at: 2017-12-27 06:38:04 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import product.*;
import search.*;
import java.util.*;

public final class biddingMall_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/include/footer.jsp", Long.valueOf(1514356622538L));
    _jspx_dependants.put("/include/css_js.jsp", Long.valueOf(1514356622531L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("product");
    _jspx_imports_packages.add("search");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET POST or HEAD");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\r\n");
      out.write("<html lang=\"zh-TW\">\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>AS拍</title>\r\n");
      out.write("\r\n");
      out.write("\t");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!-- 掛載CSS樣式 -->\r\n");
      out.write("<link rel=\"stylesheet\" href=\"./css/bootstrap.min.css\">\r\n");
      out.write("<link rel=\"stylesheet\" href=\"./css/style.css\">\r\n");
      out.write("\r\n");
      out.write("<!-- 掛載JS樣式 -->\r\n");
      out.write("<script src=\"./js/jquery.min.js\"></script>\r\n");
      out.write("<script src=\"./js/popper.min.js\"></script>\r\n");
      out.write("<script src=\"./js/bootstrap.min.js\"></script>\r\n");
      out.write("<script src=\"./js/main.js\"></script>");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");

	String intpage1 = (String) request.getParameter("page");
	if (intpage1 == null || intpage1.equals("null") || intpage1.equals("") || !intpage1.matches("[0-9]*"))
	    intpage1 = "1";
	int intpage = Integer.parseInt(intpage1);
	
	// 每頁顯示的資料量
	int pagesize = 16;
	
	// 資料從第幾筆到第幾筆
	int startpage = (intpage - 1) * pagesize + 1;
	int endpage = startpage + pagesize - 1;
	
	ArrayList<biddingItem> biddingItems = new ArrayList<biddingItem>();
	
	biddingItems = search.searchBids("", "", startpage, endpage);
	
	
	
	// 所有資料的量
	int rcount = search.searchBidsForPage("","");
	
	//計算總頁數
	int maxpage = 0;
	int datasize = pagesize;
	if (datasize > rcount && rcount > 0)
	    datasize = rcount;
	
	//取餘數
	maxpage = rcount % datasize;
	if (maxpage == 0)
	    maxpage = rcount / datasize;
	else
	    maxpage = (rcount / datasize) + 1;
	
	;
	
	

      out.write("\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- 上方選單 -->\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/topLink.jsp", out, true);
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("\t<!-- 內容區 -->\r\n");
      out.write("\t<div id=\"biddingMall_content\" class=\"content\">\r\n");
      out.write("        <div class=\"topic\">競標商品</div>\r\n");
      out.write("        <div class=\"content_container\">\r\n");
      out.write("        \t<form id=\"biddingMallForm\" name=\"biddingMallForm\">\r\n");
      out.write("        \t\r\n");
      out.write("        \t\t<input type=\"hidden\" name=\"page\">\r\n");
      out.write("        \t\r\n");
      out.write("\t            <!-- 商品區塊 -->\r\n");
      out.write("\t            <div class=\"content_container00\">\r\n");
      out.write("\t\r\n");
      out.write("\t\t        ");

		        if( biddingItems.size() > 0 ) {
		        	for( int i = 0; i < biddingItems.size(); i++) {
		        		biddingItem item = biddingItems.get(i);
	    		
      out.write("\r\n");
      out.write("\t    \t\t\t\t<div class=\"product\">\r\n");
      out.write("\t\t        \t\t\t<a href=\"singleBiddingProduct.jsp?bidId=");
      out.print(item.getBidId());
      out.write("\">\r\n");
      out.write("\t\t\t\t                <img src=\"./images/content-01.jpg\">\r\n");
      out.write("\t\t\t\t                <p>\r\n");
      out.write("\t\t\t\t                    <span>");
      out.print(item.getName());
      out.write("</span><br>\r\n");
      out.write("\t\t\t\t                    <span>現在價錢: ");
      out.print(item.getPriceNow());
      out.write("</span>\r\n");
      out.write("\t\t\t\t                </p>\r\n");
      out.write("\t\t\t                </a>\r\n");
      out.write("\t\t            \t</div>\r\n");
      out.write("\t    \t\t");

		        	}
		        }
		        
      out.write("\r\n");
      out.write("\t            </div>\r\n");
      out.write("\t\r\n");
      out.write("\t            <!-- 頁數區塊 -->\r\n");
      out.write("\t\t        <script language=\"javascript\">\r\n");
      out.write("\t\t            function list(newpage) {\r\n");
      out.write("\t\t                document.biddingMallForm.page.value = newpage;\r\n");
      out.write("\t\t                document.biddingMallForm.submit();\r\n");
      out.write("\t\t            }\r\n");
      out.write("\t\t        </script>\r\n");
      out.write("\t\r\n");
      out.write("\t            <div class=\"page\">\r\n");
      out.write("\t                <ul>\r\n");
      out.write("\t                    <!-- 上一頁的class要加上 \"previousPage\" -->\r\n");
      out.write("\t                    ");
if( maxpage != 1 && intpage != 1){ 
      out.write("\r\n");
      out.write("\t                    <li><a href=\"javascript:list(");
      out.print(intpage-1 );
      out.write(")\" class=\"previousPage\" title=\"上一頁\"> ＜ </a></li>\r\n");
      out.write("\t                    ");
} 
      out.write("\r\n");
      out.write("\t                    <!-- 現在所在的頁數class要加上 \"focusPage\", 所有頁數的class加上 \"pageview\" -->\r\n");
      out.write("\t                    ");
for(int i = 1; i <= maxpage; i++){ 
	                    	if(i == intpage){
	                    
      out.write("\r\n");
      out.write("\t                    \t<li class=\"pageview\"><a href=\"javascript:list(");
      out.print(i);
      out.write(")\" class=\"focusPage\"> ");
      out.print(i);
      out.write(" </a></li>\r\n");
      out.write("\t                    ");
	
	                    	continue;
	                    	}
	                    
      out.write("\r\n");
      out.write("\t                    \t<li class=\"pageview\"><a href=\"javascript:list(");
      out.print(i);
      out.write(")\"> ");
      out.print(i);
      out.write(" </a></li>\r\n");
      out.write("\t                    ");
} 
      out.write("\r\n");
      out.write("\t                    <!-- 下一頁的class要加上 \"nextPage\" -->\r\n");
      out.write("\t                    ");
if( maxpage != 1 && intpage != maxpage){ 
      out.write("\r\n");
      out.write("\t                    <li><a href=\"javascript:list(");
      out.print(intpage+1 );
      out.write(")\" class=\"nextPage\" title=\"下一頁\"> ＞ </a></li>\r\n");
      out.write("\t                    ");
} 
      out.write("\r\n");
      out.write("\t                </ul>\r\n");
      out.write("\t            </div>\r\n");
      out.write("            </form>\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- 網站資訊 -->\r\n");
      out.write("    ");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<div class=\"left footer\">\r\n");
      out.write("\t 網站基本資訊\r\n");
      out.write("</div>");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
