$('document').ready(function () {
    var query = window.location.search.substring(1);
    var vars = query.split("=");
    var searchword = vars[1].split("&")[0];

    $.ajax({
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/person/searchlist/" + searchword,
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            for (var i = 0, max = data.length; i < max; i++) {
                var result = data[i];

                if (data[i].firstName !== null) {
                    $(".data").append(
                            "<tr><td id='"+result.id+"'><img src='img/person.png' height='100' alt=''/></td><td id='"+result.id+"'>" + result.firstName + " " + result.lastName + "</td>" +
                            "<td id='"+result.id+"'>" + result.email + "</td></tr>"
                            );
                }
            }
        },
        error: function (data, code) {
            
        }

    });
    
    $.ajax({
        url: "https://ca-thisisanewtest.rhcloud.com/CA2SEM3/api/company/searchlist/" + searchword,
        type: "GET",
        dataType: "JSON",
        success: function (data) {
            for (var i = 0, max = data.length; i < max; i++) {
                var result = data[i];

                if (data[i].name !== null) {
                    $(".data").append(
                            "<tr><td id='"+result.id+"'><img src='img/company.png' height='100' alt=''/></td><td id='"+result.id+"'>" + result.name + "</td>" +
                            "<td id='"+result.id+"'>" + result.email + "</td></tr>"
                            );
                }
            }
        },
        error: function (data, code) {
            
        }

    });
    
});

