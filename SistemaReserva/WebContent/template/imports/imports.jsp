<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%
	/* Setando na sessão o caminho do WebContent */
	request.getSession().setAttribute("rootWeb", request.getContextPath());
%>

<!-- FavICO -->
<link rel="shortcut icon" href="${rootWeb}/images/favicon.ico" type="image/x-icon" />

<!-- Bootstrap Core CSS -->
<link href="${rootWeb}/assets/sbadmin2/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="${rootWeb}/assets/sbadmin2/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="${rootWeb}/assets/sbadmin2/dist/css/sb-admin-2.css" rel="stylesheet">

<!-- Custom Fonts -->
<link href="${rootWeb}/assets/sbadmin2/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

<!-- AutoComplete -->
<link rel="stylesheet" href="${rootWeb}/assets/jquery-autocomplete/content/styles.css" />

<!-- jQuery -->
<script src="${rootWeb}/assets/sbadmin2/bower_components/jquery/dist/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${rootWeb}/assets/sbadmin2/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${rootWeb}/assets/sbadmin2/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->
<script src="${rootWeb}/assets/sbadmin2/dist/js/sb-admin-2.js"></script>

<!-- MASCARAS -->
<script src="${rootWeb}/assets/jQuery-Mask-Plugin-master/dist/jquery.mask.min.js"></script>

<!-- AutoComplete -->
<script src="${rootWeb}/assets/jquery-autocomplete/dist/jquery.autocomplete.min.js"></script>

<!-- Máscaras -->
<script type="text/javascript">
	$(document).ready(function() {
		carregarMascaras();
	});

	function carregarMascaras() {
		$(".data").mask("00/00/0000", {
			placeholder : "__/__/____",
			clearIfNotMatch : true
		});

		$(".dinheiro").mask('000.000.000.000.000,00', {
			placeholder : "R$ 0,00",
			reverse : true
		});

		$('.inteiro').mask('##0', {
			placeholder : "0",
			reverse : true
		});
		$('.decimal').mask('##0,00', {
			placeholder : "0,00",
			reverse : true
		});
		$(".cpf").mask("000.000.000-00", {
			placeholder : "000.000.000-00",
			clearIfNotMatch : true
		});
		$(".cnpj").mask("00.000.000/0000-00", {
			placeholder : "00.000.000/0000-00",
			clearIfNotMatch : true
		});
		$(".cep").mask("00.000-000", {
			placeholder : "00.000-000",
			clearIfNotMatch : true
		});
		$(".cel").mask("(00)0000-00000", {
			placeholder : "(00)x0000-0000",
			clearIfNotMatch : false
		});

		$(".hora").mask("00:00:00", {
			placeholder : "00:00:00",
			clearIfNotMatch : false
		});
	}

	/* Início Bloqueio de Tela */
	(function(window) { 
		  'use strict'; 
		 
		var noback = { 
			 
			//globals 
			version: '0.0.1', 
			history_api : typeof history.pushState !== 'undefined', 
			 
			init:function(){ 
				window.location.hash = '#no-back'; 
				noback.configure(); 
			}, 
			 
			hasChanged:function(){ 
				if (window.location.hash == '#no-back' ){ 
					window.location.hash = '#BLOQUEIO';
					//mostra mensagem que não pode usar o btn volta do browser
					if($( "#msgAviso" ).css('display') =='none'){
						$( "#msgAviso" ).slideToggle("slow");
					}
				} 
			}, 
			 
			checkCompat: function(){ 
				if(window.addEventListener) { 
					window.addEventListener("hashchange", noback.hasChanged, false); 
				}else if (window.attachEvent) { 
					window.attachEvent("onhashchange", noback.hasChanged); 
				}else{ 
					window.onhashchange = noback.hasChanged; 
				} 
			}, 
			 
			configure: function(){ 
				if ( window.location.hash == '#no-back' ) { 
					if ( this.history_api ){ 
						history.pushState(null, '', '#BLOQUEIO'); 
					}else{  
						window.location.hash = '#BLOQUEIO';
						//mostra mensagem que não pode usar o btn volta do browser
						if($( "#msgAviso" ).css('display') =='none'){
							$( "#msgAviso" ).slideToggle("slow");
						}
					} 
				} 
				noback.checkCompat(); 
				noback.hasChanged(); 
			} 
			 
			}; 
			 
			// AMD support 
			if (typeof define === 'function' && define.amd) { 
				define( function() { return noback; } ); 
			}  
			// For CommonJS and CommonJS-like 
			else if (typeof module === 'object' && module.exports) { 
				module.exports = noback; 
			}  
			else { 
				window.noback = noback; 
			} 
			noback.init();
		}(window));
	/* Fechamento Bloqueio de Tela */
	
</script>
