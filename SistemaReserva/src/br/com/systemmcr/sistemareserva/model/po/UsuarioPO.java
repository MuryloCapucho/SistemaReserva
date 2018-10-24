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

@Entity
@Table( name = "usuarios", schema = Utilidades.SCHEMA, uniqueConstraints = @UniqueConstraint(columnNames = {"usuario"}) )
public class UsuarioPO extends AbstractPO< UsuarioPO >{
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	@Column( name = "id" )
	private Long id;
	
	@Column( name = "usuario", nullable = false, length = 20 )
	private String usuario;

	@Column( name = "senha", nullable = false, length = 255 )
	private String senha;
	
	@Column( name = "tipo", nullable=false, length = 15)
	private String tipo;

	/** Getters e Setters */
	public Long getId() {
		return id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario( String usuario ) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha( String senha ) {
		this.senha = senha;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo( String tipo ) {
		this.tipo = tipo;
	}

	/** hashCode */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
		result = prime * result + ( ( senha == null ) ? 0 : senha.hashCode() );
		result = prime * result + ( ( usuario == null ) ? 0 : usuario.hashCode() );
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
		UsuarioPO other = (UsuarioPO) obj;
		if ( id == null ) {
			if ( other.id != null )
				return false;
		} else if ( !id.equals( other.id ) )
			return false;
		if ( senha == null ) {
			if ( other.senha != null )
				return false;
		} else if ( !senha.equals( other.senha ) )
			return false;
		if ( usuario == null ) {
			if ( other.usuario != null )
				return false;
		} else if ( !usuario.equals( other.usuario ) )
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
		return "UsuarioPO [id=" + id + ", usuario=" + usuario + ", senha=" + senha + ", tipo=" + tipo + "]";
	}
	
	/** compareTo */
	@Override
	public int compareTo( UsuarioPO usuarioComparar ) {
		return getUsuario().compareTo( usuarioComparar.getUsuario() );
	}
	
	
}
