/**
 * @author Bruno Furtado
 * @author Rhonan Carneiro
 */

var cont=0;

var nomesMunicipios = [];

var markers = [];

var map;

var info;

var resultado = {};

function initialize() {
	
	var mapOptions = {
		center : new google.maps.LatLng(-10.055403, -47.636719),
		zoom : 4,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	map = new google.maps.Map($("#map_canvas").get(0), mapOptions);
	
	carregarMunicipios(map);
}


function carregarMunicipios(map){
	
	 info = new google.maps.InfoWindow();
	
	
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
				
				resultado[municipio.getTitle()] = val;
				
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
					google.load('visualization', '1.0', {'packages':['corechart'], callback:drawColumnChart});
			        function drawChart(){
						var data = new google.visualization.DataTable();
						data.addColumn('string', 'Destino do Investimento');
						data.addColumn('number', 'Valor do Investimento');
				        data.addRows([
				                      ['Educação', val.investimentos[2].educacao],
				                      ['Outros', val.investimentos[2].outros],
			        ]);
			        var options = {'title':'Investimentos em Educação',
		                       		'width':400,
		                       		'height':300,
		                       		'is3D':'true',
		                       		'backgroundColor': {fill: "none"}
			        				};
			        var formatter = new google.visualization.NumberFormat({pattern: 'R$###,###'});
					formatter.format(data,1);
			        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
		        	chart.draw(data, options);
			        }
			        
			        function drawColumnChart(){
			        	var data = google.visualization.arrayToDataTable([
			        	                                                  ['Ano', 'Educação', 'Outros'],
			        	                                                  [val.investimentos[0].ano,  val.investimentos[0].educacao,	val.investimentos[0].outros],
			        	                                                  [val.investimentos[1].ano,  val.investimentos[1].educacao,    val.investimentos[1].outros],
			        	                                                  [val.investimentos[2].ano,  val.investimentos[2].educacao,    val.investimentos[2].outros]
			        	                                                ]);
			        	var options = {
			        	          title: 'Comparativo com anos anteriores',
			        	          	'width':400,
		                       		'height':300,
		                       		'backgroundColor': {fill: "none"},
		                       		'legend': {position: "bottom"}
			        	        };
			        	var formatter = new google.visualization.NumberFormat({pattern: 'R$###,###'});
						formatter.format(data,1);
						formatter.format(data,2);
			        	var chart = new google.visualization.ColumnChart(document.getElementById('column_chart_div'));
			        	chart.draw(data, options);
			        }
			        
					info.open(map,this);
					map.panTo(new google.maps.LatLng(val.latitude, val.longitude));
					info.setContent(val.nome);
					$("#nome_municipio").html('<a href="./informacoes?cod_mun='+val.id+'">' + val.nome + "</a>");

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
				nomesMunicipios.push(val.nome);
			});
			
			
			
			var markerCluster = new MarkerClusterer(map, markers, {
				maxZoom: 11,
			    calculator: function(markers, numStyles) {
					var totalEducacao = 0.0;
					var totalOutros = 0.0;
					
					for ( var i = 0; i < markers.length; i++) {
						var marker = markers[i];
						totalEducacao += resultado[marker.getTitle()].investimentos[2].educacao;
						totalOutros +=  resultado[marker.getTitle()].investimentos[2].outros;
					}
					var valor = ( totalEducacao / totalOutros ) * 100 ;
//					alert("Valor Total: " + valor);
					return {
		                text: valor.toFixed(2) +" %",
		                index: numStyles
		            };
			    }
			});

			

		
		}
	});
		
}

$(function() {
    $( "#tags" ).autocomplete({
    	select: function(event,ui){
    		var municipioSelecionado = ui.item;	
    		for(var i = 0; i < markers.length; i++){
    			if(markers[i].getTitle()==municipioSelecionado.value){
    				map.panTo(markers[i].getPosition());
    				map.setZoom(11);
    		}
    	}
    	},
    	source: nomesMunicipios
    });
  });
