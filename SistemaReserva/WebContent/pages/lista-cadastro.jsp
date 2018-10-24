<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

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

<!-- ícone da aba -->
<link rel="icon" href="favicon.ico">

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
			<!-- Utiliza-se o conceito de linha(row) e coluna(col-??-1a12) -->

			<!-- CABEÇALHO INFORMATIVO DA TELA -->
			<div class="row">
				<div class="col-xs-12">
					<h2 class="page-header">
						<i class="glyphicon glyphicon-list-alt"></i>
						Lista VIP
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Listagem de Clientes VIP's
						</small>
					</h2>
				</div>
			</div>
			<!-- O CORPO DA TELA -->
			<div class="row ">
				<div class="col-xs-12">
					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_lista" action="listaAction.do" method="post">
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
								<!-- CRIAÇÃO DOS CAMPOS JUNTAMENTE COM SUAS LABELS -->
								<div class="row">
									<div class="form-group col-xs-12 col-sm-12 col-md-4 col-lg-2">
										<label for="id">Código</label>
										<html:text styleClass="form-control text-center bloqueado" styleId="id" name="listaForm" property="id" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-3">
										<label for="evento">Evento</label>
										<html:text property="nomeEvento" name="listaForm" styleId="evento" styleClass="form-control obrigatorio" />
										<html:hidden property="idEvento" name="listaForm" styleId="eventoSelecionado" />
									</div>

									<div class="form-group col-xs-12 col-sm-8 col-md-2 col-lg-3">
										<label for="cliente">Cliente</label>
										<html:text property="nomeCliente" name="listaForm" styleId="cliente" styleClass="form-control" />
										<html:hidden property="idCliente" name="listaForm" styleId="clienteSelecionado" />
									</div>

									<div class="form-group col-xs-12 col-sm-4 col-md-3 col-lg-4">
										<button type="button" id="adicionar" class="btn btn-success btn-block" style="margin-top: 23px">
											<i class="glyphicon glyphicon-retweet"></i>
											Adicionar
										</button>
									</div>
								</div>

								<div class="row">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
										<div class="panel panel-primary">
											<div class="panel-heading">
												<strong>Clientes</strong>
											</div>
											<div class="panel-body">
												<div class="row">
													<logic:iterate id="clienteCorrente" indexId="i" name="listaForm" property="clientes" type="br.com.systemmcr.sistemareserva.model.po.ClientePO">
														<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
															<div class="alert alert-success">
																<div class="row">
																	<div class="col-xs-10 col-sm-10 col-md-9 col-lg-10">
																		<i class="glyphicon glyphicon-user" style="font-size: 24px"></i>
																		&nbsp;&nbsp;
																		<strong>
																			<a href="${rootWeb}/clienteAction.do?method=selecionar&id=${clienteCorrente.id}"> ${clienteCorrente.nome }</a>
																		</strong>

																	</div>
																	<div class="col-xs-2 col-sm-2 col-md-3 col-lg-2 text-rigth">
																		<a href="${rootWeb}/listaAction.do?method=removerCliente&idClienteRemover=${clienteCorrente.id}">
																			<i class="glyphicon glyphicon-remove btn btn-xs btn-danger"></i>
																		</a>
																	</div>
																</div>
															</div>
														</div>
													</logic:iterate>
												</div>
											</div>
										</div>
									</div>
								</div>

								<div class="row">
									<div class="table-responsive col-xs-12">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="bg-primary">
													<th class="text-center">#</th>
													<th>Evento</th>
													<th>Lista</th>
													<th class="text-center" style="width: 80px">Selecionar</th>
												</tr>
											</thead>
											<!-- PROPRIEDADES:
           									id - Objeto corrente do FOR
           									indexId - é o contador como por exemplo o (i)
           									name - Nome do Form onde a lista esta
           									property - Nome do atributo que representa a lista
           									type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
											<logic:iterate id="listaCorrente" indexId="i" name="listaForm" property="listas" type="br.com.systemmcr.sistemareserva.model.po.ListaPO">
												<tr>
													<td class="text-center">${listaCorrente.id}</td>
													<td>${listaCorrente.evento.nome}</td>
													<td>${listaCorrente.clientes}</td>
													<td class="text-center">
														<a href="${rootWeb}/listaAction.do?method=selecionar&id=${listaCorrente.id}">
															<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>
														</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>
								</div>

								<!-- INÍCIO Paginação -->
								<logic:notEmpty name="listaForm" property="listas">
									<div class="col-xs-12 text-center">
										<nav aria-label="Page navigation">
											<ul class="pagination">
												<li>
													<a href="javascript://" style="cursor: default;">Encontrados: ${listaForm.paginacao.totalRegistros} </a>
												</li>
												<logic:equal name="listaForm" property="paginacao.voltarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="listaForm" property="paginacao.voltarPaginacaoInativo" value="false">
													<li class="page-item">
														<a class="page-link" href="${rootWeb}/listaAction.do?method=voltar&forwardDestino=listaCadastro" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>

												<core:forEach var="i" begin="${listaForm.paginacao.paginaInicial}" end="${listaForm.paginacao.paginaFinal}" step="1" varStatus="row">
													<core:if test="${listaForm.paginacao.paginaCorrente == i}">
														<li class="page-item active">
													</core:if>

													<core:if test="${listaForm.paginacao.paginaCorrente != i}">
														<li class="page-item ">
													</core:if>

													<a class="page-link" href="${rootWeb}/listaAction.do?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
													</li>

												</core:forEach>

												<logic:equal name="listaForm" property="paginacao.avancarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Próximo</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="listaForm" property="paginacao.avancarPaginacaoInativo" value="false">
													<li class="page-item ">
														<a class="page-link" href="${rootWeb}/listaAction.do?method=avancar&forwardDestino=listaCadastro" aria-label="Next">
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
							<div class="panel-footer">
								<div class="row">

									<logic:notPresent name="listaForm" property="id">
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-block">
												<i class="glyphicon glyphicon-floppy-save"></i>
												Inserir
											</button>
										</div>
									</logic:notPresent>

									<logic:present name="listaForm" property="id">
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
									
									<logic:notPresent name="listaForm" property="id">
										<div class="form-group col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="button" id="emitir" class="btn btn-default btn-block">
												<i class="glyphicon glyphicon-print"></i>
												Imprimir Lista VIP 
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

			//Desliga o auto-complete da pagina
			$("#form_lista").attr("autocomplete", "off");

			/* Criando uma configuração para campos bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estilização para os campos obrigatórios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Setando o Placeholder dos campos */
			$("#cliente").attr("placeholder", "Cliente");

			/* DEFININDO OS EVENTOS DOS BOTÕES */
			$("#inserir").click(function() {
				$("#method").val("inserir");
				$("#form_lista").attr("target", "_self");
			});

			$("#alterar").click(function() {
				$("#method").val("alterar");
				$("#form_lista").attr("target", "_self");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_lista").attr("target", "_self");
				$("#form_lista").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_lista").attr("target", "_self");
				$("#form_lista").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_lista").attr("target", "_self");
				$("#form_lista").submit();
			});
			
			$("#adicionar").click(function() {
				$("#method").val("adicionarCliente");
				$("#form_lista").attr("target", "_self");
				$("#form_lista").submit();
			});

			$("#emitir").click(function() {
				$("#method").val("gerarLista");
				$("#form_lista").attr("target", "_blank");
				$("#form_lista").submit();
			});
			
			$('#evento').autocomplete({
				minChars : 2,
				paramName : 'nomeEvento',
				serviceUrl : '${rootWeb}/listaAction.do?method=selecionarAutoCompleteEvento',
				onSelect : function(suggestion) {
					//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
					$('#eventoSelecionado').val(suggestion.value);
					document.getElementById("form_lista").method.value = "selecionarEvento";
					$("#form_lista").attr("target", "_self");
					document.getElementById("form_lista").submit();
				}
			});

			$('#cliente').autocomplete({
				minChars : 2,
				paramName : 'nomeCliente',
				serviceUrl : '${rootWeb}/listaAction.do?method=selecionarAutoCompleteCliente',
				onSelect : function(suggestion) {
					//alert('You selected: ' + suggestion.value + ', ' + suggestion.data);
					$('#clienteSelecionado').val(suggestion.value);
					document.getElementById("form_lista").method.value = "selecionarCliente";
					$("#form_lista").attr("target", "_self");
					document.getElementById("form_lista").submit();
				}
			});
		});

		/*
		Função para Bloquear botão voltar		
		 */

		(function(window) {
			'use strict';

			var noback = {

				//globals 
				version : '0.0.1',
				history_api : typeof history.pushState !== 'undefined',

				init : function() {
					window.location.hash = '#no-back';
					noback.configure();
				},

				hasChanged : function() {
					if (window.location.hash == '#no-back') {
						window.location.hash = '#BLOQUEIO';
						//mostra mensagem que não pode usar o btn volta do browser
						if ($("#msgAviso").css('display') == 'none') {
							$("#msgAviso").slideToggle("slow");
						}
					}
				},

				checkCompat : function() {
					if (window.addEventListener) {
						window.addEventListener("hashchange", noback.hasChanged, false);
					} else if (window.attachEvent) {
						window.attachEvent("onhashchange", noback.hasChanged);
					} else {
						window.onhashchange = noback.hasChanged;
					}
				},

				configure : function() {
					if (window.location.hash == '#no-back') {
						if (this.history_api) {
							history.pushState(null, '', '#BLOQUEIO');
						} else {
							window.location.hash = '#BLOQUEIO';
							//mostra mensagem que não pode usar o btn volta do browser
							if ($("#msgAviso").css('display') == 'none') {
								$("#msgAviso").slideToggle("slow");
							}
						}
					}
					noback.checkCompat();
					noback.hasChanged();
				}

			};

			// AMD support 
			if (typeof define === 'function' && define.amd) {
				define(function() {
					return noback;
				});
			}
			// For CommonJS and CommonJS-like 
			else if (typeof module === 'object' && module.exports) {
				module.exports = noback;
			} else {
				window.noback = noback;
			}
			noback.init();
		}(window));
	</script>
</body>
</html>