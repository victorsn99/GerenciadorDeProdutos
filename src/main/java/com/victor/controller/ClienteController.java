package com.victor.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.dao.ClienteDAO;
import com.victor.interfaces.ClienteControllerI;
import com.victor.vo.ClienteVO;

public class ClienteController implements ClienteControllerI {

	public void cadastrarClienteController(ClienteVO clienteVO) {
		ClienteDAO cdao = new ClienteDAO();
		int r = cdao.cadastrarClienteDAO(clienteVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public void atualizarClienteController(ClienteVO clienteVO) {
		ClienteDAO cdao = new ClienteDAO();
		int r = cdao.atualizarClienteDAO(clienteVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel atualizar");
		}
		
	}

	public void excluirClienteController(ClienteVO clienteVO) {
		ClienteDAO cdao = new ClienteDAO();
		int r = cdao.excluirClienteDAO(clienteVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir");
		}
		
	}

	public ArrayList<ClienteVO> listarTodosClientesController() {
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<ClienteVO> cvo = cdao.listarTodosClientesDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem clientes no banco");
		}
		return cvo;
	}

	public ArrayList<ClienteVO> pesquisarClientePorNomeController(ClienteVO clienteVO) {
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<ClienteVO> cvo = cdao.pesquisarClientePorNomeDAO(clienteVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem clientes no banco");
		}
		return cvo;
	}

	public ArrayList<ClienteVO> pesquisarClientePorCpfController(ClienteVO clienteVO) {
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<ClienteVO> cvo = cdao.pesquisarClientePorCpfDAO(clienteVO);
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem clientes no banco");
		}
		return cvo;
	}

	public ArrayList<ClienteVO> listarClienteOrdenadoNomeController() {
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<ClienteVO> cvo = cdao.listarClienteOrdenadoNomeDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem clientes no banco");
		}
		return cvo;
	}

	public ArrayList<ClienteVO> listarClienteOrdenadoCpfController() {
		ClienteDAO cdao = new ClienteDAO();
		ArrayList<ClienteVO> cvo = cdao.listarClienteOrdenadoCpfDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem clientes no banco");
		}
		return cvo;
	}

	
	
	

}
