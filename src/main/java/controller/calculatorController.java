package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class calculatorController
 */
@WebServlet("/calculator")
public class calculatorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public calculatorController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "WEB-INF/views/calculatorView.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String heading = request.getParameter("label");
		System.out.println(heading);
		String[] arrayCal = heading.split(" ");
		int toReturn = 0;
		toReturn = calculateHelper(arrayCal);
//		try {
//			toReturn = calculate(arrayCal);
//			toReturn = calculateHelper(arrayCal);
//		} catch (Exception e) {
//			toReturn = Integer.MIN_VALUE;
//		}
		request.setAttribute("result", toReturn);	
		doGet(request, response);
	}
	
	public int calculateHelper(String[] str) {
		if (str.length == 3) {
			return calculate(str);
		}
		String[] slicedArray = new String[str.length - 2]; // なんたる使いにくい言語だ
		System.arraycopy(str, 0, slicedArray, 0, str.length - 2);
		return calculateHelper(slicedArray) + Integer.parseInt(str[str.length - 1]);
	}
	
	public int calculate(String[] str) {
		if (str[1].equals("+")) {
			return Integer.parseInt(str[0]) + Integer.parseInt(str[2]);
		} else if (str[1].equals("-")) {
			return Integer.parseInt(str[0]) - Integer.parseInt(str[2]);
		} else if (str[1].equals("*")) {
			return Integer.parseInt(str[0]) * Integer.parseInt(str[2]);
		} else {
			return Integer.parseInt(str[0]) / Integer.parseInt(str[2]);
		}
	}

}
