package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.model.po.ListaPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractForm;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class ListaForm extends AbstractForm{

	private static final long serialVersionUID = -7798053281155227972L;
	
	private String id;
	private String idEvento;
	private String nomeEvento;
	private String idCliente;
	private String nomeCliente;
	private ArrayList< EventoPO > eventos = new ArrayList<EventoPO>();
	private ArrayList< ClientePO > clientes = new ArrayList< ClientePO >();
	private ArrayList< ListaPO > listas;
	private String idClienteRemover;
	
	/** Getters e Setters */
	public String getId() {
		return Utilidades.isNuloOuVazio( id ) ? null : id;		
	}
	public void setId( String id ) {
		this.id = id;
	}
	public String getIdEvento() {
		return idEvento;
	}
	public void setIdEvento( String idEvento ) {
		this.idEvento = idEvento;
	}
	public String getNomeEvento() {
		return nomeEvento;
	}
	public void setNomeEvento( String nomeEvento ) {
		this.nomeEvento = nomeEvento;
	}
	public String getIdCliente() {
		return idCliente;
	}
	public void setIdCliente( String idCliente ) {
		this.idCliente = idCliente;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente( String nomeCliente ) {
		this.nomeCliente = nomeCliente;
	}
	public ArrayList< EventoPO > getEventos() {
		return eventos;
	}

	public void setEventos( ArrayList< EventoPO > eventos ) {
		this.eventos = eventos;
	}
	public ArrayList< ClientePO > getClientes() {
		return clientes;
	}
	public void setClientes( ArrayList< ClientePO > clientes ) {
		this.clientes = clientes;
	}
	public ArrayList< ListaPO > getListas() {
		return listas;
	}
	public void setListas( ArrayList< ListaPO > listas ) {
		this.listas = listas;
	}
	public String getIdClienteRemover() {
		return idClienteRemover;
	}
	public void setIdClienteRemover( String idClienteRemover ) {
		this.idClienteRemover = idClienteRemover;
	}
	
	/** Método Limpar Campos */
	public void limparCampos(){
		setId( null );
		setIdEvento( null );
		setNomeEvento( null );
		setIdCliente( null );
		setNomeCliente( null );
		setIdClienteRemover( null );
		setClientes( new ArrayList< ClientePO >() );
		setListas( new ArrayList< ListaPO >() );
	}
	
	/** Método Limpar Tabela */
	public void limparTabela() {
		getListas().clear();
	}
	
	/** Método Limpar Campos Cliente */
	public void limparCamposClientes(){
		setIdCliente( null );
		setNomeCliente( null );
		setIdClienteRemover( null );
	}
	
}
