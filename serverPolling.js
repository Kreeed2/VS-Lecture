//Funktion welche die Einträge für die Liste holt
function loadEntries() {
  var allEntries = [];
  var serverAnzahl = 1;
  for (var i = 1; i <= serverAnzahl; i++) {
    queryServer(i, entries);
  }
  for (var i = 0; i < allEntries.length; i++) {
    writeEntry(allEntries[i]);
  }
}

function queryServer(number, entries) {
  var URL = "http://ipc772.inf-bb.uni-jena.de/brett/server";
  var ENDING = ".php?method=GET_MESSAGES";
  $.get(URL.concat(number,ENDING), function(data, status) {
    if (status === "success")
      var obj = JSON.parse(data);
      for (var i = 0; i < Object.keys(obj).length; i++) {
        putEntryInList(obj[i], entries);
      }
  });
}

function putEntryInList(entry, entries) {
  var found = false;
  for (var i = 0; i < allEntries.length; i++) {
    if (entries[i].id === entry.id) {
      if (entries[i].version < entry.version) {
        entries[i] = entry;
      } else {
        found = true;
      }
    }
  }
  if (found === false) {
    entries.push(entry);
  }
}

function writeEntry(jsonObj) {
  //var obj = JSON.parse(jsonObj);
  var li = document.createElement("li");
  var t = document.createTextNode(jsonObj.msg);

  li.appendChild(t);
  document.getElementById("entryList").appendChild(li);
}

$(document).ready(loadEntries());
