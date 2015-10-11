$('document').ready(function () {
    var query = window.location.search.substring(1);
    var vars = query.split("=");
    var id = vars[1].split("&")[0];
    var type = vars[2].split("$")[0];

    $.ajax({
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/" + type + "/" + id,
        type: "GET",
        dataType: "JSON",
        success: function (result) {
            if (type === "person") {
                $("#picture").append(
                        "<img src='img/person.png' height='300' alt=''/>"
                        );
                
                $(".data").append(
                        "<tr><td width='100'><strong>Firstname: </strong></td><td>" + result.firstName + "</td></tr>" +
                        "<tr><td><strong>Lastname: </strong></td><td>" + result.lastName + "</td></tr>" +
                        "<tr><td><strong>Email: </strong></td><td>" + result.email + "</td></tr>"
                        );
            } else if (type === "company") {
                $("#picture").append(
                        "<img src='img/company.png' height='300' alt=''/>"
                        );
                $(".data").append(
                        "<tr><td width='100'><strong>Name: </strong></td><td>" + result.name + "</td></tr>" +
                        "<tr><td><strong>CVR: </strong></td><td>" + result.cvr + "</td></tr>" +
                        "<tr><td><strong>Value: </strong></td><td>" + result.marketValue + "</td></tr>" +
                        "<tr><td><strong>Employees: </strong></td><td>" + result.numberEmployees + "</td></tr>" +
                        "<tr><td><strong>Description: </strong></td><td>" + result.description + "</td></tr>"
                        );
            }
        }
    });
});

