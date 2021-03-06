package br.com.boleto.util.boleto.bopepo;

import java.io.File;
import java.util.Date;

import javax.inject.Inject;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

import br.com.boleto.model.Cobranca;
import br.com.boleto.util.boleto.EmissorBoleto;
import br.com.boleto.util.modulo11.GeradorDigitoVerifiacador;

public class BopepoEmissorBoleto implements EmissorBoleto {

	private static final long serialVersionUID = 1L;

	private GeradorDigitoVerifiacador geradorDigitoVerificador;

	@Inject
	public BopepoEmissorBoleto(GeradorDigitoVerifiacador geradorDigitoVerificador) {
		this.geradorDigitoVerificador = geradorDigitoVerificador;
	}

	@Override
	public byte[] gerarBoleto(br.com.boleto.model.Cedente cedenteSistema, Cobranca cobrancaSistema) {
		Boleto boleto = criarBoleto(cedenteSistema, cobrancaSistema);

		BoletoViewer boletoViewer = new BoletoViewer(boleto);
		return boletoViewer.getPdfAsByteArray();
	}

	@Override
	public File gerarBoletoEmArquivo(String arquivo, br.com.boleto.model.Cedente cedenteSistema,
			Cobranca cobrancaSistema) {
		Boleto boleto = criarBoleto(cedenteSistema, cobrancaSistema);

		BoletoViewer boletoViewer = new BoletoViewer(boleto);
		return boletoViewer.getPdfAsFile(arquivo);
	}

	private Boleto criarBoleto(br.com.boleto.model.Cedente cedenteSistema, Cobranca cobrancaSistema) {
		ContaBancaria contaBancaria = criarContaBancaria(cedenteSistema);
		Sacado sacado = new Sacado(cobrancaSistema.getSacado().getNome());
		Cedente cedente = new Cedente(cedenteSistema.getNome(), cedenteSistema.getCnpj());

		Titulo titulo = criarTitulo(contaBancaria, sacado, cedente, cobrancaSistema);

		Boleto boleto = new Boleto(titulo);
		boleto.setLocalPagamento("Pagável em qualquer banco até o vencimento");
		boleto.setInstrucao1("Não receber após o vencimento");
		return boleto;
	}

	private ContaBancaria criarContaBancaria(br.com.boleto.model.Cedente cedenteSistema) {
		ContaBancaria contaBancaria = new ContaBancaria(BancosSuportados.BANCO_BRADESCO.create());
		Integer agencia = cedenteSistema.getContaBancaria().getAgencia();
		String digitoAgencia = cedenteSistema.getContaBancaria().getDigitoAgencia();
		Integer numeroConta = cedenteSistema.getContaBancaria().getNumero();
		String digitoConta = cedenteSistema.getContaBancaria().getDigitoConta();
		contaBancaria.setAgencia(new Agencia(agencia, digitoAgencia));
		contaBancaria.setNumeroDaConta(new NumeroDaConta(numeroConta, digitoConta));
		contaBancaria.setCarteira(new Carteira(cedenteSistema.getContaBancaria().getCodigoCarteira()));

		return contaBancaria;
	}

	private Titulo criarTitulo(ContaBancaria contaBancaria, Sacado sacado, Cedente cedente,
			br.com.boleto.model.Cobranca cobrancaSistema) {
		Titulo titulo = new Titulo(contaBancaria, sacado, cedente);

		String codigo = this.geradorDigitoVerificador.completarComZeros(String.valueOf(cobrancaSistema.getCodigo()));
		titulo.setNumeroDoDocumento(codigo);
		titulo.setNossoNumero(codigo);
		titulo.setDigitoDoNossoNumero(
				this.geradorDigitoVerificador.gerarDigito(contaBancaria.getCarteira().getCodigo(), codigo));

		titulo.setValor(cobrancaSistema.getValor());
		titulo.setDataDoDocumento(new Date());
		titulo.setDataDoVencimento(cobrancaSistema.getDataVencimento());
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setAceite(Aceite.N);
		return titulo;
	}

}
