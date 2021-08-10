package com.victor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.interfaces.ClienteDAOI;
import com.victor.vo.ClienteVO;

public class ClienteDAO implements ClienteDAOI {
	
	Connection conn;

	public ClienteDAO() {
		super();
		conn = Banco.getConnection();
		// TODO Auto-generated constructor stub
	}

	public int cadastrarClienteDAO(ClienteVO clienteVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "INSERT INTO clientes(nomeCliente, cpf, telefone, endereco) VALUES ('" + clienteVO.getNome() + "', '" + clienteVO.getCpf() +
				 "', '" + clienteVO.getTelefone() + "', '" + clienteVO.getEndereco() + "');";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public int atualizarClienteDAO(ClienteVO clienteVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "UPDATE clientes SET nomeCliente = '" + clienteVO.getNome() + "', cpf = '" + clienteVO.getCpf() +
				  "', telefone = '" + clienteVO.getTelefone()  + "', endereco = '" + clienteVO.getEndereco() + "'"
				+ " WHERE idCliente = '"+ clienteVO.getIdCliente() + "';";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public int excluirClienteDAO(ClienteVO clienteVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "DELETE FROM clientes WHERE idCliente = '" + clienteVO.getIdCliente() + "'";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public ArrayList<ClienteVO> listarTodosClientesDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();
		String query = "SELECT * FROM clientes";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ClienteVO clienteVO = new ClienteVO();
				clienteVO.setIdCliente(Integer.parseInt(rs.getString(1)));
				clienteVO.setNome(rs.getString(2));
				clienteVO.setCpf(rs.getString(3));
				clienteVO.setTelefone(rs.getString(4));
				clienteVO.setEndereco(rs.getString(5));
				clientesVO.add(clienteVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return clientesVO;
	}

	public ArrayList<ClienteVO> pesquisarClientePorNomeDAO(ClienteVO clienteVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();
		String query = "SELECT * FROM clientes WHERE nomeCliente LIKE '" + clienteVO.getNome() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ClienteVO clVO = new ClienteVO();
				clVO.setIdCliente(Integer.parseInt(rs.getString(1)));
				clVO.setNome(rs.getString(2));
				clVO.setCpf(rs.getString(3));
				clVO.setTelefone(rs.getString(4));
				clVO.setEndereco(rs.getString(5));
				clientesVO.add(clVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return clientesVO;
	}

	public ArrayList<ClienteVO> pesquisarClientePorCpfDAO(ClienteVO clienteVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();
		String query = "SELECT * FROM clientes WHERE cpf LIKE '" + clienteVO.getCpf() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ClienteVO clVO = new ClienteVO();
				clVO.setIdCliente(Integer.parseInt(rs.getString(1)));
				clVO.setNome(rs.getString(2));
				clVO.setCpf(rs.getString(3));
				clVO.setTelefone(rs.getString(4));
				clVO.setEndereco(rs.getString(5));
				clientesVO.add(clVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return clientesVO;
	}
	

	public ArrayList<ClienteVO> listarClienteOrdenadoNomeDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();
		String query = "SELECT * FROM clientes ORDER BY clientes.nomeCliente";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ClienteVO clVO = new ClienteVO();
				clVO.setIdCliente(Integer.parseInt(rs.getString(1)));
				clVO.setNome(rs.getString(2));
				clVO.setCpf(rs.getString(3));
				clVO.setTelefone(rs.getString(4));
				clVO.setEndereco(rs.getString(5));
				clientesVO.add(clVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return clientesVO;
	}
	

	public ArrayList<ClienteVO> listarClienteOrdenadoCpfDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ClienteVO> clientesVO = new ArrayList<ClienteVO>();
		String query = "SELECT * FROM clientes ORDER BY clientes.cpf";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ClienteVO clVO = new ClienteVO();
				clVO.setIdCliente(Integer.parseInt(rs.getString(1)));
				clVO.setNome(rs.getString(2));
				clVO.setCpf(rs.getString(3));
				clVO.setTelefone(rs.getString(4));
				clVO.setEndereco(rs.getString(5));
				clientesVO.add(clVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return clientesVO;
	}

}
