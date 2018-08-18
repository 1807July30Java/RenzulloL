
function getUSA() {
    var usa = document.getElementById("USA");
    var usapar = usa.parentElement;
    console.log(usapar.innerText);
};


function getPeopleInSales(){
    var emp = document.getElementsByClassName("empName");
    for(i = 0; i < emp.length; i ++){
      var par = emp[i].parentElement;
      if(par.innerText.endsWith("Sales")){
        var s = par.innerText.replace("Sales","")
        console.log(s);
        }
      };
};

function getAnchorChildren(){
    var anc = document.getElementsByTagName("a");
    for(i = 0; i < anc.length; i ++){
      if(anc[i].childElementCount > 0){
        var ch = anc[i].childNodes;
        for(j = 0; j < ch.length;j++){
          if(ch[j].nodeName === "SPAN"){console.log(ch[j].textContent);}
        }
      }
    }
};


function getSkills(){
    var skills = document.getElementsByName("skills");
    for(i = 0; i < skills.length; i ++){
      var skilltab = skills[i];
      for(j = 0; j < skilltab.length;j++){
        if(skilltab[j].selected){
          console.log(skilltab[j].textContent);
        }
      }
    }
};
