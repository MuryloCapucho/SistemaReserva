package br.com.systemmcr.sistemareserva.struts;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class LoginForm extends ActionForm{
	
	private static final long serialVersionUID = 5443405804810444725L;
	
	private String usuario = null;
	private String senha =  null;
	
	/** Getters e Setters */
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario( String usuario ) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha( String senha ) {
		this.senha = senha;
	}
	
	public void limparCampos(){
		setUsuario( null );
		setSenha( null );
	}
	
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.senha = null;
	}

}
