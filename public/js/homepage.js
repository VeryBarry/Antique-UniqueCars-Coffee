
var showHomePage = function() {
  bigStr = ''
  bigStr +=   '<div class="jumbotron main-header">'
  bigStr +=     '<h1><span style="font-size:90px">Antique and Unique</span><br/>Cars and Coffee</h1>'
  bigStr +=     '<h3>The place where Jalopies and Java come together!</h3>'
  // bigStr +=      '<p class="sign-in-btn"><a class="btn btn-primary btn-lg" href="#auth" role="button">Sign In</a></p>'
  bigStr +=   '</div>' //<!-- End jumbotron -->
  bigStr +=   '<div class="container">'
  bigStr +=     '<div class="row">'
  bigStr +=       '<div class="col-sm-6">'
  bigStr +=         '<div class="intro">'
  bigStr +=         '</div>'
  bigStr +=       '</div>'
  bigStr +=       '<div class="col-xs-12 col-sm-6 input-container">'
  bigStr +=         '<div class="input-group input-group-lg input-box">'
  bigStr +=           '<h3>Join Us Today!</h3>'
  bigStr +=           '<input type="text" class="form-control screenName-input" placeholder="Screen Name" aria-describedby="sizing-addon1">'
  bigStr +=           '<input type="text" class="form-control name-input car-make" placeholder="Car make" aria-describedby="sizing-addon1">'
  bigStr +=           '<input type="text" class="form-control name-input car-model" placeholder="Car model" aria-describedby="sizing-addon1">'
  bigStr +=           '<input type="text" class="form-control name-input car-year" placeholder="Car year" aria-describedby="sizing-addon1">'
  bigStr +=           '<input type="text" class="form-control name-input pswd-input" placeholder="Password" aria-describedby="sizing-addon1">'
  bigStr +=           '<a href="#auth"<button type="button" class="btn btn-default sign-up-btn">sign up</button></a>'
  bigStr +=         '</div>'
  bigStr +=       '</div>'
  bigStr +=     '</div>' //<!-- End of row -->
  bigStr +=   '</div>' //<!-- End of container -->
  bigStr +=   '<div class ="container photo-container">'
  bigStr +=     '<div class ="row">'
  bigStr +=       '<div class="col-sm-3">'
  bigStr +=         '<img src="images/Ford-Pinto.jpg"/>'
  bigStr +=       '</div>'
  bigStr +=       '<div class="col-sm-3">'
  bigStr +=         '<img src="images/6G6FWUh.jpg"/>'
  bigStr +=       '</div>'
  bigStr +=       '<div class="col-sm-3">'
  bigStr +=           '<img src="images/vanArtHeavyDotCom-460x279.png"/>'
  bigStr +=       '</div>'
  bigStr +=       '<div class="col-sm-3">'
  bigStr +=         '<img src="images/Very funky custome sport car picture.png"/>'
  bigStr +=       '</div>'
  bigStr +=     '</div>'
  bigStr +=   '</div>'



  document.querySelector('#app-container').innerHTML = bigStr
  var signInBtn = document.querySelector('.sign-in-btn')
}