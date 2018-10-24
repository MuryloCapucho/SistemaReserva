package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.model.po.ReservaPO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractForm;

public class ReservaForm extends AbstractForm{

	private static final long serialVersionUID = 4150375815969687038L;

	private String id;
	private String idEvento;
	private String nomeEvento;
	private String idMesa;
	private String numeroMesa;
	private String idCliente;
	private String nomeCliente;
	
	private ArrayList< EventoPO > eventos = new ArrayList<EventoPO>();
	private ArrayList< MesaPO > mesas = new ArrayList<MesaPO>();
	private ArrayList< ClientePO > clientes = new ArrayList<ClientePO>();
	private ArrayList< ReservaPO > reservas = new ArrayList< ReservaPO >();
	

	/** Getters e Setters */
	public String getId() {
		return id;
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

	public String getIdMesa() {
		return idMesa;
	}

	public void setIdMesa( String idMesa ) {
		this.idMesa = idMesa;
	}

	public String getNumeroMesa() {
		return numeroMesa;
	}

	public void setNumeroMesa( String numeroMesa ) {
		this.numeroMesa = numeroMesa;
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

	public ArrayList< MesaPO > getMesas() {
		return mesas;
	}

	public void setMesas( ArrayList< MesaPO > mesas ) {
		this.mesas = mesas;
	}

	public ArrayList< ClientePO > getClientes() {
		return clientes;
	}

	public void setClientes( ArrayList< ClientePO > clientes ) {
		this.clientes = clientes;
	}

	public ArrayList< ReservaPO > getReservas() {
		return reservas;
	}

	public void setReservas( ArrayList< ReservaPO > reservas ) {
		this.reservas = reservas;
	}
	
	/** Método Limpar Campos */
	public void limparCampos(){
		setId( null );
		setIdEvento( null );
		setNomeEvento( null );
		setIdMesa( null );
		setNumeroMesa( null );
		setIdCliente( null );
		setNomeCliente( null );
	}
	
	public void limparTabela(){
		getReservas().clear();
	}
	
}
