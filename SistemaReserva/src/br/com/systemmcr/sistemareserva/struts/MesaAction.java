package br.com.systemmcr.sistemareserva.struts;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.systemmcr.sistemareserva.controller.MesaFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractAction;
import br.com.systemmcr.sistemareserva.utilidades.Messages;

public class MesaAction extends AbstractAction{
	
	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		MesaForm mesaForm = (MesaForm) form;
		
		mesaForm.limparCampos();
		mesaForm.limparTabela();
		
		mesaForm.getPaginacao().inicializar();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "mesaCadastro" );
	}// fim abrirTela
	
	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		MesaForm mesaForm = (MesaForm) form;

		try {
			
			MesaFacade mesaFacade = new MesaFacade();

			mesaFacade.inserir( montarPO( mesaForm ) );

			mesaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, mesaForm, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		MesaForm mesaForm = (MesaForm) form;

		try {

			MesaFacade mesaFacade = new MesaFacade();

			mesaFacade.excluir( montarPO( mesaForm ) );

			mesaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, mesaForm, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		MesaForm mesaForm = (MesaForm) form;

		try {

			MesaFacade mesaFacade = new MesaFacade();

			mesaFacade.alterar( montarPO( mesaForm ) );

			mesaForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, mesaForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		MesaForm mesaForm = (MesaForm) form;

		try {
			
			mesaForm.limparTabela();
			
			mesaForm.getPaginacao().inicializar();

			MesaFacade mesaFacade = new MesaFacade();

			ArrayList< MesaPO > encontrados = mesaFacade.filtrar( montarPO( mesaForm ), mesaForm.getPaginacao() );

			mesaForm.setMesas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "mesaCadastro" );
	}
	
	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		MesaForm mesaForm = (MesaForm) form;

		try {
			
			MesaFacade mesaFacade = new MesaFacade();

			ArrayList< MesaPO > encontrados = mesaFacade.filtrar( montarPO( mesaForm ), mesaForm.getPaginacao() );

			mesaForm.setMesas( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "mesaCadastro" );
	} 
	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		MesaForm mesaForm = (MesaForm) form;

		try {
			
			MesaFacade mesaFacade = new MesaFacade();

			MesaPO encontrado = mesaFacade.filtrarPorId( Long.valueOf( mesaForm.getId() ) );
			
			mesaForm.setNumero( encontrado.getNumero() );
			mesaForm.setTipo( encontrado.getTipo() );
			mesaForm.setLocalizacao( encontrado.getLocalizacao() );
						
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "mesaCadastro" );
	}

	private MesaPO montarPO( MesaForm mesaForm ) throws ParseException, NumberFormatException, ApplicationException {
		MesaPO po = new MesaPO();

		if ( mesaForm.getId() == null || mesaForm.getId().isEmpty() ) {
			mesaForm.setId( null );
		} 
		
		if ( mesaForm.getId() != null && !mesaForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( mesaForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( mesaForm.getNumero() != null && !mesaForm.getNumero().isEmpty() ) {
			po.setNumero( mesaForm.getNumero() );
		} else {
			po.setNumero( null );
		}
		
		if ( mesaForm.getTipo() != null && !mesaForm.getTipo().isEmpty() ) {
			po.setTipo( mesaForm.getTipo() );
		} else {
			po.setTipo( null );
		}
		
		if ( mesaForm.getLocalizacao() != null && !mesaForm.getLocalizacao().isEmpty() ) {
			po.setLocalizacao( mesaForm.getLocalizacao() );
		} else {
			po.setLocalizacao( null );
		}
		
		return po;
	}
	
}
