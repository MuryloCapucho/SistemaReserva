package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractForm;

public class MesaForm extends AbstractForm{

	private static final long serialVersionUID = -8669509822548345641L;
	
	private String id;
	private String numero;
	private String tipo;
	private String localizacao;
	
	private ArrayList< MesaPO > mesas = new ArrayList< MesaPO >();

	/** Getters e Setters */
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero( String numero ) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao( String localizacao ) {
		this.localizacao = localizacao;
	}

	public ArrayList< MesaPO > getMesas() {
		return mesas;
	}

	public void setMesas( ArrayList< MesaPO > mesas ) {
		this.mesas = mesas;
	}

	/** Método Limpar Campos */
	public void limparCampos(){
		setId( null );
		setNumero( null );
		setTipo( null );
		setLocalizacao( null );
	}
	
	public void limparTabela(){
		getMesas().clear();
	}
	
}
