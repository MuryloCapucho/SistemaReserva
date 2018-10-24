package br.com.systemmcr.sistemareserva.utilidades;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Utilidades{

	public static final String SCHEMA = "sistema_reserva";

	public static final Date parseDate( String data ) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		sdf.setLenient( false );
		return sdf.parse( data );
	}// fim do Date parseDate

	public static final String parseDate( Date data ) {
		SimpleDateFormat sdf = new SimpleDateFormat( "dd/MM/yyyy" );
		sdf.setLenient( false );
		return sdf.format( data );
	}// fim do Date parseDate

	public static final boolean isCpfValido( String CPF ) {
		if ( ( CPF == null ) || CPF.isEmpty() ) {
			return false;
		}
		CPF = CPF.replace( ".", "" ).replace( "-", "" ).trim();

		// considera-se erro CPF's formados por uma sequencia de numeros iguais
		if ( CPF.equals( "00000000000" ) || CPF.equals( "11111111111" ) || CPF.equals( "22222222222" ) || CPF.equals( "33333333333" ) || CPF.equals( "44444444444" ) || CPF.equals( "55555555555" ) || CPF.equals( "66666666666" ) || CPF.equals( "77777777777" ) || CPF.equals( "88888888888" ) || CPF.equals( "99999999999" ) || ( CPF.length() != 11 ) ) {
			return ( false );
		}

		char dig10, dig11;
		int sm, i, r, num, peso;
		// "try" - protege o codigo para eventuais erros de conversao de tipo
		// (int)
		try {
			// Calculo do 1o. Digito Verificador
			sm = 0;
			peso = 10;
			for ( i = 0; i < 9; i++ ) {
				// converte o i-esimo caractere do CPF em um numero:
				// por exemplo, transforma o caractere '0' no inteiro 0
				// (48 eh a posicao de '0' na tabela ASCII)
				num = CPF.charAt( i ) - 48;
				sm = sm + ( num * peso );
				peso = peso - 1;
			}
			r = 11 - ( sm % 11 );
			if ( ( r == 10 ) || ( r == 11 ) ) {
				dig10 = '0';
			} else {
				dig10 = (char) ( r + 48 );
			}
			// converte no respectivo caractere numerico
			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for ( i = 0; i < 10; i++ ) {
				num = CPF.charAt( i ) - 48;
				sm = sm + ( num * peso );
				peso = peso - 1;
			}
			r = 11 - ( sm % 11 );
			if ( ( r == 10 ) || ( r == 11 ) ) {
				dig11 = '0';
			} else {
				dig11 = (char) ( r + 48 );
			}
			// Verifica se os digitos calculados conferem com os digitos
			// informados.
			if ( ( dig10 == CPF.charAt( 9 ) ) && ( dig11 == CPF.charAt( 10 ) ) ) {
				return ( true );
			} else {
				return ( false );
			}
		} catch ( InputMismatchException erro ) {
			return ( false );
		}

	}

	public static final String normalizeString( String texto ) {
		if ( ( texto == null ) || texto.isEmpty() ) {
			return null;
		}

		texto = texto.replaceAll( "[^a-zA-Z0-9áàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ][.,!?:...]", "" );
		String padrao = "\\s{2,}";// caso tenha 2 espaços ou mais
		Pattern regPat = Pattern.compile( padrao );
		Matcher matcher = regPat.matcher( texto );
		texto = matcher.replaceAll( " " ).trim();
		return texto;
	}

	public static final BigDecimal parseBigDecimal( String valor ) {
		if ( valor == null || valor.isEmpty() ) {
			return null;
		}

		String valorFormatado = valor.replace( ".", "" ).replace( ",", "." ).trim();

		return new BigDecimal( valorFormatado );
	}

	public static final String parseBigDecimal( BigDecimal valor ) {
		if ( valor == null ) {
			return null;
		}

		return valor.toString();
	}

	@SuppressWarnings( "rawtypes" )
	public static final boolean isNuloOuVazio( Object value ) {
		if ( value == null ) {
			return true;
		}

		if ( value instanceof Collection ) {
			Collection lista = (Collection) value;

			return lista.isEmpty();
		}

		if ( value instanceof Map ) {
			Map map = (Map) value;

			return map.isEmpty();
		}

		if ( value instanceof String ) {
			String string = (String) value;

			return string.trim().isEmpty();
		}

		return false;
	}

	public static final String criptografarSenha( String senha ) throws NoSuchAlgorithmException {
		if ( senha == null ) {
			senha = "";
		}
		MessageDigest md = MessageDigest.getInstance( "MD5" );
		BigInteger hash = new BigInteger( 1, md.digest( senha.getBytes() ) );
		String s = hash.toString( 16 );
		if ( ( s.length() % 2 ) != 0 ) {
			s = "0" + s;
		}
		return s;
	}
}
