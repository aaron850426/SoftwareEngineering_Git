<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ page import="buyer.*" %>
<%@ page import="search.*" %>
<%@ page import="java.util.*" %>
<%
	//把商品更改成熱銷商品(目前訂為銷售超過10個之商品)
	final int hotNum = 10;
	buyer.updateHot( hotNum );
	
	
	String intpage1 = (String) request.getParameter("page");
	if (intpage1 == null || intpage1.equals("null") || intpage1.equals("") || !intpage1.matches("[0-9]*"))
	    intpage1 = "1";
	int intpage = Integer.parseInt(intpage1);
	
	// 每頁顯示的資料量
	int pagesize = 12;
	
	// 資料從第幾筆到第幾筆
	int startpage = (intpage - 1) * pagesize + 1;
    int endpage = startpage + pagesize - 1;
	
	ArrayList<auctionItem> hotItems = search.searchAuctions("", "", startpage, endpage, "True");
	
	// 所有資料的量
	int rcount = search.searchAuctionsForPage("","","True");

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
	
	
	
%>