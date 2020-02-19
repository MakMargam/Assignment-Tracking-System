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
//    $(".submitbtn").click(function(){
//        $(".username").text("");
//        $(".name").text("");
//        $(".password").text("");
//
//    })
})
