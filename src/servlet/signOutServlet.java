package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class signOutServlet
 */
@WebServlet("/signOutServlet")
public class signOutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().removeAttribute("userId");
		
		//request.getRequestDispatcher("index.jsp").forward(request, response);
		
		// sendRedirect視為一個重新的請求,所以request傳進來的參數是不能共享的 (網址列會顯示index.jsp)
		response.sendRedirect("index.jsp");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
