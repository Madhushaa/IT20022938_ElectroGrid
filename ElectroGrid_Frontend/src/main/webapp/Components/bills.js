$(document).ready(function(){
	$("#alertSuccess").hide();
	$("#alertError").hide();
});

$(document).on("click", "#btnSave", function(event)
{ 
	// Clear alerts---------------------
	$("#alertSuccess").text(""); 
	$("#alertSuccess").hide(); 
	$("#alertError").text(""); 
	$("#alertError").hide();
	
	// Form validation-------------------
	var status = validateBillForm(); 
	if (status != true) 
	{
		$("#alertError").text(status); 
		$("#alertError").show(); 
		return; 
	}  
	 
	// If valid------------------------
	var type = ($("#hidBillIDSave").val() == "") ? "POST" : "PUT"; 
	$.ajax(
		{
			url : "BillsAPI", 
		 	type : type, 
		 	data : $("#formBill").serialize(), 
		 	dataType : "text", 
		 	complete : function(response, status) 
		 	{ 
		 		onBillSaveComplete(response.responseText, status);
		 	}
		 }); 
});


function onBillSaveComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully saved."); 
		 	$("#alertSuccess").show(); 
		 	$("#divBillsGrid").html(resultSet.data);
		}else if (resultSet.status.trim() == "error")
		{
			$("#alertError").text(resultSet.data); 
		 	$("#alertError").show();
		}
	}else if (status == "error")
	{
		$("#alertError").text("Error while saving...."); 
		$("#alertError").show();
	}else
	{
		$("#alertError").text("Unknown error while saving.."); 
		$("#alertError").show();
	}
	
	$("#hidBillIDSave").val(""); 
	$("#formBill")[0].reset(); 
}


// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
	$("#hidBillIDSave").val($(this).data("bill_id"));
	$("#acc_number").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#name").val($(this).closest("tr").find('td:eq(2)').text()); 
	$("#month").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#power_consumption").val($(this).closest("tr").find('td:eq(4)').text());
	$("#date").val($(this).closest("tr").find('td:eq(6)').text()); 
	
});
		
		
// REMOVE==========================================
$(document).on("click", ".btnRemove", function(event)
{
	$.ajax(
		{
			url : "BillsAPI", 
		 	type : "DELETE", 
		 	data : "bill_id=" + $(this).data("bill_id"),
		 	dataType : "text", 
		 	complete : function(response, status)
		 	{
				onBillDeleteComplete(response.responseText, status);
			} 
		 }); 
});
	
		
function onBillDeleteComplete(response, status)
{
	if (status == "success")
	{
		var resultSet = JSON.parse(response); 
		if (resultSet.status.trim() == "success") 
		{
			$("#alertSuccess").text("Successfully deleted."); 
			$("#alertSuccess").show(); 
			$("#divBillsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") 
		{
			$("#alertError").text(resultSet.data); 
			$("#alertError").show();
		}
	} else if (status == "error") 
	{ 
		$("#alertError").text("Error while deleting."); 
		$("#alertError").show(); 
	} else
	{ 
		$("#alertError").text("Unknown error while deleting.."); 
		$("#alertError").show(); 
	} 
}


// CLIENT-MODEL================================================================
function validateBillForm()
{
	// Account Number
	if ($("#acc_number").val().trim() == "")
	{
		return "Insert Account Number.";
	}
	
	//Name
	if ($("#name").val().trim() == "")
	{
		return "Insert Name.";
	}

	// Month
	if ($("#month").val().trim() == "")
	{
		return "Insert Month.";
	}
	
	// Power Consumption
	if ($("#power_consumption").val().trim() == "")
	{
		return "Insert Power Consumption.";
	}
	
	// is numerical value
	var power_consumption = $("#power_consumption").val().trim();
	if (!$.isNumeric(power_consumption))
	{
		return "Insert a numerical value for power_consumption.";
	}


	//Date
	if ($("#date").val().trim() == "")
	{
		return "Insert Date.";
	}
	
	return true;
}