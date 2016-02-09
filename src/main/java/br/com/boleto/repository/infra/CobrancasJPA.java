package br.com.boleto.repository.infra;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.boleto.model.Cobranca;
import br.com.boleto.repository.Cobrancas;

public class CobrancasJPA implements Cobrancas {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Override
	public Cobranca guardar(Cobranca cobranca) {
		return manager.merge(cobranca);
	}

	@Override
	public Cobranca porCodigo(Long codigoCobranca) {
		return manager.find(Cobranca.class, codigoCobranca);
	}
	
	

}
