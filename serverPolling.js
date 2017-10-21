function queryServer() {
  var URL = "http://ipc772.inf-bb.uni-jena.de/brett/server";
  var ENDING = ".php?method=GET_MESSAGES";
  
  for (var i = 1; i < 10; i++) {
	$.get(URL.concat(i,ENDING), function(data, status) {
		if (status === "success") {
			var obj = JSON.parse(data);
			entry = Object.values(obj);
			for (var j = 0; j < entry.length; j++) {
				writeEntry(entry[j]);
			}
		}
	});
  }
}

function putEntryInList(entry, entries) {
  var found = false;
  for (var i = 0; i < entries.length; i++) {
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

$(document).ready(queryServer());
