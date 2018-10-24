<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- TAGS PARA O USO DO STRUTS NO JSP -->
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

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

		<div id="page-wrapper">
			<!-- AQUI INICIA-SE A MONTAGEM DA TELA -->
			<!-- Utiliza-se o conceito de linha(row) e coluna(col-??-0a12) -->

			<!-- CABEÇALHO INFORMATIVO DA TELA -->
			<div class="row">
				<div class="col-xs-12">
					<h2 class="page-header">
						<i class="glyphicon glyphicon-picture"></i>
						Flyer de Evento
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Upload de Arquivo
						</small>
					</h2>
				</div>
			</div>

			<!-- O CORPO DA TELA -->
			<div class="row">
				<div class="col-xs-12">

					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_upload" action="fileUploadAction.do" method="post" enctype="multipart/form-data">
						<html:hidden styleId="method" property="method" value="nada" />

						<div class="panel panel-primary">
							<div class="panel-heading">
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
							</div>
							<div class="panel-body">
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<label>Selecione um Flyer</label>
										<html:file styleClass="form-control" styleId="file" name="fileUploadForm" property="file" accept=".jpg" />
									</div>

									<div class="form-group col-lg-offset-4 col-xs-12 col-sm-12 col-md-2 col-lg-4">
										<button type="submit" id="upload" class="btn btn-success btn-block" onclick="waitingDialog.show();setTimeout(function () {waitingDialog.hide();}, 10000);">
											<i class="glyphicon glyphicon-floppy-save"></i>
											Enviar
										</button>
									</div>
								</div>
							</div>
						</div>

					</html:form>
				</div>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		/* 
		 EXECUTADO NA CARGA DA PAGINA 
		 Trabalhando com JQuery para manipular os componentes
		 # pega os elementos pelo ID - $("#nome")
		 . pega os elementos por CLASS - $(".bloqueado")
		 attr serve para adicionar atributos em tempo de execução
		 */
		$(document).ready(function() {
			/* Bem vindo ao ambiente JQuery */

			$("#file").focus();

			// Desliga o auto-complete da pagina
			$("#form_upload").attr("autocomplete", "off");

			$("#file").attr("required", "required");

			/* Criando uma estilização para campos Bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estilização para os campos Obrigatorios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* DEFININDO OS EVENTOS DOS BOTOES */
			$("#upload").click(function() {
				$("#method").val("upload");
			});

			$("#file").filestyle({
				input : true,
				buttonName : "btn-primary",
				size : "sm col-xs-12 col-sm-12 col-md-12 col-lg-12",
				badge : false,
				buttonText : "Anexo"
			});
		});

		var waitingDialog = waitingDialog
				|| (function($) {
					'use strict';

					// Creating modal dialog's DOM
					var $dialog = $('<div class="modal fade" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true" style="padding-top:15%; overflow-y:visible;">'
							+ '<div class="modal-dialog modal-m">'
							+ '<div class="modal-content">'
							+ '<div class="modal-header text-center"><h3 style="margin:0;"></h3></div>'
							+ '<div class="modal-body">'
							+ '<div class="progress progress-striped active" style="margin-bottom:0;"><div class="progress-bar" style="width: 100%"></div></div>'
							+ '</div>'
							+ '</div></div></div>');

					return {
						/**
						 * Opens our dialog
						 * @param message Custom message
						 * @param options Custom options:
						 * 				  options.dialogSize - bootstrap postfix for dialog size, e.g. "sm", "m";
						 * 				  options.progressType - bootstrap postfix for progress bar type, e.g. "success", "warning".
						 */
						show : function(message, options) {
							// Assigning defaults
							if (typeof options === 'undefined') {
								options = {};
							}
							if (typeof message === 'undefined') {
								message = 'Fazendo Upload do Arquivo para o Servidor';
							}
							var settings = $.extend({
								dialogSize : 'm',
								progressType : '',
								onHide : null
							// This callback runs after the dialog was hidden
							}, options);

							// Configuring dialog
							$dialog.find('.modal-dialog').attr('class', 'modal-dialog').addClass('modal-' + settings.dialogSize);
							$dialog.find('.progress-bar').attr('class', 'progress-bar');
							if (settings.progressType) {
								$dialog.find('.progress-bar').addClass('progress-bar-' + settings.progressType);
							}
							$dialog.find('h3').text(message);
							// Adding callbacks
							if (typeof settings.onHide === 'function') {
								$dialog.off('hidden.bs.modal').on('hidden.bs.modal', function(e) {
									settings.onHide.call($dialog);
								});
							}
							// Opening dialog
							$dialog.modal();
						},
						/**
						 * Closes dialog
						 */
						hide : function() {
							$dialog.modal('hide');
						}
					};

				})(jQuery);
	</script>
	
	<!-- Upload -->
	<script src="${rootWeb}/assets/bootstrap/bower_components/bootstrap-filestyle-1.2.1/src/bootstrap-filestyle.min.js" type="text/javascript"></script>

</body>
</html>