package br.com.systemmcr.sistemareserva.interfaces;

import java.util.ArrayList;

import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public interface Crud< T >{
	 
	void inserir ( T po ) throws ApplicationException;
	
	void alterar ( T po ) throws ApplicationException;
	
	void excluir ( T po ) throws ApplicationException;
	
	ArrayList< T > filtrar ( T po, Paginacao paginacao ) throws ApplicationException;
	
	ArrayList< T > filtrarTodos ( Paginacao paginacao ) throws ApplicationException;
	
	T filtrarPorId ( Long id ) throws ApplicationException;	
	
}// fim da interface Crud
