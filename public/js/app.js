console.log('wired up!')
console.log($)
console.log(_)
console.log(Backbone)


//
var routerController = function(){
   var currentHash = window.location.hash.slice(1)
    console.log(currentHash.length)
   if(currentHash.length === 0) { return showHomePage() }

   var currentHashComponents = currentHash.split('/')
   console.log(currentHashComponents)
// //
   var currentHashPrefix = currentHashComponents[0]
   var currentHashSuffix = currentHashComponents[1]
   console.log(currentHashPrefix)
  //  console.log(currentHashSuffix)
    switch(currentHashPrefix){
      case "auth":
         showAuthPage();
         break;

//       // case "user-profile":
//       //    showUserProfile(currentHashSuffix);
//       //    break;
//       //
//       // default:
//         //  document.querySelector('#app-container').innerHTML = "<h1 class='bg-warning'>PAGE NOT FOUND</h1>";
    }
//
}
// //
// // var authenticateUser = function(evt){
// //    evt.preventDefault()
// //    console.log("email", evt.target.email.value)
// //    console.log("passord", evt.target.password.value)
// //    var dataForServer = {
// //       email: evt.target.email.value,
// //       password: evt.target.password.value
// //    }
// //
// //    console.log('to server:', JSON.stringify(dataForServer))
// //
// //    $.post( '/login', JSON.stringify(dataForServer) ).then(function(godKnowsWhat){
// //       console.log('Success !!!!')
// //       window.location.hash = "user-profile/"+dataForServer.email
// //    })
// //
// // }
// //
// var authPage = function (){
//    showAuthPage()
// // //
// }
// // var signInBtn = document.querySelector('.sign-in-btn')
// //
window.addEventListener('hashchange', routerController)
// document.addEventListener('click',  authPage)
routerController()
