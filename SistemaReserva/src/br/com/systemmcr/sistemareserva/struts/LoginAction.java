package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import br.com.systemmcr.sistemareserva.controller.UsuarioFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.UsuarioPO;
import br.com.systemmcr.sistemareserva.utilidades.Messages;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class LoginAction extends DispatchAction{

	public ActionForward abrirLogin( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		LoginForm loginForm = (LoginForm) form;
		loginForm.limparCampos();
		return mapping.findForward( "loginCampo" );
	}

	public ActionForward limparCampos( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		LoginForm loginForm = (LoginForm) form;
		loginForm.limparCampos();
		return abrirLogin( mapping, loginForm, request, response );
	}

	public ActionForward logar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		LoginForm loginForm = (LoginForm) form;

		try {
			UsuarioPO usuarioPO = new UsuarioPO();
			usuarioPO.setUsuario( loginForm.getUsuario() );
			usuarioPO.setSenha( Utilidades.criptografarSenha( loginForm.getSenha() ) );

			UsuarioFacade usuarioFacade = new UsuarioFacade();

			ArrayList< UsuarioPO > usuarioPOsEncontrados = usuarioFacade.filtrar( usuarioPO, null );

			if ( usuarioPOsEncontrados != null && !usuarioPOsEncontrados.isEmpty() ) {
				if ( usuarioPOsEncontrados != null ) {
					loginForm.limparCampos();
					request.getSession().setAttribute( "usuarioLogado", usuarioPOsEncontrados.get( 0 ) );
					request.getSession().setAttribute( "tipoUsuario", usuarioPOsEncontrados.get( 0 ).getTipo() );
					return mapping.findForward( "principal" );
				}
			} else {
				ApplicationException e = new ApplicationException( "Login Inválido" );
				this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
				loginForm.limparCampos();
			}
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		return mapping.findForward( "loginCampo" );
	}

}
