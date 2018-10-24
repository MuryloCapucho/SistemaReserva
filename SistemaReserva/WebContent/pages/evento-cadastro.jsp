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
						<i class="glyphicon glyphicon-music"></i>
						Evento
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Cadastro de Evento
						</small>
					</h2>
				</div>
			</div>

			<!-- O CORPO DA TELA -->
			<div class="row">
				<div class="col-xs-12">

					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_evento" action="eventoAction.do" method="post" enctype="multipart/form-data">
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

								<!-- CRIACAO DOS CAMPOS JUNTAMENTE COM SUAS LABELS -->
								<div class="row">

									<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
										<label for="id">Código</label>
										<html:text styleClass="form-control bloqueado text-center" styleId="id" name="eventoForm" property="id" />
									</div>

									<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-3">
										<label for="data">Data Evento</label>
										<html:text styleClass="form-control obrigatorio data text-center" styleId="data" name="eventoForm" property="data" />
									</div>

									<div class="form-group col-xs-12 col-sm-4 col-md-8 col-lg-7">
										<label for="nome">Nome</label>
										<html:text styleClass="form-control obrigatorio" styleId="nome" name="eventoForm" property="nome" />
									</div>

								</div>

								<div class="row">
									<div class="table-responsive col-xs-12">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="bg-primary">
													<th class="text-center" style="width: 80px">#</th>
													<th class="text-center" width="20%">Data Evento</th>
													<th>Nome</th>
													<th class="text-center" style="width: 80px">Selecionar</th>
												</tr>
											</thead>
											<!-- PROPRIEDADES:
											id - Objeto corrente do FOR
											indexId - è o contador como por exemplo o (i)
											name - Nome do Form onde a lista esta
											property - Nome do atributo que representa a lista
											type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
											<logic:iterate id="eventoCorrente" indexId="i" name="eventoForm" property="eventos" type="br.com.systemmcr.sistemareserva.model.po.EventoPO">
												<tr>
													<td class="text-center">${eventoCorrente.id}</td>
													<td class="text-center">${eventoCorrente.dataToString}</td>
													<td>${eventoCorrente.nome}</td>
													<td class="text-center">
														<a href="${rootWeb}/eventoAction.do?method=selecionar&id=${eventoCorrente.id}">
															<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>
														</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>

									<!-- INÍCIO Paginação -->
									<logic:notEmpty name="eventoForm" property="eventos">
										<div class="col-xs-12 text-center">
											<nav aria-label="Page navigation">
											<ul class="pagination">
												<li>
													<a href="javascript://" style="cursor: default;">Encontrados: ${eventoForm.paginacao.totalRegistros} </a>
												</li>
												<logic:equal name="eventoForm" property="paginacao.voltarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="eventoForm" property="paginacao.voltarPaginacaoInativo" value="false">
													<li class="page-item">
														<a class="page-link" href="${rootWeb}/eventoAction.do?method=voltar&forwardDestino=eventoCadastro" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>



												<core:forEach var="i" begin="${eventoForm.paginacao.paginaInicial}" end="${eventoForm.paginacao.paginaFinal}" step="1" varStatus="row">
													<core:if test="${eventoForm.paginacao.paginaCorrente == i}">
														<li class="page-item active">
													</core:if>

													<core:if test="${eventoForm.paginacao.paginaCorrente != i}">
														<li class="page-item ">
													</core:if>

													<a class="page-link" href="${rootWeb}/eventoAction.do?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
													</li>

												</core:forEach>



												<logic:equal name="eventoForm" property="paginacao.avancarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Próximo</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="eventoForm" property="paginacao.avancarPaginacaoInativo" value="false">
													<li class="page-item ">
														<a class="page-link" href="${rootWeb}/eventoAction.do?method=avancar&forwardDestino=eventoCadastro" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Próximo</span>
														</a>
													</li>
												</logic:equal>
											</ul>
											</nav>
										</div>
									</logic:notEmpty>
									<!-- FIM Paginação -->

								</div>
							</div>
							<div class="panel-footer">
								<div class="row">


									<logic:notPresent name="eventoForm" property="id">
										<div class="form-group col-lg-offset-1 col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-block">
												<i class="glyphicon glyphicon-floppy-save"></i>
												Inserir
											</button>
										</div>
									</logic:notPresent>

									<logic:present name="eventoForm" property="id">
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="alterar" class="btn btn-primary btn-block">
												<i class="glyphicon glyphicon-retweet"></i>
												Alterar
											</button>
										</div>
										<div class="form-group col-xs-6 col-sm-12 col-md-3 col-lg-3">
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
		 attr serve para adicionar atributos em tempo de execução
		 */
		$(document).ready(function() {
			/* Bem vindo ao ambiente JQuery */

			$("#data").focus();

			// Desliga o auto-complete da pagina
			$("#form_evento").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estilização para os campos Obrigatorios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#nome").attr("maxlength", 50);

			/* Setando o Placeholder dos campos */
			$("#nome").attr("placeholder", "Nome do Evento");

			/* DEFININDO AS MASCARAS */

			/* $(".data").mask("00/00/0000", {
				placeholder : "__/__/____",
				clearIfNotMatch : true
			}); */

			/* DEFININDO OS EVENTOS DOS BOTOES */
			$("#inserir").click(function() {
				$("#method").val("inserir");
			});

			$("#alterar").click(function() {
				$("#method").val("inserir");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_evento").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_evento").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_evento").submit();
			});

		});
	</script>

</body>
</html>