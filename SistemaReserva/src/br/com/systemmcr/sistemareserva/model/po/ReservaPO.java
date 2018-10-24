package br.com.systemmcr.sistemareserva.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

/**
 * Classe que representa os atributos da Classe ReservaPO
 *
 * @author Murylo Capucho Ribeiro <murylo_capucho@outlook.com>
 * @since 30 de jan de 2017 22:01:29
 * @version 1.0
 */

@Entity
@Table( name = "reservas", schema = Utilidades.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = {"mesa", "evento"}) )
public final class ReservaPO extends AbstractPO< ReservaPO >{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@ManyToOne( fetch = FetchType.EAGER ) 
	@JoinColumn( name = "evento", nullable = false )
	private EventoPO evento;

	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "mesa", nullable = false )
	private MesaPO mesa;
	
	@ManyToOne( fetch = FetchType.EAGER )
	@JoinColumn( name = "cliente", nullable = false )
	private ClientePO cliente;
	
	/** Getters e Setters */
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public EventoPO getEvento() {
		return evento;
	}
	public void setEvento( EventoPO evento ) {
		this.evento = evento;
	}
	public MesaPO getMesa() {
		return mesa;
	}
	public void setMesa( MesaPO mesa ) {
		this.mesa = mesa;
	}
	public ClientePO getCliente() {
		return cliente;
	}
	public void setCliente( ClientePO cliente ) {
		this.cliente = cliente;
	}
	
	/** hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cliente == null ) ? 0 : cliente.hashCode() );
		result = prime * result + ( ( evento == null ) ? 0 : evento.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( mesa == null ) ? 0 : mesa.hashCode() );
		return result;
	}
	
	/** Equals */
	@Override
	public boolean equals( Object obj ) {
		if ( this == obj )
			return true;
		if ( obj == null )
			return false;
		if ( getClass() != obj.getClass() )
			return false;
		ReservaPO other = (ReservaPO) obj;
		if ( cliente == null ) {
			if ( other.cliente != null )
				return false;
		} else if ( !cliente.equals( other.cliente ) )
			return false;
		if ( evento == null ) {
			if ( other.evento != null )
				return false;
		} else if ( !evento.equals( other.evento ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( mesa == null ) {
			if ( other.mesa != null )
				return false;
		} else if ( !mesa.equals( other.mesa ) )
			return false;
		return true;
	}
	
	/** toString */
	@Override
	public String toString() {
		return "ReservaPO [id=" + id + ", evento=" + evento + ", mesa=" + mesa + ", cliente=" + cliente + "]";
	}
	
	/** compareTo */
	@Override
	public int compareTo( ReservaPO reservaComparar ) {
		return getCliente().getNome().compareTo( reservaComparar.getCliente().getNome() );
	}
	
}// fim da class ReservaPO
