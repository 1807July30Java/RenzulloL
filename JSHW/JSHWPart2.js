
/*
1. USA Define function getUSA()

Find the html element that contains "USA".

Print that element's contents.
*/
function getUSA() {
    var usa =  document.querySelectorAll("[data-customAttr = 'USA']");
    var usapar = usa[0].parentElement;
    console.log(usapar.innerText);
    return usa[0];
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
          console.log(skilltab[j].value);
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
     rbc2.forEach(function(element){element.style.backgroundColor = oldButton.value;});
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

/*
 10. Current Time

Regarding this element: <h5 id="currentTime"></h5>

Show the current time in this element in this format: 9:05:23 AM

The time should be accurate to the second without having to reload the page.
*/
function updateTime(){
  var ct = document.getElementById("currentTime");
  var d = new Date(Date.now());
  var ampm = "AM";
  var t;
  if(d.getHours()>12){
    ampm = "PM";
    var t = (d.getHours()-12) + ":" + d.getMinutes() + ":" + d.getSeconds() + " " +ampm;
  }
  else{
    ampm = "AM";
    var t = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + " " + ampm;
  }
  ct.innerText = t;
};
setInterval(updateTime,1000);

/*
11. Delay Regarding this element:

<p id="helloWorld">Hello, World!</p>

Three seconds after a user clicks on this element, change the text to a random color.
*/

function changeColorsAtInterval(){
  var hw = document.getElementById("helloWorld");
  hw.onclick = function(){
    setTimeout(function(){
      var e = document.getElementById("helloWorld");
      a = Math.floor(Math.random()*256);
      b = Math.floor(Math.random()*256);
      c = Math.floor(Math.random()*256);
      e.style = "color:RGB(" + a +"," +  b+"," + c +")"},3000)
  }
};
changeColorsAtInterval();


/*
12. Walk the DOM

Define function walkTheDOM(node, func)

This function should traverse every node in the DOM.  Use recursion.

On each node, call func(node).
*/
function walkTheDOM(node,func){//depth first search
  var seen = []
  function walk(nd){
    var stack = [];
    var ch = nd.childNodes;
    for(i = 0; i < ch.length;i++){stack.push(ch[i])};
    while(stack.length>0){
      var n = stack.pop();
      if(seen.lastIndexOf(n)=== -1|| seen.length == 0){
        console.log(n);
        seen.push(n);
        func(n);
        walk(n);
      }
    }
  };
  walk(node);
};
