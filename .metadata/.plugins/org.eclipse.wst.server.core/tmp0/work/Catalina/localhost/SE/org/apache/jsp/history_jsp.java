/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.23
 * Generated at: 2017-12-27 06:46:18 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class history_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\t<!-- 上方選單 -->\r\n");
      out.write("\t");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "include/topLink.jsp", out, true);
      out.write("\r\n");
      out.write("\t\r\n");
      out.write("    <!-- 內容區 -->\r\n");
      out.write("\t<div id=\"history_content\" class=\"content\">\r\n");
      out.write("\t    <div class=\"topic\">歷史紀錄(None)</div>\r\n");
      out.write("\t    <div class=\"content_container\">\r\n");
      out.write("\t            <div class=\"content_list center\">\r\n");
      out.write("\t                <!-- 標題class用list_head，商品用list_list -->\r\n");
      out.write("\t                <div class=\"css_tr list_head\">\r\n");
      out.write("\t                    <div class=\"css_hd list_img\">&nbsp;</div>\r\n");
      out.write("\t                    <div class=\"css_hd list_title\">商品名稱</div>\r\n");
      out.write("\t                    <div class=\"css_hd list_price\">單價</div>\r\n");
      out.write("\t                    <div class=\"css_hd list_delete\">刪除</div>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t                <div class=\"css_tr list_list\">\r\n");
      out.write("\t                    <!-- 按商品名稱連結到單一商品頁面 -->\r\n");
      out.write("\t                    <div class=\"css_hd list_img\"><img src=\"./images/content-01.jpg\"></div>\r\n");
      out.write("\t                    <div class=\"css_hd list_title\"><a href=\"#\" title=\"商品1\">商品1</a></div>\r\n");
      out.write("\t                    <div class=\"css_hd list_price\">100</div>\r\n");
      out.write("\t                    <div class=\"css_hd list_delete\">\r\n");
      out.write("\t                        <!-- 網址要存歷史紀錄ID -->\r\n");
      out.write("\t                        <a href=\"#\" title=\"刪除\"><img src=\"./images/trashCan.png\"></a>\r\n");
      out.write("\t                    </div>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t                <div class=\"css_tr list_list\">\r\n");
      out.write("\t                    <div class=\"css_hd list_img\"><img src=\"./images/content-01.jpg\"></div>\r\n");
      out.write("\t                    <div class=\"css_hd list_title\"><a href=\"#\" title=\"商品2\">商品2</a></div>\r\n");
      out.write("\t                    <div class=\"css_hd list_price\">200</div>\r\n");
      out.write("\t                    <div class=\"css_hd list_delete\">\r\n");
      out.write("\t                        <a href=\"#\" title=\"刪除\"><img src=\"./images/trashCan.png\"></a>\r\n");
      out.write("\t                    </div>\r\n");
      out.write("\t                </div>\r\n");
      out.write("\t            </div>\r\n");
      out.write("\t\r\n");
      out.write("\t    </div>\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\t</div>\r\n");
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