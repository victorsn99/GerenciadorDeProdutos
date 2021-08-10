package com.victor.vo;

public class Produtos_EncomendasVO {
	
	private int idProd_Enc;
	private EncomendaVO encomenda = new EncomendaVO();
	private ProdutoVO produto = new ProdutoVO();
	private int qtdProduto;
	public Produtos_EncomendasVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Produtos_EncomendasVO(int idProd_Enc, EncomendaVO encomendaVO, ProdutoVO produtoVO, int qtdProduto) {
		super();
		this.idProd_Enc = idProd_Enc;
		this.encomenda = encomendaVO;
		this.produto = produtoVO;
		this.qtdProduto = qtdProduto;
	}
	public int getIdProd_Enc() {
		return idProd_Enc;
	}
	public void setIdProd_Enc(int idProd_Enc) {
		this.idProd_Enc = idProd_Enc;
	}
	public EncomendaVO getEncomenda() {
		return encomenda;
	}
	public void setEncomenda(EncomendaVO encomendaVO) {
		this.encomenda = encomendaVO;
	}
	public ProdutoVO getProduto() {
		return produto;
	}
	public void setProduto(ProdutoVO produtoVO) {
		this.produto = produtoVO;
	}
	public int getQtdProduto() {
		return qtdProduto;
	}
	public void setQtdProduto(int qtdProduto) {
		this.qtdProduto = qtdProduto;
	}
	
	
	
	

}
