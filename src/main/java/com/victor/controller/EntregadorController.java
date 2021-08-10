package com.victor.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.dao.EntregadorDAO;
import com.victor.vo.ClienteVO;
import com.victor.vo.EntregadorVO;

public class EntregadorController {
	
	public void cadastrarEntregadorController(EntregadorVO entregadorVO) {
		EntregadorDAO cdao = new EntregadorDAO();
		int r = cdao.cadastrarEntregadorDAO(entregadorVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Cadastrado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel cadastrar");
		}
		
	}

	public void atualizarEntregadorController(EntregadorVO entregadorVO) {
		EntregadorDAO cdao = new EntregadorDAO();
		int r = cdao.atualizarEntregadorDAO(entregadorVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel atualizar");
		}
		
	}

	public void excluirEntregadorController(EntregadorVO entregadorVO) {
		EntregadorDAO cdao = new EntregadorDAO();
		int r = cdao.excluirEntregadorDAO(entregadorVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido com sucesso");
		} else {
			JOptionPane.showMessageDialog(null, "Não foi possivel excluir");
		}
		
	}

	public ArrayList<EntregadorVO> listarTodosEntregadorController() {
		EntregadorDAO cdao = new EntregadorDAO();
		ArrayList<EntregadorVO> cvo = cdao.listarEntregadoresDAO();
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem clientes no banco");
		}
		return cvo;
	}

}
