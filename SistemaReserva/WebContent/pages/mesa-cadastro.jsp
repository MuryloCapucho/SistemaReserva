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
						<i class="glyphicon glyphicon-plus"></i>
						Mesa
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Cadastro de Mesa/Bistrô/Camarote
						</small>
					</h2>
				</div>
			</div>

			<!-- O CORPO DA TELA -->
			<div class="row">
				<div class="col-xs-12">

					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_mesa" action="mesaAction.do" method="post">
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
									<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-2">
										<label for="id">Código</label>
										<html:text styleClass="form-control bloqueado text-center" styleId="id" name="mesaForm" property="id" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-2 col-lg-3">
										<label for="tipo">Tipo de Mesa</label>
										<html:select styleClass="form-control obrigatorio" styleId="tipo" name="mesaForm" property="tipo">
											<html:option value="">Selecione</html:option>
											<html:option value="Camarote">Camarote</html:option>
											<html:option value="Bistrô">Bistrô</html:option>
											<html:option value="Mesa">Mesa</html:option>
										</html:select>
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-3">
										<label for="numero">Número</label>
										<html:text styleClass="form-control obrigatorio inteiro text-center" styleId="numero" name="mesaForm" property="numero" />
									</div>

									<div class="form-group col-xs-12 col-sm-6 col-md-8 col-lg-4">
										<label for="localizacao">Localização</label>
										<html:text styleClass="form-control obrigatorio" styleId="localizacao" name="mesaForm" property="localizacao" />
									</div>

								</div>

								<div class="row">
									<div class="table-responsive col-xs-12">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="bg-primary">
													<th class="text-center" style="width: 80px">#</th>
													<th>Tipo</th>
													<th>Número</th>
													<th>Localização</th>
													<th class="text-center" style="width: 80px">Selecionar</th>
												</tr>
											</thead>
											<!-- PROPRIEDADES:
											id - Objeto corrente do FOR
											indexId - è o contador como por exemplo o (i)
											name - Nome do Form onde a lista esta
											property - Nome do atributo que representa a lista
											type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
											<logic:iterate id="mesaCorrente" indexId="i" name="mesaForm" property="mesas" type="br.com.systemmcr.sistemareserva.model.po.MesaPO">
												<tr>
													<td class="text-center">${mesaCorrente.id}</td>
													<td>${mesaCorrente.tipo}</td>
													<td>${mesaCorrente.numero}</td>
													<td>${mesaCorrente.localizacao}</td>
													<td class="text-center">
														<a href="${rootWeb}/mesaAction.do?method=selecionar&id=${mesaCorrente.id}">
															<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>
														</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>

									<!-- INÍCIO Paginação -->
									<logic:notEmpty name="mesaForm" property="mesas">
										<div class="col-xs-12 text-center">
											<nav aria-label="Page navigation">
											<ul class="pagination">
												<li>
													<a href="javascript://" style="cursor: default;">Encontrados: ${mesaForm.paginacao.totalRegistros} </a>
												</li>
												<logic:equal name="mesaForm" property="paginacao.voltarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="mesaForm" property="paginacao.voltarPaginacaoInativo" value="false">
													<li class="page-item">
														<a class="page-link" href="${rootWeb}/mesaAction.do?method=voltar&forwardDestino=mesaCadastro" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>



												<core:forEach var="i" begin="${mesaForm.paginacao.paginaInicial}" end="${mesaForm.paginacao.paginaFinal}" step="1" varStatus="row">
													<core:if test="${mesaForm.paginacao.paginaCorrente == i}">
														<li class="page-item active">
													</core:if>

													<core:if test="${mesaForm.paginacao.paginaCorrente != i}">
														<li class="page-item ">
													</core:if>

													<a class="page-link" href="${rootWeb}/mesaAction.do?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>
													</li>

												</core:forEach>



												<logic:equal name="mesaForm" property="paginacao.avancarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Próximo</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="mesaForm" property="paginacao.avancarPaginacaoInativo" value="false">
													<li class="page-item ">
														<a class="page-link" href="${rootWeb}/mesaAction.do?method=avancar&forwardDestino=mesaCadastro" aria-label="Next">
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


									<logic:notPresent name="mesaForm" property="id">
										<div class="form-group col-lg-offset-1 col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-block">
												<i class="glyphicon glyphicon-floppy-save"></i>
												Inserir
											</button>
										</div>
									</logic:notPresent>

									<logic:present name="mesaForm" property="id">
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
		 attr serve para adicionar atributos em tempo de execução
		 */
		$(document).ready(function() {
			/* Bem vindo ao ambiente JQuery */

			$("#tipo").focus();

			// Desliga o auto-complete da pagina
			$("#form_mesa").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estilização para os campos Obrigatorios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#numero").attr("maxlength", 4);
			$("#tipo").attr("maxlength", 7);
			$("#localizacao").attr("maxlength", 10);

			/* Setando o Placeholder dos campos */
			$("#numero").attr("placeholder", "Número");
			$("#tipo").attr("placeholder", "Tipo");
			$("#localizacao").attr("placeholder", "Localização");

			/* DEFININDO OS EVENTOS DOS BOTOES */
			$("#inserir").click(function() {
				$("#method").val("inserir");
			});

			$("#alterar").click(function() {
				$("#method").val("inserir");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_mesa").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_mesa").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_mesa").submit();
			});
		});
	</script>

</body>
</html>