package br.com.systemmcr.sistemareserva.model.service;

import java.util.ArrayList;
import java.util.List;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.connection.HibernateConnection;
import br.com.systemmcr.sistemareserva.dao.ClienteDAO;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.interfaces.Crud;
import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public final class ClienteService implements Crud< ClientePO >{

	private final ClienteDAO DAO;

	/** Construtor responsável por inicializar o atributo DAO */
	public ClienteService(){
		DAO = new ClienteDAO();
	}

	public void inserir( ClientePO po ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {

			/** 1º Iniciar a Transação */
			hibernate.iniciarTransacao();
			/** Fazer o que tem que fazer */
			DAO.inserir( po, hibernate );
			/** 3º Confirmar a Transação */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}// fim do void inserir

	public void alterar( ClientePO po ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {

			/** 1º Iniciar a Transação */
			hibernate.iniciarTransacao();
			/** Fazer o que tem que fazer */
			DAO.alterar( po, hibernate );
			/** 3º Confirmar a Transação */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}

	}

	public void excluir( ClientePO po ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1º Iniciar a Transação */
			hibernate.iniciarTransacao();
			/** Fazer o que tem que fazer */
			DAO.excluir( po, hibernate );
			/** 3º Confirmar a Transação */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	public ArrayList< ClientePO > filtrar( ClientePO po, Paginacao paginacao ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1º Iniciar a Transação */
			hibernate.iniciarTransacao();

			/** Fazer o que tem que fazer */
			ArrayList< ClientePO > encontrados = DAO.filtrar( po, hibernate, paginacao );

			/** 3º Confirmar a Transação */
			hibernate.commitTransacao();

			return encontrados;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	@SuppressWarnings( "unchecked" )
	public ArrayList< ClientePO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1º Iniciar a Transação */
			hibernate.iniciarTransacao();

			/** Fazer o que tem que fazer */
			@SuppressWarnings( "rawtypes" )
			List encontrados = DAO.filtrarTodos( hibernate, paginacao );

			/** 3º Confirmar a Transação */
			hibernate.commitTransacao();

			return (ArrayList< ClientePO >) encontrados;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	public ClientePO filtrarPorId( Long id ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1º Iniciar a Transação */
			hibernate.iniciarTransacao();

			/** Fazer o que tem que fazer */
			ClientePO encontrado = DAO.filtrarPorId( id, hibernate );

			/** 3º Confirmar a Transação */
			hibernate.commitTransacao();

			return encontrado;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}
	
}
