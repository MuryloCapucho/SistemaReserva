package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractForm;

public class EventoForm extends AbstractForm{

	private static final long serialVersionUID = -4970237242953502687L;
	
	private String id;
	private String data;
	private String nome;
	
	private ArrayList< EventoPO > eventos = new ArrayList< EventoPO >();

	/** Getters e Setters */
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData( String data ) {
		this.data = data;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}
	
	public ArrayList< EventoPO > getEventos() {
		return eventos;
	}

	public void setEventos( ArrayList< EventoPO > eventos ) {
		this.eventos = eventos;
	}

	/** Método Limpar Campos */
	public void limparCampos(){
		setId( null );
		setData( null );
		setNome( null );
	}
	
	public void limparTabela(){
		getEventos().clear();
	}
}
