package br.com.systemmcr.sistemareserva.struts;

import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.systemmcr.sistemareserva.controller.UsuarioFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.UsuarioPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractAction;
import br.com.systemmcr.sistemareserva.utilidades.Messages;

public class UsuarioAction extends AbstractAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		
		UsuarioForm usuarioForm = (UsuarioForm) form;
		
		usuarioForm.limparCampos();
		usuarioForm.limparTabela();
		
		usuarioForm.getPaginacao().inicializar();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "usuarioCadastro" );
	}// fim abrirTela
	
	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UsuarioForm usuarioForm = (UsuarioForm) form;

		try {
			
			UsuarioFacade usuarioFacade = new UsuarioFacade();

			usuarioFacade.inserir( montarPO( usuarioForm ) );

			usuarioForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, usuarioForm, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UsuarioForm usuarioForm = (UsuarioForm) form;

		try {

			UsuarioFacade usuarioFacade = new UsuarioFacade();

			usuarioFacade.excluir( montarPO( usuarioForm ) );

			usuarioForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, usuarioForm, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UsuarioForm usuarioForm = (UsuarioForm) form;

		try {

			UsuarioFacade usuarioFacade = new UsuarioFacade();

			usuarioFacade.alterar( montarPO( usuarioForm ) );

			usuarioForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, usuarioForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UsuarioForm usuarioForm = (UsuarioForm) form;

		try {
			
			usuarioForm.limparTabela();
			
			usuarioForm.getPaginacao().inicializar();

			UsuarioFacade usuarioFacade = new UsuarioFacade();

			ArrayList< UsuarioPO > encontrados = usuarioFacade.filtrar( montarPO( usuarioForm ), usuarioForm.getPaginacao() );

			usuarioForm.setUsuarios( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "usuarioCadastro" );
	}
	
	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UsuarioForm usuarioForm = (UsuarioForm) form;

		try {
			
			UsuarioFacade usuarioFacade = new UsuarioFacade();

			ArrayList< UsuarioPO > encontrados = usuarioFacade.filtrar( montarPO( usuarioForm ), usuarioForm.getPaginacao() );

			usuarioForm.setUsuarios( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "usuarioCadastro" );
	}  

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		UsuarioForm usuarioForm = (UsuarioForm) form;

		try {
			
			UsuarioFacade usuarioFacade = new UsuarioFacade();

			UsuarioPO encontrado = usuarioFacade.filtrarPorId( Long.valueOf( usuarioForm.getId() ) );
			
			usuarioForm.setUsuario( encontrado.getUsuario() );
			usuarioForm.setSenha( encontrado.getSenha() );
			usuarioForm.setTipo( encontrado.getTipo() );
						
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "usuarioCadastro" );
	}

	private UsuarioPO montarPO( UsuarioForm usuarioForm ) throws ParseException, NumberFormatException, ApplicationException {
		UsuarioPO po = new UsuarioPO();
		
		if ( usuarioForm.getId() == null || usuarioForm.getId().isEmpty() ) {
			usuarioForm.setId( null );
		} 
		
		if ( usuarioForm.getId() != null && !usuarioForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( usuarioForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( usuarioForm.getUsuario() != null && !usuarioForm.getUsuario().isEmpty() ) {
			po.setUsuario( usuarioForm.getUsuario() );
		} else {
			po.setUsuario( null );
		}
		
		if ( usuarioForm.getSenha() != null && !usuarioForm.getSenha().isEmpty() ) {
			po.setSenha( usuarioForm.getSenha() );
		} else {
			po.setSenha( null );
		}
		
		if ( usuarioForm.getTipo() != null && !usuarioForm.getTipo().isEmpty() ) {
			po.setTipo( usuarioForm.getTipo() );
		} else {
			po.setTipo( null );
		}
		
		return po;
	}
	
}
