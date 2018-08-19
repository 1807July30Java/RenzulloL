
/*
1. USA Define function getUSA()

Find the html element that contains "USA".

Print that element's contents.
*/
function getUSA() {
    var usa = document.getElementById("USA");
    var usapar = usa.parentElement;
    console.log(usapar.innerText);
    return usa;
};

/*
2. Sales

Define function getPeopleInSales()

Print the names of all the people in the sales department.
*/
function getPeopleInSales(){
    var emp = document.getElementsByClassName("empName");
    var ret = [];
    for(i = 0; i < emp.length; i ++){
      var par = emp[i].parentElement;
      if(par.innerText.endsWith("Sales")){
        var s = par.innerText.replace("Sales","")
        ret.push(par);
        console.log(s);
        }
      }
      return ret;
};
/*
3. Click Here

Define function getAnchorChildren()

Find all anchor elements with a <span> child.

Print the contents of <span>
*/
function getAnchorChildren(){
    var anc = document.getElementsByTagName("a");
    var ret = [];
    for(i = 0; i < anc.length; i ++){
      if(anc[i].childElementCount > 0){
        var ch = anc[i].childNodes;
        for(j = 0; j < ch.length;j++){
          if(ch[j].nodeName === "SPAN"){
            console.log(ch[j].textContent);
            ret.push(ch[j]);
          }
        }
      }
    }
    return ret;
};

/*
4. Hobbies

Define function getSkills()



Find all checked options in the 'skills' select element.

Print the value and the contents.
*/
function getSkills(){
    var skills = document.getElementsByName("skills");
    var retskills = [];
    for(i = 0; i < skills.length; i ++){
      var skilltab = skills[i];
      for(j = 0; j < skilltab.length;j++){
        retskills.push(skilltab[j]);
        if(skilltab[j].selected){
          console.log(skilltab[j].textContent);
        }
      }
    }
    return retskills;
};

/*
5. Custom Attribute

Define function getCustomAttribute()

Find all elements with "data-customAttr" attribute

Print the value of the attribute.

Print the element that has the attribute.
*/
function getCustomAttribute(){
    var c = document.querySelectorAll("[data-customAttr]");
    c.forEach(function(element){
      console.log(element.innerText)
      console.log(element);
    });
    return c;
};

/*
6. Sum Event

NOTE: Write unobtrusive Javascript

Regarding these elements:

<input id="num1" class="nums" type="text"/>

<input id="num2" class="nums" type="text"/>

<h3>Sum: span id="sum"></span></h3>

Define onchange event handler.

Add <input> element values.

Put the sum in the <span> element.

If values cannot be added, put "Cannot add" in the <span> element
*/

var n1 = document.getElementById("num1");
var n2 = document.getElementById("num2");
var s = document.getElementById("sum");
//var hs = s.parentElement;
n1.onchange = function () {
  var nn1 = n1.value;
  var nn2 = n2.value;
  var sum = parseInt(nn1) + parseInt(nn2);
  console.log(sum);
  if(sum){
    s.innerText = sum;
  }
  else{
    s.innerText = "Cannot add";
  }
};
n2.onchange = n1.onchange;



/*
7. Skills Event

NOTE: Write unobtrusive Javascript

When user selects a skill, create an alert with a message similar to:

"Are you sure CSS is one of your skills?"

NOTE: no alert should appear when user deselects a skill.
*/
function makeSkillsAlert(){
  var sk = document.getElementsByName("skills");
  var skills = sk[0];
  skills.onchange = function(){
    var index = skills.selectedIndex;
    var message;
    switch (index){
      case 0:
        message = "Python is better";
        break;
      case 1:
        message = "You're probably a genius";
        break;
      case 2:
        message = "Ill bet you're fun at parties";
        break;
      case 3:
        message = "Bruh, we've all seen your myspace page, don't joke";
        break;
      case 4:
        message = "Clearly no actual style is required for this skill";
        break;
      case 5:
        message = "AAAAYYY DAS MEEE :D";
        break;
    }
    alert(message);
  }
}
makeSkillsAlert();
/*
8. Favorite Color Event

NOTE: Write unobtrusive Javascript

NOTE: This is regarding the favoriteColor radio buttons.

When a user selects a color, create an alert with a message similar to:

"So you like green more than blue now?"

In this example, green is the new value and blue is the old value.

Make the background color (of all favoriteColor radio buttons)  the newly selected favoriteColor
 */
function addBackgroundColorsToButtons(){
  var blue = document.createElement('style');
  blue.type = 'text/css';
  blue.innerHTML = '.blue { border-style: solid; border-color: blue; border-width: 15px; visibility: visible; }';
  var green = document.createElement('style');
  green.type = 'text/css';
  green.innerHTML = '.green { border-style: solid; border-color: green; border-width: 15px;visibility: visible;}';
  var red = document.createElement('style');
  red.type = 'text/css';
  red.innerHTML = '.red { border-style: solid; border-color: red; border-width: 15px;visibility: visible;}';
  var orange = document.createElement('style');
  orange.type = 'text/css';
  orange.innerHTML = '.orange { border-style: solid; border-color: orange; border-width: 15px:visibility: visible; }';
  document.getElementsByTagName('head')[0].appendChild(blue);
  document.getElementsByTagName('head')[0].appendChild(green);
  document.getElementsByTagName('head')[0].appendChild(red);
  document.getElementsByTagName('head')[0].appendChild(orange);
};
addBackgroundColorsToButtons();
 function makeColorsAlert(){ //uses closure to ensure client cannot hack which button was previously selected.
   var oldButton;           ///here at Revature we take security VERY seriously.
   var rbc = document.getElementsByName("favoriteColor");
   rbc.forEach(function(element){element.onclick = function(){
     if(!oldButton){alert("It was a hard decision but " +this.value +  " is definitly the right choice");}
     else{
       alert("But " + oldButton.value +  " was fine! Who just switches to " +  this.value + "?");
     }
     oldButton = this;
     var rbc2 = document.getElementsByName("favoriteColor");
     rbc2.forEach(function(element){element.className = oldButton.value;});
    }
   });
 };
 makeColorsAlert();

/*
9. Show/Hide Event

NOTE: Write unobtrusive Javascript

When user hovers over an employees name:

Hide the name if shown. Show the name if hidden.
*/
function toggleHideEmployees(){
  var tog = true;
  var e = document.getElementsByClassName("empName");
  for(i=0;i<e.length;i++){
    element = e[i];
      element.onmouseenter = function(){
        if(!this.hidden && tog){
          this.hidden = true;
            }
      }
  }
  for(i=0;i<e.length;i++){
    var par = e[i].parentElement;
      par.onmouseenter = function(){
        if(this.firstElementChild.hidden){
        this.firstElementChild.hidden = false;
        tog = false;
      }
      }
      par.onmouseleave = function(){tog = true;}
  }
};
toggleHideEmployees();
