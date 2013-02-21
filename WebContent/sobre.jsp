<!DOCTYPE html>
<%@page import="java.util.GregorianCalendar"%>
<html>
<head>
	<title>EducaBrasil.org</title>
	<meta name="description" content="O EducaBrasil.org � um mashup para exibir de forma simplificada os investimentos dos munic�pios em educa��o.">
	<meta name="keywords" content="educabrasil,educa��o">
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
	<script src="./js/jquery.js"></script>
	<script src="./jquery.simpleslide.plugin.js"></script>
	<script src="./jquery-ui-1.9.2.custom.js"></script>
	
	
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<style type="text/css">

	#conteudo {
	        padding-top: 60px;
	}
	
	body {
        padding-top: 20px;
        padding-bottom: 40px;
      }

      /* Custom container */
      .container-narrow {
        margin: 0 auto;
        max-width: 700px;
      }
      .container-narrow > hr {
        margin: 30px 0;
      }

      /* Main marketing message and sign up button */
      .jumbotron {
        margin: 60px 0;
        text-align: center;
      }
      .jumbotron h1 {
        font-size: 72px;
        line-height: 1;
      }
      .jumbotron .btn {
        font-size: 21px;
        padding: 14px 24px;
      }

      /* Supporting marketing content */
      .marketing {
        margin: 60px 0;
      }
      .marketing p + h4 {
        margin-top: 28px;
      }
      
      .photo_profile {
      	width: 140px;
      	height: 140px;
      }
      
      .box_profile {
      	height: 300px;
      }

	</style>
	
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
		<a href="https://github.com/bcfurtado/educabrasil"><img style="position: absolute; top: 0; right: 0; border: 0;" src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png" alt="Fork me on GitHub"></a>
		<div class="navbar-inner">
			<div class="container">
				<a class="btn btn-navbar" data-toggle="collapse"
					data-target=".nav-collapse"> <span class="icon-bar"></span> <span
					class="icon-bar"></span> <span class="icon-bar"></span>
				</a> <a class="brand" href="./">EducaBrasil</a>
				<div class="nav-collapse collapse">
					<ul class="nav">
						<li><a href="./">Inicio</a></li>
						<li><a href="./informacoes" >Informações detalhadas</a></li>
						<li class="active"><a href="./sobre">Sobre</a></li>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
		</div>

	</div>

	<div class="container-narrow" id="conteudo">
		<h3 class="muted">EducaBrasil.org</h3>
	      <hr>
			<div class="jumbotron">
				<h1>EducaBrasil</h1>
					<p class="lead"> <a href="http://www.educabrasil.org/">O EducaBrasil.org</a> é um mashup para exibir de forma simplificada os investimentos dos municípios em educação.</p>
					<a class="btn btn-large btn-success" href="http://github.com/bcfurtado/educabrasil/">Contribua agora</a>
				</div>
				<hr>
				<div>
					<h2>Informações</h2>
					<p>O EducaBrasil.org foi desenvolvido por alunos do curso de <a href="http://www.es.ufc.br/">Engenharia de Software</a> da <a href="http://www.ufc.br/">Universidade Federal do Ceará</a> sob orientação do professor Camilo Almendra. Sua funcionalidade principal é mostrar o percentual do orçamento dos municípios brasileiros investidos em educação de forma interativa utilizando mapas, possibilitando ao usuário a visualização do valor investido por regiões ou por município específico.</p>

					<p>Também é possível a visualização do valor bruto em Reais que foi investido, assim como o total do orçamento do município e uma descrição detalhada destes gastos.</p>

					<p>Inicialmente a aplicação contém apenas dados do Ceará, que são disponibilizados pelo <a href="http://api.tcm.ce.gov.br/">TCM do Ceará</a>, entretanto existe a intenção de incluir dados do demais munícipios do país e também que essa aplicação possa ser alimentada com novos dados, como por exemplo o <a href="http://www.pnud.org.br/atlas/ranking/IDH-Globlal-2013.aspx?indiceAccordion=1&li=li_Ranking2013">IDH</a>, para que com isso seja desenvolvido um ranking para que esses municípios possam ser comparados e classificados pelo nível de qualidade de vida que os investimentos em educação trouxeram.</p>
				</div>
				<hr>
				<div>
		      		<h2>Membros do Projeto</h2>
		      	</div>

				<div class="row-fluid marketing">
				<div class="span6">
		        	<div class="box_profile">
		        		<img src="https://si0.twimg.com/profile_images/1466467779/eued.jpg" class="img-circle photo_profile">
		        		<h4>Bruno Furtado</h4>
		        		<p>Estudante de Engenharia de Software, Linux User, Aficionado por tecnologia e Desenvolvimento de software</p>
		          		<p><a href="http://twitter.com/bcfurtado">@bcfurtado</a></p>
				  	</div>
		           	<hr>
					<div class="box_profile">
						<img src="https://si0.twimg.com/profile_images/1122408169/pequena.jpg" class="img-circle photo_profile">
						<h4>Camilo Almendra</h4>
						<p>Software Developer, Software Engineering Professor, Churrasco Addicted, Racing fan. I live on Ceará, nice place to rest & get your software done.</p>
						<p><a href="https://twitter.com/ccalmendra/">@ccalmendra</a></p>
		           	</div>
		           	<hr>
		    	  	<div class="box_profile">
		    	  		<img src="https://fbcdn-sphotos-e-a.akamaihd.net/hphotos-ak-snc6/283234_1711166118964_3256951_n.jpg" class="img-circle photo_profile">
		    	  		<h4>Mauricio Lima</h4>
						<p>@mauriciolimabob</p>
		        	</div>		           	
		        </div>
		
		        <div class="span6">
					<div class="box_profile">
						<img src="http://sphotos-g.ak.fbcdn.net/hphotos-ak-snc6/224474_417462058329742_1286034763_n.jpg" class="img-circle photo_profile">
						<h4>Rhonan Carneiro</h4>
						<p>Estudante de Engenharia de Software-UFC.</p>
						<p><a href="http://twitter.com/rhonan">@rhonan</a></p>
					</div>
		        	<hr>
					<div class="box_profile">
			        	<img src="https://fbcdn-sphotos-g-a.akamaihd.net/hphotos-ak-ash3/12745_331514633614234_1043577776_n.jpg" class="img-circle photo_profile">
			        	<h4>Antônio José</h4>
			        	<p>@ajamancio</p>
					</div>
					<hr>
					<div class="box_profile">
						<img src="https://si0.twimg.com/profile_images/1597322158/296119_179748578775123_100002199473491_473727_1780342601_n.jpg" class="img-circle photo_profile">
						<h4>Jorge Anderson</h4>
						<p><a href="https://twitter.com/Jorgiinho_10">@Jorgiinho_10</a></p>
					</div>
				</div>
			</div>
			<hr>
			<p><img alt="Tecnologias Utilizadas" src="./img/footer.png"></p>
			<hr>
			<div class="footer">
				<%
					GregorianCalendar gc = new GregorianCalendar();
					gc.setTimeInMillis(System.currentTimeMillis());
					int ano = gc.get(GregorianCalendar.YEAR);
				%>
				<p><a href="http://www.educabrasil.org/">EducaBrasil.org</a> - <%= ano %></p>
			</div>
		</div>
		<!-- /container -->
</body>
</html>

