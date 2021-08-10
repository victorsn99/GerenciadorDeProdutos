package com.victor.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.dao.CategoriaDAO;
import com.victor.dao.ClienteDAO;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ClienteVO;

public class CategoriaController  {

	public void cadastrarCategoriaController(CategoriaVO categoriaVO) {
		CategoriaDAO cdao = new CategoriaDAO();
		int r = cdao.cadastrarCategoriaDAO(categoriaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public void atualizarCategoriaController(CategoriaVO categoriaVO) {
		CategoriaDAO cdao = new CategoriaDAO();
		int r = cdao.atualizarCategoriaDAO(categoriaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public void excluirCategoriaController(CategoriaVO categoriaVO) {
		CategoriaDAO cdao = new CategoriaDAO();
		int r = cdao.excluirCategoriaDAO(categoriaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public ArrayList<CategoriaVO> listarTodasCategoriasController() {
		CategoriaDAO cdao = new CategoriaDAO();
		ArrayList<CategoriaVO> cvo = cdao.listarTodasCategoriasDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem categorias no banco");
		}
		return cvo;
	}

	public ArrayList<CategoriaVO> pesquisarCategoriaPorNomeController(CategoriaVO categoriaVO) {
		CategoriaDAO cdao = new CategoriaDAO();
		ArrayList<CategoriaVO> cvo = cdao.pesquisarCategoriasNomeDAO(categoriaVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem categorias no banco");
		}
		return cvo;
	}
	
	public ArrayList<CategoriaVO> pesquisarCategoriaPorIdController(CategoriaVO categoriaVO) {
		CategoriaDAO cdao = new CategoriaDAO();
		ArrayList<CategoriaVO> cvo = cdao.pesquisarCategoriasIdDAO(categoriaVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem categorias no banco");
		}
		return cvo;
	}

	public ArrayList<CategoriaVO> listarCategoriaOrdenadoNomeController() {
		CategoriaDAO cdao = new CategoriaDAO();
		ArrayList<CategoriaVO> cvo = cdao.ordenarCategoriasNomeDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem categorias no banco");
		}
		return cvo;
	}

}
