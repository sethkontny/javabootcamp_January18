// DOM Manipulation

var p = document.querySelectorAll("p"); // element/tag name -- by default returns an array because there are/ can be multiple p tags
var info = document.querySelectorAll(".info"); // class -- this will return items that have a class assigned to them, multiple elements can have the class assignment
var name = document.querySelectorAll("#name"); // id -- this will be 1 unique variable, remember ids should be unique and should only be assigned once in any html document

console.log(p);

p[0].innerText = "Antonella"; // changing the value of Adam to Antonella
p[0].style.color = "purple"; // changes the font color to purple
p[0].style.fontSize = "30px"; // enlarges the font to 30px

p[2].innerText = "Merc";
p[2].style.color = "blue"; // changes the font color to blue
p[2].style.fontSize = "50px"; // enlarges the font to 50px

// using tilde allows us to add multiple lines of code as one string
info[0].innerHTML = `
<form>
  <input type="text">
  <button>Sign Up</button>
</form>
`;
