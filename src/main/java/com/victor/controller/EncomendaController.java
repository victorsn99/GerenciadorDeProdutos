package com.victor.controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.dao.EncomendaDAO;
import com.victor.dao.ProdutoDAO;
import com.victor.vo.EncomendaVO;
import com.victor.vo.ProdutoVO;

public class EncomendaController {
	
	public void cadastrarEncomendaController(EncomendaVO encomendaVO) {
		EncomendaDAO edao = new EncomendaDAO();
		int r = edao.cadastrarEncomendaDAO(encomendaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Inserido no banco", "sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Não inserido", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizarEncomendaController(EncomendaVO encomendaVO) {
		EncomendaDAO edao = new EncomendaDAO();
		int r = edao.atualizarEncomendaDAO(encomendaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado no banco", "sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Não atualizado", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void atualizarStatusEncomendaController(EncomendaVO encomendaVO) {
		EncomendaDAO edao = new EncomendaDAO();
		int r = edao.atualizarStatusEncomendaDAO(encomendaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Atualizado no banco", "sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Não atualizado", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void excluirEncomendaController(EncomendaVO encomendaVO) {
		EncomendaDAO edao = new EncomendaDAO();
		int r = edao.excluirEncomendaDAO(encomendaVO);
		if (r == 1) {
			JOptionPane.showMessageDialog(null, "Excluido no banco", "sucesso", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Não excluido", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public ArrayList<EncomendaVO> listarTodosEncomendasController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarTodasEncomendas();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaClienteController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasOrdenadaCliente();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaEnderecoController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasOrdenadaEndereco();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaDataEntregaController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasOrdenadaDataEntrega();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaProdutoController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasOrdenadaProduto();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaStatusController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasOrdenadaStatus();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasStatusFinalizadaController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasStatusFinalizada();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaEntregadorController() {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasOrdenadaEntregador();
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> pesquisarEncomendasEntregadorController(EncomendaVO encomendaVO) {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.pesquisarEncomendasEntregador(encomendaVO);
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> pesquisarEncomendasIdController(EncomendaVO encomendaVO) {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.pesquisarEncomendasId(encomendaVO);
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> pesquisarEncomendasDataController(EncomendaVO encomendaVO) {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.pesquisarEncomendasData(encomendaVO);
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> pesquisarEncomendasClienteController(EncomendaVO encomendaVO) {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.pesquisarEncomendasCliente(encomendaVO);
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	

	public EncomendaVO listarUltimaEncomendaController() {
		EncomendaDAO cdao = new EncomendaDAO();
		EncomendaVO cvo = cdao.listarUltimaEncomendaDAO();
		
		if (cvo == null) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasDataAtualController(String dataAtual) {
		EncomendaDAO cdao = new EncomendaDAO();
		ArrayList<EncomendaVO> cvo = cdao.listarEncomendasDataAtual(dataAtual);
		
		if (cvo.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Não existem encomendas no banco");
		}
		return cvo;
	}
	

}
