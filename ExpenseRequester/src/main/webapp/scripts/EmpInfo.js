/**
 * 
 */

function EmployeeInfo(xhr){
	var res = JSON.parse(xhr.responseText);
	var ei = document.getElementById("employeename");
	ei.innerText = "Welcome " + res.email;
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