/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
let choice = document.getElementsByTagName("div");
choice[0].style.color = "red";
choice[1].style.color = "blue";
choice[2].style.color = "green";

function Reload() {


    for (var i = i; i < MemberFacade.getMemberCount - 1; i++) {
        document.getElementById(i)
    }
    document.getElementById("1").style.color = "blue"
    document.getElementById("2").style.color = "red"
    document.getElementById("3").style.color = "yellow"
}
<<<<<<< HEAD
 */

const btn1= document.getElementById("btn");

btn1.onclick = function(){
    
    var url = document.location.origin +'/jpareststarter/api/member/all';
    fetch(url)
  .then(res => res.json()) //get at json array 
  .then(data => {
   // Inside this callback, and only here, the response data is available
   console.log("data",data);
   document.getElementById('testval').innerHTML = data.map(function (item){
       return item.name; });
   });
   
  /* data now contains the response, converted to JavaScript
     Observe the output from the log-output above
     Now, just build your DOM changes using the data*/       


//   fetch('http://localhost:8080/jpareststarter/api/member/all') //https://pencilash.com/Member/api/member/all
//  .then(function(response) {
//    return response.json();
//  }).then(function(myJson) {
//    myJson.map(function (member){
//        console.log(member);
//        
//    });
//    
//  });
}

