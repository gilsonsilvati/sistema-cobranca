package br.com.boleto.service;

import java.io.Serializable;

import javax.inject.Inject;

import org.apache.deltaspike.jpa.api.transaction.Transactional;

import br.com.boleto.model.Cobranca;
import br.com.boleto.model.Status;
import br.com.boleto.repository.Cobrancas;

public class NovaCobrancaService implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Cobrancas cobrancas;
	
	@Transactional
	public Cobranca salvar(Cobranca cobranca) {
		cobranca.setStatus(Status.PENDENTE);
		cobranca = cobrancas.guardar(cobranca);
		
		return cobranca;
	}
	
}
