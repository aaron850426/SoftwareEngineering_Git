package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.*;

/**
 * Servlet implementation class checkOrderServlet
 */
@WebServlet("/checkOrderServlet")
public class checkOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("checkOrderServlet Ing");
		
		String []checkOrder = request.getParameterValues("checkbox");
		
		for( int i = 0; i < checkOrder.length; i++){
			seller.checkOrder( Integer.parseInt(checkOrder[i]) );
		}
		
		response.sendRedirect("order_seller.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
