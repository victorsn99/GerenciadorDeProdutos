package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.ProdutoVO;

public interface ProdutoDAOI {
	
	public int cadastrarProdutoDAO(ProdutoVO produtoVO);
	public int atualizarProdutoDAO(ProdutoVO produtoVO);
	public int excluirProdutoDAO(ProdutoVO produtoVO);
	public ArrayList<ProdutoVO> listarProdutosIdDAO();
	public ArrayList<ProdutoVO> pesquisarProdutosNomeDAO(ProdutoVO produtoVO);
	public ArrayList<ProdutoVO> pesquisarProdutosIdDAO(ProdutoVO produtoVO);
	public ArrayList<ProdutoVO> listarProdutosOrdenadoCategoriaDAO();
	public ArrayList<ProdutoVO> listarProdutosOrdenadoNomeDAO();
	public ArrayList<ProdutoVO> listarProdutosOrdenadoValorDAO();

}
