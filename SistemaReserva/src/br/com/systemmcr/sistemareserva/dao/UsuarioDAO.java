package br.com.systemmcr.sistemareserva.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.connection.HibernateConnection;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.UsuarioPO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public final class UsuarioDAO{
	
	@SuppressWarnings( "rawtypes" )
	public void inserir( UsuarioPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {

			po.setId( ( (UsuarioPO) hibernate.inserir( po ) ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
	@SuppressWarnings( "rawtypes" )
	public void alterar( UsuarioPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public void excluir( UsuarioPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public ArrayList< UsuarioPO > filtrar( UsuarioPO poFiltrar, HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {

		try {
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( UsuarioPO.class );
			
			ArrayList< Criterion > criterios = new ArrayList< Criterion >();

			/*MONTANDO AS RESTRIÇÕES DINAMICAS*/
			if ( poFiltrar.getUsuario() != null ) {
				criteria.add( Restrictions.like( "usuario", poFiltrar.getUsuario(), MatchMode.START ) );
				criterios.add( Restrictions.like( "usuario", poFiltrar.getUsuario(), MatchMode.START ) );
			}

			if ( poFiltrar.getSenha() != null ) {
				criteria.add( Restrictions.eq( "senha", poFiltrar.getSenha() ) );
				criterios.add( Restrictions.eq( "senha", poFiltrar.getSenha() ) );
			}
			
			if ( poFiltrar.getTipo() != null ) {
				criteria.add( Restrictions.like( "tipo", poFiltrar.getTipo(), MatchMode.START ) );
				criterios.add( Restrictions.like( "tipo", poFiltrar.getTipo(), MatchMode.START ) );
			}
						
			if ( poFiltrar.getId() != null ) {
				criteria.add( Restrictions.idEq( poFiltrar.getId() ) );
				criterios.add( Restrictions.idEq( poFiltrar.getId() ) );
			}
			
			if( paginacao != null ){
				paginacao.setTotalRegistros( hibernate.getTotalRegistros( UsuarioPO.class, criterios ).intValue() );
				
				criteria.setMaxResults( Paginacao.QUANTIDADE_REGISTROS_EXIBIR );
				criteria.setFirstResult( paginacao.prepararInicial( (long) paginacao.getTotalRegistros() ).intValue() );
			}

			return (ArrayList< UsuarioPO >) criteria.list();
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public List filtrarTodos( HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {
		try {
			return hibernate.filtrarTodos( UsuarioPO.class, paginacao );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public UsuarioPO filtrarPorId( Long id, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {

		try {
			return (UsuarioPO) hibernate.filtrarPorId( UsuarioPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
}// fim da class UsuarioDAO
