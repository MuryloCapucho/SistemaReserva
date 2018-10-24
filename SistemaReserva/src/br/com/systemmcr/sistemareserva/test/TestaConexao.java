package br.com.systemmcr.sistemareserva.test;

import br.com.systemmcr.sistemareserva.connection.HibernateConnection;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.MesaPO;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;

public class TestaConexao{
	public static void main( String[ ] args ) {

		HibernateConnection< MesaPO > hibernate = new HibernateConnection< MesaPO >();
		Paginacao paginacao = new Paginacao();
		
		try {
			/** 1� Iniciar a Transa��o */
			hibernate.iniciarTransacao();

			/** 2� Fazer o que tem que Fazer */
			hibernate.filtrarTodos( MesaPO.class, paginacao );

			/** 3� Confirmar a Transa��o */
			hibernate.commitTransacao();
		} catch ( ApplicationException e ) {
			e.printStackTrace();
		} // fim do catch

	}// fim do void main
}// fim da class TestaConexao
