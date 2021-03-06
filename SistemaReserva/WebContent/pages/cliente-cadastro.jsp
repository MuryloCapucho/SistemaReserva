<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- TAGS PARA O USO DO STRUTS NO JSP -->
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="core"%>

<html lang="en">
<head>

<!-- Upload de Imagem -->
<link href="${rootWeb}/assets/bootstrap/bootstrap-fileinput-master/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />

<style>
.kv-avatar .file-preview-frame, .kv-avatar .file-preview-frame:hover {
	margin: 0;
	padding: 0;
	border: none;
	box-shadow: none;
	text-align: center;
}

.kv-avatar .file-input {
	display: table-cell;
	max-width: 220px;
}
</style>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Sistema Reserva</title>

<!-- IMPORTA��O DOS CSS e DOS JSs -->
<jsp:include page="../template/imports/imports.jsp"></jsp:include>
</head>
<body>

	<div id="wrapper">

		<!-- IMPORTANDO O TEMPLATE PADR�O DA TELA -->
		<jsp:include page="../template/cabecalho.jsp"></jsp:include>

		<div id="page-wrapper">
			<!-- AQUI INICIA-SE A MONTAGEM DA TELA -->
			<!-- Utiliza-se o conceito de linha(row) e coluna(col-??-0a12) -->

			<!-- CABE�ALHO INFORMATIVO DA TELA -->
			<div class="row">
				<div class="col-xs-12">
					<h2 class="page-header">
						<i class="glyphicon glyphicon-user"></i>
						Cliente
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Cadastro de Cliente
						</small>
					</h2>
				</div>
			</div>

			<!-- O CORPO DA TELA -->
			<div class="row">
				<div class="col-xs-12">

					<!-- CONFIGURA��O IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executar� o action -->
					<html:form styleId="form_cliente" action="clienteAction.do" method="post" enctype="multipart/form-data">
						<html:hidden styleId="method" property="method" value="nada" />

						<div class="row">
							<div class="form-group text-center col-xs-12 col-sm-12 col-md-12 col-lg-12">
								<div id="kv-avatar-errors" class="center-block" style="width: 800px; display: none"></div>
							</div>
						</div>

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

								<!-- CRIACAO DOS CAMPOS JUNTAMENTE COM SUAS LABELS -->

								<div class="row">
									<div class="form-group text-center col-xs-12 col-sm-6 col-md-4 col-lg-3">
										<div class="kv-avatar center-block">
											<input id="foto1" name="file" type="file" class="file-loading">
										</div>
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
										<label for="id">C�digo</label>
										<html:text styleClass="form-control bloqueado text-center" styleId="id" name="clienteForm" property="id" />
									</div>

									<%-- <div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-7">
										<label for="nome">Nome</label>
										<html:text styleClass="form-control obrigatorio" styleId="nome" name="clienteForm" property="nome" />
									</div> --%>

									<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-7">
										<label for="nome">Nome</label>
										<html:text property="nome" name="clienteForm" styleId="nome" styleClass="form-control obrigatorio" />
										<html:hidden property="id" name="clienteForm" styleId="clienteSelecionado" />
									</div>

									<div class="form-group col-lg-offset-2 col-xs-12 col-sm-6 col-md-8 col-lg-7">
										<label for="cidade">Cidade</label>
										<html:text styleClass="form-control obrigatorio" styleId="cidade" name="clienteForm" property="cidade" />
									</div>
								</div>

								<div class="row">
									<div class="table-responsive col-xs-12">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="bg-primary">
													<th class="text-center" style="width: 80px">#</th>
													<th>Nome</th>
													<th width="30%">Cidade</th>
													<th class="text-center" style="width: 80px">Selecionar</th>
												</tr>
											</thead>
											<!-- PROPRIEDADES:
											id - Objeto corrente do FOR
											indexId - � o contador como por exemplo o (i)
											name - Nome do Form onde a lista esta
											property - Nome do atributo que representa a lista
											type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
											<logic:iterate id="clienteCorrente" indexId="i" name="clienteForm" property="clientes" type="br.com.systemmcr.sistemareserva.model.po.ClientePO">
												<tr>
													<td class="text-center">${clienteCorrente.id}</td>
													<td>${clienteCorrente.nome}</td>
													<td>${clienteCorrente.cidade}</td>
													<td class="text-center">
														<a href="${rootWeb}/clienteAction.do?method=selecionar&id=${clienteCorrente.id}">
															<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>
														</a>

													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>

									<!-- IN�CIO Pagina��o -->
									<logic:notEmpty name="clienteForm" property="clientes">
										<div class="col-xs-12 text-center">
											<nav aria-label="Page navigation">
											<ul class="pagination">
												<li>
													<a href="javascript://" style="cursor: default;">Encontrados: ${clienteForm.paginacao.totalRegistros} </a>
												</li>
												<logic:equal name="clienteForm" property="paginacao.voltarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="clienteForm" property="paginacao.voltarPaginacaoInativo" value="false">
													<li class="page-item">
														<a class="page-link" href="${rootWeb}/clienteAction.do?method=voltar&forwardDestino=clienteCadastro" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>

												<core:forEach var="i" begin="${clienteForm.paginacao.paginaInicial}" end="${clienteForm.paginacao.paginaFinal}" step="1" varStatus="row">
													<core:if test="${clienteForm.paginacao.paginaCorrente == i}">
														<li class="page-item active">
													</core:if>

													<core:if test="${clienteForm.paginacao.paginaCorrente != i}">
														<li class="page-item ">
													</core:if>

													<a class="page-link" href="${rootWeb}/clienteAction.do?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
													</li>

												</core:forEach>

												<logic:equal name="clienteForm" property="paginacao.avancarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Pr�ximo</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="clienteForm" property="paginacao.avancarPaginacaoInativo" value="false">
													<li class="page-item ">
														<a class="page-link" href="${rootWeb}/clienteAction.do?method=avancar&forwardDestino=clienteCadastro" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Pr�ximo</span>
														</a>
													</li>
												</logic:equal>
											</ul>
											</nav>
										</div>
									</logic:notEmpty>
									<!-- FIM Pagina��o -->

								</div>
							</div>
							<div class="panel-footer">
								<div class="row">


									<logic:notPresent name="clienteForm" property="id">
										<div class="form-group col-lg-offset-1 col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-block">
												<i class="glyphicon glyphicon-floppy-save"></i>
												Inserir
											</button>
										</div>
									</logic:notPresent>

									<logic:present name="clienteForm" property="id">
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="alterar" class="btn btn-primary btn-block">
												<i class="glyphicon glyphicon-retweet"></i>
												Alterar
											</button>
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="button" id="excluir" class="btn btn-danger btn-block">
												<i class="glyphicon glyphicon-remove"></i>
												Excluir
											</button>
										</div>
									</logic:present>

									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
										<button type="button" id="pesquisar" class="btn btn-info btn-block">
											<i class="glyphicon glyphicon-th-list"></i>
											Pesquisar
										</button>
									</div>
									<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
										<button type="button" id="limpar" class="btn btn-warning btn-block">
											<i class="glyphicon glyphicon-erase"></i>
											Limpar
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
		 attr serve para adicionar atributos em tempo de execu��o
		 */
		$(document).ready(function() {
			/* Bem vindo ao ambiente JQuery */

			$("#nome").focus();

			// Desliga o auto-complete da pagina
			$("#form_cliente").attr("autocomplete", "off");

			/* Criando uma estiliza��o para campos Bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estiliza��o para os campos Obrigatorios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#nome").attr("maxlength", 50);
			$("#cidade").attr("maxlength", 30);

			/* Setando o Placeholder dos campos */
			$("#nome").attr("placeholder", "Nome");
			$("#cidade").attr("placeholder", "Cidade");

			/* DEFININDO OS EVENTOS DOS BOTOES */
			$("#inserir").click(function() {
				$("#method").val("inserir");
			});

			$("#alterar").click(function() {
				$("#method").val("inserir");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_cliente").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_cliente").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_cliente").submit();
			});

			var pathTemp1 = 'remover_${clienteForm.imagem}';
			pathTemp1 = pathTemp1.replace(".", "_").replace("/", "_");
			$("#foto1").fileinput({
				overwriteInitial : true,
				maxFileSize : 5000,
				showClose : false,
				showCaption : false,
				browseLabel : 'Selecione',
				removeLabel : '',
				browseIcon : '<i class="glyphicon glyphicon-folder-open"></i>',
				removeIcon : '<i class="glyphicon glyphicon-remove"></i>',
				removeTitle : 'Cancela ou desfaz as altera��es',
				removeClass : pathTemp1 + ' btn btn-default',
				elErrorContainer : '#kv-avatar-errors',
				msgErrorClass : 'alert alert-block alert-danger',
				initialPreview : '<img src="${rootWeb}/upload/${clienteForm.prefixoImagem}/${clienteForm.imagem}" alt="Imagem" style="width:100%">',
				defaultPreviewContent : '<img src="${rootWeb}/images/empty.png" alt="Imagem" style="width:100%">',
				layoutTemplates : {
					main2 : '{preview} {remove} {browse}'
				},
				allowedFileExtensions : [ "jpg", "png", "gif" ]
			});

			$('#nome').autocomplete({
				minChars : 2,
				paramName : 'nome',
				serviceUrl : '${rootWeb}/clienteAction.do?method=selecionarAutoCompleteCliente',
				onSelect : function(suggestion) {
					//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
					$('#clienteSelecionado').val(suggestion.data);
					document.getElementById("form_cliente").method.value = "selecionarCliente";
					$("#form_cliente").attr("target", "_self");
					document.getElementById("form_cliente").submit();
				}
			});
		});
	</script>

	<!-- Upload de Imagem -->
	<script src="${rootWeb}/assets/bootstrap/bootstrap-fileinput-master/js/plugins/canvas-to-blob.min.js" type="text/javascript"></script>
	<script src="${rootWeb}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput.min.js" type="text/javascript"></script>
	<script src="${rootWeb}/assets/bootstrap/bootstrap-fileinput-master/js/fileinput_locale_pt-BR.js"></script>

</body>
</html>