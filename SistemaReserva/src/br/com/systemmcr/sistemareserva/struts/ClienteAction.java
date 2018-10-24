package br.com.systemmcr.sistemareserva.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import br.com.systemmcr.sistemareserva.controller.ClienteFacade;
import br.com.systemmcr.sistemareserva.exceptions.ApplicationException;
import br.com.systemmcr.sistemareserva.model.po.ClientePO;
import br.com.systemmcr.sistemareserva.struts.abstracts.AbstractAction;
import br.com.systemmcr.sistemareserva.utilidades.Messages;
import br.com.systemmcr.sistemareserva.utilidades.Paginacao;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class ClienteAction extends AbstractAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		clienteForm.limparCampos();
		clienteForm.limparTabela();

		clienteForm.getPaginacao().inicializar();

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "clienteCadastro" );
	}// fim abrirTela

	public ActionForward inserir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		try {

			try {
				uploadImagem( clienteForm, request );
			} catch ( ApplicationException e ) {
				if ( Utilidades.isNuloOuVazio( clienteForm.getImagem() ) ) {
					clienteForm.setImagem( "empty.png" );
				}
			}

			ClienteFacade clienteFacade = new ClienteFacade();

			clienteFacade.inserir( montarPO( clienteForm ) );

			clienteForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, clienteForm, request, response );
	}

	public ActionForward excluir( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		try {

			ClienteFacade clienteFacade = new ClienteFacade();

			clienteFacade.excluir( montarPO( clienteForm ) );

			clienteForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, clienteForm, request, response );
	}

	public ActionForward alterar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		try {
			
			try {
				uploadImagem( clienteForm, request );
			} catch ( ApplicationException e ) {
				if ( Utilidades.isNuloOuVazio( clienteForm.getImagem() ) ) {
					clienteForm.setImagem( "empty.png" );
				}
			}

			ClienteFacade clienteFacade = new ClienteFacade();

			clienteFacade.alterar( montarPO( clienteForm ) );

			clienteForm.limparCampos();

			this.addMessages( request, Messages.createMessages( "sucesso" ) );
		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return filtrar( mapping, clienteForm, request, response );
	}

	public ActionForward filtrar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		try {

			clienteForm.limparTabela();

			clienteForm.getPaginacao().inicializar();

			ClienteFacade clienteFacade = new ClienteFacade();

			ArrayList< ClientePO > encontrados = clienteFacade.filtrar( montarPO( clienteForm ), clienteForm.getPaginacao() );

			clienteForm.setClientes( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "clienteCadastro" );
	}

	public ActionForward paginar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		try {

			ClienteFacade clienteFacade = new ClienteFacade();

			ArrayList< ClientePO > encontrados = clienteFacade.filtrar( montarPO( clienteForm ), clienteForm.getPaginacao() );

			clienteForm.setClientes( encontrados );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "clienteCadastro" );
	}

	public ActionForward selecionar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		ClienteForm clienteForm = (ClienteForm) form;

		try {

			ClienteFacade clienteFacade = new ClienteFacade();

			ClientePO encontrado = clienteFacade.filtrarPorId( Long.valueOf( clienteForm.getId() ) );

			clienteForm.setNome( encontrado.getNome() );
			clienteForm.setCidade( encontrado.getCidade() );
			clienteForm.setImagem( encontrado.getImagem() );

		} catch ( ApplicationException e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}

		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parametro */
		return mapping.findForward( "clienteCadastro" );
	}
	
	public ActionForward selecionarCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ClienteForm clienteForm = (ClienteForm) form;

			ClientePO clientePO = new ClientePO();
			clientePO.setNome( clienteForm.getId() );

			ArrayList< ClientePO > clientesEncontrados = new ClienteFacade().filtrar( clientePO, Paginacao.NAO );
			
			for ( ClientePO clienteCorrente : clientesEncontrados ) {
				if ( !Utilidades.isNuloOuVazio( clientesEncontrados ) ) {
					clienteForm.setId( clienteCorrente.getId().toString() );
					clienteForm.setNome( clienteCorrente.getNome() );
				} else {
					clienteForm.setId( null );
					clienteForm.setNome( null );
				}
			}

			if ( clienteForm.getId() == null || clienteForm.getId().isEmpty() ) {
				clienteForm.setId( null );
			}
			
			Collections.sort( clienteForm.getClientes() );

		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return mapping.findForward( "clienteCadastro" );
	}
	
	@SuppressWarnings( "unchecked" )
	public ActionForward selecionarAutoCompleteCliente( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		try {
			ClienteForm clienteForm = (ClienteForm) form;

			JSONObject json = new JSONObject();
			JSONObject map = null;
			JSONArray arr = new JSONArray();

			ClientePO clientePO = new ClientePO();
			clientePO.setNome( clienteForm.getNome() );

			ArrayList< ClientePO > encontrados = new ClienteFacade().filtrar( clientePO, Paginacao.NAO );

			for ( ClientePO clienteCorrente : encontrados ) {

				map = new JSONObject();
				map.put( "value", clienteCorrente.getNome() );
				map.put( "data", clienteCorrente.getId() );
				arr.add( map );
			}
			json.put( "suggestions", arr );

			response.setContentType( "application/json" );
			response.setHeader( "Cache-Control", "nocache" );
			response.getWriter().print( json.toString() );
		} catch ( ApplicationException e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { e.getMessage() } ) );

			e.printStackTrace();
		} catch ( Throwable e ) {
			this.addErrors( request, Messages.createMessagesErrors( "falha", new String[ ] { "Erro inesperado![" + e.getMessage() + "]" } ) );

			e.printStackTrace();
		}
		return null;
	}

	private ClientePO montarPO( ClienteForm clienteForm ) throws ParseException, NumberFormatException, ApplicationException {
		ClientePO po = new ClientePO();

		if ( clienteForm.getId() == null || clienteForm.getId().isEmpty() ) {
			clienteForm.setId( null );
		}

		if ( clienteForm.getId() != null && !clienteForm.getId().isEmpty() ) {
			po.setId( Long.valueOf( clienteForm.getId() ) );
		} else {
			po.setId( null );
		}

		if ( clienteForm.getNome() != null && !clienteForm.getNome().isEmpty() ) {
			po.setNome( clienteForm.getNome() );
		} else {
			po.setNome( null );
		}

		if ( clienteForm.getCidade() != null && !clienteForm.getCidade().isEmpty() ) {
			po.setCidade( clienteForm.getCidade() );
		} else {
			po.setCidade( null );
		}
		
		if ( clienteForm.getImagem() != null && !clienteForm.getImagem().isEmpty() ) {
			po.setImagem( clienteForm.getImagem() );
		} else {
			po.setImagem( null );
		}

		return po;
	}

	private void uploadImagem( ClienteForm clienteForm, HttpServletRequest request ) throws Exception, ApplicationException {
		try {
			String identificador = clienteForm.getNome();
						
			FormFile file = clienteForm.getFile();
			
			String fileName = file.getFileName();
			
			if(!Utilidades.isNuloOuVazio( fileName )){
				identificador = identificador.replace( " ", "_" ).toLowerCase().trim();
				
				int indiceUltimoPonto = fileName.lastIndexOf( "." );
				String nomeArquivo = identificador + "_" + String.valueOf( new Date().getTime() );
				String extensao = fileName.substring( indiceUltimoPonto + 1, fileName.length() );
				
				// Obter o nome do caminho real do diretório de upload dos servidores
				String filePath = getServlet().getServletContext().getRealPath( "/" ) + "upload" + File.separator + identificador;
				
				//create the upload folder if not exists
				File folder = new File( filePath );

				if ( !folder.exists() ) {
					folder.mkdirs();
				}

				fileName = nomeArquivo + "." + extensao;
				
				if ( !( "" ).equals( fileName ) ) {
					System.out.println( "Server path:" + filePath );
					File newFile = new File( filePath, fileName );

					if ( !newFile.exists() ) {
						FileOutputStream fos = new FileOutputStream( newFile );
						fos.write( file.getFileData() );
						fos.flush();
						fos.close();
					}

					//request.setAttribute( "uploadedFilePath", newFile.getAbsoluteFile() );
					//request.setAttribute( "uploadedFileName", newFile.getName() );
				}
				
				String nome = nomeArquivo + "." + extensao;
				clienteForm.setImagem( nome );
			}	

		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
	}
}
