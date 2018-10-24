package br.com.systemmcr.sistemareserva.controller;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.ReservaPO;
import br.com.systemmcr.sistemareserva.model.service.ReservaService;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class ReservaFacade{

	private final ReservaService SERVICE;

	public ReservaFacade(){
		SERVICE = new ReservaService();
	}

	public void inserir( ReservaPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}// fim do void inserir

	public void alterar( ReservaPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}// fim do void alterar

	public void excluir( ReservaPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}// fim do void excluir

	public ArrayList< ReservaPO > filtrar( ReservaPO po, Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrar( po, paginacao );

	}// fim do List filtrar

	public ArrayList< ReservaPO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrarTodos( paginacao );

	}// fim do List filtrarTodos

	public ReservaPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );

	}// fim do List filtrarPorId
	
}
