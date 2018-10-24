package br.com.systemmcr.sistemareserva.model.service;

import java.util.ArrayList;
import java.util.List;

import br.com.systemmcr.sistemareserva.abstracts.po.AbstractPO;
import br.com.systemmcr.sistemareserva.connection.HibernateConnection;
import br.com.systemmcr.sistemareserva.dao.UsuarioDAO;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.interfaces.Crud;
import br.com.systemmcr.sistemareserva.model.po.UsuarioPO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public final class UsuarioService implements Crud< UsuarioPO >{

	private final UsuarioDAO DAO;

	/** Construtor respons�vel por inicializar o atributo DAO */
	public UsuarioService(){
		DAO = new UsuarioDAO();
	}

	public void inserir( UsuarioPO po ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {

			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();
			
			/** Fazer o que tem que fazer */
			po.setSenha( Utilidades.criptografarSenha( po.getSenha() ) );
			
			DAO.inserir( po, hibernate );
			/** 3� Confirmar a Transa��o */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}// fim do void inserir

	public void alterar( UsuarioPO po ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {

			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();
			/** Fazer o que tem que fazer */
			if( po.getSenha().length() < 8 ) {
				po.setSenha( Utilidades.criptografarSenha( po.getSenha() ) );
			}
			
			DAO.alterar( po, hibernate );
			/** 3� Confirmar a Transa��o */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}

	}

	public void excluir( UsuarioPO po ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();
			/** Fazer o que tem que fazer */
			DAO.excluir( po, hibernate );
			/** 3� Confirmar a Transa��o */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	public ArrayList< UsuarioPO > filtrar( UsuarioPO po, Paginacao paginacao ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();

			/** Fazer o que tem que fazer */
			ArrayList< UsuarioPO > encontrados = DAO.filtrar( po, hibernate, paginacao );

			/** 3� Confirmar a Transa��o */
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
	public ArrayList< UsuarioPO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();

			/** Fazer o que tem que fazer */
			@SuppressWarnings( "rawtypes" )
			List encontrados = DAO.filtrarTodos( hibernate, paginacao );

			/** 3� Confirmar a Transa��o */
			hibernate.commitTransacao();

			return (ArrayList< UsuarioPO >) encontrados;
		} catch ( ApplicationException e ) {
			hibernate.rollbackTransacao();
			throw e;
		} catch ( Exception e ) {
			hibernate.rollbackTransacao();
			throw new ApplicationException( "Erro desconhecido", e );
		}
	}

	public UsuarioPO filtrarPorId( Long id ) throws ApplicationException {

		@SuppressWarnings( "rawtypes" )
		HibernateConnection< AbstractPO > hibernate = new HibernateConnection< AbstractPO >();

		try {
			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();

			/** Fazer o que tem que fazer */
			UsuarioPO encontrado = DAO.filtrarPorId( id, hibernate );

			/** 3� Confirmar a Transa��o */
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
