var forEach = function(arr, cb){
  for(var i = 0 ; i < arr.length; i+=1){
     cb(arr[i], i, arr)
  }
}

var listingPage =  function(carList){

  var bigStr = ''
  bigStr += '<div class="jumbotron main-header">'
  bigStr += '<h1>Antique and Unique<br/>Cars and Coffee</h1>'
  bigStr += '</div>'
  bigStr += '<table class="cars-attending text-center">'
  bigStr += '<thead class="text-center">'
  bigStr += '<tr>'
  bigStr += '<th>  Make  </th>'
  bigStr += '<th> Model  </th>'
  bigStr += '<th>  Year </th>'
  bigStr += '</tr>'
  bigStr += '<tbody class="car-tab">'

  forEach(carList, function(serverRes){
    bigStr += '<tr>'
    bigStr +=  '<td>'+ serverRes.make + '</td>'
    bigStr +=  '<td>' + serverRes.model + '</td>'
    bigStr +=  '<td>'+ serverRes.year + '</td>'
    bigStr += '</tr>'
})
  bigStr += '</tbody>'
  bigStr += '</table>'

// })
document.querySelector('#app-container').innerHTML = bigStr

  }
