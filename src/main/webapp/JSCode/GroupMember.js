/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 document.getElementById("btn").onclick = loadAll;
 
 function loadAll() {
 let url = document.location.origin + "/jpareststarter/api/member/all";
 fetch(url)
 .then(res => res.json()) //in flow1, just do it
 .then(data => {
 document.getElementById("tableBox").innerHTML
 = groupToHTMLTable(data);
 });
 }
 function groupToHTMLTable(arr) {
 var arrHTML = arr.map(obj => "<tr>"
 + "<td>" + obj.name + "</td>"
 + "<td>" + obj.id + "</td>"
 + "<td>" + obj.color + "</td>"
 + "</tr>");
 var arrStr = arrHTML.join('');
 var result = "<table class=\"table\"><tr>"
 + "<th>Name</th>"
 + "<th>Student ID</th>"
 + "<th>Color</th>"
 + arrStr + "</table>";
 return result;
 }
 loadAll();
 */

document.getElementById("btn").onclick = LoadMember;
function LoadMember() {
    let url = document.location.origin + "/jpareststarter/api/member/all";
    fetch(url)
            .then(res => res.json()) //get at json array 
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.getElementById('tableBox').innerHTML = MemberToHTMLTable(data);
            });

}
function MemberToHTMLTable(arr) {
    var arrHTML = arr.map(item => "<tr>"
                + "<td>" + item.name + "</td>"
                + "<td>" + item.id + "</td>"
                + "<td>" + item.color + "</td>"
                + "</tr>");
    var arrStr = arrHTML.join('');
    var result = "<table class=\"table\"><tr>"
            + "<th width = 100px>Name</th>"
            + "<th width = 100px>Student ID</th>"
            + "<th width = 100px>Color</th>"
            + arrStr + "</table>";
    return result;
}
LoadMember();
