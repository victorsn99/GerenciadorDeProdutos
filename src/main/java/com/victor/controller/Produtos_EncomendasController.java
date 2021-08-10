package com.victor.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.dao.ProdutoDAO;
import com.victor.dao.Produto_EncomendaDAO;
import com.victor.vo.ProdutoVO;
import com.victor.vo.Produtos_EncomendasVO;

public class Produtos_EncomendasController {
	
	public void cadastrarProdutoController(Produtos_EncomendasVO peVO) {
		Produto_EncomendaDAO pdao = new Produto_EncomendaDAO();
		int r = pdao.cadastrarProduto_EncomendaDAO(peVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public void atualizarProdutoController(Produtos_EncomendasVO peVO) {
		Produto_EncomendaDAO pdao = new Produto_EncomendaDAO();
		int r = pdao.atualizarProduto_EncomendaDAO(peVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel atualizar");
		}
		
	}

	public void excluirProdutoController(Produtos_EncomendasVO peVO) {
		Produto_EncomendaDAO pdao = new Produto_EncomendaDAO();
		int r = pdao.excluirProduto_EncomendaDAO(peVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir");
		}
		
	}
	
	public void excluirTodosProdutosController(Produtos_EncomendasVO peVO) {
		Produto_EncomendaDAO pdao = new Produto_EncomendaDAO();
		int r = pdao.excluirTodosProdutos_EncomendaDAO(peVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir");
		}
		
	}
	
	public ArrayList<Produtos_EncomendasVO> listarTodosProdutos_EncomendasController(int id) {
		Produto_EncomendaDAO cdao = new Produto_EncomendaDAO();
		ArrayList<Produtos_EncomendasVO> cvo = cdao.listarTodosProdutos_EncomendasIdDAO(id);
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}
	
	public ArrayList<Produtos_EncomendasVO> listarProdutos_EncomendasCadastroController() {
		Produto_EncomendaDAO cdao = new Produto_EncomendaDAO();
		ArrayList<Produtos_EncomendasVO> cvo = cdao.listarProdutos_EncomendasCadastroDAO();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}

	public ArrayList<Produtos_EncomendasVO> pesquisarProdutoPorNomeController(Produtos_EncomendasVO peVO) {
		Produto_EncomendaDAO cdao = new Produto_EncomendaDAO();
		ArrayList<Produtos_EncomendasVO> cvo = cdao.pesquisarProdutos_EncomendasNomeDAO(peVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}


}
