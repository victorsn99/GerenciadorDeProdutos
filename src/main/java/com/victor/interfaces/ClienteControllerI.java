package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.ClienteVO;

public interface ClienteControllerI {
	
	public void cadastrarClienteController(ClienteVO clienteVO);
	public void atualizarClienteController(ClienteVO clienteVO);
	public void excluirClienteController(ClienteVO clienteVO);
	public ArrayList<ClienteVO> listarTodosClientesController();
	public ArrayList<ClienteVO> pesquisarClientePorNomeController(ClienteVO clienteVO);
	public ArrayList<ClienteVO> pesquisarClientePorCpfController(ClienteVO clienteVO);
	public ArrayList<ClienteVO> listarClienteOrdenadoNomeController();
	public ArrayList<ClienteVO> listarClienteOrdenadoCpfController();

}
