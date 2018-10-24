package br.com.systemmcr.sistemareserva.controller;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.UsuarioPO;
import br.com.systemmcr.sistemareserva.model.service.UsuarioService;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class UsuarioFacade{

	private final UsuarioService SERVICE;

	public UsuarioFacade(){
		SERVICE = new UsuarioService();
	}

	public void inserir( UsuarioPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}// fim do void inserir

	public void alterar( UsuarioPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}// fim do void alterar

	public void excluir( UsuarioPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}// fim do void excluir

	public ArrayList< UsuarioPO > filtrar( UsuarioPO po, Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrar( po, paginacao );

	}// fim do List filtrar

	public ArrayList< UsuarioPO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrarTodos( paginacao );

	}// fim do List filtrarTodos

	public UsuarioPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );

	}// fim do List filtrarPorId
	
}
