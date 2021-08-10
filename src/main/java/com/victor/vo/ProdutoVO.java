package com.victor.vo;

public class ProdutoVO {
	
	private int idProduto;
	private double valor;
	private String nome;
	private CategoriaVO categoria;
	
	public ProdutoVO() {
		super();
		categoria = new CategoriaVO();
	}
	
	public ProdutoVO(int idProduto, double valor, String nome, CategoriaVO categoria) {
		super();
		this.idProduto = idProduto;
		this.valor = valor;
		this.nome = nome;
		this.categoria = categoria;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public CategoriaVO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaVO categoria) {
		this.categoria = categoria;
	}

	

}
