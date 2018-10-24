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
import br.com.systemmcr.sistemareserva.controller.MesaFacade;
import br.com.systemmcr.sistemareserva.controller.ReservaFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.model.po.ReservaPO;
import br.com.systemmcr.sistemareserva.model.pojo.RelatorioReservaPOJO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractAction;
import br.com.systemmcr.sistemareserva.utilidades.Messages;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class ReservaAction extends AbstractAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		reservaForm.limparCampos();
		reservaForm.limparTabela();

		reservaForm.getPaginacao().inicializar();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "reservaCadastro" );
	}// fim abrirTela

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {

			ReservaFacade reservaFacade = new ReservaFacade();

			reservaFacade.inserir( montarPO( reservaForm ) );

			reservaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, reservaForm, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {

			ReservaFacade reservaFacade = new ReservaFacade();

			reservaFacade.excluir( montarPO( reservaForm ) );

			reservaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, reservaForm, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {

			ReservaFacade reservaFacade = new ReservaFacade();

			reservaFacade.alterar( montarPO( reservaForm ) );

			reservaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, reservaForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {

			reservaForm.limparTabela();

			reservaForm.getPaginacao().inicializar();

			ReservaFacade reservaFacade = new ReservaFacade();

			ArrayList< ReservaPO > encontrados = reservaFacade.filtrar( montarPO( reservaForm ), reservaForm.getPaginacao() );

			reservaForm.setReservas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "reservaCadastro" );
	}

	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {

			ReservaFacade reservaFacade = new ReservaFacade();

			ArrayList< ReservaPO > encontrados = reservaFacade.filtrar( montarPO( reservaForm ), reservaForm.getPaginacao() );

			reservaForm.setReservas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "reservaCadastro" );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {

			ReservaFacade reservaFacade = new ReservaFacade();

			ReservaPO encontrado = reservaFacade.filtrarPorId( Long.valueOf( reservaForm.getId() ) );

			reservaForm.setIdEvento( encontrado.getEvento().getId().toString() );
			reservaForm.setNomeEvento( encontrado.getEvento().getNome() );
			reservaForm.setIdMesa( encontrado.getMesa().getId().toString() );
			reservaForm.setNumeroMesa( encontrado.getMesa().getNumero() );
			reservaForm.setIdCliente( encontrado.getCliente().getId().toString() );
			reservaForm.setNomeCliente( encontrado.getCliente().getNome() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "reservaCadastro" );
	}

	public ActionForward selecionarEvento( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ReservaForm reservaForm = (ReservaForm) form;

			EventoPO eventoPO = new EventoPO();
			eventoPO.setNome( reservaForm.getIdEvento() );

			ArrayList< EventoPO > eventosEncontrados = new EventoFacade().filtrar( eventoPO, Paginacao.NAO );

			for ( EventoPO eventoCorrente : eventosEncontrados ) {
				if ( !Utilidades.isNuloOuVazio( eventosEncontrados ) ) {
					reservaForm.setIdEvento( eventoCorrente.getId().toString() );
					reservaForm.setNomeEvento( eventoCorrente.getNome() );
				} else {
					reservaForm.setIdEvento( null );
					reservaForm.setNomeEvento( null );
				}
			}

			if ( reservaForm.getId() == null || reservaForm.getId().isEmpty() ) {
				reservaForm.setId( null );
			}
			
			Collections.sort( reservaForm.getEventos() );
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return mapping.findForward( "reservaCadastro" );
	}

	public ActionForward selecionarMesa( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ReservaForm reservaForm = (ReservaForm) form;

			MesaPO mesaPO = new MesaPO();
			mesaPO.setNumero( reservaForm.getIdMesa() + reservaForm.getNumeroMesa() );

			ArrayList< MesaPO > mesasEncontrados = new MesaFacade().filtrar( mesaPO, Paginacao.NAO );

			for ( MesaPO mesaCorrente : mesasEncontrados ) {
				if ( !Utilidades.isNuloOuVazio( mesasEncontrados ) ) {
					reservaForm.setIdMesa( mesaCorrente.getId().toString() );
					reservaForm.setNumeroMesa( mesaCorrente.getNumero() );
				} else {
					reservaForm.setIdMesa( null );
					reservaForm.setNumeroMesa( null );
				}
			}

			if ( reservaForm.getId() == null || reservaForm.getId().isEmpty() ) {
				reservaForm.setId( null );
			}
			
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return mapping.findForward( "reservaCadastro" );
	}

	public ActionForward selecionarCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ReservaForm reservaForm = (ReservaForm) form;

			ClientePO clientePO = new ClientePO();
			clientePO.setNome( reservaForm.getIdCliente() );

			ArrayList< ClientePO > clientesEncontrados = new ClienteFacade().filtrar( clientePO, Paginacao.NAO );
			
			for ( ClientePO clienteCorrente : clientesEncontrados ) {
				if ( !Utilidades.isNuloOuVazio( clientesEncontrados ) ) {
					reservaForm.setIdCliente( clienteCorrente.getId().toString() );
					reservaForm.setNomeCliente( clienteCorrente.getNome() );
				} else {
					reservaForm.setIdCliente( null );
					reservaForm.setNomeCliente( null );
				}
			}

			if ( reservaForm.getId() == null || reservaForm.getId().isEmpty() ) {
				reservaForm.setId( null );
			}
			
			Collections.sort( reservaForm.getClientes() );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return mapping.findForward( "reservaCadastro" );
	}

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarAutoCompleteEvento( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ReservaForm reservaForm = (ReservaForm) form;

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			EventoPO eventoPO = new EventoPO();
			eventoPO.setNome( reservaForm.getNomeEvento() );

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

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarAutoCompleteMesa( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ReservaForm reservaForm = (ReservaForm) form;

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			MesaPO mesaPO = new MesaPO();
			mesaPO.setNumero( reservaForm.getNumeroMesa() );

			ArrayList< MesaPO > encontrados = new MesaFacade().filtrar( mesaPO, Paginacao.NAO );

			for ( MesaPO mesaCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", mesaCorrente.getNumero() + " " + mesaCorrente.getTipo() + " " + mesaCorrente.getLocalizacao() );
				map.put( "data", mesaCorrente.getId() );
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

	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarAutoCompleteCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ReservaForm reservaForm = (ReservaForm) form;

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			ClientePO clientePO = new ClientePO();
			clientePO.setNome( reservaForm.getNomeCliente() );

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

	private ReservaPO montarPO( ReservaForm reservaForm ) throws ParseException, NumberFormatException, ApplicationException {
		ReservaPO po = new ReservaPO();

		if ( reservaForm.getId() == null || reservaForm.getId().isEmpty() ) {
			reservaForm.setId( null );
		}

		if ( reservaForm.getId() != null && !reservaForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( reservaForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( reservaForm.getIdEvento() != null && !reservaForm.getIdEvento().isEmpty() ) {
			EventoFacade eventoFacade = new EventoFacade();

			EventoPO encontrado = eventoFacade.filtrarPorId( Long.valueOf( reservaForm.getIdEvento() ) );

			po.setEvento( encontrado );
		} else {
			po.setEvento( null );
		}

		if ( reservaForm.getIdMesa() != null && !reservaForm.getIdMesa().isEmpty() ) {
			MesaFacade mesaFacade = new MesaFacade();

			MesaPO encontrado = mesaFacade.filtrarPorId( Long.valueOf( reservaForm.getIdMesa() ) );

			po.setMesa( encontrado );
		} else {
			po.setMesa( null );
		}

		if ( reservaForm.getIdCliente() != null && !reservaForm.getIdCliente().isEmpty() ) {
			ClienteFacade clienteFacade = new ClienteFacade();

			ClientePO encontrado = clienteFacade.filtrarPorId( Long.valueOf( reservaForm.getIdCliente() ) );

			po.setCliente( encontrado );
		} else {
			po.setCliente( null );
		}

		return po;
	}

	public ActionForward gerarRelatorio( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {
			
			ReservaFacade reservaFacade = new ReservaFacade();

			ArrayList< ReservaPO > encontrados = reservaFacade.filtrar( montarPO( reservaForm ), Paginacao.NAO );
			
			EasyWorkingReportFacade report = new EasyWorkingReportFacade();

			String filePath = getServlet().getServletContext().getRealPath( "/" ) + "relatorio";

			ArrayList< RelatorioReservaPOJO > lista = new ArrayList< RelatorioReservaPOJO >();

			RelatorioReservaPOJO relatorioReservaPOJO = new RelatorioReservaPOJO();

			for ( ReservaPO reservaCorrente : encontrados ) {
				
				relatorioReservaPOJO.setData( reservaCorrente.getEvento().getDataToString() );
				relatorioReservaPOJO.setEvento( reservaCorrente.getEvento().getNome() );
				
				if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M1" ) ) {
					relatorioReservaPOJO.setMesa1( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M2" ) ) {
					relatorioReservaPOJO.setMesa2( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M3" ) ) {
					relatorioReservaPOJO.setMesa3( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M4" ) ) {
					relatorioReservaPOJO.setMesa4( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M5" ) ) {
					relatorioReservaPOJO.setMesa5( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M6" ) ) {
					relatorioReservaPOJO.setMesa6( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M7" ) ) {
					relatorioReservaPOJO.setMesa7( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M8" ) ) {
					relatorioReservaPOJO.setMesa8( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M9" ) ) {
					relatorioReservaPOJO.setMesa9( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M10" ) ) {
					relatorioReservaPOJO.setMesa10( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M11" ) ) {
					relatorioReservaPOJO.setMesa11( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M12" ) ) {
					relatorioReservaPOJO.setMesa12( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M13" ) ) {
					relatorioReservaPOJO.setMesa13( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M14" ) ) {
					relatorioReservaPOJO.setMesa14( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M15" ) ) {
					relatorioReservaPOJO.setMesa15( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M16" ) ) {
					relatorioReservaPOJO.setMesa16( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M17" ) ) {
					relatorioReservaPOJO.setMesa17( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M18" ) ) {
					relatorioReservaPOJO.setMesa18( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M19" ) ) {
					relatorioReservaPOJO.setMesa19( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M20" ) ) {
					relatorioReservaPOJO.setMesa20( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M21" ) ) {
					relatorioReservaPOJO.setMesa21( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M22" ) ) {
					relatorioReservaPOJO.setMesa22( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M23" ) ) {
					relatorioReservaPOJO.setMesa23( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M24" ) ) {
					relatorioReservaPOJO.setMesa24( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M25" ) ) {
					relatorioReservaPOJO.setMesa25( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M26" ) ) {
					relatorioReservaPOJO.setMesa26( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M27" ) ) {
					relatorioReservaPOJO.setMesa27( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M28" ) ) {
					relatorioReservaPOJO.setMesa28( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M29" ) ) {
					relatorioReservaPOJO.setMesa29( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "M30" ) ) {
					relatorioReservaPOJO.setMesa30( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B1" ) ) {
					relatorioReservaPOJO.setBistro1( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B2" ) ) {
					relatorioReservaPOJO.setBistro2( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B3" ) ) {
					relatorioReservaPOJO.setBistro3( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B4" ) ) {
					relatorioReservaPOJO.setBistro4( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B5" ) ) {
					relatorioReservaPOJO.setBistro5( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B6" ) ) {
					relatorioReservaPOJO.setBistro6( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "B7" ) ) {
					relatorioReservaPOJO.setBistro7( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "C1" ) ) {
					relatorioReservaPOJO.setCamarote1( reservaCorrente.getCliente().getNome() );
				} else if ( reservaCorrente.getMesa().getLocalizacao().equalsIgnoreCase( "C2" ) ) {
					relatorioReservaPOJO.setCamarote2( reservaCorrente.getCliente().getNome() );
				}

			}

			lista.add( relatorioReservaPOJO );

			byte[ ] relatorioGerado = report.gerarRelatorioPDF( "reservas", lista, filePath, filePath, new HashMap<>() );
			
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
			// TODO Auto-generated catch block
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
	}// fim gerarRelatorio
	
	public ActionForward gerarRelatorioColagem( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ReservaForm reservaForm = (ReservaForm) form;

		try {
			ReservaFacade reservaFacade = new ReservaFacade();
			
			ArrayList< ReservaPO > encontrados = reservaFacade.filtrar( montarPO( reservaForm ), Paginacao.NAO );
			
			EasyWorkingReportFacade report = new EasyWorkingReportFacade();

			String filePath = getServlet().getServletContext().getRealPath( "/" ) + "relatorio";

			byte[ ] relatorioGerado = report.gerarRelatorioPDF( "colagem", encontrados, filePath, filePath, new HashMap<>() );
			
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
