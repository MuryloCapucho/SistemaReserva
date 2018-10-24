package br.com.systemmcr.sistemareserva.model.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

/**
 * Classe que representa os atributos da Classe ClientePO
 *
 * @author Murylo Capucho Ribeiro <murylo_capucho@outlook.com>
 * @since 30 de jan de 2017 21:55:21
 * @version 1.0
 */

@Entity
@Table( name = "clientes", schema = Utilidades.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = {"nome"} ) )
public final class ClientePO extends AbstractPO< ClientePO >{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "nome", nullable = false, length = 50 )
	private String nome;
	
	@Column( name = "cidade", nullable = false, length = 30 )
	private String cidade;
	
	@Column( name = "imagem", length = 200, nullable = true )
	private String imagem;
	
	/** Getters e Setters */
	public Long getId() {
		return id;
	}
	public void setId( Long id ) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome( String nome ) {
		this.nome = nome;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade( String cidade ) {
		this.cidade = cidade;
	}
	public String getImagem() {
		return imagem;
	}
	public void setImagem( String imagem ) {
		this.imagem = imagem;
	}
	
	
	/** hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( cidade == null ) ? 0 : cidade.hashCode() );
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
		ClientePO other = (ClientePO) obj;
		if ( cidade == null ) {
			if ( other.cidade != null )
				return false;
		} else if ( !cidade.equals( other.cidade ) )
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
		return nome;
	}
	
	/** compareTo */
	@Override
	public int compareTo( ClientePO clienteComparar ) {
		return getNome().compareTo( clienteComparar.getNome() );
	}
	
}// fim da class ClientePO
