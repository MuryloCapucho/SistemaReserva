package br.com.systemmcr.sistemareserva.struts;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.systemmcr.sistemareserva.controller.EventoFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractAction;
import br.com.systemmcr.sistemareserva.utilidades.Messages;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class EventoAction extends AbstractAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		eventoForm.limparCampos();
		eventoForm.limparTabela();

		eventoForm.getPaginacao().inicializar();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "eventoCadastro" );
	}// fim abrirTela

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		try {

			EventoFacade eventoFacade = new EventoFacade();

			eventoFacade.inserir( montarPO( eventoForm ) );

			eventoForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Data inválida! - " + eventoForm.getData() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, eventoForm, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		try {

			EventoFacade eventoFacade = new EventoFacade();

			eventoFacade.excluir( montarPO( eventoForm ) );

			eventoForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Data inválida! - " + eventoForm.getData() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, eventoForm, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		try {

			EventoFacade eventoFacade = new EventoFacade();

			eventoFacade.alterar( montarPO( eventoForm ) );

			eventoForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Data inválida! - " + eventoForm.getData() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, eventoForm, request, response );
	}

	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		try {

			EventoFacade eventoFacade = new EventoFacade();

			ArrayList< EventoPO > encontrados = eventoFacade.filtrar( montarPO( eventoForm ), eventoForm.getPaginacao() );

			eventoForm.setEventos( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Data inválida! - " + eventoForm.getData() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "eventoCadastro" );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		try {

			eventoForm.limparTabela();

			eventoForm.getPaginacao().inicializar();

			EventoFacade eventoFacade = new EventoFacade();

			ArrayList< EventoPO > encontrados = eventoFacade.filtrar( montarPO( eventoForm ), eventoForm.getPaginacao() );

			eventoForm.setEventos( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( ParseException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", "Data inválida! - " + eventoForm.getData() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "eventoCadastro" );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		EventoForm eventoForm = (EventoForm) form;

		try {

			EventoFacade eventoFacade = new EventoFacade();

			EventoPO encontrado = eventoFacade.filtrarPorId( Long.valueOf( eventoForm.getId() ) );

			eventoForm.setData( Utilidades.parseDate( encontrado.getData() ) );
			eventoForm.setNome( encontrado.getNome() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "eventoCadastro" );
	}

	private EventoPO montarPO( EventoForm eventoForm ) throws ParseException, NumberFormatException, ApplicationException {
		EventoPO po = new EventoPO();

		if ( eventoForm.getId() == null || eventoForm.getId().isEmpty() ) {
			eventoForm.setId( null );
		}

		if ( eventoForm.getId() != null && !eventoForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( eventoForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( eventoForm.getData() != null && !eventoForm.getData().isEmpty() ) {
			po.setData( Utilidades.parseDate( eventoForm.getData() ) );
		} else {
			po.setData( null );
		}

		if ( eventoForm.getNome() != null && !eventoForm.getNome().isEmpty() ) {
			po.setNome( eventoForm.getNome() );
		} else {
			po.setNome( null );
		}

		return po;
	}
}
