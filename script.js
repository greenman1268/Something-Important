var username = "";

	function send_message(message){


		var prevState = $("#container").html();

		if (prevState.length > 3){
			prevState = prevState + "<br>";
		}

		$("#container").html(prevState + "<span class = 'bot'>Chatbot: </span>" + message);

	}

	function get_username(){
		send_message("Hello, what is your name?");
	}

	function ai(message){
		if (username.length<3){
			username = message;
			send_message("Nice to meet you " + username + ", how are you doing?");
		}
	}

	$(function(){

		get_username();

		$("#textbox").keypress(function(event){
			if ( event.which == 13){
				if ( $("#enter").prop("checked") ){
 
					$("#send").click();
					event.preventDefault();

				}

			}

		});


		$("#send").click(function(){

			var username = "<span class ='username' = >You: </span>";

			var newMessage = $("#textbox").val();

			$("#textbox").val("");

			var prevState = $("#container").html();

			if (prevState.length > 3){
				prevState = prevState + "<br>";
			}

			$("#container").html(prevState + username + newMessage);
			
			$("#container").scrollTop($("#container").prop("scrollHeight"));

			ai(newMessage);

		});

	});