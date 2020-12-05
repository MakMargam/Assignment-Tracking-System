
jQuery(function ($) {

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
                "aLengthMenu": [[5, 10, 25, -1], [5, 10, 25, "All"]],
                "iDisplayLength": 5
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
});

$(document).ready(function () {




    var id = 0;

    Date.prototype.formatYYYYMMDD = function () {
        return this.getFullYear() +
                "/" + (this.getMonth() + 1) +
                "/" + this.getDate();
    }
    var template1 = '<div class="card ##bgprimary##">' +
            '<div class="card-header " id="header##button##">' +
            '<span style="font-size: 20px;" ><strong>##AssignmentName##</strong></span>' +
            '<div class = "float-right ml-auto">##PostedOn##</div></div><div class="card-body">' +
            '<p class="card-text">##description##</p><br><a href="#">##download##</div>' +
            '<div class="card-footer">' +
            '</a><form id="file##button##"> Upload Assignment  : <input type="file"  name="files" style="width:200px;%;" value="Select file"></file> <div id="##button##" class="btn btn-outline-success btn-sm subbtn">Upload</div>' +
            '<span class = "float-right ml-auto ml-3">##PostedBy## </span>' +
            '<span class = "mr-2 float-right ml-auto">  ##PostedFor##</span></div></div><br>';

    function assignmmentlist() {
        $.ajax({
            type: 'GET',
            url: "/assignment/all",
            data: {},
            success: function (data) {
            if(data.length == 0){
            	$(".viewassignments").html("<img src=\"/libs/no_assignments.jpg\" style=\"width:90%; height:90%;\" />");
            }
            else{
                $(".viewassignments").html("");
                for (var i = data.length - 1; i >= 0; i--) {
                    var d;
                    $.ajax({
                        type: 'GET',
                        async: false,
                        url: '/submission/defaulter',
                        data: {
                            assignment: data[i]["id"]
                        },
                        success: function (data1) {
                            d=data1;

                            
                        },
                        error: function (err) {
                            console.log(err)
                        }
                    })
//                    console.log(d);
//==================================================================================
                    var temp = template1;
                    if(d.length==0)
                        temp = temp.replace("##bgprimary##", "border-danger");
                    else
                        temp = temp.replace("##bgprimary##", "border-success");
                    if (data[i]["attachment"] == null)
                        temp = temp.replace("##download##", "No Attachments Present");
                    else
                        temp = temp.replace("##download##", "<a target=\"_blank\" href=\"" + "/assignment/viewfile/" + data[i]["attachment"] + "\" > <b>Download</b></a>");
                    temp = temp.replace("##AssignmentName##", data[i]["assignmentName"]);
                    var st = new Date(data[i]["postedOn"]);
                    temp = temp.replace("##PostedOn##", "Posted On : " + st.formatYYYYMMDD());
                    temp = temp.replace("##description##", data[i]["assignmentDesc"]);
                    temp = temp.replace("##PostedBy##", "<b>Posted by : </b>" + data[i]["postedBy"]["name"]);
                    temp = temp.replace("##button##", data[i]["id"]);
                    temp = temp.replace("##button##", data[i]["id"]);
                    temp = temp.replace("##button##", data[i]["id"]);
                    temp = temp.replace("##PostedFor##", "<b>Posted for : </b>" + data[i]["postedFor"]["groupName"] + ",");
                    $(".viewassignments").append(temp);
                }
                }
                // Submit Assignment
                $(".subbtn").click(function () {
                    var a = "#file" + $(this).attr("id");
                    console.log(a);
                    var formData = new FormData($(a)[0]);
                    formData.append("assignment", $(this).attr("id"));

                    $.ajax({
                        type: "POST",
                        enctype: 'multipart/form-data',
                        url: "/submission",
                        data: formData,
                        processData: false,
                        contentType: false,
                        cache: false,
                        timeout: 600000,
                        success: function (data) {
//                            console.log(data)
//                            $(".home").show();
//                            $(".home1").hide();
//                            $(".viewassignments").hide();
//                            $(".viewcourse").hide();
//                            $(".viewgroup").hide();
//                            $(".viewuser").hide();
                            assignmmentlist();
                            alert("Assignment Submitted !!");
                        },
                        error: function (err) {
                            console.log(err)
                        },
                    })
                })

            },
            error: function (error) {
                console.log(error)
            }
        });

    }

    $("#viewassignment").click(function (e) {

        $(".home").hide();
        $(".home1").hide();
        $(".viewassignments").show();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewuser").hide();
        $(".viewaboutus").hide();

        $("#selectgroups").html("");
        $("#selectcourse").html("");
        $("#selectgroups5").html("");
        $("#selectcourse5").html("");

        $.ajax({
            type: 'GET',
            url: "/course/all",
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
            url: "/group/all",
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

    });

    $("#addnewassignment").click(function () {
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
            url: "/assignment/",
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
            url: "/course/all",
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
        $(".home1").hide();
        $(".viewuser").hide();
        $(".viewgroup").hide();
        $(".viewaboutus").hide();
        $(".viewcourse").show();
        refreshcourse();

    });

    $("#addnewcourse").click(function () {

        $.ajax({
            type: 'POST',
            url: "/course/",
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
            url: "/group/all",
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
        $(".home1").hide();
        $(".viewassignments").hide();
        $(".viewgroup").show();
        $(".viewuser").hide();
        $(".viewcourse").hide();
        $(".viewaboutus").hide();
        refreshgroup();
    });

    $("#addnewgroup").click(function () {

        $.ajax({
            type: 'POST',
            url: "/group/",
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
            url: "/user/all",
            data: {},
            success: function (data) {
                $('#table4').DataTable().clear().draw();

                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    if(data[i]["photo"]!=null)
                    	list.push("<img style=\"height:100px; width:100px;\" src=\"/user/viewfile/" + data[i]["photo"] + "\">");
                    else
                    	list.push("<img style=\"height:100px; width:100px;\" src=\"/user/viewfile/images.png\">");
                    list.push(data[i]["userName"]);
                    list.push(data[i]["name"]);
                    list.push(data[i]["roleId"]["roleName"]);
                    list.push(data[i]["groupId"]["groupName"]);
                    list.push(data[i]["active"]);
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
        $(".home1").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewaboutus").hide();
        $(".viewuser").show();
        $.ajax({
            type: 'GET',
            url: "/group/all",
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

    $.ajax({
        type: 'GET',
        url: "/user/currentuser",
        data: {},
        success: function (data) {
            if (data["photo"] != null)
                $("#profilepic").attr("src", "/user/viewfile/" + data["photo"]);
        },
        error: function (err) {
            console.log(err);
        }
    })

    $("#mysubmissions").click(function () {
        $("#show-sidebar").hide();
        $(".home").show();
        $(".viewassignments").hide();
        $(".home1").hide();
        $(".viewcourse").hide();
        $(".viewgroup").hide();
        $(".viewuser").hide();
        $(".viewaboutus").hide();

        $.ajax({
            type: "GET",
            url: "/submission/userall",
            data: {},
            success: function (data) {
//                console.log(data);
                $('#table').DataTable().clear().draw();
                var list = [];
                for (var i = data.length - 1; i >= 0; i--) {
                    list = [];
                    list.push(data.length - i);
                    list.push(data[i]["assignmentId"]["assignmentName"]);
                    st = new Date(data[i]["assignmentId"]["deadline"]);
                    list.push(st.formatYYYYMMDD());
                    st = new Date(data[i]["submittedOn"]);
                    list.push(st.formatYYYYMMDD());
                    if (data[i]["attachment"] != null)
                        list.push("<a target=\"_blank\" href=\"" + "/submission/viewfile/" + data[i]["attachment"] + "\" > <b>Download</b></a>");
                    else
                        list.push("No Attachment present");
                    $("#table").dataTable().fnAddData(list);
                }
            },
            error: function (err) {

            }
        })


    })    
    $("#viewaboutus").click(function () {
	    $("#show-sidebar").hide();
	    $(".home").hide();
	    $(".viewassignments").hide();
	    $(".home1").hide();
	    $(".viewcourse").hide();
	    $(".viewgroup").hide();
	    $(".viewuser").hide();
        $(".viewaboutus").show();
    });

    $("#show-sidebar").hide();
    $(".home").hide();
    $(".viewassignments").show();
    $(".home1").hide();
    $(".viewcourse").hide();
    $(".viewgroup").hide();
    $(".viewuser").hide();
    $(".viewaboutus").hide();
    assignmmentlist();

});