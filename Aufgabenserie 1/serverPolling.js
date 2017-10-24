var allEntries = []; //Lokaler Speicher der Einträge
var readQuorum = 3;
var checkedServer = []; //Bisher besuchte Server

function queryServer() {
  var URL = "http://ipc772.inf-bb.uni-jena.de/brett/server";
  var ENDING = ".php?method=GET_MESSAGES";

  $.get(URL.concat(getRandomServerNumber(),ENDING), function(data) {
    var entryFound = false;

    for (i in data) {
      for (j in allEntries) {
        if (allEntries[j].id === data[i].id) { //Passender Eintag in Speicher gefunden
          var entryFound = true;
          if (allEntries[j].id < data[i].id || (allEntries[j].id === data[i].id && new Date(allEntries[j].creation) < new Date(data[i].creation))) { //Abfrageeintrag ist neuer als Eintag im Speicher
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
      queryServer(); //Selbstaufruf bis Lesequorum erreicht ist
    } else {
      for (i in allEntries) {
        writeEntry(allEntries[i]);
      }
    }
  }, "json")
  .fail(function() { //Abfrage an der Server nicht erfolgreich
    queryServer();
  });
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

function getRandomServerNumber() {
  while (true) {
    var nextServer = Math.floor((Math.random() * 10) + 1); // Keine doppelten Serverabfragen
    if (!(nextServer in checkedServer)) {
      checkedServer.push(nextServer);
      return nextServer;
    }
  }
}

$(document).ready(queryServer());
