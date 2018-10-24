<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>

<div class="navbar-default sidebar" role="navigation" style="background-color: #FAFAFA; margin-top: 2px !important;">
	<div class="sidebar-nav navbar-collapse" style="background-color: #FAFAFA">

		<!-- BARRA DE MENU -->
		<ul class="nav" id="side-menu">

			<!-- MENU -->
			<!-- Cada li � um Menu -->
			
			<logic:equal value="Administrador" name="tipoUsuario" scope="session">
				<li>
					<!-- Realizando uma requizi��o via GET com STRUTS -->
					<a href="${rootWeb}/usuarioAction.do?method=abrirTela" style="color: #000000">
						<i class="glyphicon glyphicon-hand-right"></i>
						Usu�rio
					</a>
				</li>
			</logic:equal>

			<li>
				<!-- Realizando uma requizi��o via GET com STRUTS -->
				<a href="${rootWeb}/eventoAction.do?method=abrirTela" style="color: #000000">
					<i class="glyphicon glyphicon-hand-right"></i>
					Evento
				</a>
			</li>

			<li>
				<!-- Realizando uma requizi��o via GET com STRUTS -->
				<a href="${rootWeb}/fileUploadAction.do?method=abrirTela" style="color: #000000">
					<i class="glyphicon glyphicon-hand-right"></i>
					Flyer
				</a>
			</li>

			<logic:equal value="Administrador" name="tipoUsuario" scope="session">
				<li>
					<!-- Realizando uma requizi��o via GET com STRUTS -->
					<a href="${rootWeb}/mesaAction.do?method=abrirTela" style="color: #000000">
						<i class="glyphicon glyphicon-hand-right"></i>
						Mesa/Bistr�/Camarote
					</a>
				</li>
			</logic:equal>

			<li>
				<!-- Realizando uma requizi��o via GET com STRUTS -->
				<a href="${rootWeb}/clienteAction.do?method=abrirTela" style="color: #000000">
					<i class="glyphicon glyphicon-hand-right"></i>
					Cliente
				</a>
			</li>

			<li>
				<!-- Realizando uma requizi��o via GET com STRUTS -->
				<a href="${rootWeb}/reservaAction.do?method=abrirTela" style="color: #000000">
					<i class="glyphicon glyphicon-hand-right"></i>
					Reserva Mesa/Bistr�/Camarote
				</a>
			</li>
			
			<li>
				<!-- Realizando uma requizi��o via GET com STRUTS -->
				<a href="${rootWeb}/listaAction.do?method=abrirTela" style="color: #000000">
					<i class="glyphicon glyphicon-hand-right"></i>
					Lista VIP
				</a>
			</li>

			<li>
				<a href="${rootWeb}/loginAction.do?method=abrirLogin" style="color: #000000">
					<i class="glyphicon glyphicon-remove-circle"></i>
					Fazer Logoff
				</a>
			</li>

		</ul>
	</div>
	<!-- /.sidebar-collapse -->
</div>