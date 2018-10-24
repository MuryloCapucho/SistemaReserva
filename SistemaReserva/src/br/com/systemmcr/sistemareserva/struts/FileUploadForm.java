package br.com.systemmcr.sistemareserva.struts;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class FileUploadForm extends ActionForm{

	private static final long serialVersionUID = -6083844347573071971L;

	private FormFile file;

	public FormFile getFile() {
		return file;
	}

	public void setFile( FormFile file ) {
		this.file = file;
	}
	
	/**  Método Limpar Campo */
	public void limparCampos(){
		setFile( null );
	}
}
