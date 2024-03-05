package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<String> arrayCal = new ArrayList<>(Arrays.asList(heading.split(" ")));
		int toReturn = 0;
		try {
			arrayCal = removeMulDiv(arrayCal);
			toReturn = cal(arrayCal);
		} catch (Exception e) {
			toReturn = Integer.MIN_VALUE;
		}
		request.setAttribute("result", toReturn);	
		doGet(request, response);
	}
	
	public List<String> removeMulDiv(List<String> str) {
		while (true) {
			if (str.contains("*")) {
				// idx = str.index("*")
				int idx = str.indexOf("*");
				
				// tmp = calculate(str[idx-1:idx+2])
				int tmp = calculate(str.subList(idx - 1, idx + 2));
				
				str.remove(idx - 1); // str.pop(idx-1)
				str.remove(idx - 1); // str.pop(idx-1)
				
				// str[idx - 1] = tmp
				str.set(idx - 1, Integer.toString(tmp));
				
			} else if (str.contains("/")) {
				// idx = str.index("*")
				int idx = str.indexOf("/"); 
				
				// tmp = calculate(str[idx-1:idx+2])
				int tmp = calculate(str.subList(idx - 1, idx + 2)); 
				
				str.remove(idx - 1); // str.pop(idx-1)
				str.remove(idx - 1); // str.pop(idx-1)
				
				// str[idx - 1] = tmp
				str.set(idx - 1, Integer.toString(tmp));
			} else {
				break;
			}
		}
		return str;
	}
	
	public int cal(List<String> str) {
		int N = str.size();
		for (int i = 0; i < N - 2; i += 2) {
			// str[i+2] = calculate(str[i:i+3])
			str.set(i + 2, Integer.toString(calculate(str.subList(i, i + 3))));
		}
		// return str[N-1]
		return Integer.parseInt(str.get(N - 1)); // Return the last index
	}
	
	public int calculate(List<String> str) {
		assert(str.size() == 3);
		if (str.get(1).equals("+")) {
			return Integer.parseInt(str.get(0)) + Integer.parseInt(str.get(2));
		} else if (str.get(1).equals("-")) {
			return Integer.parseInt(str.get(0)) - Integer.parseInt(str.get(2));
		} else if (str.get(1).equals("*")) {
			return Integer.parseInt(str.get(0)) * Integer.parseInt(str.get(2));
		} else {
			return Integer.parseInt(str.get(0)) / Integer.parseInt(str.get(2));
		}
	}

}
