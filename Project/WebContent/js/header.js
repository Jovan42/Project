$(document).ready(function(e) {
    var current;   
    $.ajax({
        url: './Main',
		type: 'GET',
        dataType: 'json',        
        success: function(response) {        	
        	var loggedInUser = document.getElementById('loggedInUser');
        	var myProfile = document.getElementById("myProfile");
        	
        	if(response["user"] == null) {
                $(loggedInUser).css("display", "none"); 
                $(loginForm).css("display", "block");
                $(registerForm).css("display", "block");
        	} else {
        		$(loggedInUser).css("display", "block"); 
                $(loginForm).css("display", "none");
                $(registerForm).css("display", "none");
        		loggedInUser.innerHTML = response["user"]["userName"];
        		myProfile.href =  "./profile.html?userName=" + response["user"]["userName"];
            }        	
        },
        error: function(request, message, error) {
			alert('Error: ' + error);
		}
    });
})