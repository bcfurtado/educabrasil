/**
 * @author Bruno Furtado
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
					info.open(map,municipio);
				});

			}); 
		}
	});
	

}