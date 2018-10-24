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
import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public final class MesaDAO{
	
	@SuppressWarnings( "rawtypes" )
	public void inserir( MesaPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {

			po.setId( ( (MesaPO) hibernate.inserir( po ) ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
	@SuppressWarnings( "rawtypes" )
	public void alterar( MesaPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public void excluir( MesaPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public ArrayList< MesaPO > filtrar( MesaPO poFiltrar, HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {

		try {
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( MesaPO.class );
			
			ArrayList< Criterion > criterios = new ArrayList< Criterion >();

			/*MONTANDO AS RESTRIÇÕES DINAMICAS*/
			if ( poFiltrar.getNumero() != null ) {
				criteria.add( Restrictions.like( "numero", poFiltrar.getNumero().trim(), MatchMode.START ) );
				criterios.add( Restrictions.like( "numero", poFiltrar.getNumero(), MatchMode.START ) );
			}

			if ( poFiltrar.getTipo() != null ) {
				criteria.add( Restrictions.eq( "tipo", poFiltrar.getTipo() ) );
				criterios.add( Restrictions.eq( "tipo", poFiltrar.getTipo() ) );
			}
			
			if ( poFiltrar.getLocalizacao() != null ) {
				criteria.add( Restrictions.eq( "localizacao", poFiltrar.getLocalizacao() ) );
				criterios.add( Restrictions.eq( "localizacao", poFiltrar.getLocalizacao() ) );
			}
			
			if ( poFiltrar.getId() != null ) {
				criteria.add( Restrictions.idEq( poFiltrar.getId() ) );
				criterios.add( Restrictions.idEq( poFiltrar.getId() ) );
			}
			
			if( paginacao != null ){
				paginacao.setTotalRegistros( hibernate.getTotalRegistros( MesaPO.class, criterios ).intValue() );
				
				criteria.setMaxResults( Paginacao.QUANTIDADE_REGISTROS_EXIBIR );
				criteria.setFirstResult( paginacao.prepararInicial( (long) paginacao.getTotalRegistros() ).intValue() );
			}

			return (ArrayList< MesaPO >) criteria.list();
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public List filtrarTodos( HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {
		try {
			return hibernate.filtrarTodos( MesaPO.class, paginacao );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public MesaPO filtrarPorId( Long id, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {

		try {
			return (MesaPO) hibernate.filtrarPorId( MesaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
}// fim da class MesaDAO
