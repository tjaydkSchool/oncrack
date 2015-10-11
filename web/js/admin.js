var entityType = "Person";
var entityId = 0;

$('document').ready(function () {
    $.ajax({
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/complete",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            for (var i = 0, max = data.length; i < max; i++) {
                var result = data[i];
                $(".content").append(
                        "<tr><td>" + result.id + "</td><td>" + result.firstName + " " + result.lastName + "</td><td class='editBtn' id='" + result.id + "'>Update</td><td class='editBtn' id='" + result.id + "'>Delete</td></tr>"
                        );
            }
        }
    });
});

$("ul").click(function (event) {
    var type = event.target.id;

    switch (type) {
        case "Person":
            entityType = "Person";
            $.ajax({
                url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/complete",
                type: "GET",
                dataType: "JSON",
                success: function (data) {
                    $(".content").empty();
                    for (var i = 0, max = data.length; i < max; i++) {
                        var result = data[i];
                        $(".content").append(
                                "<tr><td>" + result.id + "</td><td>" + result.firstName + " " + result.lastName + "</td><td class='editBtn' id='" + result.id + "'>Update</td><td class='editBtn' id='" + result.id + "'>Delete</td></tr>"
                                );
                    }
                }
            });
            break;

        case "Company":
            entityType = "Company";
            $.ajax({
                url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/company/complete",
                type: "GET",
                dataType: "JSON",
                success: function (data) {
                    $(".content").empty();
                    for (var i = 0, max = data.length; i < max; i++) {
                        var result = data[i];
                        $(".content").append(
                                "<tr><td>" + result.id + "</td><td>" + result.name + "</td><td class='editBtn' id='" + result.id + "'>Update</td><td class='editBtn' id='" + result.id + "'>Delete</td></tr>"
                                );
                    }
                }
            });
            break;

        case "LogOut":
            document.location.href = "../index.jsp";
            break;
    }
    ;
});


//UPDATE OR DELETE
$("table").click(function (event) {
    var id = event.target.id;
    var action = $(event.target).text();
    if (id !== "" || id !== null) {
        if (action === "Update") {
            switch (entityType) {
                case "Person":
                    $.ajax({
                        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/" + id,
                        type: "GET",
                        dataType: "JSON",
                        success: function (data) {
                            $("#fname-update").val(data.firstName);
                            $("#lname-update").val(data.lastName);
                            $("#email-update").val(data.email);
                            $("#id-update").val(data.id);
                        }
                    });
                    $("#myModalUpdate").modal("toggle");
                    break;
                case "Company" :

                    break;
            }
        } else if (action === "Delete") {
            var c = confirm("Are you sure you want to delete this entity?");
            if (c) {
                switch (entityType) {
                    case "Person":
                        $.ajax({
                            url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/" + id,
                            type: "DELETE",
                            dataType: "JSON",
                            success: function (data) {
                                alert("Person with id: " + id + " deleted");
                                var element = document.getElementById(id);
                                element.parentNode.remove();
                            }
                        });
                        break;
                    case "Company" :
                        $.ajax({
                            url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/company/" + id,
                            type: "DELETE",
                            dataType: "JSON",
                            success: function (data) {
                                alert("Company with id: " + id + " deleted");
                                var element = document.getElementById(id);
                                element.parentNode.remove();
                            }
                        });
                        break;
                }
            }
        }
    }
});

$("#saveBtn-update").click(function () {
    var fname = $("#fname-update").val();
    var lname = $("#lname-update").val();
    var email = $("#email-update").val();
    var id = $("#id-update").val();

    var object = {'id': id, 'firstName': fname, 'lastName': lname, 'email': email};


    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        data: JSON.stringify(object),
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/" + id,
        type: "PUT",
        dataType: "JSON",
        success: function (data) {
            var element = document.getElementById(id);
            element.parentNode.remove();
            $(".content").append(
                    "<tr><td>" + data.id + "</td><td>" + data.firstName + " " + data.lastName + "</td><td class='editBtn' id='" + data.id + "'>Update</td><td class='editBtn' id='" + data.id + "'>Delete</td></tr>"
                    );
            $("#myModalUpdate").modal("toggle");
        }
    });
});

//END UPDATE DELETE

$("select").click(function (event) {
    var selected = $(".entity-dropdown option:selected").text();
    if (selected !== "" || selected !== null) {
        switch (selected) {
            case "Person":
                $(".form-body").html(
                        "<strong>Firstname: </strong><input id='fname' type='text' name='fname' placeholder='Enter firstname'/><br>" +
                        "<strong>Lastname: </strong><input id='lname' type='text' name='lname' placeholder='Enter lastname'/><br>" +
                        "<strong>Email: </strong><input id='email' type='email' name='email' placeholder='Enter email'/>"
                        );
                break;
        }
    }
});

$("#populate").click(function () {
    
    $.ajax({
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/populate",
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            alert("Database generated");
        }
    });
});


$("#saveBtn").click(function (event) {
    var fname = $("#fname").val();
    var lname = $("#lname").val();
    var email = $("#email").val();

    var object = {'firstName': fname, 'lastName': lname, 'email': email};


    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/",
        type: "POST",
        data: JSON.stringify(object),
        dataType: "JSON",
        success: function (data) {
            $("#fname").val("");
            $("#lname").val("");
            $("#email").val("");
            $("#myModal").modal("toggle");



            $.ajax({
                url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/fullname/" + fname + "/" + lname,
                type: "GET",
                dataType: "JSON",
                success: function (data) {
                    $(".content").append(
                            "<tr><td>" + data.id + "</td><td>" + data.firstName + " " + data.lastName + "</td><td class='editBtn' id='" + data.id + "'>Update</td><td class='editBtn' id='" + data.id + "'>Delete</td></tr>"
                            );
                }
            });
        }
    });
});