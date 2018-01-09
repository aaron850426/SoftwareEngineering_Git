package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import seller.seller;

/**
 * Servlet implementation class finishOrderServlet
 */
@WebServlet("/finishOrderServlet")
public class finishOrderServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("finishOrderServlet Ing");
		
		String buyerId = (String)request.getSession().getAttribute("userId");
		String []finishOrder = request.getParameterValues("checkbox");
		
		for( int i = 0; i < finishOrder.length; i++){
			seller.finishOrder( Integer.parseInt(finishOrder[i]) );
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
