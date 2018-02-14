// Form Demo taking input and doing stuff with it

var users = [
  {userName: "merc", password: "password"},
  {userName: "antonella", password: "grantChirpus"},
  {userName: "syntax", password: "food"}
];

function signIn() {
  var inputs = document.querySelectorAll("input");
  console.log(inputs);
  var userInfo = {
    userName: inputs[0].value,
    password: inputs[1].value
  };
  for(var i = 0; i < users.length; i++) {
    if(users[i].userName === userInfo.userName && users[i].password === userInfo.password) {
      console.log("Success!");
      var form = document.querySelectorAll("form");
      form[0].style.display ="none"; // this hides the form, still need to use the reference to index 0 even though it's the only form on the page
      var greeting = document.createElement("h1");
      greeting.innerText = "Thanks for signing in. Buy all my stuff";
      document.body.appendChild(greeting);
      break;
    } else {
      console.log("incorrect username and/or password!");
    }
  }
  // this code should go inside of the loop but was left out for demo purposes

}
