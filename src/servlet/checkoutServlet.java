package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.*;
import java.util.*;

import product.*;
import buyer.*;
import java.util.*;

/**
 * Servlet implementation class checkoutServlet
 */
@WebServlet("/checkoutServlet")
public class checkoutServlet extends HttpServlet {
	// 結束跳到訂單頁面
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkoutServlet Ing");
		
		String userId = (String)request.getSession().getAttribute("userId");
		int totalPrice = Integer.parseInt( request.getParameter("totalPrice") );
		ArrayList<shoppingCartItem> shoppingCartItems = buyer.getInfoShoppingCart(userId);
		
		
		
		buyer.addOrder(shoppingCartItems, totalPrice);
		buyer.addChechOutAuction(shoppingCartItems);
		buyer.deleteShoppingCart(shoppingCartItems);
		
		
		
		response.sendRedirect("order_buyer.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
