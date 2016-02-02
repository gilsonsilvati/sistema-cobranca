package br.com.boleto.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "cedente")
public class Cedente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private String nome;
	private String cnpj;
	private ContaBancaria contaBancaria;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	@OneToOne
	@JoinColumn(name = "codigo_conta_bancaria")
	public ContaBancaria getContaBancaria() {
		return contaBancaria;
	}
	public void setContaBancaria(ContaBancaria contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if ((obj == null) || getClass() != obj.getClass()) {
			return false;
		}
		Cedente other = (Cedente) obj;
		return new EqualsBuilder().append(codigo, other.codigo).isEquals();
	}
	
}
