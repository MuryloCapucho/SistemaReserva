package br.com.systemmcr.sistemareserva.controller;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.ListaPO;
import br.com.systemmcr.sistemareserva.model.service.ListaService;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class ListaFacade{

	private final ListaService SERVICE;

	public ListaFacade(){
		SERVICE = new ListaService();
	}

	public void inserir( ListaPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}// fim do void inserir

	public void alterar( ListaPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}// fim do void alterar

	public void excluir( ListaPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}// fim do void excluir

	public ArrayList< ListaPO > filtrar( ListaPO po, Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrar( po, paginacao );

	}// fim do List filtrar

	public ArrayList< ListaPO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrarTodos( paginacao );

	}// fim do List filtrarTodos

	public ListaPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );

	}// fim do List filtrarPorId
	
}
