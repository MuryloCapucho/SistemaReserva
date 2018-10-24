<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- TAGS PARA O USO DO STRUTS NO JSP -->
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<html:html>

<head>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js"></script>
<jsp:include page="/template/imports/imports.jsp"></jsp:include>
<link type="text/css" href="assets/template/bower_components/datatables-responsive/css/responsive.jqueryui.scss" rel="stelesheet" />

<!-- Bootstrap Core CSS -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="../vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="../dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="../vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-timepicker.min.js"></script>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Sistema Reserva</title>

<jsp:include page="/template/imports/imports.jsp"></jsp:include>

</head>

<body background="${rootWeb}/images/login.jpg">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="login-panel panel panel-default">
					<div class="panel-heading" style="background-color: #000000">
						<h2 class="panel-title" align="center" style="color: #ffffff">Sistema Reserva</h2>
					</div>

					<!-- CONFIGURACAO PARA O FUNCIONAMENTO DO STRUCTS -->
					<html:form styleId="form_login" action="loginAction.do" method="post">
						<html:hidden property="method" value="empty" styleId="method" />

						<logic:messagesPresent message="false">
							<div class="alert alert-danger">
								<html:messages id="message" message="false">
									<bean:write filter='false' name='message' />
								</html:messages>
							</div>
						</logic:messagesPresent>
						<logic:messagesPresent message="true">
							<div class="alert alert-success">
								<html:messages id="message" message="true">
									<bean:write filter='false' name='message' />
								</html:messages>
							</div>
						</logic:messagesPresent>

						<div class="panel-body">
							<form role="form" id="form_login">
								<fieldset>
									<div class="form-group">
										<h4>Usuário</h4>
										<html:text property="usuario" name="loginForm" styleId="usuario" styleClass="form-control"></html:text>
									</div>
									<div class="form-group">
										<h4>Senha</h4>
										<html:password property="senha" name="loginForm" styleId="senha" styleClass="form-control" />
									</div>

									<!-- Change this to a button or input when using this as a form -->
									<br>
									<div align="center">
										<button type="submit" id="entrar" class="btn btn-success" style="background-color: #000000; color: #FFFFFF;">
											<i class="glyphicon glyphicon-log-in"></i>
											Entrar
										</button>

										<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modal-1" style="background-color: #000000; color: #FFFFFF;">
											<i class="glyphicon glyphicon-wrench"></i>
											Suporte
										</button>
										<div class="modal fade" id="modal-1">
											<div class="modal-dialog modal-lg">
												<div class="modal-content">
													<div class="modal-header">
														<button type="button" class="close" data-dismiss="modal">&times;</button>
														<h3 class="modal-title">
															<b>MCR Development</b>
														</h3>
														<img src="${rootWeb}/images/Perfil.jpg" name="Perfil" width="140" height="140" border="0" class="img-circle">
													</div>

													<div class="modal-body">
														<b>Murylo Capucho Ribeiro</b>
														<br>
														(43) 9 9686-9330
														<br>
														Rua Colômbia - 29 - AP. 03 - Vila Brasil - Londrina - PR
														<br>
													</div>

													<div class="modal-body">
														E-mail: murylo_capucho@outlook.com
														<br>
														Skype: murylo_capucho
														<br>
														Facebook: www.facebook.com/murylocapucho
														<br>
														Linkedin: www.linkedin.com/in/murylo-capucho-ribeiro-b185b6a3
													</div>

													<div class="modal-footer">
														<a href="" class="btn btn-default" data-dismiss="modal">Fechar</a>
													</div>
												</div>
											</div>
										</div>
									</div>
								</fieldset>
							</form>
						</div>
					</html:form>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="../vendor/jquery/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="../vendor/bootstrap/js/bootstrap.min.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="../vendor/metisMenu/metisMenu.min.js"></script>

	<!-- Custom Theme JavaScript -->
	<script src="../dist/js/sb-admin-2.js"></script>

</body>

<script>
	$(document).ready(function() {
		$("#usuario").focus();
		// Desliga o auto-complete da pagina
		$("#form_login").attr("autocomplete", "off");

		$("#usuario").attr("required", "required");
		$("#senha").attr("required", "required");

		$("#entrar").bind("click", function() {
			document.getElementById("form_login").method.value = "logar";
		});
	});
</script>

</html:html>
