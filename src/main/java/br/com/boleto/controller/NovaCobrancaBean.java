package br.com.boleto.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.boleto.model.Cedente;
import br.com.boleto.model.Cobranca;
import br.com.boleto.model.Sacado;
import br.com.boleto.repository.Cedentes;
import br.com.boleto.service.NovaCobrancaService;
import br.com.boleto.util.jsf.FacesMessages;

@Named
@ViewScoped
public class NovaCobrancaBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Cobranca cobranca;
	
	@Inject
	private Cedentes cedentes;
	
	@Inject
	private NovaCobrancaService novaCobrancaService;
	
	@Inject
	private FacesMessages messages;
	
	public void inicializar() {
		cobranca = new Cobranca();
		cobranca.setSacado(new Sacado());
	}
	
	public void emitir() {
		@SuppressWarnings("unused")
		Cedente cedente = cedentes.porCodigo(1L);
		cobranca = novaCobrancaService.salvar(cobranca);
		inicializar();
		messages.info("Dados salvo com sucesso!");
	}

	public Cobranca getCobranca() {
		return cobranca;
	}
	
}
