/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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