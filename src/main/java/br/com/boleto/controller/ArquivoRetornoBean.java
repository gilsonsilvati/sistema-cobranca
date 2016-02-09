package br.com.boleto.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import br.com.boleto.exception.ArquivoRetornoException;
import br.com.boleto.service.ArquivoRetornoService;
import br.com.boleto.util.jsf.FacesMessages;

@Named
@RequestScoped
public class ArquivoRetornoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private UploadedFile arquivo;
	
	@Inject
	private ArquivoRetornoService arquivoRetornoService;
	
	@Inject
	private FacesMessages messages;
	
	public void upload() {
		try {
			List<String> mensagens = arquivoRetornoService.carregar(arquivo.getFileName(),
					arquivo.getInputstream());
			
			mensagens.forEach(m -> messages.info(m));
		} catch(ArquivoRetornoException | IOException e) {
			messages.error(e.getMessage());
		}
	}

	public UploadedFile getArquivo() {
		return arquivo;
	}
	public void setArquivo(UploadedFile arquivo) {
		this.arquivo = arquivo;
	}
	
}
