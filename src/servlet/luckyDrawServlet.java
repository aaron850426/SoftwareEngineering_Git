package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import discount.*;
import java.io.PrintWriter;

/**
 * Servlet implementation class luckyDrawServlet
 */
@WebServlet("/luckyDrawServlet")
public class luckyDrawServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("luckyDrawServlet Ing");
		
		String userId = request.getParameter("userId");
		
		response.setContentType("text/html; charset=UTF-8");      
        response.setCharacterEncoding("Big5");
	     
	     response.getWriter().write(discount.luckyDraw(userId));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
