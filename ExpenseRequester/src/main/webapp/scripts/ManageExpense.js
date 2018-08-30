/**
 * 
 */
const expidpos = 4;
function expenseOnSelect(){
	var expid = JSON.parse(this.getAttribute("info"))["expenseId"];
	var url = 'image?expid=' + expid;
	sel(this);
	if(this.getAttribute("selected") === "true"){
		showExpenseImageFromTable(this.parentElement.id,expid,url);
	}else{
		var imm = this.childNodes[5];
		var im;
		if(imm){
			im = imm.childNodes[0];
		}
		if(this.parentElement.id.substring("all")){
			imm = this.childNodes[6];
			if(imm){
				im = imm.childNodes[0];
			}
		}
		if(im){
			if(!(im.getAttribute("hover")=="true")){
				hideExpenseImageFromTable(this.parentElement.id,expid,url);
			}else{
				sel(this);
				im.setAttribute("hover",null);
			}
			
		}else{
			hideExpenseImageFromTable(this.parentElement.id,expid,url);
		}
	}
	
}
function expenseOnHover(){
	hov(this);
}
function expenseOnLeave(){
	leave(this);
	hov(this);
}
function ApproveExpense(tablename){
	//var exp = document.getElementById("EmployeeExpenseTableUA");
	//var ch = exp.childNodes;
	var selected = getSelectedFromTable(tablename);
	selected.forEach(
			function(element){
				var expid = element.childNodes[expidpos].innerText;
				var url = 'expenseadd?ExpenseId=' + expid + "&val=1";
				AjaxGet(url,ifApproved);
			}
	)
};

function DenyExpense(tablename){
	//var exp = document.getElementById("EmployeeExpenseTableUA");
	//var ch = exp.childNodes;
	var selected = getSelectedFromTable(tablename);
	selected.forEach(
			function(element){
				var expid = element.childNodes[expidpos].innerText;
				var url = 'expenseadd?ExpenseId=' + expid+"&val=2";
				AjaxGet(url,ifDenied);
			}
	)
};
function showExpenseReceipt(tablename){
	//var exp = document.getElementById("EmployeeExpenseTableUA");
	//var ch = exp.childNodes;
	console.log(tablename);
	var selected = getSelectedFromTable(tablename);
	selected.forEach(
			function(element){
				var expid = element.childNodes[expidpos].innerText;
				console.log(expid);
				var url = 'image?expid=' + expid;
				console.log(url);
				showExpenseImageFromTable(tablename,expid,url);
			}
	);
};
function hideExpenseReceipt(tablename){
	
		//var exp = document.getElementById("EmployeeExpenseTableUA");
		//var ch = exp.childNodes;
		var selected = getSelectedFromTable(tablename);
		selected.forEach(
				function(element){
					var expid = element.childNodes[expidpos].innerText;
					hideExpenseImageFromTable(tablename,expid);
				}
		);
}
function showExpenseImageFromTable(tablename,expenseid,url){
	console.log("modifying expense" + expenseid);
	if(tablename && expenseid){
		var t = document.getElementById(tablename);
		var r = t.rows;
		var ret = {};
		for(i = 1; i < r.length; i ++){
			var ch = r[i].childNodes;
			var rowInfo = JSON.parse(r[i].getAttribute("info"));
			if(rowInfo["expenseId"] == expenseid){
				if(ch.length<6 || (tablename.substring("all")&&ch.length<7)){
				var td  = document.createElement("td");
				var imgg = document.createElement("img");
				imgg.src = url;
				imgg.name = "rxpt"
				imgg.style = "width:50px;height:auto";
				function openImage(){
					window.open(url,'_blank');
				};
				function hovering(){
					this.setAttribute("hover","true");
				};
				function nothovering(){
					this.setAttribute("hover",null);
				}
				imgg.onmouseleave = nothovering;
				imgg.onmouseover = hovering;
				imgg.onclick = openImage;
				console.log("image source:"+imgg.src);
				td.appendChild(imgg);
				r[i].appendChild(td);
				break;
				}
			}
		}
	}
}
function hideExpenseImageFromTable(tablename,expenseid){
	if(tablename && expenseid){
		var t = document.getElementById(tablename);
		var r = t.rows;
		for(i = 1; i < r.length;i++){
			var ch = r[i].childNodes;
			var rowInfo = JSON.parse(r[i].getAttribute("info"));
			if(rowInfo["expenseId"] == expenseid){
				if(ch.length>5){
					r[i].removeChild(ch[ch.length - 1]);
				}
			}
		}
	}
}
function addReceipt(xhr){
	var res = xhr.responseText;
	
}
function ifApproved(xhr){
	var res = xhr.responseText;
	if(res){
		var exp = removeExpenseFromTable("EmployeeExpenseTableUA",res);
		addToTable("EmployeeExpenseTableA",expenseOnSelect,exp,expenseOnHover,expenseOnLeave);
	}
};
function ifDenied(xhr){
	var res = xhr.responseText;
	if(res){
		var exp = removeExpenseFromTable("EmployeeExpenseTableUA",res);
		addToTable("EmployeeExpenseTableD",expenseOnSelect,exp,expenseOnHover,expenseOnLeave);
	}
};