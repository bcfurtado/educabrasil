/**
 * @author Bruno Furtado
 * @author Rhonan Carneiro
 */

var cont=0;

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
	var info = new google.maps.InfoWindow();
	
	
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
				
				
				
				google.maps.event.addListener(municipio,'click', function(){
					
					if(cont<1){
					$('a.close_right').get(0).click();
					}
					else{
						$('a.close_right').get(0).click();
						$('a.close_right').get(0).click();
					}
					
					cont++;
					
					info.close();	
					google.load('visualization', '1.0', {'packages':['corechart'], callback:drawChart});
			        function drawChart(){
						var data = new google.visualization.DataTable();
						data.addColumn('string', 'Destino do Investimento');
						data.addColumn('number', 'Valor do Investimento');
				        data.addRows([
				                      ['Educa‹o', val.investimentos.educacao],
				                      ['Outros', val.investimentos.outros],
			        ]);
			        var options = {'title':'Investimentos em Educa‹o',
		                       		'width':400,
		                       		'height':300,
		                       		'is3D':'true',
		                       		'backgroundColor': {fill: "none"}};
			        var formatter = new google.visualization.NumberFormat({pattern: 'R$###,###'});
					formatter.format(data,1);
			        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
		        	chart.draw(data, options);
			        }
					info.open(map,this);
					map.panTo(new google.maps.LatLng(val.latitude, val.longitude));
					info.setContent(val.nome);
					$("#nome_municipio").html(val.nome);
			        	//window.open("./PegarMunicipio");
//						$(".second").pageslide({ direction: "left", modal: true });
//						$pageslide({ direction: 'left', href='_secondary.html' });
			        	
			        	//var informacaoes = $("#chart_div").get(0);
			        	//informacaoes.pageslide();
						//$(informacaoes).pageslide({ width: "300px", direction: "left", modal: true });

			    });
				
				google.maps.event.addListener(map,'click', function(){
					info.close();
					if(cont>0){
						$('a.close_right').get(0).click();
					}
					cont=0;
				});
				
				google.maps.event.addListener(info,'closeclick', function(){
					info.close();
					if(cont>0){
						$('a.close_right').get(0).click();
					}
					cont=0;
				});
				
				markers.push(municipio);
				
			});
			
			
			
			var markerCluster = new MarkerClusterer(map, markers);
		
		}
	});
		
}