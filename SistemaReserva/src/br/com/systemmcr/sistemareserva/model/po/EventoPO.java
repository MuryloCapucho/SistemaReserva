package br.com.systemmcr.sistemareserva.model.po;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

/**
 * Classe que representa os atributos da Classe EventoPO
 * 
 * @author Murylo Capucho Ribeiro <murylo_capucho@outlook.com>
 * @since 30 de jan de 2017 21:41:24
 * @version 1.0
 */

@Entity
@Table( name = "eventos", schema = Utilidades.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = {"data"} ) )
public final class EventoPO extends AbstractPO< EventoPO >{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "data", nullable = false )
	@Temporal( TemporalType.DATE )
	private Date data;
	
	@Column( name = "nome", nullable = false, length = 50 )
	private String nome;
	
	/** Getters e Setters */
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public Date getData() {
		return data;
	}
	public void setData( Date data ) {
		this.data = data;
	}
	public String getNome() {
		return nome;
	}
	public void setNome( String nome ) {
		this.nome = nome;
	}
	
	/** hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( data == null ) ? 0 : data.hashCode() );
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( nome == null ) ? 0 : nome.hashCode() );
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
		EventoPO other = (EventoPO) obj;
		if ( data == null ) {
			if ( other.data != null )
				return false;
		} else if ( !data.equals( other.data ) )
			return false;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( nome == null ) {
			if ( other.nome != null )
				return false;
		} else if ( !nome.equals( other.nome ) )
			return false;
		return true;
	}
	
	/** toString */
	@Override
	public String toString() {
		return "EventoPO [id=" + id + ", data=" + data + ", nome=" + nome + "]";
	}
	
	/** compareTo */
	@Override
	public int compareTo( EventoPO eventoComparar ) {
		return getData().compareTo( eventoComparar.getData() );
	}
	
	public String getDataToString(){
		return Utilidades.parseDate( getData() );
	}
	
}// fim da class EventoPO
