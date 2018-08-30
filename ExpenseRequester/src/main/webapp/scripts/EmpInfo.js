/**
 * 
 */

function EmployeeInfo(xhr){
	var res = JSON.parse(xhr.responseText);
	var ei = document.getElementById("employeename");
	var e = document.getElementById("employeelog");
	ei.innerText = "Welcome " + res.email;
	e.innerText = "Logged in as " + res.fname + " " + res.lname;
	
}

function EmployeeSettingsInfo(xhr){
	var res = JSON.parse(xhr.responseText);
	var ei = document.getElementById("employeename");
	ei.innerText = "Settings for " + res.fname + " "+ res.lname;
}
function onclickSettings(){
	AjaxGet("settings",function(xhr){
		var b = document.getElementsByTagName("html")[0];
		//console.log(xhr.responseText)
		b.innerHTML = xhr.responseText;
		AjaxGet("employeeinfo",EmployeeSettingsInfo);
	});
}
AjaxGet("employeeinfo",EmployeeInfo);