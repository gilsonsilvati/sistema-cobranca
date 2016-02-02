package br.com.boleto.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.EqualsBuilder;

@Entity
@Table(name = "cobranca")
public class Cobranca implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long codigo;
	private BigDecimal valor;
	private Date dataVencimento;
	private Sacado sacado;
	private Status status;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name = "data_vencimento")
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	@ManyToOne
	@JoinColumn(name = "codigo_sacado")
	public Sacado getSacado() {
		return sacado;
	}
	public void setSacado(Sacado sacado) {
		this.sacado = sacado;
	}
	
	@Enumerated(EnumType.STRING)
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
		Cobranca other = (Cobranca) obj;
		return new EqualsBuilder().append(codigo, other.codigo).isEquals(); 
	}
	
}
