package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.ClienteVO;

public interface ClienteDAOI {
	
	public int cadastrarClienteDAO(ClienteVO clienteVO);
	public int atualizarClienteDAO(ClienteVO clienteVO);
	public int excluirClienteDAO(ClienteVO clienteVO);
	public ArrayList<ClienteVO> listarTodosClientesDAO();
	public ArrayList<ClienteVO> pesquisarClientePorNomeDAO(ClienteVO clienteVO);
	public ArrayList<ClienteVO> pesquisarClientePorCpfDAO(ClienteVO clienteVO);
	public ArrayList<ClienteVO> listarClienteOrdenadoNomeDAO();
	public ArrayList<ClienteVO> listarClienteOrdenadoCpfDAO();

}
