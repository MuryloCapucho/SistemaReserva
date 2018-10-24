package br.com.systemmcr.sistemareserva.utilidades;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings( "serial" )
public class Paginacao implements Serializable{

	public static final Paginacao NAO = null;

	public static final int QUANTIDADE_REGISTROS_EXIBIR = 3;

	public static final int QUANTIDADE_PAGINA_EXIBIR = 5;

	private int paginaInicial = 1;

	private int paginaFinal;

	private int paginaCorrente;

	private int totalRegistros;

	/** Getters e Setters Total de Registro */
	public int getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros( int totalRegistros ) {
		this.totalRegistros = totalRegistros;
	}

	/** Método para mostrar o Máximo de Páginas */
	public int getMaximoPaginas() {
		if ( getTotalRegistros() != 0 ) {
			double totalPaginasCalculados = (double) getTotalRegistros() / (double) QUANTIDADE_REGISTROS_EXIBIR;

			if ( ( getTotalRegistros() % QUANTIDADE_REGISTROS_EXIBIR ) != 0 ) {
				return ( (int) totalPaginasCalculados ) + 1;
			}

			return (int) totalPaginasCalculados;
		}

		return 0;
	}// fim do getMaximoPaginas

	/** Getters e Setters Página Inicial e Final, Página Corrente */
	public int getPaginaInicial() {
		return paginaInicial;
	}

	public void setPaginaInicial( int paginaInicial ) {
		this.paginaInicial = paginaInicial;
	}

	public int getPaginaFinal() {
		return paginaFinal;
	}

	public void setPaginaFinal( int paginaFinal ) {
		this.paginaFinal = paginaFinal;
	}

	public int getPaginaCorrente() {
		return paginaCorrente;
	}

	public void setPaginaCorrente( int paginaCorrente ) {
		this.paginaCorrente = paginaCorrente;
	}

	/** Método para Voltar e Avançar Paginação Inativo */
	public boolean getVoltarPaginacaoInativo() {
		return paginaInicial == 1;
	}

	public boolean getAvancarPaginacaoInativo() {
		return paginaFinal >= getMaximoPaginas();
	}

	/** Método para Avançar e Voltar */
	public void avancar() {

		if ( getPaginaFinal() < getMaximoPaginas() ) {

			setPaginaFinal( getPaginaFinal() + 1 );
			setPaginaInicial( getPaginaInicial() + 1 );
		}
	}

	public void voltar() {

		if ( getPaginaInicial() > 1 ) {
			setPaginaFinal( getPaginaFinal() - 1 );
			setPaginaInicial( getPaginaInicial() - 1 );
		}
	}

	public void inicializar() {
		setPaginaCorrente( 1 );
		setPaginaInicial( 1 );
		setPaginaFinal( 1 );
		setTotalRegistros( 0 );
	}

	/** Método OK */
	public Long prepararInicial( Long quantidadeRegistrosEncontrados ) {
		if ( getPaginaCorrente() == 0 ) {
			setPaginaCorrente( 1 );
		}

		if ( quantidadeRegistrosEncontrados == null ) {
			quantidadeRegistrosEncontrados = 0L;
		}

		Long paginasTotal = new BigDecimal( quantidadeRegistrosEncontrados / QUANTIDADE_REGISTROS_EXIBIR ).setScale( 0, BigDecimal.ROUND_FLOOR ).longValue();

		if ( ( quantidadeRegistrosEncontrados % QUANTIDADE_REGISTROS_EXIBIR ) != 0 ) {
			paginasTotal++;
		}

		definirPaginaFinal( paginasTotal.intValue() );

		if ( paginasTotal > 0 && getPaginaCorrente() > paginasTotal ) {
			setPaginaCorrente( paginasTotal.intValue() );
		}

		return (long) ( ( getPaginaCorrente() - 1 ) * QUANTIDADE_REGISTROS_EXIBIR );
	}

	private void definirPaginaFinal( int paginasTotal) {
		
		if( paginasTotal > QUANTIDADE_PAGINA_EXIBIR ){
			if(getPaginaInicial() == 1 ){
				setPaginaFinal( QUANTIDADE_PAGINA_EXIBIR );
			}
		} else {
			setPaginaFinal(  paginasTotal );
		}
		
	}

}
