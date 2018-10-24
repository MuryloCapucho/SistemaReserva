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
						<i class="glyphicon glyphicon-check"></i>
						Reserva
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Reserva de Mesas / Bistrôs / Camarotes por Cliente
						</small>
					</h2>
				</div>
			</div>

			<!-- O CORPO DA TELA -->
			<div class="row">
				<div class="col-xs-12">

					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_reserva" action="reservaAction.do" method="post">
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
									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
										<label for="id">Código</label>
										<html:text styleClass="form-control bloqueado text-center" styleId="id" name="reservaForm" property="id" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-3">
										<label for="evento">Evento</label>
										<html:text property="nomeEvento" name="reservaForm" styleId="evento" styleClass="form-control obrigatorio" />
										<html:hidden property="idEvento" name="reservaForm" styleId="eventoSelecionado" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-2">
										<label for="mesa">Mesa</label>
										<html:text property="numeroMesa" name="reservaForm" styleId="mesa" styleClass="form-control obrigatorio" />
										<html:hidden property="idMesa" name="reservaForm" styleId="mesaSelecionada" />
									</div>

									<div class="form-group col-xs-12 col-sm-8 col-md-2 col-lg-3">
										<label for="cliente">Cliente</label>
										<html:text property="nomeCliente" name="reservaForm" styleId="cliente" styleClass="form-control obrigatorio" />
										<html:hidden property="idCliente" name="reservaForm" styleId="clienteSelecionado" />
									</div>

									<div class="form-group col-xs-12 col-sm-4 col-md-2 col-lg-2">
										<a style="margin-top: 24px" href="${rootWeb}/clienteAction.do?method=abrirTela" class="btn btn-success btn-block">
											<i class="glyphicon glyphicon-plus"></i>
											Cliente
										</a>
									</div>

								</div>

								<div class="row">
									<div class="table-responsive col-xs-12">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="bg-primary">
													<th class="text-center" style="width: 80px">#</th>
													<th>Evento</th>
													<th>Mesa</th>
													<th>Cliente</th>
													<th class="text-center" style="width: 80px">Selecionar</th>
												</tr>
											</thead>
											<!-- PROPRIEDADES:
											id - Objeto corrente do FOR
											indexId - è o contador como por exemplo o (i)
											name - Nome do Form onde a lista esta
											property - Nome do atributo que representa a lista
											type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
											<logic:iterate id="reservaCorrente" indexId="i" name="reservaForm" property="reservas" type="br.com.systemmcr.sistemareserva.model.po.ReservaPO">
												<tr>
													<td class="text-center">${reservaCorrente.id}</td>
													<td>${reservaCorrente.evento.nome}</td>
													<td>${reservaCorrente.mesa.numero}-${reservaCorrente.mesa.tipo}</td>
													<td>${reservaCorrente.cliente.nome}</td>
													<td class="text-center">
														<a href="${rootWeb}/reservaAction.do?method=selecionar&id=${reservaCorrente.id}">
															<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>
														</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>

									<!-- INÍCIO Paginação -->
									<logic:notEmpty name="reservaForm" property="reservas">
										<div class="col-xs-12 text-center">
											<nav aria-label="Page navigation">
											<ul class="pagination">
												<li>
													<a href="javascript://" style="cursor: default;">Encontrados: ${reservaForm.paginacao.totalRegistros} </a>
												</li>
												<logic:equal name="reservaForm" property="paginacao.voltarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="reservaForm" property="paginacao.voltarPaginacaoInativo" value="false">
													<li class="page-item">
														<a class="page-link" href="${rootWeb}/reservaAction.do?method=voltar&forwardDestino=reservaCadastro" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>



												<core:forEach var="i" begin="${reservaForm.paginacao.paginaInicial}" end="${reservaForm.paginacao.paginaFinal}" step="1" varStatus="row">
													<core:if test="${reservaForm.paginacao.paginaCorrente == i}">
														<li class="page-item active">
													</core:if>

													<core:if test="${reservaForm.paginacao.paginaCorrente != i}">
														<li class="page-item ">
													</core:if>

													<a class="page-link" href="${rootWeb}/reservaAction.do?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
													</li>

												</core:forEach>



												<logic:equal name="reservaForm" property="paginacao.avancarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Próximo</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="reservaForm" property="paginacao.avancarPaginacaoInativo" value="false">
													<li class="page-item ">
														<a class="page-link" href="${rootWeb}/reservaAction.do?method=avancar&forwardDestino=reservaCadastro" aria-label="Next">
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

									<logic:notPresent name="reservaForm" property="id">
										<div class="form-group col-lg-offset-1 col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-block">
												<i class="glyphicon glyphicon-floppy-save"></i>
												Inserir
											</button>
										</div>
									</logic:notPresent>

									<logic:present name="reservaForm" property="id">
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

								<div class="row">
									<logic:notPresent name="reservaForm" property="id">
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-6">
											<button type="button" id="emitir" class="btn btn-default btn-block">
												<i class="glyphicon glyphicon-print"></i>
												Imprimir Croquí 
											</button>
										</div>
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-6">
											<button type="button" id="emitirColagem" class="btn btn-danger btn-block">
												<i class="glyphicon glyphicon-print"></i>
												Imprimir Colagem
											</button>
										</div>
									</logic:notPresent>
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

			$("#evento").focus();

			// Desliga o auto-complete da pagina
			$("#form_reserva").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estilização para os campos Obrigatorios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Setando o Placeholder dos campos */
			$("#evento").attr("placeholder", "Evento");
			$("#mesa").attr("placeholder", "Mesa");
			$("#cliente").attr("placeholder", "Cliente");

			/* DEFININDO OS EVENTOS DOS BOTOES */
			$("#inserir").click(function() {
				$("#method").val("inserir");
				$("#form_reserva").attr("target", "_self");
			});

			$("#alterar").click(function() {
				$("#method").val("inserir");
				$("#form_reserva").attr("target", "_self");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_reserva").attr("target", "_self");
				$("#form_reserva").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_reserva").attr("target", "_self");
				$("#form_reserva").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_reserva").attr("target", "_self");
				$("#form_reserva").submit();
			});

			$("#emitir").click(function() {
				$("#method").val("gerarRelatorio");
				$("#form_reserva").attr("target", "_blank");
				$("#form_reserva").submit();
			});

			$("#emitirColagem").click(function() {
				$("#method").val("gerarRelatorioColagem");
				$("#form_reserva").attr("target", "_blank");
				$("#form_reserva").submit();
			});

			$('#evento').autocomplete({
				minChars : 2,
				paramName : 'nomeEvento',
				serviceUrl : '${rootWeb}/reservaAction.do?method=selecionarAutoCompleteEvento',
				onSelect : function(suggestion) {
					//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
					$('#eventoSelecionado').val(suggestion.value);
					document.getElementById("form_reserva").method.value = "selecionarEvento";
					$("#form_reserva").attr("target", "_self");
					document.getElementById("form_reserva").submit();
				}
			});

			$('#mesa').autocomplete({
				minChars : 2,
				paramName : 'numeroMesa',
				serviceUrl : '${rootWeb}/reservaAction.do?method=selecionarAutoCompleteMesa',
				onSelect : function(suggestion) {
					//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
					$('#mesaSelecionada').val(suggestion.data);
					document.getElementById("form_reserva").method.value = "selecionarMesa";
					$("#form_reserva").attr("target", "_self");
					document.getElementById("form_reserva").submit();
				}
			});

			$('#cliente').autocomplete({
				minChars : 2,
				paramName : 'nomeCliente',
				serviceUrl : '${rootWeb}/reservaAction.do?method=selecionarAutoCompleteCliente',
				onSelect : function(suggestion) {
					//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
					$('#clienteSelecionado').val(suggestion.value);
					document.getElementById("form_reserva").method.value = "selecionarCliente";
					document.getElementById("form_reserva").submit();
				}
			});
		});
	</script>

</body>
</html>