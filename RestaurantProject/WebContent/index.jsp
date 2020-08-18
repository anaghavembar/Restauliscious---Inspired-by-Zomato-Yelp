<!DOCTYPE html>
<html lang="en">
<head>
  <title>RestauLicious!!</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <style>
	body {background-color: #BDB76B;}
	h1   {color: #228B22;}
	
</style>
</head>
<body>

<div class="container">
  <table width="100%"><tr><td width="50%"><h1 >RestauLicious</h1></td><td width="50%" align="right"><a href="RestaurantServlet" style=color:#228B22>List Restaurants</a></td></tr></table>
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <img src="images/pasta.jpeg" alt="Pasta" style="width:100%;">
      </div>

      <div class="item">
        <img src="images/salad.jpg" alt="Salad" style="width:100%;">
      </div>
    
      <div class="item">
        <img src="images/pizza.jpg" alt="pizza" style="width:100%;">
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

</body>
</html>
