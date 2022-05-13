package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BillsAPI
 */
@WebServlet("/BillsAPI")
public class BillsAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Bill billObj = new Bill();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillsAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String power_consumption = request.getParameter("power_consumption");
		double power_usage =Double.parseDouble(power_consumption);
		Double amount = billObj.calculateAmount(power_usage);
		//calculateAmount(power_consumption);
		String output = billObj.insertBill(request.getParameter("acc_number"), 
				 						   request.getParameter("name"), 
				 						   request.getParameter("month"),
										   request.getParameter("power_consumption"), 
										   amount.toString(), 
										   request.getParameter("date"));
	    response.getWriter().write(output);
	}
	

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
