package com.victor.vo;

import java.util.Date;

public class EncomendaVO {
	private int idEncomenda, quantidadeProduto;
	private ClienteVO cliente;
	private ProdutoVO produto;
	private EntregadorVO entregador;
	private Date dataEntrega;
	private String endereco, status, horaEntrega;
	public EncomendaVO() {
		super();
		// TODO Auto-generated constructor stub
		cliente = new ClienteVO();
		produto = new ProdutoVO();
		entregador = new EntregadorVO();
	}
	

	


	public EncomendaVO(int idEncomenda, int quantidadeProduto, ClienteVO cliente, ProdutoVO produto,
			EntregadorVO entregador, Date dataEntrega, String endereco, String status, String horaEntrega) {
		super();
		this.idEncomenda = idEncomenda;
		this.quantidadeProduto = quantidadeProduto;
		this.cliente = cliente;
		this.produto = produto;
		this.entregador = entregador;
		this.dataEntrega = dataEntrega;
		this.endereco = endereco;
		this.status = status;
		this.horaEntrega = horaEntrega;
	}





	public int getIdEncomenda() {
		return idEncomenda;
	}
	public void setIdEncomenda(int idEncomenda) {
		this.idEncomenda = idEncomenda;
	}
	public ClienteVO getCliente() {
		return cliente;
	}
	public void setCliente(ClienteVO cliente) {
		this.cliente = cliente;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produto) {
		this.produto = produto;
	}
	public Date getDataEntrega() {
		return dataEntrega;
	}
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public EntregadorVO getEntregador() {
		return entregador;
	}
	public void setEntregador(EntregadorVO entregador) {
		this.entregador = entregador;
	}


	public int getQuantidadeProduto() {
		return quantidadeProduto;
	}


	public void setQuantidadeProduto(int quantidadeProduto) {
		this.quantidadeProduto = quantidadeProduto;
	}





	public String getHoraEntrega() {
		return horaEntrega;
	}





	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}
	
	
	

}
