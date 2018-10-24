package br.com.systemmcr.sistemareserva.struts.abstracts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AbstractAction extends DispatchAction{

	public ActionForward avancar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		AbstractForm abstractForm = (AbstractForm) form;
		
		abstractForm.getPaginacao().avancar();
		
		System.out.println( "avancarPaginacao" );
		System.out.println( abstractForm.getPaginacao().getPaginaInicial() );
		System.out.println( abstractForm.getPaginacao().getPaginaFinal() );
		
		return mapping.findForward( abstractForm.getForwardDestino() );
	}

	public ActionForward voltar( ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response ) {
		AbstractForm abstractForm = (AbstractForm) form;
		
		abstractForm.getPaginacao().voltar();
		
		System.out.println( "voltarPaginacao" );
		System.out.println( abstractForm.getPaginacao().getPaginaInicial() );
		System.out.println( abstractForm.getPaginacao().getPaginaFinal() );
		
		return mapping.findForward( abstractForm.getForwardDestino() );
	}
}
