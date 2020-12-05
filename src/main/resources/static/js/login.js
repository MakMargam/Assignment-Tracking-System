$(document).ready(function(){
  
    $(".login").show();
//    $(".register").hide();

    $("#signup").click(function (){
            $(".login").hide();
            $(".register").show(); 
            $(".username").val("");
            $(".name").val("");
            $(".password").val("");
    })
    $("#login").click(function (){
            $(".register").hide(); 
            $(".login").show();
            $(".username").val("");
            $(".name").val("");
            $(".password").val("");
    })
	$('#submitRegister').prop('disabled', true);
	$("#agreeRegister").click(function (){
		$('#submitRegister').prop('disabled', false);
    })
})
