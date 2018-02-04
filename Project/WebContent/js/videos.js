$(document).ready(function(e) {
    var current;   
    $.ajax({
        url: './Main',
		type: 'GET',
        dataType: 'json',        
        data: { "sName" : getAllUrlParams(window.location.href).sname, 
        		"sOwner" : getAllUrlParams(window.location.href).sowner,
        		"sViews" : getAllUrlParams(window.location.href).sviews,
        		"sDate" : getAllUrlParams(window.location.href).sdate},
        success: function(response) {
        	console.log(getAllUrlParams(window.location.href).sname);
        	
        	var loggedInUser = document.getElementById('loggedInUser');
        	var myProfile = document.getElementById("myProfile");
        	
        	console.log(myProfile);
        	if(response["user"] == null) {
                $(loggedInUser).css("display", "none"); 
                $(loginForm).css("display", "block");
                $(registerForm).css("display", "block");
        	} else {
        		$(loggedInUser).css("display", "block"); 
                $(loginForm).css("display", "none");
                $(registerForm).css("display", "none");
        		loggedInUser.innerHTML = response["user"]["userName"];
        		myProfile.href =  "./Project/Profile?userName=" + response["user"]["userName"];
            }
            
        	var videos = response["videos"];
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

function btnSearch(input){
	document.getElementById("content").innerHTML='';
	var sSort = document.getElementById("sort");
	var sSortType = document.getElementById("sortType");
	console.log(sSort.options[sSort.selectedIndex].value);
	
	 $.ajax({
	        url: './Main',
			type: 'GET',
	        dataType: 'json',
	        data: { "sName" : document.getElementById("sName").value, 
        		"sOwner" : document.getElementById("sOwner").value,
        		"sViews" : document.getElementById("sViews").value,
        		"sDate" : document.getElementById("sDate").value,
        		"sort" : sort.options[sort.selectedIndex].value,
        		"sortType" : sortType.options[sortType.selectedIndex].value},
	        success: function(response) {
	        	 
	        	var loggedInUser = document.getElementById('loggedInUser');
	        	var myProfile = document.getElementById("myProfile");
	        	
	        	console.log(myProfile);
	        	if(response["user"] == null) {
	                $(loggedInUser).css("display", "none"); 
	                $(loginForm).css("display", "block");
	        	} else {
	        		loggedInUser.innerHTML = response["user"]["userName"];
	        		myProfile.href =  "./Project/Profile?userName=" + response["user"]["userName"];
	            }
	            
	        	var videos = response["videos"];
	        	videos.forEach(function(element) { 
	        		createDiv(element);
	        		
	        	});
	        	
	        	
	        },
	        error: function(request, message, error) {
				alert('GRESKA: ' + error);
			}
	    });
	
}

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

function createDiv(element) {
	var date = new Date(element['created']);
	var newDate = date.getDate() + '/' + (date.getMonth()+1) + '/' + date.getFullYear(); 
	
	var newDiv = $('<div></div>');
	var vidDiv = $('<div></div>');
	var pName = $('<p> Name: ' + element['name'] +'</p>');
	var pOwner = $('<p> Owner: ' + element['ownersUserName'] +'</p>');
	var pDate = $('<p> Date:' + newDate +'</p>');
	console.log(newDiv);
	console.log(pName);
	
	newDiv.css({
		'background': 'black',
		'color' : 'white',
	});
	vidDiv.css({
		'background': 'url("img/defThumb.jpg")',
		'background-size' : 'cover',

	});
	vidDiv.addClass('vidDiv');
	newDiv.addClass('aboutDiv');
	$('#content').append(newDiv);
	newDiv.data('videoId', element['id']);
	newDiv.append(vidDiv);
	newDiv.append(pName);
	newDiv.append(pOwner);
	newDiv.append(pDate);
}
