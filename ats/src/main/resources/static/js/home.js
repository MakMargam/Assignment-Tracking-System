jQuery(function ($) {
    $("#show-sidebar").hide();

    $(".home").show();
    $(".viewassignments").hide();
    $(".editassignments").hide();
    $(".viewcourse").hide();
    $(".viewgroup").hide();
    $(".viewuser").hide();
    $(".viewsubmissions").hide();
    $(".viewaboutus").hide();


    $(".sidebar-dropdown > a").click(function () {
        $(".sidebar-submenu").slideUp(200);
        if (
                $(this)
                .parent()
                .hasClass("active")
                ) {
            $(".sidebar-dropdown").removeClass("active");
            $(this).parent().removeClass("active");
        } else {
            $(".sidebar-dropdown").removeClass("active");
            $(this)
                    .next(".sidebar-submenu")
                    .slideDown(200);
            $(this)
                    .parent()
                    .addClass("active");
        }
    });
    $("#close-sidebar").click(function () {
        $(".page-wrapper").removeClass("toggled");
        $("#show-sidebar").show();
    });
    $("#show-sidebar").click(function () {
        $(".page-wrapper").addClass("toggled");
        $("#show-sidebar").hide();
    });
    $("#viewassignment").click(function () {

    })
    $('#table').DataTable(
            {
                "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
                "iDisplayLength": 5
            });
    $('#table1').DataTable(
            {
                "aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]],
                "iDisplayLength": 10
            });
    $('#table2').DataTable(
            {
                "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
                "iDisplayLength": 5
            });
    $('#table3').DataTable(
            {
                "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
                "iDisplayLength": 5
            });
    $('#table4').DataTable(
            {
                "aLengthMenu": [[10, 50, 100, -1], [10, 50, 100, "All"]],
                "iDisplayLength": 10
            });
    $('#table5').DataTable(
            {
                "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
                "iDisplayLength": 5
            });
});

