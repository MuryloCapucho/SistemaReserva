<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Sistema Reserva</title>

<!-- IMPORTAÇÃO DOS CSS e DOS JSs -->
<jsp:include page="../template/imports/imports.jsp"></jsp:include>
</head>
<body>

	<div id="wrapper">

		<!-- IMPORTANDO O TEMPLATE PADRÃO DA TELA -->
		<jsp:include page="../template/cabecalho.jsp"></jsp:include>

		<div id="page-wrapper" style="background-color: #FFFFFF">
			<!-- AQUI INICIA-SE A MONTAGEM DA TELA -->
			<!-- Utiliza-se o conceito de linha(row) e coluna(col-??-0a12) -->

			<div class="row">
				<div class="col-lg-12">
					<h3 align="center">!!! Seja Bem Vindo !!!</h3>
					<br>
					<br>
					<div class="row" align="center" >
						<h3>
							<img alt="Imagem" class="img-responsive" title="" src="${rootWeb}/images/logo.jpg">
						</h3>
					</div>
					<br>
					<br>
					<br>
					<h5 align="center">© MCR Development - Todos os Direitos Reservados.</h5>
				</div>
			</div>
		</div>
	</div>

</body>
</html>