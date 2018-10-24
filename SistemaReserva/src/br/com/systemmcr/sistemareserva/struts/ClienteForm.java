package br.com.systemmcr.sistemareserva.struts;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractForm;

public class ClienteForm extends AbstractForm{

	private static final long serialVersionUID = -6743755422237664241L;

	private String id;
	private String nome;
	private String cidade;
	private String imagem;
	private FormFile file;

	private ArrayList< ClientePO > clientes = new ArrayList< ClientePO >();

	/** Getters e Setters */
	public String getId() {
		return id;
	}

	public void setId( String id ) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome( String nome ) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade( String cidade ) {
		this.cidade = cidade;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem( String imagem ) {
		this.imagem = imagem;
	}
	
	public FormFile getFile() {
		return file;
	}
	
	public void setFile( FormFile file ) {
		this.file = file;
	}

	public ArrayList< ClientePO > getClientes() {
		return clientes;
	}

	public void setClientes( ArrayList< ClientePO > clientes ) {
		this.clientes = clientes;
	}

	/** Método Limpar Campos */
	public void limparCampos() {
		setId( null );
		setNome( null );
		setCidade( null );
		setImagem( "temp_empty.png" );
	}

	/** Método Limpar Tabela */
	public void limparTabela() {
		getClientes().clear();
	}
	
	/** Prefixo Imagem */
	public String getPrefixoImagem() {
		if ( imagem == null ) {
			return "";
		}
		String[ ] prefixo = imagem.split( "_" );

		return prefixo.length > 1 ? prefixo[ 0 ] : "";
	}

}
