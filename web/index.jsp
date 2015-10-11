<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" type="image/png" href="img/favicon.png" />
        <title>Crack.dk - Søgemaskinen for crackheads</title>

        <!--STYLESHEETS-->
        <link href="css/bootstrap-theme.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <link href="css/custom.css" rel="stylesheet" type="text/css"/>

        <!--FONTS-->
    <a href="fonts/glyphicons-halflings-regular.eot"></a>
    <link href='https://fonts.googleapis.com/css?family=Lato' rel='stylesheet' type='text/css'>
</head>
<body>
    <!--TOP CONTENT-->
    <div class="top-content">

        <video id="bg-Vid" width="100%" height="auto" autoplay loop>
            <source src="vid/bgClip.mp4" type="video/mp4">
        </video>

        <!--BRAND-->
        <div id="brand">
            <a href="#"><img src="img/krak-logo-desktop2.png" alt=""/></a>
        </div>

        <br>


        <!--INPUTFIELD AND BUTTON-->
        <div class="input-search">
            <input type="text" name="search" id="searchField" placeholder="Søg efter firma eller person." />
            <span id='searchButton'>
                <i class="glyphicon glyphicon-search"></i>
            </span>


            <!--SEARCH RESULTS-->
            <div class="search-result">
                <table class='searchList' id="searchListPerson">
                </table>
                <table class='searchList' id="searchListCompany">
                </table>
            </div>
        </div>
    </div>

    <!--ADMINISTRATOR MENU-->
    <div id="admin">
        <i class="glyphicon glyphicon-cog"></i>
    </div>
    

    <!--FOOTER-->
    <div class="footer-text text-center">
        <div>
            <p>All rights reserved   |   2015   |   Dennis & Andreas   |   Klasse A</p>
        </div>
    </div>




    <script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
    <script src="js/bootstrap.min.js" type="text/javascript"></script>
    <script src="js/custom.js" type="text/javascript"></script>
</body>
</html>
