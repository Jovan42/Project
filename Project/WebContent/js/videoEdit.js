$(document).ready(function(e) {
    var current;   
    $.ajax({
        url: './Watch',
		type: 'GET',
        dataType: 'json',        
        data: { "videoID" : getAllUrlParams(window.location.href).videoid, 
        },
        success: function(response) {
        	console.log(response);
        	document.getElementById('url').value = response['Video']['name'];
        	document.getElementById('thumbURL').value = response['Video']['tumbnailURL'];
        	document.getElementById('description').value= response['Video']['description'];
        	if (response['Video']['visible']) document.getElementById('visible').checked ='checked' ;
        	if (response['Video']['comment']) document.getElementById('comment').checked= 'checked';
        	if(response['Video']['rate'])document.getElementById('rate').checked = 'checked';
        	document.getElementById('videoId').value= getAllUrlParams(window.location.href).videoid;
        	
        	if(response['role'] != true || response['user'].toLowerCase() !=  response['Video']['ownerUserName'] && response['user'] == 'Guest')
        		window.location.replace("./main.html");
        },
        error: function(request, message, error) {
			alert('GRESKA: ' + error);
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