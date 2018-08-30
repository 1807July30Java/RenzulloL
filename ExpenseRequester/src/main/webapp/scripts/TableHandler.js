
function addToTable(tablename,func,args,hover,lev){
  if(tablename){
	  var t = document.getElementById(tablename);
	  var tr = document.createElement("tr");
	  var last;
	  for(k in args){
		if(args[k]!= undefined && args[k]!= null){
			if(k==="employeeId"){
				last = k;
			}else{
				var td= document.createElement("td");
		    	var tx = document.createTextNode(args[k]);
		    	td.appendChild(tx);
		    	tr.appendChild(td);
			}
		}
  	}
	  if(last!=null){
		  var td= document.createElement("td");
		  var tx = document.createTextNode(args[last]);
		  td.appendChild(tx);
		  tr.appendChild(td);
	  }
	  tr.name = "unselected";
	  tr.onclick = func;
	  tr.onmouseenter = hover;
	  tr.onmouseleave = lev;
	  tr.classList.add("unselected");
	  tr.setAttribute("info",JSON.stringify(args));
	  t.appendChild(tr);
  }
};

function removeExpenseFromTable(tablename,expenseid){
	if(tablename && expenseid){
		var t = document.getElementById(tablename);
		var r = t.rows;
		var ret = {};
		for(i = 1; i < r.length; i ++){
			var rowInfo = JSON.parse(r[i].getAttribute("info"));
			if(rowInfo["expenseId"]==expenseid){
				t.deleteRow(i);
				return rowInfo;
			}
		}
	}
}

function addEmployeeToSelect(selection,func,Emp){
  if(selection){
	  var sel = document.getElementById(selection);
	  var op = document.createElement("option");
	  op.value = Emp.fname + " " + Emp.lname;
	  op.innerText = Emp.fname + " " + Emp.lname;
	  op.setAttribute("email",Emp.email);
	  op.setAttribute("info",JSON.stringify(Emp));
	  sel.onchange = func; 
	  if(sel.childElementCount < 1){
		  op.selected = true;
		  putEmployeeInfo(op);
		  AjaxGet("manage?" + Emp.email,PopulateSelectedEmployeeExpenses);
	  }
	  sel.add(op);
  }
};

function sel(x){
	    if(x.getAttribute("selected") === "true"){
	      x.setAttribute("selected","false");
	      x.setAttribute("recentlyselected","true");
	      x.setAttribute("hover","true");
	      x.classList.remove("selected");
	      x.classList.add("unselected");
	      x.classList.add("hover");
	    }
	    else{
	      x.setAttribute("selected","true");
	      x.classList.add("selected");
	      x.classList.remove("unselected")
	      //x.style = "background-color:#dc9bff";
	    }
	
};
function hov(x){
    if(x.getAttribute("hover") === "true"){
      x.setAttribute("hover","false");
      x.classList.remove("hover");
      x.classList.add("nothover");
    }
    else{
    	if(x.getAttribute("selected")!= "true" && x.getAttribute("recentlyselected")!="true"){
	      x.setAttribute("hover","true");
	      x.classList.add("hover");
	      x.classList.remove("nothover");	
    	}
    }

};
function leave(x){
	x.setAttribute("recentlyselected","false");
}
function getSelectedFromTable(tablename){
  var ret = [];
  var t = document.getElementById(tablename);
  var tr = t.rows;
  for(i = 1; i < tr.length;i++){
    if(tr[i].getAttribute("selected") === "true" && tr[i].parentElement.id === tablename){
      ret.push(tr[i]);
    }
  }
  return ret;
};

function AjaxGet(url, func){
  var xhr = new XMLHttpRequest();
  xhr.onreadystatechange = function (){
    if(this.readyState==4 && this.status == 200){
      func(this);
    }
  }
  xhr.open("GET",url,true);
  xhr.send();
}

