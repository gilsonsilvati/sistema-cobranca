package br.com.boleto.repository.infra;

import java.io.Serializable;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.boleto.model.Cedente;
import br.com.boleto.repository.Cedentes;

public class CedentesJPA implements Cedentes, Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager manager;

	@Override
	public Cedente porCodigo(Long codigo) {
		return manager.find(Cedente.class, codigo);
	}

}
