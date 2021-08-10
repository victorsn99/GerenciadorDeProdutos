package com.victor.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.dao.ClienteDAO;
import com.victor.dao.ProdutoDAO;
import com.victor.vo.ClienteVO;
import com.victor.vo.ProdutoVO;

public class ProdutoController {
	
	public void cadastrarProdutoController(ProdutoVO clienteVO) {
		ProdutoDAO pdao = new ProdutoDAO();
		int r = pdao.cadastrarProdutoDAO(clienteVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public void atualizarProdutoController(ProdutoVO clienteVO) {
		ProdutoDAO pdao = new ProdutoDAO();
		int r = pdao.atualizarProdutoDAO(clienteVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel atualizar");
		}
		
	}

	public void excluirProdutoController(ProdutoVO clienteVO) {
		ProdutoDAO pdao = new ProdutoDAO();
		int r = pdao.excluirProdutoDAO(clienteVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir");
		}
		
	}

	public ArrayList<ProdutoVO> listarTodosProdutosController() {
		ProdutoDAO cdao = new ProdutoDAO();
		ArrayList<ProdutoVO> cvo = cdao.listarProdutosIdDAO();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}

	public ArrayList<ProdutoVO> pesquisarProdutoPorNomeController(ProdutoVO clienteVO) {
		ProdutoDAO cdao = new ProdutoDAO();
		ArrayList<ProdutoVO> cvo = cdao.pesquisarProdutosNomeDAO(clienteVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}

	public ArrayList<ProdutoVO> pesquisarProdutoPorIdController(ProdutoVO clienteVO) {
		ProdutoDAO cdao = new ProdutoDAO();
		ArrayList<ProdutoVO> cvo = cdao.pesquisarProdutosIdDAO(clienteVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}

	public ArrayList<ProdutoVO> listarProdutoOrdenadoNomeController() {
		ProdutoDAO cdao = new ProdutoDAO();
		ArrayList<ProdutoVO> cvo = cdao.listarProdutosOrdenadoNomeDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}

	public ArrayList<ProdutoVO> listarProdutoOrdenadoCategoriaController() {
		ProdutoDAO cdao = new ProdutoDAO();
		ArrayList<ProdutoVO> cvo = cdao.listarProdutosOrdenadoCategoriaDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}
	
	public ArrayList<ProdutoVO> listarProdutoOrdenadoValorController() {
		ProdutoDAO cdao = new ProdutoDAO();
		ArrayList<ProdutoVO> cvo = cdao.listarProdutosOrdenadoValorDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem produtos no banco");
		}
		return cvo;
	}

}
