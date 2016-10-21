// console.log('wired up!')
// console.log($)
// console.log(_)
// console.log(Backbone)
// var BACKEND_URL = "https://quik-spitter-api.herokuapp.com/api"
var BACKEND_URL = ""


var routerController = function(){
   var currentHash = window.location.hash.slice(1)
    // console.log("hash length", currentHash.length)
   if(currentHash.length === 0) {
     showHomePage()
     var newCarForm = document.querySelector('#new-car-form')
     newCarForm.addEventListener('submit',  saveUserCarInfo)
      // console.log(newCarForm)
      return;
   }

   var currentHashComponents = currentHash.split('/')
  //  console.log("components:", currentHashComponents)

   var currentHashPrefix = currentHashComponents[0]
   var currentHashSuffix = currentHashComponents[1]

  //  console.log("prefix:", currentHashPrefix)
  //  console.log(currentHashSuffix)
    switch(currentHashPrefix){

      case "listing":
        $.getJSON(BACKEND_URL + "/car").then(function(serverRes){

          showListingPage(serverRes);
          console.log(serverRes)
          document.querySelector('#app-container').innerHTML
      })
          // showListingPage(serverRes)
          // console.log("serRes", serverRes)
        break;
    }
}

var saveUserCarInfo = function(evt){
  evt.preventDefault()
  // console.log(evt.target)
  document.querySelector('.car-make')
  document.querySelector('.car-model')
  document.querySelector('.car-year')
  // console.log(evt.target)
  var makeFormVal = evt.target.make.value
  var modelFormVal = evt.target.model.value
  var yearFormVal = evt.target.year.value
  // console.log(makeFormVal)

  var objForDatabase = {
    "make": makeFormVal,
    "model": modelFormVal,
    "year": yearFormVal
  }
  // console.log(objForDatabase)

  var jsonStr = JSON.stringify(objForDatabase)

  var postReqSettings =  {
      url: BACKEND_URL + '/add-car',
      data: jsonStr,
      headers: {'Content-Type': 'application/json'}
    }

  $.post(postReqSettings)
  // $.post('/add-car', JSON.stringify(postReqSettings))
    .then(function(serverRes){
      // console.log(serverRes)
      window.location.hash = "listing"
    })
}

window.addEventListener('hashchange', routerController)
routerController()