function putEmployeeInfo(op){
	var info = JSON.parse(op.getAttribute("info"));
	var disp = document.getElementById("EmployeeInfoDisplay");
	disp.innerText = "Employee Id: " + info["employeeId"] + "    " + 
	"Name:" + info["fname"] + " "+
	info["lname"] + "               "+
	"Email: " +info["email"] + "    ";
}

function employeeOnSelect(){
	var sel = document.getElementById("EmployeeSelection");
	var exptbu = document.getElementById("EmployeeExpenseTableUA");
	var exptba = document.getElementById("EmployeeExpenseTableA");
	var exptbd = document.getElementById("EmployeeExpenseTableD");
	exptbu.innerHTML = "<tr><td>Approving Manager</td><td>Expense Date</td><td>Amount</td><td>Description</td><td>Expense Id</td></tr>";
	exptba.innerHTML = "<tr><td>Approving Manager</td><td>Expense Date</td><td>Amount</td><td>Description</td><td>Expense Id</td></tr>";
	exptbd.innerHTML = "<tr><td>Approving Manager</td><td>Expense Date</td><td>Amount</td><td>Description</td><td>Expense Id</td></tr>";
	//exptbu.innerHTML = "";
	//exptba.innerHTML = "";
	//exptbd.innerHTML = "";
	var emp  = sel.selectedOptions[0];
	putEmployeeInfo(emp)
	AjaxGet("manage?" + emp.getAttribute("email"),PopulateSelectedEmployeeExpenses);
}


function PopulateEmployees(xhr){
  var res = JSON.parse(xhr.responseText);
  res.forEach(function(element){
	  element.password = undefined;
	  element.managerId = undefined;
	  addEmployeeToSelect("EmployeeSelection",employeeOnSelect,element);
  }
  );
}
function PopulateExpenses(xhr){
	var res = JSON.parse(xhr.responseText);
	  res.forEach(function(element){
		  switch(element.approved){
		  	case 1:
			  	element.approved = null;
			  	element.employeeId = null;
			  	addToTable("ERtableA",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
			  	break;
		  	case 0:
				element.approved = null;
			  	element.employeeId = null;
			  	addToTable("ERtableUA",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
			  	break;
		  	case 2:
		  		element.approved = null;
			  	element.employeeId = null;
			  	addToTable("ERtableD",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
			  	break;
		  		
		  }
	  }
	  );
}
function PopulateSelectedEmployeeExpenses(xhr){
	var res = JSON.parse(xhr.responseText);//collection of expenses
	  res.forEach(function(element){
		  switch(element.approved){
		  	case 1:
			  	element.approved = null;
			  	element.employeeId = null;
			  	addToTable("EmployeeExpenseTableA",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
			  	break;
		  	case 0:
				element.approved = null;
			  	element.employeeId = null;
			  	addToTable("EmployeeExpenseTableUA",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
			  	break;
		  	case 2:
		  		element.approved = null;
			  	element.employeeId = null;
			  	addToTable("EmployeeExpenseTableD",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
			  	break;	
		  }
	  	}
	  );
}

function PopulateAllExpenses(xhr){
	var res = JSON.parse(xhr.responseText);
	res.forEach(function(element){
		if(element.approved == 1){
			element.approved = null
			addToTable("allexpensestableA",expenseOnSelect,element,expenseOnHover,expenseOnLeave);
		}else if(element.approved == 2){
			element.approved = null;
			addToTable("allexpensestableD",expenseOnSelect,element,expenseOnHover,expenseOnLeave)
		}
	});
}
function ShowAllExpenses(){
	AjaxGet("manage",PopulateAllExpenses);
}
function scrollShowAllExpenses(){
		var t = document.getElementById("allexpensesContainer");
	    if (Math.abs(document.body.scrollTop - t.offsetTop) < 510 && t.getAttribute("loaded") != "true") {
	    	t.setAttribute("loaded","true");
	        ShowAllExpenses();
	    }
}
function load(){
AjaxGet("manager",PopulateEmployees);
AjaxGet("expense",PopulateExpenses);
ShowAllExpenses();
};
window.onload = load;


