var forEach = function(arr, cb){
  for(var i = 0 ; i < arr.length; i+=1){
     cb(arr[i], i, arr)
  }
}

var showListingPage =  function(carList){

  var bigStr = ''
  bigStr += '<div class="jumbotron main-header">'
  bigStr +=   '<h1>Antique & Unique<br/>Cars & Coffee</h1>'
  bigStr +=   '<h2 class="showEntrants">Entrants to the up-coming show!!</h2>'
  bigStr += '</div>'
  bigStr += '<table class="cars-attending text-center">'
  bigStr += '<thead class="text-center">'
  bigStr +=   '<tr>'
  bigStr +=     '<th class="headerNames">  Make  </th>'
  bigStr +=     '<th class="headerNames"> Model  </th>'
  bigStr +=     '<th class="headerNames">  Year </th>'
  bigStr +=   '</tr>'
  bigStr += '<tbody class="car-tab">'

  forEach(carList,function(serverRes){
    bigStr += '<tr>'
    bigStr +=  '<td class="entryRows">'+ serverRes.make + '</td>'
    bigStr +=  '<td class="entryRows">' + serverRes.model + '</td>'
    bigStr +=  '<td class="entryRows">'+ serverRes.year + '</td>'
    bigStr += '</tr>'
  })

  bigStr += '</tbody>'
  bigStr += '</table>'


  document.querySelector('#app-container').innerHTML = bigStr
  bigStr += '</div>'


  }
