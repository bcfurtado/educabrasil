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
					title: val.nome,
				});
				
				var info = new google.maps.InfoWindow({
					content: val.nome
				});
				
				
				google.maps.event.addListener(municipio,'click', function(){
			        	info.open(map,municipio);
			        	//window.open("./PegarMunicipio");
//						$(".second").pageslide({ direction: "left", modal: true });
//						$pageslide({ direction: 'left', href='_secondary.html' });
			        	
			        	var informacaoes = $("#chart_div").get(0);
			        	//informacaoes.pageslide();
						$(informacaoes).pageslide({ width: "300px", direction: "left", modal: true });

			    });
				
				markers.push(municipio);
				
			});
	
			var markerCluster = new MarkerClusterer(map, markers);
		
		}
	});
		
}