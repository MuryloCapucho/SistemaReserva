package br.com.systemmcr.sistemareserva.controller;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.EventoPO;
import br.com.systemmcr.sistemareserva.model.service.EventoService;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class EventoFacade{
	
	private final EventoService SERVICE;

	public EventoFacade(){
		SERVICE = new EventoService();
	}

	public void inserir( EventoPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}// fim do void inserir

	public void alterar( EventoPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}// fim do void alterar

	public void excluir( EventoPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}// fim do void excluir

	public ArrayList< EventoPO > filtrar( EventoPO po, Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrar( po, paginacao );

	}// fim do List filtrar

	public ArrayList< EventoPO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrarTodos( paginacao );

	}// fim do List filtrarTodos

	public EventoPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );

	}// fim do List filtrarPorId
	
}
