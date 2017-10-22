var allEntries = [];
var readQuorum = 3;
var checkedServer = [];

function queryServer(serverNumber) {
  var URL = "http://ipc772.inf-bb.uni-jena.de/brett/server";
  var ENDING = ".php?method=GET_MESSAGES";

  $.get(URL.concat(serverNumber,ENDING), function(data) {
    checkedServer.push(serverNumber);
    var entryFound = false;

    for (var i = 0; i < data.length; i++) {
      for (var j = 0; j < allEntries.length; j++) {
        if (allEntries[j].id === data[i].id) { //Passender Eintag in Speicher gefunden
          var entryFound = true;
          if (allEntries[j].id < data[i].id) { //Abfrageeintrag ist neuer als Eintag im Speicher
            allEntries[j] = data[i];
          } else { //Abfrageeintrag ist älter als Eintag im Speicher
            break;
          }
        }
      }
      if (entryFound === false) { //Neuen Eintag in den Speicher aufnehmen
        allEntries.push(data[i]);
      }
    }
    if (checkedServer.length < readQuorum) {
      while (true) {
        var nextServer = Math.floor((Math.random() * 10) + 1); // Keine doppelten Serverabfragen
        if (!(nextServer in checkedServer)) {
          queryServer(nextServer);
          break;
        }
      }
    } else {
      for (var i = 0; i < allEntries.length; i++) {
        writeEntry(allEntries[i]);
      }
    }
  }, "json");
}

function writeEntry(jsonObj) {
  var li = document.createElement("li");
  li.setAttribute('identifier', jsonObj.id);
  li.setAttribute('version', jsonObj.version);

  var h = document.createElement("h4");
  var p = document.createElement("p");
  var s = document.createElement("small");

  h.innerHTML = jsonObj.author;
  p.innerHTML = jsonObj.msg;
  s.innerHTML = jsonObj.creation;

  li.appendChild(h);
  li.appendChild(p);
  li.appendChild(s);

  document.getElementById("entryList").appendChild(li);
}

$(document).ready(queryServer(1));
