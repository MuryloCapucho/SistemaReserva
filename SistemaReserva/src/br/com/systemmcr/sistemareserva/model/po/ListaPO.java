package br.com.systemmcr.sistemareserva.model.po;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

/**
 * Classe que representa os atributos da Classe ListaPO
 *
 * @author Murylo Capucho Ribeiro <murylo_capucho@outlook.com>
 * @since 30 de jan de 2017 22:01:29
 * @version 1.0
 */

@Entity
@Table( name = "listas", schema = Utilidades.SCHEMA )
public class ListaPO extends AbstractPO< ListaPO >{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@ManyToOne( fetch = FetchType.EAGER ) 
	@JoinColumn( name = "evento", nullable = false )
	private EventoPO evento;
	
	@ManyToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "clientes_lista", joinColumns = @JoinColumn( name = "id_lista" ), inverseJoinColumns = @JoinColumn( name = "id_cliente" ), schema = Utilidades.SCHEMA )
	private Set< ClientePO > clientes = new HashSet< ClientePO >();
	
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

	public Set< ClientePO > getClientes() {
		return clientes;
	}

	public void setClientes( Set< ClientePO > clientes ) {
		this.clientes = clientes;
	}

	/** hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( clientes == null ) ? 0 : clientes.hashCode() );
		result = prime * result + ( ( evento == null ) ? 0 : evento.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
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
		ListaPO other = (ListaPO) obj;
		if ( clientes == null ) {
			if ( other.clientes != null )
				return false;
		} else if ( !clientes.equals( other.clientes ) )
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
		return true;
	}

	/** toString */
	@Override
	public String toString() {
		return "ListaPO [id=" + id + ", evento=" + evento + ", clientes=" + clientes + "]";
	}
	
	/** compateTo */
	public int compareTo( ListaPO listaComparar ){
		return getId().compareTo( listaComparar.getId() );
	}

}
