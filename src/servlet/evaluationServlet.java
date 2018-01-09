package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import buyer.*;

/**
 * Servlet implementation class evaluationServlet
 */
@WebServlet("/evaluationServlet")
public class evaluationServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("evaluationServlet Ing");
		String []evaluationIdAndIndex = request.getParameterValues("check");
		String []evaluationValue = request.getParameterValues("evaluation");
		
		for( int i = 0; i < evaluationIdAndIndex.length; i++ ){
			int evaluationId = Integer.parseInt(evaluationIdAndIndex[i].split(",")[0]);
			int evaluationIndex = Integer.parseInt(evaluationIdAndIndex[i].split(",")[1]);
			
			buyer.evalution(evaluationId, Integer.parseInt(evaluationValue[evaluationIndex]) );
		}
		
		response.sendRedirect("evaluation.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
