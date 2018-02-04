$(document).ready(function(e) {
    var current;   
    $.ajax({
        url: './Watch',
		type: 'GET',
        dataType: 'json',        
        data: { "videoID" : getAllUrlParams(window.location.href).videoid, 
        },
        success: function(response) {
        	
        	if(response['user'] == 'Guest') {
        		$('#commentForm').css("display", "none"); 
        	}
        	var date = new Date(response['Video']['created']);
        	var formatedDate = date.getDate() + '/' + (date.getMonth()+1) + '/' + date.getFullYear(); 
        	console.log(response);
        	var iframeVideo = document.getElementById('video');
        	var name = document.getElementById('name');
        	var owner = document.getElementById('owner');
        	
        	iframeVideo.src = 'https://www.youtube.com/embed/' + response['Video']['url'] + '?color=white';
        	name.innerHTML = response['Video']['url'];
        	owner.innerHTML = 'By: ' + response['Video']['ownerUserName'];
        	
        	var date = document.getElementById('date');
        	date.innerHTML = formatedDate;
        	
        	
        	var videos = response["Comments"];
        	videos.forEach(function(element) { 
        		createDiv(element);
        		
        	});
        },
        error: function(request, message, error) {
			alert('GRESKA: ' + error);
		}
    });
    
    $(document).on('click', '.aboutDiv', function(e) {
    	
    	window.location = "./watch.html?videoID=" + $(this).data('videoId');
    	
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

function submitComment() {
	var videoID = document.getElementById('videoid');
	videoID.value = getAllUrlParams(window.location.href).videoid;
	
	var content = document.getElementById('comContent');
	var textContent = document.getElementById('textComment');
	console.log(textContent.value);
	content.value = textContent.value;
	
	var form = document.getElementById('commentForm');
	form.submit();
	
}

function createDiv(element) {
	var date = new Date(element['created']);
	var newDate = date.getDate() + '/' + (date.getMonth()+1) + '/' + date.getFullYear(); 
	
	var newDiv = $('<div></div>');
	
	var pContent = $('<p>' + element['content'] +'</p>');
	var pOwner = $('<p> Owner: ' + element['ownerUserName'] +'</p>');
	var pDate = $('<p> Date:' + newDate +'</p>');
	
	pOwner.css({
		'color': '#808080',
	});
	pDate.css({
		'color': '#808080',
	});

	newDiv.addClass('comDiv');
	$('#comments').append(newDiv);
	newDiv.append(pContent);
	newDiv.append(pOwner);
	newDiv.append(pDate);
}