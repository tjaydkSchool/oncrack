$("#searchField").keyup(function () {
    var searchWord = $("#searchField").val();
    if (true) {
        $.ajax({
            url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/search/" + searchWord,
            type: "GET",
            dataType: 'JSON',
            success: function (data, textStatus, jqXHR) {
                if (data !== null) {
                    $("#searchListPerson").empty();
                    data.forEach(function (result) {
                        $("#searchListPerson").append("<tr id='" + result.id + "'><td><i id='" + result.id + "' class='glyphicon glyphicon-user'></i></td><td id='" + result.id + "'>" + result.firstName + "</td><td>" + result.lastName + "</td></tr>");
                    });
                } else {
                    $("#searchListPerson").empty();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#searchListPerson").empty();
            },
            complete: function (jqXHR, textStatus) {

            }
        });
    }
});

$("#searchField").keyup(function (e) {
    var searchWord = $("#searchField").val();

    var code = e.keyCode || e.which;
    if (code == 13) { //Enter keycode
        if (searchWord === null || searchWord === "") {
            alert("Skriv venligst et søgeord i søgefeltet");
        } else {
            search(searchWord);
        }
    }

    if (true) {
        $.ajax({
            url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/company/search/" + searchWord,
            type: "GET",
            dataType: 'JSON',
            success: function (data, textStatus, jqXHR) {
                if (data !== null) {
                    $("#searchListCompany").empty();
                    data.forEach(function (result) {
                        $("#searchListCompany").append("<tr id='" + result.id + "'><td><i id='" + result.id + "' class='glyphicon glyphicon-briefcase'></i></td><td id='" + result.id + "'>" + result.name + "</td></tr>");
                    });
                } else {
                    $("#searchListCompany").empty();
                }
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $("#searchListCompany").empty();
            },
            complete: function (jqXHR, textStatus) {

            }
        });
    }
});

$("table").click(function (event) {
    var id = event.target.id;
    $.ajax({
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/infoentity/" + id,
        type: "GET",
        dataType: "JSON",
        success: function (data) {
//            IN CASE COMPANY
            if (data.firstName === "undefined" || data.firstName == "" || data.firstName == null) {
                document.location.href = "result.html?id=" + id + "&type=company";
            }
//            IN CASE PERSON
            else {
                document.location.href = "result.html?id=" + id + "&type=person";
            }
        }
    });
});

$("#searchButton").click(function () {
    var searchWord = $("#searchField").val();
    if (searchWord === null || searchWord === "") {
        alert("Skriv venligst et søgeord i søgefeltet");
    } else {
        search(searchWord);
    }
});

function search(searchword) {
    document.location.href = "resultlist.html?search=" + searchword;
};

$("#admin").mouseenter(function() {
    $("#admin").css("right", "0px");
    $("#admin").css("background-color", "rgba(213,0,25,1)");
});

$("#admin").mouseleave(function() {
    $("#admin").css("right", "-305px");
    $("#admin").css("background-color", "rgba(213,0,25,0.9)");
});

$("#admin").click(function() {
   document.location.href = "admin/administrator.html" 
});