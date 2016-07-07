$(function(){

	$("#portfolio img").click(function(){

		var src = $(this).attr("src");
		$("#main").attr("src", src);
		$("#frame").fadeIn();
		$("#overlay").fadeIn();
	});

	$("#overlay").click(function(){
		$(this).fadeOut();
		$("#frame").fadeOut();
	});

	$("#right").click(function(){

	});

	$("#left").click(function(){
		
	});
});