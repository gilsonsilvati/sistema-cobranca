package br.com.boleto.repository;

import java.io.Serializable;

import br.com.boleto.model.Cedente;

public interface Cedentes extends Serializable {
	
	public Cedente porCodigo(Long codigo);
	
}
