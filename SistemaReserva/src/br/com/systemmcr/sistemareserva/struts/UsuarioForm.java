package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.model.po.UsuarioPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractForm;

public class UsuarioForm extends AbstractForm{

	private static final long serialVersionUID = -6743755422237664241L;
	
	private String id;
	private String usuario;
	private String senha;
	private String tipo;
	
	private ArrayList< UsuarioPO > usuarios = new ArrayList< UsuarioPO >();

	/** Getters e Setters */
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

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

	public ArrayList< UsuarioPO > getUsuarios() {
		return usuarios;
	}

	public void setUsuarios( ArrayList< UsuarioPO > usuarios ) {
		this.usuarios = usuarios;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	/** Método Limpar Campos */
	public void limparCampos(){
		setId( null );
		setUsuario( null );
		setSenha( null );
		setTipo( null );
	}
	
	public void limparTabela(){
		getUsuarios().clear();
	}
	
}
