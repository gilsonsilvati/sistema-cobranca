package br.com.boleto.repository;

import java.io.Serializable;

import br.com.boleto.model.Cobranca;

public interface Cobrancas extends Serializable {

	public Cobranca guardar(Cobranca cobranca);
	public Cobranca porCodigo(Long codigoCobranca);

}
