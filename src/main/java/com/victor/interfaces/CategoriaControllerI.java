package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.CategoriaVO;

public interface CategoriaControllerI {
	
	public void cadastrarCategoriaController(CategoriaVO categoriaVO);
	public void atualizarCategoriaController(CategoriaVO categoriaVO);
	public void excluirCategoriaController(CategoriaVO categoriaVO);
	public ArrayList<CategoriaVO> listarTodosCategoriasController();
	public ArrayList<CategoriaVO> pesquisarCategoriaPorNomeController(CategoriaVO categoriaVO);
	public ArrayList<CategoriaVO> listarCategoriaOrdenadoNomeController();

}