$(document).ready(function () {

    var id = 0;

    Date.prototype.formatYYYYMMDD = function () {
        return this.getFullYear() +
                "/" + (this.getMonth() + 1) +
                "/" + this.getDate();
    }

    function assignmmentlist() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/assignment/all",
            data: {},
            success: function (data) {
                $('#table').DataTable().clear().draw();
                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    list.push(data[i]["assignmentName"]);
                    list.push(data[i]["assignmentDesc"]);
                    list.push(data[i]["courseId"]["courseName"]);
                    var st = new Date(data[i]["postedOn"]);
                    list.push(st.formatYYYYMMDD());
                    st = new Date(data[i]["startTime"]);
                    list.push(st.formatYYYYMMDD());
                    st = new Date(data[i]["deadline"]);
                    list.push(st.formatYYYYMMDD());
                    if (data[i]["attachment"] != null)
                        list.push("<a target=\"_blank\" href=\"" + "assignment/viewfile/" + data[i]["attachment"] + "\" > <b>Download</b></a>");
                    else
                        list.push("No Attachment present");
                    list.push(data[i]["postedFor"]["groupName"]);
                    list.push(data[i]["postedBy"]["name"]);
                    $("#table").dataTable().fnAddData(list);
                }
            },
            error: function (error) {
                console.log(error)
            }
        });
    }

    $("#viewassignment, #editassignment").click(function (e) {

        $(".home").hide();
        $(".viewassignments").show();
        $(".editassignments").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewuser").hide();
		$(".viewaboutus").hide();
		$(".viewsubmissions").hide();
		
        $("#selectgroups").html("");
        $("#selectcourse").html("");
        $("#selectgroups5").html("");
        $("#selectcourse5").html("");

        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/course/all",
            data: {},
            success: function (data) {
                $.each(data, function (index, item) {
                    $("#selectcourse").append("<option>" + item["courseName"] + "</option>");
                    $("#selectcourse5").append("<option>" + item["courseName"] + "</option>");
                });
            },
            error: function (error) {
                console.log(error)
            }
        });
        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/group/all",
            data: {},
            success: function (data) {
                $.each(data, function (index, item) {
                    $("#selectgroups").append("<option>" + item["groupName"] + "</option>");
                    $("#selectgroups5").append("<option>" + item["groupName"] + "</option>");
                });
            },
            error: function (error) {
                console.log(error)
            }
        });

        assignmmentlist();

        $("#addnewassignment").click(function () {

            assignmmentlist();
        });
    });
    $("#editassignment").click(function (e) {
        $(".home").hide();
        $(".viewassignments").hide();
        $(".viewcourse").hide();
        $(".viewuser").hide();
        $(".viewgroup").hide();
        $(".viewsubmissions").hide();
        $(".editassignments").show();
        $(".viewaboutus").hide();

        function editassignmmentlist() {
            $.ajax({
                type: 'GET',
                url: "http://localhost:8000/app/assignment/all",
                data: {},
                success: function (data) {
                    $('#table1').DataTable().clear().draw();

                    var list = [];
                    for (var i = data.length - 1; i >= 0; i--) {
                        list = [];
                        list.push(data.length - i);
                        list.push(data[i]["assignmentName"]);
                        list.push(data[i]["assignmentDesc"]);
                        list.push(data[i]["courseId"]["courseName"]);
                        var st = new Date(data[i]["startTime"]);
                        list.push(st.formatYYYYMMDD());
                        st = new Date(data[i]["deadline"]);
                        list.push(st.formatYYYYMMDD());
                        if (data[i]["attachment"] != null)
                            list.push("<a target=\"_blank\" href=\"" + "assignment/viewfile/" + data[i]["attachment"] + "\" > <b>Download</b></a>");
                        else
                            list.push("No Attachment present");
                        list.push(data[i]["postedFor"]["groupName"]);
                        list.push(data[i]["postedBy"]["name"]);
                        list.push("<button data-toggle=\"modal\" data-target=\"#editassmodal\" class=\"btn btn-success editassbtn\" id=\"" + data[i]["id"] + "\"><i>edit</i></button>");
                        $("#table1").dataTable().fnAddData(list);


                    }
                    $(".editassbtn").click(function () {
                        id = $(this).attr('id');
                        console.log(id);
                        $.ajax({
                            type: 'GET',
                            url: "http://localhost:8000/app/assignment/get",
                            data: {
                                id: id
                            },
                            success: function (data) {
//                            console.log($(this).attr('id'));
                                $("#assignmentname5").val(data["assignmentName"]);
                                $("#description5").val(data["assignmentDesc"]);
//                                $('#selectgroups5 option:selected').text(data["postedFor"]["groupName"])
//                                $('#selectcourse5 option:selected').text(data["courseId"]["courseName"])
                                $('#deadline5').val(data["deadline"].substring(0, 10));
                                $('#startdate5').val(data["startTime"].substring(0, 10));

                            },
                            error: function (err) {
                                console.log(err)
                            },
                        });

                    });

                    $("#editassignment5").click(function () {
                        var formData = new FormData($("#upload-file-form5")[0]);
                        formData.append("assignmentname", $("#assignmentname5").val());
                        formData.append("course", $('#selectcourse5 option:selected').text());
                        formData.append("deadline", $("#deadline5").val());
                        formData.append("start", $("#startdate5").val());
                        formData.append("description", $("#description5").val());
                        formData.append("postedfor", $('#selectgroups5 option:selected').text());
                        formData.append("id", id);
                        $.ajax({
                            type: 'POST',
                            enctype: 'multipart/form-data',
                            url: "http://localhost:8000/app/assignment/update",
                            data: formData,
                            processData: false,
                            contentType: false,
                            cache: false,
                            timeout: 600000,
                            success: function (data) {
                                console.log(data);
                                editassignmmentlist();
                            },
                            error: function (err) {
                                console.log(err);
                            }
                        });
                    });


                },
                error: function (error) {
                    console.log(err);
                }

            });
        }
        editassignmmentlist();
    });



    $("#addnewassignment").click(function () {
//        var files = $("#files");
        var formData = new FormData($("#upload-file-form")[0]);
        formData.append("assignmentname", $("#assignmentname").val());
        formData.append("postedby", $('#postedby option:selected').text());
        formData.append("course", $('#selectcourse option:selected').text());
        formData.append("deadline", $("#deadline").val());
        formData.append("description", $("#description").val());
        formData.append("start", $("#startdate").val());
        formData.append("postedfor", $('#selectgroups option:selected').text());
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: "http://localhost:8000/app/assignment/",
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                assignmmentlist();
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

    function refreshcourse() {

        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/course/all",
            data: {},
            success: function (data) {
                $('#table2').DataTable().clear().draw();

                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    list.push(data[i]["courseName"]);
                    $("#table2").dataTable().fnAddData(list);
                }

            },
            error: function (error) {
                console.log(error)
            }
        });
    }
    $("#viewcourse").click(function (e) {

        $(".home").hide();
        $(".viewassignments").hide();
        $(".editassignments").hide();
        $(".viewuser").hide();
        $(".viewgroup").hide();
        $(".viewcourse").show();
        $(".viewaboutus").hide();
        $(".viewsubmissions").hide();
        refreshcourse();

    });

    $("#addnewcourse").click(function () {

        $.ajax({
            type: 'POST',
            url: "http://localhost:8000/app/course/",
            data: {
                coursename: $("#coursename").val(),
            },
            success: function (data) {
//                console.log(data);
                refreshcourse();
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

    function refreshgroup() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/group/all",
            data: {},
            success: function (data) {
                $('#table3').DataTable().clear().draw();

                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    list.push(data[i]["groupName"]);
                    list.push(data[i]["groupDesc"]);
                    $("#table3").dataTable().fnAddData(list);
                }

            },
            error: function (error) {
                console.log(error)
            }
        });
    }
    $("#viewgroup").click(function (e) {

        $(".home").hide();
        $(".viewassignments").hide();
        $(".editassignments").hide();
        $(".viewgroup").show();
        $(".viewuser").hide();
        $(".viewcourse").hide();
        $(".viewaboutus").hide();
        $(".viewsubmissions").hide();
        refreshgroup();
    });

    $("#addnewgroup").click(function () {

        $.ajax({
            type: 'POST',
            url: "http://localhost:8000/app/group/",
            data: {
                groupname: $("#groupname").val(),
                groupdesc: $("#groupdesc").val(),
            },
            success: function (data) {
//                console.log(data);
                refreshgroup();
            },
            error: function (err) {
                console.log(err);
            }
        });
    });

    function refreshuser() {
        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/user/all",
            data: {},
            success: function (data) {
                $('#table4').DataTable().clear().draw();

                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    if(data[i]["photo"]!=null)
                    	list.push("<img style=\"height:100px; width:100px;\" src=\"http://localhost:8000/app/user/viewfile/" + data[i]["photo"] + "\">");
                    else
                    	list.push("<img style=\"height:100px; width:100px;\" src=\"http://localhost:8000/app/user/viewfile/images.png\">");
                    list.push(data[i]["userName"]);
                    list.push(data[i]["name"]);
                    list.push(data[i]["roleId"]["roleName"]);
                    list.push(data[i]["groupId"]["groupName"]);
                    list.push(data[i]["active"]);
                    list.push("<button class=\"btn btn-success userbtn\" name=\"" + data[i]["name"] + "\" id=\"" + data[i]["userName"] + "\" data-toggle=\"modal\" data-target=\"#editusermodal\" ><i class=\"fa fa-pencil-alt\" aria-hidden=\"true\"></i></button>");
                    $("#table4").dataTable().fnAddData(list);

                    $(".userbtn").click(function () {
                        $('#username1').val($(this).attr('id'));
                        $('#name1').val($(this).attr('name'));
                    })
                }

            },
            error: function (error) {
                console.log(error)
            }
        });
    }
    $("#viewuser").click(function () {
        $(".home").hide();
        $(".viewassignments").hide();
        $(".editassignments").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewsubmissions").hide();
        $(".viewuser").show();
        $(".viewaboutus").hide();
        $.ajax({
            type: 'GET',
            url: "http://localhost:8000/app/group/all",
            data: {},
            success: function (data) {
                $.each(data, function (index, item) {
                    $("#selectgroups1").append("<option>" + item["groupName"] + "</option>");
                });
            },
            error: function (error) {
                console.log(error)
            }
        });
        refreshuser();

    });

    $("#editnewuser").click(function () {
        var formData = new FormData($("#upload-photo-form")[0]);
        formData.append("username", $('#username1').val());
        formData.append("name", $('#name1').val());
        formData.append("group", $('#selectgroups1 option:selected').text());
        formData.append("role", $('#selectrole1 option:selected').text());
        formData.append("deleted", $('#deleted option:selected').text());
        formData.append("active", $('#active option:selected').text());
        $.ajax({
            type: 'POST',
            enctype: 'multipart/form-data',
            url: "http://localhost:8000/app/user/update",
            data: formData,
            processData: false,
            contentType: false,
            cache: false,
            timeout: 600000,
            success: function (data) {
                refreshuser();
            },
            error: function (err) {
                console.log(err);
            }
        });
    });
    
    $("#viewsubmissions").click(function () {
       $(".home").hide();
        $(".viewassignments").hide();
        $(".editassignments").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewuser").hide();
        $(".viewsubmissions").show();
        $(".viewaboutus").hide();

        $.ajax({
            type: "GET",
            url: "http://localhost:8000/app/submission/all",
            data: {},
            success: function (data) {
                $('#table5').DataTable().clear().draw();
                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    list.push(data[i]["assignmentId"]["assignmentName"]);
                    st = new Date(data[i]["assignmentId"]["deadline"]);
                    list.push(st.formatYYYYMMDD());
                    st = new Date(data[i]["submittedOn"]);
                    list.push(st.formatYYYYMMDD());
                    list.push(data[i]["submittedBy"]["name"]);
                    if (data[i]["attachment"] != null)
                        list.push("<a target=\"_blank\" href=\"" + "/app/submission/viewfile/" + data[i]["attachment"] + "\" > <b>Download</b></a>");
                    else
                        list.push("No Attachment present");
                    $("#table5").dataTable().fnAddData(list);
                }
            },
            error: function (err) {

            }
        })
    })
    
    
    $("#viewaboutus").click(function () {
        $(".home").hide();
        $(".viewassignments").hide();
        $(".editassignments").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewuser").hide();
        $(".viewaboutus").show();
        $(".viewsubmissions").hide();
    });
    
    $("#home").click(function () {
        $(".home").show();
        $(".viewassignments").hide();
        $(".editassignments").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewuser").hide();
        $(".viewsubmissions").hide();
        $(".viewaboutus").hide();
    });



    $.ajax({
        type: 'GET',
        url: "http://localhost:8000/app/user/currentuser",
        data: {},
        success: function (data) {
            console.log(data["photo"]);
            if(data["photo"]!=null)
                $("#profilepic").attr("src", "http://localhost:8000/app/user/viewfile/" + data["photo"]);
        },
        error: function (err) {
            console.log(err);
        }
    })

});