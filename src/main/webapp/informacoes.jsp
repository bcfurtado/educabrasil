<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.GregorianCalendar"%>
<%@page import="org.educabrasil.beans.Orcamento"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.educabrasil.beans.Despesa"%>
<%@page import="java.util.List"%>
<%@page import="org.educabrasil.beans.Municipio"%>
<html>
<head>
	<title>EducaBrasil.org</title>
	<meta name="description" content="O EducaBrasil.org é um mashup para exibir de forma simplificada os investimentos dos municípios em educação.">
	<meta name="keywords" content="educabrasil,educação">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="./css/bootstrap.css" rel="stylesheet">
	<link href="./css/bootstrap-responsive.css" rel="stylesheet">
	<link href="./css/jquery-ui-1.9.2.custom.css" rel="stylesheet">
	<script src="./js/bootstrap.js"></script>
	<script src="./js/bootstrap-affix.js"></script>
	<script src="./js/bootstrap-button.js"></script>
	<script src="./js/bootstrap-collapse.js"></script>
	<script src="./js/bootstrap-modal.js"></script>
	<script src="./js/bootstrap-scrollspy.js"></script>
	<script src="./js/bootstrap-tooltip.js"></script>
	<script src="./js/bootstrap-typeahead.js"></script>
	<script src="./js/bootstrap-alert.js"></script>
	<script src="./js/bootstrap-carousel.js"></script>
	<script src="./js/bootstrap-dropdown.js"></script>
	<script src="./js/bootstrap-popover.js"></script>
	<script src="./js/bootstrap-tab.js"></script>
	<script src="./js/bootstrap-transition.js"></script>
	<script src="./markerclusterer.js"></script>
	<script src="./js/jquery.js"></script>
	<script src="./jquery.simpleslide.plugin.js"></script>
	<script src="./jquery-ui-1.9.2.custom.js"></script>
	<script src="http://www.panoramio.com/wapi/wapi.js?v=1"></script>
	
	
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">
		#conteudo {
			padding-top: 60px;
		}
		#wapiblock .panoramio-wapi-images {
			background-color: #eeeeee;
		}
		#wapiblock .pwanoramio-wapi-tos{
			background-color: #eeeeee;
		}
	</style>
	
	<script type="text/javascript" src="https://www.google.com/jsapi"></script>
	<script src="./core.js"></script>
	
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push(['_setAccount', 'UA-37631842-1']);
		_gaq.push(['_trackPageview']);
		(function() {
			var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		})();
	</script>

</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="./">EducaBrasil</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="./">Inicio</a></li>
						<li class="active" ><a href="./informacoes" >Informações detalhadas</a></li>
						<li><a href="./sobre">Sobre</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>

	</div>
	<% 
		List<Municipio> municipios = (List<Municipio>)request.getAttribute("municipios");
		Municipio municipio = (Municipio)request.getAttribute("municipio");
		List<Orcamento> orcamentos = (List<Orcamento>)request.getAttribute("orcamentos");
		List<Despesa> despesas = (List<Despesa>)request.getAttribute("despesas");
	%>
	<div class="container-fluid" id="conteudo">
      <div class="hero-unit">
        <% String nomeDoMunicipio = municipio.getNome().substring(0,1).toUpperCase().concat(municipio.getNome().toLowerCase().substring(1, municipio.getNome().length())); %>
        <h1><%=nomeDoMunicipio %></h1>
        <div id="wapiblock"></div>
		<script type="text/javascript">
			var myRequest = {
			  'tag': '<%=nomeDoMunicipio%>',
			};
			var myOptions = {
				'columns': 8,
				'croppedPhotos': true
			};
			var wapiblock = document.getElementById('wapiblock');
			var widget = new panoramio.PhotoListWidget(wapiblock, myRequest, myOptions);
			widget.setPosition(0);
		</script>
		
		
      </div>
      
		<div class="row-fluid">
			<div class="span2">
				<div class="well sidebar-nav">
				        <ul class="nav nav-list">
				        	<li class="nav-header">Cidades</li>
				        
							<% for(Municipio municipioAtual : municipios){ %>
			        			<li><a href="./informacoes?cod_mun=<%=municipioAtual.getId() %>"><%=municipioAtual.getNome() %></a></li>
			        		<% } %>
				        </ul>
				</div>
			</div>

			<div class="span10">
				<h2>Despesas de <%=request.getAttribute("ano") %></h2>
				<% for(Orcamento orcamento : orcamentos){ %>
					<a class="btn" href="./informacoes?cod_mun=<%=municipio.getId()%>&amp;ano=<%=orcamento.getAno()%>"><%=orcamento.getAno() %></a>
				<% } %>
				
				<section>
					<table class="table table-striped">

						<thead>
							<tr>
								<th style="width: 40%">Nome</th>
								<th style="width: 40%">Decrição</th>
								<th style="width: 20%">Valor</th>

							</tr>
						</thead>
						<tbody>
							<%
								DecimalFormat df = new DecimalFormat("R$ ###,###,###.00"); 
								for (Despesa despesa : despesas) {
							%>
							<tr>
								<td><%=despesa.getNome()%></td>
								<td><%=despesa.getDescricao()%></td>
								<td><%=df.format(despesa.getValor()) %></td>
							<% } %>
							
						</tbody>
					</table>
				</section>
			</div>
		</div>
		<hr>
		<footer>
			<%
				GregorianCalendar gc = new GregorianCalendar();
				gc.setTimeInMillis(System.currentTimeMillis());
				int ano = gc.get(GregorianCalendar.YEAR);
			%>
    		<p>EducaBrasil.org - <%= ano %></p>
    	</footer>
	</div>
	<!-- /container -->

</body>
</html>
