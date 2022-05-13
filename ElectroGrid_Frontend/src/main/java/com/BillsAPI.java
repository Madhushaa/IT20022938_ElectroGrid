package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

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
		
		Map paras = getParasMap(request);
		
		String power_consumption = paras.get("power_consumption").toString();
		double power_usage =Double.parseDouble(power_consumption);
		Double amount = billObj.calculateAmount(power_usage);
		
		String output = billObj.updateBill(paras.get("hidBillIDSave").toString(),  
					paras.get("acc_number").toString(), 
					paras.get("name").toString(), 
					paras.get("month").toString(), 
					paras.get("power_consumption").toString(), 
					amount.toString(), 
					paras.get("date").toString());
		response.getWriter().write(output); 
		 
		
		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request); 
		 String output = billObj.deleteItem(paras.get("bill_id").toString()); 
		response.getWriter().write(output);
		
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close(); 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 {
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
		 } 
		return map; 
		}


}
