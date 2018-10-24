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
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public final class EventoDAO{
	
	@SuppressWarnings( "rawtypes" )
	public void inserir( EventoPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {

			po.setId( ( (EventoPO) hibernate.inserir( po ) ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
	@SuppressWarnings( "rawtypes" )
	public void alterar( EventoPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public void excluir( EventoPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public ArrayList< EventoPO > filtrar( EventoPO poFiltrar, HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {

		try {
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( EventoPO.class );
			
			ArrayList< Criterion > criterios = new ArrayList< Criterion >();
			
			/*MONTANDO AS RESTRIÇÕES DINAMICAS*/
			if ( poFiltrar.getData() != null ) {
				criteria.add( Restrictions.eq( "data", poFiltrar.getData() ) );
				criterios.add( Restrictions.eq( "data", poFiltrar.getData() ) );
			}

			if ( poFiltrar.getNome() != null ) {
				criteria.add( Restrictions.like( "nome", poFiltrar.getNome().trim(), MatchMode.START ) );
				criterios.add(Restrictions.like( "nome", poFiltrar.getNome(), MatchMode.START ) );
			}
			
			if ( poFiltrar.getId() != null ) {
				criteria.add( Restrictions.idEq( poFiltrar.getId() ) );
				criterios.add( Restrictions.idEq( poFiltrar.getId() ) );
			}
			
			if( paginacao != null ){
				paginacao.setTotalRegistros( hibernate.getTotalRegistros( EventoPO.class, criterios ).intValue() );
				
				criteria.setMaxResults( Paginacao.QUANTIDADE_REGISTROS_EXIBIR );
				criteria.setFirstResult( paginacao.prepararInicial( (long) paginacao.getTotalRegistros() ).intValue() );
			}

			return (ArrayList< EventoPO >) criteria.list();
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public List filtrarTodos( HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {
		try {
			return hibernate.filtrarTodos( EventoPO.class, paginacao );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public EventoPO filtrarPorId( Long id, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {

		try {
			return (EventoPO) hibernate.filtrarPorId( EventoPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
}// fim da class EventoDAO
