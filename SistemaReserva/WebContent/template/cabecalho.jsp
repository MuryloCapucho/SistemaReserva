<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<!-- Navigation -->
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0; background-color: #000000;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" style="color: #ffffff; font-weight: bold; font-size: 20px" href="${rootWeb}/pages/principal.jsp">
				<i class="fa fa-home fa-fw"></i>
				Sistema Reserva
			</a>
		</div>

		<!-- Colocar no canto direito -->
		<ul class="nav navbar-nav navbar-right">
			<li>
				<a href="javascript://"
					style="text-shadow: 1px 0px 0px blue, -1px 0px 0px blue, 0px 1px 0px blue, 0px -1px 0px blue; font-size: 140%; font-weight: bold; color: #FFF; font-weight: bold;"
					class="navbar-brand ">
					<i class="fa fa-user fa-fw" style="font-size: 20px"></i>
					${usuarioLogado.usuario}
				</a>
			</li>
		</ul>
	</div>

	<!-- ################ FIM - MENU ################ -->
	<jsp:include page="menu.jsp"></jsp:include>
	<!-- ################ FIM - MENU ################ -->
</nav>

