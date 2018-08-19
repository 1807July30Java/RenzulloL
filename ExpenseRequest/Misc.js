
function addExpenseToTable(){
  var d = document.getElementById("expensedate").value;
  var a = document.getElementById("amount").value;
  if(a && d){
    var t = document.getElementById("ERtable");
    var tr = document.createElement("tr");
    var td1 = document.createElement("td");
    var td2 = document.createElement("td");
    var dtx = document.createTextNode(d);
    var atx = document.createTextNode(a);
    td1.appendChild(dtx);
    td2.appendChild(atx);
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.name = "unselected";
    tr.onclick = sel;
    t.appendChild(tr);
  }
};

function sel(){
    if(this.name === "selected"){
      this.name = 'unselected';
      this.bgColor = "";
    }
    else{
      this.name = "selected";
      this.bgColor = "red";
  }
  console.log(this.name);
};

function getSelectedFromTable(){
  var ret = [];
  var tr = document.getElementsByTagName("tr");
  for(i = 0; i < tr.length;i++){
    if(tr[i].name === "selected"){
      ret.push(tr[i]);
    }
  }
  return ret;
};
