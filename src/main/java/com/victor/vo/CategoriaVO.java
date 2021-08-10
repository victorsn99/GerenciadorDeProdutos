package com.victor.vo;

public class CategoriaVO {
	private int idCategoria;
	private String nomeCategoria;
	public CategoriaVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CategoriaVO(int idCategoria, String nomeCategoria) {
		super();
		this.idCategoria = idCategoria;
		this.nomeCategoria = nomeCategoria;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}
	public String getNomeCategoria() {
		return nomeCategoria;
	}
	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}
	
	

}
