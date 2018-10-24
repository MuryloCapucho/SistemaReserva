package br.com.systemmcr.sistemareserva.struts;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import br.com.systemmcr.sistemareserva.utilidades.Messages;
import br.com.systemmcr.sistemareserva.utilidades.Utilidades;

public class FileUploadAction extends DispatchAction{

	public ActionForward abrirTela( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {

		FileUploadForm fileUploadForm =(FileUploadForm) form;

		fileUploadForm.limparCampos();
		
		/** O Mapping vai até o struts-config.xml procurar um forward com o nome passado como parâmetro */
		return mapping.findForward( "uploadCampo" );
	}// fim abrirTela

	public ActionForward upload ( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) throws Exception {

		FileUploadForm fileUploadForm = (FileUploadForm) form;

		try {
			String identificador = "AA";
			
			for( int i = 0; i < 1000000; i++){
				System.out.println( "" );
			}
			
			FormFile file = fileUploadForm.getFile();
			
			String fileName = file.getFileName();
			
			if(!Utilidades.isNuloOuVazio( fileName )){
				identificador = identificador.replace( " ", "_" ).toLowerCase().trim();
				
				int indiceUltimoPonto = fileName.lastIndexOf( "." );
				String nomeArquivo = identificador + "_" + String.valueOf( new Date().getTime() );
				String extensao = fileName.substring( indiceUltimoPonto + 1, fileName.length() );
				
				// Obter o nome do caminho real do diretório de upload dos servidores
				String filePath = getServlet().getServletContext().getRealPath( "/" ) + "upload";

				//create the upload folder if not exists
				File folder = new File( filePath );

				if ( !folder.exists() ) {
					folder.mkdir();
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

					request.setAttribute( "uploadedFilePath", newFile.getAbsoluteFile() );
					request.setAttribute( "uploadedFileName", newFile.getName() );
				}
			}	
			
			this.addMessages( request, Messages.createMessages( "sucesso" ) );
			
		} catch ( Exception e ) {
			e.printStackTrace();
			this.addErrors( request, Messages.createMessagesErrors( "erro", e.getMessage() ) );
		}
		return mapping.findForward( "uploadCampo" );
	}

}
