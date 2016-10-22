// console.log('wired up!')
// console.log($)
// console.log(_)
// console.log(Backbone)


// =======  Two different URL's to use(Travis or back-end) =======
var BACKEND_URL = "https://quik-spitter-api.herokuapp.com/api"
// var BACKEND_URL = ""


var routerController = function(){
   var currentHash = window.location.hash.slice(1)

   if(currentHash.length === 0) {
     showHomePage();
     var newCarForm = document.querySelector('#new-car-form');
     newCarForm.addEventListener('submit',  saveUserCarInfo);

      return;
   }

   var currentHashComponents = currentHash.split('/');

   var currentHashPrefix = currentHashComponents[0];
   var currentHashSuffix = currentHashComponents[1];

    switch(currentHashPrefix){

      case "listing":
        $.getJSON(BACKEND_URL + "/car").then(function(serverRes){

          showListingPage(serverRes);
          // console.log(serverRes)
          document.querySelector('#app-container').innerHTML;
      })
        break;
    }
}

var saveUserCarInfo = function(evt){
  evt.preventDefault();

  document.querySelector('.car-make');
  document.querySelector('.car-model');
  document.querySelector('.car-year');

  var makeFormVal = evt.target.make.value;
  var modelFormVal = evt.target.model.value;
  var yearFormVal = evt.target.year.value;

  var objForDatabase = {
    "make": makeFormVal,
    "model": modelFormVal,
    "year": yearFormVal
  }

  var jsonStr = JSON.stringify(objForDatabase);

  var postReqSettings =  {
      url: BACKEND_URL + '/add-car',
      data: jsonStr,
      headers: {'Content-Type': 'application/json'}
    }

  $.post(postReqSettings)
    .then(function(serverRes){

      window.location.hash = "listing"
    })
}

window.addEventListener('hashchange', routerController);
routerController();
