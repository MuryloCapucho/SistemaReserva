package br.com.systemmcr.sistemareserva.struts;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.aghata.EasyWorkingReport.exceptions.ReportException;
import br.com.aghata.EasyWorkingReport.facade.EasyWorkingReportFacade;
import br.com.systemmcr.sistemareserva.controller.ClienteFacade;
import br.com.systemmcr.sistemareserva.controller.EventoFacade;
import br.com.systemmcr.sistemareserva.controller.ListaFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.model.po.ListaPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractAction;
import br.com.systemmcr.sistemareserva.utilidades.Messages;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class ListaAction extends AbstractAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		listaForm.limparCampos();
		listaForm.limparTabela();

		listaForm.getPaginacao().inicializar();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "listaCadastro" );
	}// fim abrirTela

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			ListaFacade listaFacade = new ListaFacade();

			listaFacade.inserir( montarPO( listaForm ) );

			listaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, listaForm, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			ListaFacade listaFacade = new ListaFacade();

			listaFacade.excluir( montarPO( listaForm ) );

			listaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, listaForm, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			ListaFacade listaFacade = new ListaFacade();

			listaFacade.alterar( montarPO( listaForm ) );

			listaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, listaForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			listaForm.limparTabela();

			listaForm.getPaginacao().inicializar();

			ListaFacade listaFacade = new ListaFacade();

			ArrayList< ListaPO > encontrados = listaFacade.filtrar( montarPO( listaForm ), listaForm.getPaginacao() );

			listaForm.setListas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "listaCadastro" );
	}

	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			ListaFacade listaFacade = new ListaFacade();

			ArrayList< ListaPO > encontrados = listaFacade.filtrar( montarPO( listaForm ), listaForm.getPaginacao() );

			listaForm.setListas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "listaCadastro" );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			ListaFacade listaFacade = new ListaFacade();

			ListaPO encontrado = listaFacade.filtrarPorId( Long.valueOf( listaForm.getId() ) );

			listaForm.setIdEvento( encontrado.getEvento().getId().toString() );
			listaForm.setNomeEvento( encontrado.getEvento().getNome() );
			listaForm.setClientes( new ArrayList< ClientePO >( encontrado.getClientes() ) );

			Collections.sort( listaForm.getListas() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "listaCadastro" );
	}
	
	public ActionForward selecionarEvento( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ListaForm listaForm = (ListaForm) form;

			EventoPO eventoPO = new EventoPO();
			eventoPO.setNome( listaForm.getIdEvento() );

			ArrayList< EventoPO > eventosEncontrados = new EventoFacade().filtrar( eventoPO, Paginacao.NAO );

			for ( EventoPO eventoCorrente : eventosEncontrados ) {
				if ( !Utilidades.isNuloOuVazio( eventosEncontrados ) ) {
					listaForm.setIdEvento( eventoCorrente.getId().toString() );
					listaForm.setNomeEvento( eventoCorrente.getNome() );
				} else {
					listaForm.setIdEvento( null );
					listaForm.setNomeEvento( null );
				}
			}

			if ( listaForm.getId() == null || listaForm.getId().isEmpty() ) {
				listaForm.setId( null );
			}
						
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return mapping.findForward( "listaCadastro" );
	}
	
	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarAutoCompleteEvento( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ListaForm listaForm = (ListaForm) form;

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			EventoPO eventoPO = new EventoPO();
			eventoPO.setNome( listaForm.getNomeEvento() );

			ArrayList< EventoPO > encontrados = new EventoFacade().filtrar( eventoPO, Paginacao.NAO );

			for ( EventoPO eventoCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", eventoCorrente.getNome() );
				map.put( "data", eventoCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return null;
	}

	public ActionForward adicionarCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {

			ClienteFacade clienteFacade = new ClienteFacade();

			ClientePO clienteEncontrado = clienteFacade.filtrarPorId( Long.valueOf( listaForm.getIdCliente() ) );

			listaForm.getClientes().add( clienteEncontrado );
			listaForm.limparCamposClientes();

			Collections.sort( listaForm.getClientes() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		return mapping.findForward( "listaCadastro" );
	}

	public ActionForward removerCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {
			
			ClienteFacade clienteFacade = new ClienteFacade();

			ClientePO clienteEncontrado = clienteFacade.filtrarPorId( Long.valueOf( listaForm.getIdClienteRemover() ) );

			listaForm.getClientes().remove( clienteEncontrado );

			Collections.sort( listaForm.getClientes() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		return mapping.findForward( "listaCadastro" );
	}

	public ActionForward selecionarCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ListaForm listaForm = (ListaForm) form;

			ClientePO clientePO = new ClientePO();
			clientePO.setNome( listaForm.getIdCliente() );

			ArrayList< ClientePO > clientesEncontrados = new ClienteFacade().filtrar( clientePO, Paginacao.NAO );

			for ( ClientePO clienteCorrente : clientesEncontrados ) {
				if ( !Utilidades.isNuloOuVazio( clientesEncontrados ) ) {
					listaForm.setIdCliente( clienteCorrente.getId().toString() );
					listaForm.setNomeCliente( clienteCorrente.getNome() );
				} else {
					listaForm.setIdCliente( null );
					listaForm.setNomeCliente( null );
				}
			}

			if ( listaForm.getId() == null || listaForm.getId().isEmpty() ) {
				listaForm.setId( null );
			}
			
			Collections.sort( listaForm.getClientes() );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return mapping.findForward( "listaCadastro" );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarAutoCompleteCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ListaForm listaForm = (ListaForm) form;

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			ClientePO clientePO = new ClientePO();
			clientePO.setNome( listaForm.getNomeCliente() );

			ArrayList< ClientePO > encontrados = new ClienteFacade().filtrar( clientePO, Paginacao.NAO );

			for ( ClientePO clienteCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", clienteCorrente.getNome() );
				map.put( "data", clienteCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return null;
	}

	private ListaPO montarPO( ListaForm listaForm ) throws ParseException, NumberFormatException, ApplicationException {
		ListaPO po = new ListaPO();

		if ( listaForm.getId() == null || listaForm.getId().isEmpty() ) {
			listaForm.setId( null );
		}

		if ( listaForm.getId() != null && !listaForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( listaForm.getId() ) );
		} else {
			po.setId( null );
		}
		
		if ( listaForm.getIdEvento() != null && !listaForm.getIdEvento().isEmpty() ) {
			EventoFacade eventoFacade = new EventoFacade();

			EventoPO encontrado = eventoFacade.filtrarPorId( Long.valueOf( listaForm.getIdEvento() ) );

			po.setEvento( encontrado );
		} else {
			po.setEvento( null );
		}
			
		po.getClientes().addAll( listaForm.getClientes() );

		return po;
	}

	public ActionForward gerarLista( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ListaForm listaForm = (ListaForm) form;

		try {
			ListaFacade listaFacade = new ListaFacade();
			
			ArrayList< ListaPO > encontrados = listaFacade.filtrar( montarPO( listaForm ), Paginacao.NAO );
			
			EasyWorkingReportFacade report = new EasyWorkingReportFacade();

			String filePath = getServlet().getServletContext().getRealPath( "/" ) + "relatorio";
			
			ArrayList< ClientePO > lista = new ArrayList< ClientePO >( encontrados.get( 0 ).getClientes());
			
			Collections.sort( lista );
			
			byte[ ] relatorioGerado = report.gerarRelatorioPDF( "listagem", lista, filePath, filePath, new HashMap<>() );
			
			response.setContentType( "application/pdf" );
			response.setContentLength( relatorioGerado.length );
			
			ServletOutputStream out = response.getOutputStream();
			
			out.write( relatorioGerado );
			out.flush();
			response.flushBuffer();
		} catch ( ReportException e ) {
			e.printStackTrace();
		} catch ( IOException e ) {
			e.printStackTrace();
		} catch ( NumberFormatException e ) {
			e.printStackTrace();
		} catch ( ApplicationException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch ( ParseException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return null;
	}// fim gerarRelatorioColagem

}
