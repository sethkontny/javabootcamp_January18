// this is how you comment in javascript -- same as java single line comment
var name = "Adam"; // data type: string

// newName = "Adam"; bad practice - you should avoid doing this but your code will still compile if you don't use var

// in javascript you can reassign variables to any type without casting
name = 20;
name = true;
name = null;
name = undefined;
name = {name: "Adam"};

// var 9day = "new";  this is not a valid variable declaration

var num = 50; // data type: number

var isDetroitAwesome = true; // data type: boolean

var thisIsNull = null; // this means we've given it a blank value like a placedholder waiting for some value eventually

var dog; // if you don't assign a value it is automatically declared as undefined (no declared type)

// there is also a data type known as symbol: https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Symbol

// Objects
// properties are separated by commas like an array and can include functions
var myInfo = {
  name : "Adam",
  age: 31,
  sayHello: function() {
    console.log("Hello");
  }
};

myInfo.sayHello; // this is how you call the function from the object myInfo

function greetStudent() {
  console.log("Hello friend");
}

function sayGoodBye () {
  console.log("Bye Felicia");
}

// declaring an array - uses square brackets instead of {} (Java uses the curly brackets)
// arrays can have different data types and functions
var listOfPeople = ["Antonella", "Merc", "Zach", "Steven"];
var things = ["Antonella", 30, null, {test: "hello"}, [3,4,5]]; // example mixing different types in an array
listOfPeople.push("Andrew");

// Adam's code by yourself examples:
// Decalre 2 variables per data type and log those values to the console

// string, number, boolean, null, undefined

// Declare and object called myInfo that contains three properties: name, age, favFood.
// Log those values to the console.

var name1 = "Antonella";
var name2 = "Adam";
console.log(name1);
console.log(name2);

var num1 = 12;
var num2 = 32;
console.log(num1);
console.log(num2);

var bool1 = true;
var bool2 = false;
console.log(bool1);
console.log(bool2);

var n = null;
var p = null;
console.log(n);
console.log(p);

var j = undefined;
var k = undefined;
console.log(j);
console.log(k);


var myInfo = {
  name: "Antonella",
  age: 34,
  favFood: "Pizza"
};
console.log(myInfo.name + " " + myInfo.age+ " " + myInfo.favFood);

// functions

function multiply(x,y) {
  console.log(x*y); // this line is just for testing -- we can't use this value anywhere else in the program
  return x*y; // we can use the return value from the method again if assigned to a variable
}

var num = multiply(3,4); // similar to java we can set a return value from a method to a variable

// can also do this
var num = 0; // global variable

function multiply2(x,y) {
  num = x*y; // methods in javascript don't need to use the keyword return (this is different than java, we must use the return keyword unless it is a void method)
}

multiply2(5,10);
console.log(num); // as a result of setting up our method in this way num will never be 0 again in this application


// Taking in user input
var userFavFood = prompt("What is your favorite food? "); // the prompt() is like our scanner in java
console.log(userFavFood);

if (userFavFood === "pizza") {
  console.log("Awesome. That's my favorite too!");
}


// Code Challenge
var name = "";
var done = false;
function ask() {
  return prompt("Please enter your name");
}
function helloName() {
  while (!done) {
    usernput = ask();
    if (isTooShort(userinput)) {
      alert("Your name is short. Too short.\n Do it again!");
    } else {
      alert("Hello " + userinput);
      done = true;
    }
  }
}
