package br.com.systemmcr.sistemareserva.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.connection.HibernateConnection;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.ReservaPO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public final class ReservaDAO{

	@SuppressWarnings( "rawtypes" )
	public void inserir( ReservaPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {

			po.setId( ( (ReservaPO) hibernate.inserir( po ) ).getId() );

		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
	@SuppressWarnings( "rawtypes" )
	public void alterar( ReservaPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.alterar( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public void excluir( ReservaPO po, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {
		try {
			hibernate.excluir( po );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( { "rawtypes", "unchecked" } )
	public ArrayList< ReservaPO > filtrar( ReservaPO poFiltrar, HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {

		try {
			Criteria criteria = hibernate.getSessaoCorrente().createCriteria( ReservaPO.class );

			ArrayList< Criterion > criterios = new ArrayList< Criterion >();
			
			/*MONTANDO AS RESTRIÇÕES DINAMICAS*/
			if ( poFiltrar.getEvento() != null ) {
				if ( poFiltrar.getEvento().getId() != null ) {
					criteria.add( Restrictions.eq( "evento.id", poFiltrar.getEvento().getId() ) );
					criterios.add( Restrictions.eq( "evento.id", poFiltrar.getEvento().getId() ) );
				}
			}
			
			if ( poFiltrar.getMesa() != null ) {
				if ( poFiltrar.getMesa().getId() != null ) {
					criteria.add( Restrictions.eq( "mesa.id", poFiltrar.getMesa().getId() ) );
					criterios.add( Restrictions.eq( "mesa.id", poFiltrar.getMesa().getId() ) );
				}
			}
			
			if ( poFiltrar.getCliente() != null ) {
				if ( poFiltrar.getCliente().getId() != null ) {
					criteria.add( Restrictions.eq( "cliente.id", poFiltrar.getCliente().getId() ) );
					criterios.add( Restrictions.eq( "cliente.id", poFiltrar.getCliente().getId() ) );
				}
			}
			
			if ( poFiltrar.getId() != null ) {
				criteria.add( Restrictions.idEq( poFiltrar.getId() ) );
				criterios.add( Restrictions.idEq( poFiltrar.getId() ) );
			}
			
			if( paginacao != null ){
				paginacao.setTotalRegistros( hibernate.getTotalRegistros( ReservaPO.class, criterios ).intValue() );
				
				criteria.setMaxResults( Paginacao.QUANTIDADE_REGISTROS_EXIBIR );
				criteria.setFirstResult( paginacao.prepararInicial( (long) paginacao.getTotalRegistros() ).intValue() );
			}

			return (ArrayList< ReservaPO >) criteria.list();
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public List filtrarTodos( HibernateConnection< AbstractPO > hibernate, Paginacao paginacao ) throws ApplicationException {
		try {
			return hibernate.filtrarTodos( ReservaPO.class, paginacao );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "rawtypes" )
	public ReservaPO filtrarPorId( Long id, HibernateConnection< AbstractPO > hibernate ) throws ApplicationException {

		try {
			return (ReservaPO) hibernate.filtrarPorId( ReservaPO.class, id );
		} catch ( ApplicationException e ) {
			throw e;
		} catch ( Exception e ) {
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
}// fim da class ReservaDAO
