package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bill {
	
	//A common method to connect to the DB
	private Connection connect(){ 
		
		Connection con = null; 
		
		try{ 
				Class.forName("com.mysql.cj.jdbc.Driver"); 

				//DBServer/DBName, user name, password 
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrogrid_bill", "root", ""); 
		} 
		catch (Exception e) {
			e.printStackTrace();
			} 
		
		return con; 
	}
	
	
	//readBill method to View All bills
	public String readBills() 
	{ 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
	 if (con == null) 
	 { 
	 return "Error while connecting to the database for reading."; 
	 } 
	 
	 // Prepare the html table to be displayed
	 output = "<table border='1' class='table'>"
	 		+ "<tr><th>Bill ID</th>"
	 		+ "<th>Account Number</th><th>Name</th>"
	 		+ "<th>month</th>" 
	 		+ "<th>power consumption</th>"
	 		+ "<th>Total Amount</th>"
	 		+ "<th>Date</th>"
	 		+ "<th>Update</th>"
	 		+ "<th>Remove</th></tr>"; 
	
	 String query = "select * from bills"; 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String bill_id = Integer.toString(rs.getInt("bill_id")); 
	 String acc_number = rs.getString("acc_number"); 
	 String name = rs.getString("name"); 
	 String month = rs.getString("month"); 
	 String power_consumption = Double.toString(rs.getDouble("power_consumption")); 
	 String total_amount = Double.toString(rs.getDouble("total_amount")); 
	 String date = rs.getString("date"); 
	 
	 // Add into the html table
	 output += "<tr><td>"+bill_id+"</td>"; 
	 output += "<td>" + acc_number + "</td>"; 
	 output += "<td>" + name + "</td>"; 
	 output += "<td>" + month + "</td>"; 
	 output += "<td>" + power_consumption + "</td>";
	 output += "<td>" +"Rs." + total_amount + "</td>";
	 output += "<td>" + date + "</td>";
	 
	 // buttons
	 output += "<td><input name='btnUpdate' type='button' value='Update' "
			 + "class='btnUpdate btn btn-secondary' data-bill_id='" + bill_id + "'></td>"
			 + "<td><input name='btnRemove' type='button' value='Remove' "
			 + "class='btnRemove btn btn-danger' data-bill_id='" + bill_id + "'></td></tr>"; 
	 
	 } 
	 con.close();
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 
	catch (Exception e) 
	 { 
	 output = "Error while reading the Bills."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
	
	//insertBill function for insert bill
	public String insertBill(String acc_number, String name, String month, String power_consumption, String total_amount, String date){ 
		
		String output = ""; 
		
		try
		{ 
			Connection con = connect(); 
			
			if (con == null) 
			{
				return "Error while connecting to the database for inserting."; 
				
			} 
			
			// create a prepared statement
			String query = " insert into bills (`bill_id`,`acc_number`,`name`,`month`,`power_consumption`,`total_amount`,`date`)"+" values (?, ?, ?, ?, ?,?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, acc_number); 
			preparedStmt.setString(3, name); 
			preparedStmt.setString(4, month ); 
			preparedStmt.setString(5, power_consumption); 
			preparedStmt.setDouble(6, Double.parseDouble(total_amount)); 
			preparedStmt.setString(7, date); 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			
			String newBills = readBills(); 
			output = "{\"status\":\"success\",\"data\":\""+newBills+"\"}"; 
		} 
		
		catch (Exception e) 
		{ 
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Bill.\"}"; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	
	
	//method to calculate monthly total amount
	public Double calculateAmount(Double usage) {
		double total_amount;
		double amount1;
		double amount2;
		double amount3;
		double rate1 = 7;
		double rate2 = 10;
		
		
		if (usage <= 64 ) {
			
			total_amount = usage*rate1;	
		}	
			else if( 96 >= usage && usage> 64) {
				amount1= 64*rate1;
				amount2= (usage-64)*rate2;
				total_amount = amount1+amount2   ;
				
			}
				else {
					amount1= 64*7;
					amount2= 32*10;
					amount3 = (usage-97)*20 ;
					total_amount = amount1 + amount2 + amount3;
				}
		
		return total_amount;
	}
	
	
	
	//updateBill function for update bills
	public String updateBill(String bill_id, String acc_number, String name, String month, String power_consumption,String total_amount, String date ){ 
		
		String output = ""; 
		
		try{ 
				Connection con = connect(); 
				if (con == null){
					return "Error while connecting to the database for updating.";
				} 
				
				// create a prepared statement
				String query = "UPDATE bills SET acc_number=?,name=?,month=?,power_consumption=? ,total_amount=? ,date=?  WHERE bill_id=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				
				// binding values
				preparedStmt.setString(1, acc_number); 
				preparedStmt.setString(2, name); 
				preparedStmt.setString(3, month); 
				preparedStmt.setString(4, power_consumption);
				preparedStmt.setDouble(5, Double.parseDouble(total_amount));
				preparedStmt.setString(6, date);
				preparedStmt.setInt(7, Integer.parseInt(bill_id)); 
				
				// execute the statement
				preparedStmt.execute(); 
				
				con.close(); 
				
				String newBills = readBills(); 
				output = "{\"status\":\"success\",\"data\":\""+newBills+"\"}"; 

		}catch (Exception e){ 
			
			output = "{\"status\":\"error\",\"data\":\"Error while updating the Bill.\"}"; 
			System.err.println(e.getMessage()); 
			
		} 
		
		return output; 
	}
	
	
	//deleteBill function for delete bills
	public String deleteBill(String bill_id){ 
		
		String output = ""; 
		
		try{ 
			Connection con = connect(); 
			
			if (con == null){
				return "Error while connecting to the database for deleting."; 
			} 
			
			// create a prepared statement
			String query = "delete from bills where bill_id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(bill_id)); 
			
			// execute the statement
			preparedStmt.execute(); 
			
			con.close(); 
			
			String newBills = readBills(); 
			output = "{\"status\":\"success\",\"data\":\""+newBills+"\"}"; 

		}catch (Exception e){ 
			output = "{\"status\":\"error\",\"data\":\"Error while deleting the Bill.\"}";
			System.err.println(e.getMessage()); 
		} 
		return output; 
}


}
