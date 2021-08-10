package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.EncomendaVO;

public interface EncomendaDAOI {
	
	public int cadastrarEncomendaDAO(EncomendaVO encomendaVO);
	public int atualizarEncomendaDAO(EncomendaVO encomendaVO);
	public int excluirEncomendaDAO(EncomendaVO encomendaVO);
	public ArrayList<EncomendaVO> listarTodasEncomendas();
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaEndereco();
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaDataEntrega();
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaStatus();
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaProduto();
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaCliente();
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaEntregador();
	public ArrayList<EncomendaVO> pesquisarEncomendasId(EncomendaVO encomendaVO);
	public ArrayList<EncomendaVO> pesquisarEncomendasCliente(EncomendaVO encomendaVO);
	public ArrayList<EncomendaVO> pesquisarEncomendasData(EncomendaVO encomendaVO);
	public ArrayList<EncomendaVO> pesquisarEncomendasEntregador(EncomendaVO encomendaVO);
	public int atualizarStatusEncomendaDAO(EncomendaVO encomendaVO);

}
