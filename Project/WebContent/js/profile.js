$(document).ready(function(e) {
    var current;   
    $.ajax({
        url: './Profile',
		type: 'GET',
        dataType: 'json',        
        data: { "userName" : getAllUrlParams(window.location.href).username, 
        },
        success: function(response) {
        	if(response['msg'] == 'NotFound') window.location.replace("./profileDeleted.html");

        	console.log(response);
        	document.getElementById('fistName').innerHTML = response['profile']['firstName'];
        	document.getElementById('lastName').innerHTML= response['profile']['lastName'];
        	document.getElementById('name').innerHTML =  response['profile']['userName'];
        	document.getElementById('eMail').innerHTML= response['profile']['eMail'];
        	document.getElementById('description').innerHTML= response['profile']['description'];
        	
        	if(response['role'] == true || response['user'].toLowerCase() ==  getAllUrlParams(window.location.href).username)
        		ifAdmin(getAllUrlParams(window.location.href).username, response['profile']['deleted']);
        	if(response['role'] == true) {
        		var divInfo = document.getElementById("info");
        		var btn3 = document.createElement("BUTTON");   
        		
        		if(response['profile']['banned'] == true) btn3.innerHTML = 'Unban';
        		else btn3.innerHTML = 'Ban';
        		btn3.onclick = function() {
        			window.location.replace('./BanUser?userName=' + getAllUrlParams(window.location.href).username)
        		}
        		divInfo.append(btn3);
        	}
        },
        error: function(request, message, error) {
        	window.location.replace('./profileDeleted.html')
		}
    });
       
});


function getAllUrlParams(url) {

	  // get query string from url (optional) or window
	  var queryString = url ? url.split('?')[1] : window.location.search.slice(1);

	  // we'll store the parameters here
	  var obj = {};

	  // if query string exists
	  if (queryString) {

	    // stuff after # is not part of query string, so get rid of it
	    queryString = queryString.split('#')[0];

	    // split our query string into its component parts
	    var arr = queryString.split('&');

	    for (var i=0; i<arr.length; i++) {
	      // separate the keys and the values
	      var a = arr[i].split('=');

	      // in case params look like: list[]=thing1&list[]=thing2
	      var paramNum = undefined;
	      var paramName = a[0].replace(/\[\d*\]/, function(v) {
	        paramNum = v.slice(1,-1);
	        return '';
	      });

	      // set parameter value (use 'true' if empty)
	      var paramValue = typeof(a[1])==='undefined' ? true : a[1];

	      // (optional) keep case consistent
	      paramName = paramName.toLowerCase();
	      paramValue = paramValue.toLowerCase();

	      // if parameter name already exists
	      if (obj[paramName]) {
	        // convert value to array (if still string)
	        if (typeof obj[paramName] === 'string') {
	          obj[paramName] = [obj[paramName]];
	        }
	        // if no array index number specified...
	        if (typeof paramNum === 'undefined') {
	          // put the value on the end of the array
	          obj[paramName].push(paramValue);
	        }
	        // if array index number specified...
	        else {
	          // put the value at that index number
	          obj[paramName][paramNum] = paramValue;
	        }
	      }
	      // if param name doesn't exist yet, set it
	      else {
	        obj[paramName] = paramValue;
	      }
	    }
	  }

	  return obj;
	}

function ifAdmin(user, deleted) {
	
	var divInfo = document.getElementById("info");
	var btn = document.createElement("BUTTON");   
	btn.innerHTML = 'Edit Profile';
	btn.onclick = function() {
		window.location.replace('./edit.html?userName=' + user)
	}
	
	var btn2 = document.createElement("BUTTON");   
	
	if(deleted == true) btn2.innerHTML = 'Undelete';
	else btn2.innerHTML = 'Delete';
	
	btn2.onclick = function() {
		window.location.replace('./DeleteUser?userName=' + user)
	}
	
	//var button = $('<button id="edit" onclick="edit(this)">Edit Profile</button>');
	divInfo.append(btn);
	divInfo.append(btn2);
}
