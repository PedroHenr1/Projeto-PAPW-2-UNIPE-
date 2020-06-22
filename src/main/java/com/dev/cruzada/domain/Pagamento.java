package com.dev.cruzada.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pagamento
{
	@Id
	@GeneratedValue
	Long pagamentoId;
	Long pagamentoPedido;
	Long pagamentoValor;
	
}
