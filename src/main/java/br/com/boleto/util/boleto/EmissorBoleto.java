package br.com.boleto.util.boleto;

import java.io.File;
import java.io.Serializable;

import br.com.boleto.model.Cedente;
import br.com.boleto.model.Cobranca;

public interface EmissorBoleto extends Serializable {
	
	public byte[] gerarBoleto(Cedente cedenteSistema, Cobranca cobrancaSistema);
	public File gerarBoletoEmArquivo(String arquivo, Cedente cedenteSistema, Cobranca cobrancaSistema);

}
