/**
 * @author Bruno Furtado
 * @author Rhonan Carneiro
 */
function initialize() {
	
	var mapOptions = {
		center : new google.maps.LatLng(-10.055403, -47.636719),
		zoom : 4,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	var map = new google.maps.Map($("#map_canvas").get(0), mapOptions);
	
	carregarMunicipios(map);
}

function carregarMunicipios(map){
	var markers = [];
	
	$.ajax({
		type: "get",
		url: "./ListaMunicipios",
		dataType: "json",
		success: function(result){
			 
			$.each(result, function(key, val){
				var municipio = new google.maps.Marker({
					position: new google.maps.LatLng(val.latitude ,val.longitude),
					map: map,
					title: val.nome
				});
				
				var info = new google.maps.InfoWindow({
					content: val.nome
				});
				
				
				google.maps.event.addListener(municipio,'click', function(){
					google.load('visualization', '1.0', {'packages':['corechart'], callback:drawChart});
			        function drawChart(){
						var data = new google.visualization.DataTable();
				        data.addColumn('string', 'Topping');
				        data.addColumn('number', 'Slices');
				        data.addRows([
				          ['Mushrooms', val.id],
				          ['Onions', 1],
				          ['Olives', 1],
				          ['Zucchini', 1],
				          ['Pepperoni', 2]
				        ]);
				        var options = {'title':'How Much Pizza I Ate Last Night',
			                       'width':400,
			                       'height':300};
				        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
			        	chart.draw(data, options);
			        	info.open(map,municipio);
			        }
			        
			   });
				
				markers.push(municipio);
				
			});
	
			var markerCluster = new MarkerClusterer(map, markers);
		
		}
	});
		
}