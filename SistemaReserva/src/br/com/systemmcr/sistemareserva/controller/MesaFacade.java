package br.com.systemmcr.sistemareserva.controller;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.model.service.MesaService;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class MesaFacade{

	private final MesaService SERVICE;

	public MesaFacade(){
		SERVICE = new MesaService();
	}

	public void inserir( MesaPO po ) throws ApplicationException {
		SERVICE.inserir( po );
	}// fim do void inserir

	public void alterar( MesaPO po ) throws ApplicationException {
		SERVICE.alterar( po );
	}// fim do void alterar

	public void excluir( MesaPO po ) throws ApplicationException {
		SERVICE.excluir( po );
	}// fim do void excluir

	public ArrayList< MesaPO > filtrar( MesaPO po, Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrar( po, paginacao );

	}// fim do List filtrar

	public ArrayList< MesaPO > filtrarTodos( Paginacao paginacao ) throws ApplicationException {
		return SERVICE.filtrarTodos( paginacao );

	}// fim do List filtrarTodos

	public MesaPO filtrarPorId( Long id ) throws ApplicationException {
		return SERVICE.filtrarPorId( id );

	}// fim do List filtrarPorId
	
}
