package com.dev.cruzada.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pedido
{
	@Id
	@GeneratedValue
	Long pedidoId;
	Float pedidoValorTotal;
	Float pedidoQuantidade;
	Boolean pedidoStatus;
	Boolean pedidoEnviado;
	Boolean pedidoPago;

	public Long getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public Float getPedidoValorTotal() {
		return pedidoValorTotal;
	}

	public void setPedidoValorTotal(Float pedidoValorTotal) {
		this.pedidoValorTotal = pedidoValorTotal;
	}

	public Float getPedidoQuantidade() {
		return pedidoQuantidade;
	}

	public void setPedidoQuantidade(Float pedidoQuantidade) {
		this.pedidoQuantidade = pedidoQuantidade;
	}

	public Boolean getPedidoStatus() {
		return pedidoStatus;
	}

	public void setPedidoStatus(Boolean pedidoStatus) {
		this.pedidoStatus = pedidoStatus;
	}

	public Boolean getPedidoEnviado() {
		return pedidoEnviado;
	}

	public void setPedidoEnviado(Boolean pedidoEnviado) {
		this.pedidoEnviado = pedidoEnviado;
	}

	public Boolean getPedidoPago() {
		return pedidoPago;
	}

	public void setPedidoPago(Boolean pedidoPago) {
		this.pedidoPago = pedidoPago;
	}
}
