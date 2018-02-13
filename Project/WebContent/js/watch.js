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
        	owner.innerHTML = ('By: ' + response['Video']['ownerUserName']);
        	owner.href = './profile.html?userName=' + response['Video']['ownerUserName'];
        		
        	
        	var date = document.getElementById('date');
        	date.innerHTML = formatedDate;
        	document.getElementById("upvotes").innerHTML  = response['Likes'];
        	document.getElementById("downvotes").innerHTML  = response['Dislikes'];
        	if(response['Video']['comments'] == true){
        		
        	var videos = response["Comments"];
        	videos.forEach(function(element) { 
        		createDiv(element);
        		
        	});
        	} else $('#comments').css('display','none');
        	if(response['prati'] == true) {
        		document.getElementById('follow').innerHTML= 'Unfollow';
        	}
        	if(response['Video']['rate'] == true){
        	var userRate =  response['UserRate'];        	
        	switch (userRate) {
			case "like":
				document.getElementById("upvoteImg").src = "img/upvoted.png";
				break;
			case "dislike":
				document.getElementById("downvoteImg").src = "img/downvoted.png";
				break;
			default:
				break;
			}} else {
				$('#upvoteDiv').css('display','none');
				$('#downvoteDiv').css('display','none');
			}
        	
        	if(response['role'] == true || response['user'].toLowerCase() ==  getAllUrlParams(window.location.href).username)
        		ifAdmin(getAllUrlParams(window.location.href).videoid, response['Video']['deleted']);
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
	var upvoteURL = 'img/upvote.png';
	var downvoteURL = 'img/downvote.png';
	
	$.ajax({
		 url: './CommentRates',
			type: 'GET',
	        dataType: 'json', 
	        data: { "videoID" : getAllUrlParams(window.location.href).videoid, 
	        		"commentID" : element['id'],
	        },
	        success: function(response) {
	        	
	        	if(response['UserRate'] == 'like') upvoteURL = 'img/upvoted.png';
	        	if(response['UserRate'] == 'dislike') downvoteURL = 'img/downvoted.png'
	        		var date = new Date(element['created']);
	        	var newDate = date.getDate() + '/' + (date.getMonth()+1) + '/' + date.getFullYear(); 
	        	
	        	var newDiv = $('<div></div>');
	        	
	        	var pContent = $('<p>' + element['content'] +'</p>');
	        	var pOwner = $('<p> By: ' + element['ownerUserName'] +'</p>');
	        	var pDate = $('<p> Date:' + newDate +'</p>');
	        	
	        	newDiv.css({
	        		'padding-bottom' : '5%',
	        	});
	        	pContent.css({
	        		'max-width' : '560px',
	        		'margin-bottom' : '5%',
	        	});
	        	pOwner.css({
	        		'color': '#808080',
	        	});
	        	pDate.css({
	        		'color': '#808080',
	        	});
	        	
	        	var id = element['id'];
	        	var likes = element['likes'];
	        	var dislikes = element['dislikes'];
	        	var vote = $('<div id="upvoteDiv' + id +'" class="vote" onclick="upvote(this)"> ' +
	        					'<img alt="upvote" id="upvoteImg" src="'+ upvoteURL +'"  height="50px">' +
	        					'<p id="upvotes">' + likes +'</p>' +
	        				'</div>' +
	        				'<div id="downvoteDiv' + id +'" class="vote" onclick="downvote(this)">' +
	        					'<img alt="downvote" id="downvoteImg" src="' + downvoteURL + '" height="50px">' +
	        					'<p id="downvotes">' + dislikes + '</p>' +
	        				'</div>"');

	        	newDiv.addClass('comDiv');
	        	$('#comments').append(newDiv);
	        	
	        	newDiv.append(pContent);
	        	newDiv.append(vote)
	        	newDiv.append(pOwner);
	        	newDiv.append(pDate);
	        },
	        error: function(request, message, error) {
				alert('GRESKA: ' + error);
			}
	});
	
}


function upvote(e) {
	console.log(e);
	var id =   e['id'].replace(/^upvoteDiv+/i, '');
	console.log(id);
	$.ajax({
		 url: './CommentRates',
			type: 'GET',
	        dataType: 'json', 
	        data: { "videoID" : getAllUrlParams(window.location.href).videoid, 
	        		"commentID" : id,
	        		"job" : "vote",
	        		"like" : "true",
	        },
	        success: function(response) {
	        	window.location.reload(false); 
	        	
	        },
	        error: function(request, message, error) {
				alert('GRESKA: ' + error);
			}
	});
}

function downvote(e) {
	console.log(e);
	var id =   e['id'].replace(/^downvoteDiv+/i, '');
	console.log(id);
	$.ajax({
		 url: './CommentRates',
			type: 'GET',
	        dataType: 'json', 
	        data: { "videoID" : getAllUrlParams(window.location.href).videoid, 
	        		"commentID" : id,
	        		"job" : "vote",
	        		"like" : "false",
	        },
	        success: function(response) {
	        	window.location.reload(false); 
	        	
	        },
	        error: function(request, message, error) {
				alert('GRESKA: ' + error);
			}
	});
}

function ifAdmin(id, deleted) {
	
	var divInfo = document.getElementById("videoContainer");
	var btn = document.createElement("BUTTON");   
	btn.innerHTML = 'Edit Video';
	btn.onclick = function() {
		window.location.replace('./videoEdit.html?videoId=' + id)
	}
	
	var btn2 = document.createElement("BUTTON");   
	
	if(deleted == true) btn2.innerHTML = 'Undelete';
	else btn2.innerHTML = 'Delete';
	
	btn2.onclick = function() {
		window.location.replace('./DeleteVideo?videoId=' + id)
	}
	
	//var button = $('<button id="edit" onclick="edit(this)">Edit Profile</button>');
	divInfo.append(btn);
	divInfo.append(btn2);
}