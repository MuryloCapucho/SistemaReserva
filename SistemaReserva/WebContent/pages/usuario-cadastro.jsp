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
						<i class="glyphicon glyphicon-user"></i>
						Usuário
						<small style="font-size: 45%">
							<i class="glyphicon glyphicon-forward"></i>
							Cadastro de Usuário
						</small>
					</h2>
				</div>
			</div>

			<!-- O CORPO DA TELA -->
			<div class="row">
				<div class="col-xs-12">

					<!-- CONFIGURAÇÃO IMPORTANTE PARA O FUNCIONAMENTO DO STRUTS (html:form) -->
					<!-- Sempre que o form sofrer um SUBMIT, ele executará o action -->
					<html:form styleId="form_usuario" action="usuarioAction.do" method="post">
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
									<div class="form-group col-xs-12 col-sm12 col-md-2 col-lg-3">
										<label for="id">Código</label>
										<html:text styleClass="form-control bloqueado text-center" styleId="id" name="usuarioForm" property="id" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-3">
										<label for="usuario">Usuário</label>
										<html:text styleClass="form-control obrigatorio" styleId="usuario" name="usuarioForm" property="usuario" />
									</div>

									<div class="form-group col-xs-12 col-sm-12 col-md-8 col-lg-3">
										<label for="senha">Senha</label>
										<html:password styleClass="form-control obrigatorio" styleId="senha" name="usuarioForm" property="senha" />
									</div>
									
									<div class="form-group col-xs-12 col-sm-12 col-md-2 col-lg-3">
										<label for="tipo">Tipo de Usuário</label>
										<html:select styleClass="form-control obrigatorio" styleId="tipo" name="usuarioForm" property="tipo">
											<html:option value="">Selecione</html:option>
											<html:option value="Administrador">Administrador</html:option>
											<html:option value="Usuario">Usuário</html:option>
										</html:select>
									</div>

								</div>

								<div class="row">
									<div class="table-responsive col-xs-12">
										<table class="table table-bordered table-striped table-hover">
											<thead>
												<tr class="bg-primary">
													<th class="text-center" style="width: 80px">#</th>
													<th>Usuário</th>
													<th width="30%">Tipo</th>
													<th class="text-center" style="width: 80px">Selecionar</th>
												</tr>
											</thead>
											<!-- PROPRIEDADES:
											id - Objeto corrente do FOR
											indexId - è o contador como por exemplo o (i)
											name - Nome do Form onde a lista esta
											property - Nome do atributo que representa a lista
											type = Tipo do Objeto que esta dentro(ArrayList<type>) da lista -->
											<logic:iterate id="usuarioCorrente" indexId="i" name="usuarioForm" property="usuarios" type="br.com.systemmcr.sistemareserva.model.po.UsuarioPO">
												<tr>
													<td class="text-center">${usuarioCorrente.id}</td>
													<td>${usuarioCorrente.usuario}</td>
													<td>${usuarioCorrente.tipo}</td>
													<td class="text-center">
														<a href="${rootWeb}/usuarioAction.do?method=selecionar&id=${usuarioCorrente.id}">
															<i class="glyphicon glyphicon-edit btn btn-xs btn-success"></i>
														</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</div>

									<!-- INÍCIO Paginação -->
									<logic:notEmpty name="usuarioForm" property="usuarios">
										<div class="col-xs-12 text-center">
											<nav aria-label="Page navigation">
											<ul class="pagination">
												<li>
													<a href="javascript://" style="cursor: default;">Encontrados: ${usuarioForm.paginacao.totalRegistros} </a>
												</li>
												<logic:equal name="usuarioForm" property="paginacao.voltarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="usuarioForm" property="paginacao.voltarPaginacaoInativo" value="false">
													<li class="page-item">
														<a class="page-link" href="${rootWeb}/usuarioAction.do?method=voltar&forwardDestino=usuarioCadastro" aria-label="Previous">
															<span aria-hidden="true">&laquo;</span>
															<span class="sr-only">Anterior</span>
														</a>
													</li>
												</logic:equal>

												<core:forEach var="i" begin="${usuarioForm.paginacao.paginaInicial}" end="${usuarioForm.paginacao.paginaFinal}" step="1" varStatus="row">
													<core:if test="${usuarioForm.paginacao.paginaCorrente == i}">
														<li class="page-item active">
													</core:if>

													<core:if test="${usuarioForm.paginacao.paginaCorrente != i}">
														<li class="page-item ">
													</core:if>

													<a class="page-link" href="${rootWeb}/usuarioAction.do?method=paginar&paginacao.paginaCorrente=${i}">${i}</a>

												</core:forEach>

												<logic:equal name="usuarioForm" property="paginacao.avancarPaginacaoInativo" value="true">
													<li class="page-item disabled">
														<a class="page-link" href="javascript://" aria-label="Next">
															<span aria-hidden="true">&raquo;</span>
															<span class="sr-only">Próximo</span>
														</a>
													</li>
												</logic:equal>
												<logic:equal name="usuarioForm" property="paginacao.avancarPaginacaoInativo" value="false">
													<li class="page-item ">
														<a class="page-link" href="${rootWeb}/usuarioAction.do?method=avancar&forwardDestino=usuarioCadastro" aria-label="Next">
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
							<div class="panel-footer" >
								<div class="row" >

									<logic:notPresent name="usuarioForm" property="id">
										<div class="form-group col-lg-offset-1 col-lg-offset-1 col-xs-12 col-sm-12 col-md-3 col-lg-3">
											<button type="submit" id="inserir" class="btn btn-success btn-block">
												<i class="glyphicon glyphicon-floppy-save"></i>
												Inserir
											</button>
										</div>
									</logic:notPresent>

									<logic:present name="usuarioForm" property="id">
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

			$("#usuario").focus();

			// Desliga o auto-complete da pagina
			$("#form_usuario").attr("autocomplete", "off");

			/* Criando uma estilização para campos Bloqueados */
			$(".bloqueado").attr("readonly", "readonly");
			$(".bloqueado").css("font-weight", "bold");
			$(".bloqueado").css("cursor", "not-allowed");

			/* Criando a estilização para os campos Obrigatorios */
			$(".obrigatorio").attr("required", "required");
			$(".obrigatorio").css("border-color", "red");

			/* Definindo o tamanho maximo dos campos */
			$("#usuario").attr("maxlength", 20);
			$("#senha").attr("maxlength", 8);
			$("#tipo").attr("maxlength", 15);

			/* Setando o Placeholder dos campos */
			$("#usuario").attr("placeholder", "Usuário");
			$("#senha").attr("placeholder", "Senha");

			/* DEFININDO OS EVENTOS DOS BOTOES */							
			$("#inserir").click(function() {
				$("#method").val("inserir");
			});

			$("#alterar").click(function() {
				$("#method").val("inserir");
			});

			$("#excluir").click(function() {
				$("#method").val("excluir");
				$("#form_usuario").submit();
			});

			$("#pesquisar").click(function() {
				$("#method").val("filtrar");
				$("#form_usuario").submit();
			});

			$("#limpar").click(function() {
				$("#method").val("abrirTela");
				$("#form_usuario").submit();
			});
		});
	</script>

</body>
</html>