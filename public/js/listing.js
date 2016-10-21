

var listingPage =  function(carList){

  var bigStr = ''
  bigStr += '<div class="jumbotron main-header">'
  bigStr += '<h1>Antique and Unique<br/>Cars and Coffee</h1>'
  bigStr += '<table class="cars-attending">'
  bigStr += '<thead>'
  bigStr += '<tr>'
  bigStr += '<th>  Make  </th>'
  bigStr += '<th> Model  </th>'
  bigStr += '<th>  Year </th>'
  bigStr += '</tr>'
  bigStr += '<tbody>'

  // forEach(carList,function(returnData){
  //   bigStr += '<tr>'
  //   bigStr +=  '<td>'+ returnData.make + '</td>'
  //   bigStr +=  '<td>' +returnData.model + '</td>'
  //   bigStr +=  '<td>'+ returnData.year + '</td>'
  //   bigStr += '</tr>'

  bigStr += '</tbody>'
  bigStr += '</table>'
  bigStr += '</div>'
// })
document.querySelector('#app-container').innerHTML = bigStr

  }
