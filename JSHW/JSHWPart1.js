//JS HW Part ONE

//Due: 5pm Friday, August 17

//Fill in the functions and submit them to your repository in a file called JSHWPart1.js

var homework = {};

/*
1. Return the nth fibonacci number

f(0) = 0
f(1) = 1
f(10) = 55
*/
homework.fibonacci = function(n){
  var k1=1;
  var k2=1;
  var i = 2;
  if(n === 1 || n ===2){return 1;}
  else{
    while(i < n){
      var temp = k2;
      k2 = k1 + temp;
      k1 = temp;
      i+=1;
    }
    return k2;
  }
};

/*
2. Sort array of integers

f([2,4,5,1,3,1]) = [1,1,2,3,4,5]

Don't use the Array sort() method... that would be lame.
*/

/*
homework.sort = function(array) {
    if(array.length <= 1){return array;}
    else{
      var a = [];
      var aInd = 0;
      var b = [];
      var bInd = 0;
      var x = array[homework.randInt(array.length)];
      for(i = 0; i < array.length; i++){
          if(array[i] < x){
            a[aInd] = array[i];
            aInd++;
            }
          else{
            b[bInd] = array[i];
            bInd++;
          }
      }
      return homework.sort(a).concat(homework.sort(b));
    }
};
quicksort is fun until you exceed the call stack*/
homework.min = function(array){
    var m = array[0];
    for(i = 0 ; i < array.length; i ++){
      if(array[i]<m){m = array[i];}
    }
    return m;
};
homework.sort = function(array) {
    var isSorted = false;
    var lastInd = 0;
    var sortedArr = [];
    while(array.length>0){
      var m = homework.min(array);
      sortedArr[lastInd] = m;
      lastInd++;
      var hashmap = [];
      array = array.filter(function(element){
        if(element != m || hashmap[element] === 1){return true;}
        else{
          hashmap[element] = 1;
          return false;
        }
        });
    }
    return sortedArr;
};
/*
3. Return the factorial of n

f(0) = 1
f(1) = 1
f(3) = 6
*/
homework.factorial = function(n){
    if(n===1|| n === 0){return 1;}
    else{
      var k = 1;
      for(i = n; i > 0; i --){
        k*=i;
      }
      return k;
    }
};

/*
4. Rotate left

Given array, rotate left n times and return array

f([1,2,3,4,5], 1) = [2,3,4,5,1]
f([1,2,3,4,5], 6) = [2,3,4,5,1]
f([1,2,3,4,5], 3) = [4,5,1,2,3]

*/
/*because % is not algebraic mod :p */
homework.mod = function(n,m){
    if(n>=0){return n%m;}
    else{
      var k = ((-1)*n)%m;
      if(k>0){
      return m - k;}
      else{return k;}
    }
};
homework.rotateLeft = function(array, n) {
    var t = [];
    for(i = 0; i < array.length;i++){
      t[homework.mod(i-n,array.length)] = array[i];
    }
    return t;
};

/*
5. Balanced Brackets

A bracket is any one of the following: (, ), {, }, [, or ]

The following are balanced brackets:
   ()
   ()()
   (())
   ({[]})

The following are NOT balanced brackets:
(
)
(()
([)]

Return true if balanced
Return false if not balanced
*/
homework.balancedBrackets = function(bracketsString){
  var charstack = [];
  for(i = 0; i < bracketsString.length; i ++){
      switch(bracketsString[i]){
          case '(':
            charstack[charstack.length] = '(';
            break;
          case '{':
            charstack[charstack.length] = '{';
            break;
          case '[':
            charstack[charstack.length] = '[';
            break;
          case ')':
              if(charstack[charstack.length-1] === '('){
                charstack.pop();
              }
              break;
          case '}':
              if(charstack[charstack.length-1] === '{'){
                charstack.pop();
               }
              break;
          case ']':
              if(charstack[charstack.length-1] === '['){
                charstack.pop();
               }
              break;
      }
    }
    return charstack.length === 0;
};

/*YOUR SOLUTIONS, NOT STACKOVERFLOW’S  ;)*/
