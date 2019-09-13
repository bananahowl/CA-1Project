/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//"https://pencilash.com/Member/"

document.getElementById("Jokebtn").onclick = LoadJoke;
function LoadJoke() {

    let add ="";
    if(document.location.origin == "http://localhost:8080"){
        add = "http://localhost:8080";
    }
    else{
        add = "https://pencilash.com";
    }
    let url = add + "/Member/api/Joke/all";
    fetch(url)
            .then(res => res.json()) //get at json array 
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.getElementById('JokeBox').innerHTML = JokeToHTMLTable(data);
            });
}

function JokeToHTMLTable(arr) {
    var arrHTML = arr.map(item => "<tr>"
                + "<td>" + item.name + "</td>"
                + "<td>" + item.id + "</td>"
                + "<td>" + item.description + "</td>"
                + "<td>" + item.color + "</td>"
                + "</tr>");
    var arrStr = arrHTML.join('');
    var result = "<table class=\"table table-striped\"><tr>"
            + "<th width = 100px>Name</th>"
            + "<th width = 100px>Joke.nr</th>"
            + "<th width = 100px>Description</th>"
            + "<th width = 100px>Color</th>"
            + arrStr + "</table>";
    return result;
}

LoadJoke();


document.getElementById("RandomJokebtn").onclick = function (e) {

    let add ="";
    if(document.location.origin == "http://localhost:8080"){
        add = "http://localhost:8080";
    }
    else{
        add = "https://pencilash.com";
    }
    let url = add + "/Member/api/Joke/all";

    fetch(url)
            .then(res => res.json()) //get at json array 
            .then(data => {
                // Inside this callback, and only here, the response data is available
                console.log("data", data);
                document.getElementById('RandomJokeBox').innerHTML = data.description
            });

}
