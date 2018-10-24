package br.com.systemmcr.sistemareserva.model.po;

import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

/**
 * Classe que representa os atributos da Classe MesaPO
 *
 * @author Murylo Capucho Ribeiro <murylo_capucho@outlook.com>
 * @since 30 de jan de 2017 21:54:50
 * @version 1.0
 */

@Entity
@Table( name = "mesas", schema = Utilidades.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = {"numero", "tipo"}) )
public final class MesaPO extends AbstractPO< MesaPO >{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "numero", nullable = false, length = 4 )
	private String numero;
	
	@Column( name = "tipo", nullable = false, length = 8 )
	private String tipo;
	
	@Column( name = "localizacao", nullable = false, length = 10 )
	private String localizacao;
	
	/** Getters e Setters */
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
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
	
	/** hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( localizacao == null ) ? 0 : localizacao.hashCode() );
		result = prime * result + ( ( numero == null ) ? 0 : numero.hashCode() );
		result = prime * result + ( ( tipo == null ) ? 0 : tipo.hashCode() );
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
		MesaPO other = (MesaPO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( localizacao == null ) {
			if ( other.localizacao != null )
				return false;
		} else if ( !localizacao.equals( other.localizacao ) )
			return false;
		if ( numero == null ) {
			if ( other.numero != null )
				return false;
		} else if ( !numero.equals( other.numero ) )
			return false;
		if ( tipo == null ) {
			if ( other.tipo != null )
				return false;
		} else if ( !tipo.equals( other.tipo ) )
			return false;
		return true;
	}
	
	/** toString */
	@Override
	public String toString() {
		return "MesaPO [id=" + id + ", numero=" + numero + ", tipo=" + tipo + ", localizacao=" + localizacao + "]";
	}
	
	/** compareTo */
	@Override
	public int compareTo( MesaPO mesaComparar ) {
		return getNumero().compareTo( mesaComparar.getNumero() );
	}
	
}// fim da class MesaPO
