package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.CategoriaVO;

public interface CategoriaDAOI {
	
	public int cadastrarCategoriaDAO(CategoriaVO categoriaVO);
	public int atualizarCategoriaDAO(CategoriaVO categoriaVO);
	public int excluirCategoriaDAO(CategoriaVO categoriaVO);
	public ArrayList<CategoriaVO> listarTodasCategoriasDAO();
	public ArrayList<CategoriaVO> pesquisarCategoriasNomeDAO(CategoriaVO categoriaVO);
	public ArrayList<CategoriaVO> ordenarCategoriasNomeDAO();

}
