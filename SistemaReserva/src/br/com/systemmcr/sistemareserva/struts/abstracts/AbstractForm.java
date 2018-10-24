package br.com.systemmcr.sistemareserva.struts.abstracts;

import org.apache.struts.action.ActionForm;

import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class AbstractForm extends ActionForm{

	private static final long serialVersionUID = -8893564235429380127L;
	
	private Paginacao paginacao;
	private String forwardDestino;

	/** Getters e Setters */
	public Paginacao getPaginacao() {
		if(paginacao == null){
			paginacao = new Paginacao();
		}
		
		return paginacao;
	}

	public void setPaginacao( Paginacao paginacao ) {
		this.paginacao = paginacao;
	}
	
	public String getForwardDestino() {
		return forwardDestino;
	}
	
	public void setForwardDestino( String forwardDestino ) {
		this.forwardDestino = forwardDestino;
	}
	
}
