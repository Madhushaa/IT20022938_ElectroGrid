<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%@page import="com.Bill"%>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Bill Management</title>
		<link rel="stylesheet" href="Views/bootstrap.min.css">
		<script src="Components/jquery-3.6.0.min.js"></script>
		<script src="Components/bills.js"></script>
	</head>
	<body> 
		<div class="container"  ><div class="row" ><div class="col-6"> 
			<h1 style="margin-left:122px"><b>Generate Bills</b></h1>
			<form id="formBill" name="formBill" method="post" action="bills.jsp" >
				 Account Number: 
				 <input id="acc_number" name="acc_number" type="text" 
				 class="form-control form-control-sm">
				 <br> Name: 
				 <input id="name" name="name" type="text" 
				 class="form-control form-control-sm">
				 <br> Month: 
				 <select id="month" name="month" type="text" 
				 class="form-control form-control-sm">
				 <option value=""  > Select a Month</option>
				 <option value="January" > January</option>
				 <option value="February"> February</option>
				 <option value="March"> March</option>
				 <option value="April"> April</option>
				 <option value="May"> May</option>
				 <option value="June"> June</option>
				 <option value="July"> July</option>
				 <option value="August"> August</option>
				 <option value="September"> September</option>
				 <option value="October"> October</option>
				 <option value="November"> November</option>
				 <option value="December"> December</option>
				 </select>
				 <br> Power Consumption: 
				 <input id="power_consumption" name="power_consumption" type="text" 
				 class="form-control form-control-sm">
				 <br> Date: 
				 <input id="date" name="date" type="date" 
				 class="form-control form-control-sm">
				
				 <br>
				 <input id="btnSave" name="btnSave" type="button" value="Save" 
				 class="btn btn-primary">
				 <br>
				 <input type="hidden" id="hidBillIDSave" 
				 name="hidBillIDSave" value="">
				 <br>
			</form>
			<div id="alertSuccess" class="alert alert-success"></div>
			<div id="alertError" class="alert alert-danger"></div>
			<br>
			<div id="divBillsGrid">
				 <%
				 Bill billObj = new Bill(); 
				 out.print(billObj.readBills()); 
				 %>
			</div>
			</div> </div> 
		</div> 
	</body>
</html>