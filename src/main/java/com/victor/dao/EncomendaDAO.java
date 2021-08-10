package com.victor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.sql.Date;

import com.victor.interfaces.EncomendaDAOI;
import com.victor.vo.EncomendaVO;

public class EncomendaDAO implements EncomendaDAOI{
	
	Connection conn;

	public EncomendaDAO() {
		super();
		conn = Banco.getConnection();
	}

	public int cadastrarEncomendaDAO(EncomendaVO encomendaVO) {
		int st = 0;
		if (encomendaVO.getStatus().equalsIgnoreCase("Em Produção")) {
			st = 0;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Separado para Entrega")) {
			st = 1;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Em Transporte")) {
			st = 2;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Entrega Finalizada")) {
			st = 3;
		}
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "insert into encomendas (idCliente, idEntregador, endereco, dataEntrega, horaEntrega, statusEnc)"
				+ " values ('" + encomendaVO.getCliente().getIdCliente() +
				"', '" + encomendaVO.getEntregador().getId() + "', '" + encomendaVO.getEndereco() + 
				"', '" + encomendaVO.getDataEntrega() + "', '" + encomendaVO.getHoraEntrega() + "', '" + st + "')";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public int atualizarEncomendaDAO(EncomendaVO encomendaVO) {
		int st = 0;
		if (encomendaVO.getStatus().equalsIgnoreCase("Em Produção")) {
			st = 0;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Separado para Entrega")) {
			st = 1;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Em Transporte")) {
			st = 2;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Entrega Finalizada")) {
			st = 3;
		}
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "UPDATE encomendas SET idCliente = '" + encomendaVO.getCliente().getIdCliente() + 
				"', idEntregador = '" + encomendaVO.getEntregador().getId() + "', endereco = '" + encomendaVO.getEndereco() + 
				"', dataEntrega = '" + encomendaVO.getDataEntrega() + 
				"', horaEntrega = '" + encomendaVO.getHoraEntrega() + "', statusEnc = '" + st + "' WHERE idEncomenda = '" + encomendaVO.getIdEncomenda() + "'";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public int excluirEncomendaDAO(EncomendaVO encomendaVO) {
		
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "DELETE FROM encomendas WHERE idEncomenda = '" + encomendaVO.getIdEncomenda() + "' ";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Primeiro exclua os produtos desta encomenda (" + encomendaVO.getIdEncomenda() 
			+ ") para poder excluir a encomenda.", "Aviso", JOptionPane.WARNING_MESSAGE);
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public ArrayList<EncomendaVO> listarTodasEncomendas() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente"+ /*produtos.nomeProduto, produtos.valor,*/ 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " + 
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador " +
				"WHERE NOT encomendas.statusEnc = 4 "+
				"ORDER BY encomendas.idEncomenda";  // 3
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> listarEncomendasOrdenadaEndereco() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador " +
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "ORDER BY encomendas.endereco";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> listarEncomendasOrdenadaDataEntrega() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "ORDER BY encomendas.dataEntrega";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> listarEncomendasOrdenadaStatus() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "ORDER BY encomendas.statusEnc";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasStatusFinalizada() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
			    "WHERE encomendas.statusEnc = '" + 3 + "'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> listarEncomendasOrdenadaProduto() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "ORDER BY produtos.nomeProduto";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> listarEncomendasOrdenadaCliente() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "ORDER BY clientes.nomeCliente";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}
	
	public int atualizarStatusEncomendaDAO(EncomendaVO encomendaVO) {
		int st = 0;
		if (encomendaVO.getStatus().equalsIgnoreCase("Em Produção")) {
			st = 0;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Separado para Entrega")) {
			st = 1;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Em Transporte")) {
			st = 2;
		} else if (encomendaVO.getStatus().equalsIgnoreCase("Entrega Finalizada")) {
			st = 3;
		}
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "UPDATE encomendas SET statusEnc = '" + st + "' WHERE idEncomenda = '" + encomendaVO.getIdEncomenda() + "'";
		try {
			r = stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
		}
		return r;
	}

	public ArrayList<EncomendaVO> pesquisarEncomendasId(EncomendaVO encomendaVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "AND encomendas.idEncomenda = '" + encomendaVO.getIdEncomenda() + "'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasOrdenadaEntregador() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "ORDER BY entregadores.nomeEntregador";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> pesquisarEncomendasCliente(EncomendaVO encomendaVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "AND clientes.nomeCliente LIKE '" + encomendaVO.getCliente().getNome() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> pesquisarEncomendasData(EncomendaVO encomendaVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "AND encomendas.dataEntrega LIKE '" + encomendaVO.getDataEntrega() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	public ArrayList<EncomendaVO> pesquisarEncomendasEntregador(EncomendaVO encomendaVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 "
				+ "AND entregadores.nomeEntregador LIKE '" + encomendaVO.getEntregador().getNome() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}
	
	public EncomendaVO listarUltimaEncomendaDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		EncomendaVO encomendaID = new EncomendaVO();
		String query = "SELECT MAX(encomendas.idEncomenda) FROM encomendas";  // 3
		try {
			rs = stmt.executeQuery(query);
				while (rs.next()) {
				encomendaID.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				System.out.println(rs.getString(1));
				}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendaID;
	}
	
	public ArrayList<EncomendaVO> listarEncomendasDataAtual(String dataAtual) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EncomendaVO> encomendas = new ArrayList<EncomendaVO>();
		String query = "select encomendas.idEncomenda ,clientes.nomeCliente" + 
				", encomendas.endereco, entregadores.nomeEntregador, encomendas.dataEntrega, encomendas.horaEntrega,encomendas.statusEnc from encomendas " + 
				"inner join clientes on clientes.idCliente = encomendas.idCliente " +  
				"inner join entregadores on entregadores.idEntregador = encomendas.idEntregador "+
				"WHERE NOT encomendas.statusEnc = 4 AND encomendas.dataEntrega = '" + dataAtual + "'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				String st = null;
				EncomendaVO encVO = new EncomendaVO();
				if (Integer.parseInt(rs.getString(7)) == 0) {
					st = "Em Produção";
				} else if (Integer.parseInt(rs.getString(7)) == 1) {
					st = "Separado para Entrega";
				} else if (Integer.parseInt(rs.getString(7)) == 2) {
					st = "Em Transporte";
				} else if (Integer.parseInt(rs.getString(7)) == 3) {
					st = "Entrega Finalizada";
				}
				encVO.setIdEncomenda(Integer.parseInt(rs.getString(1)));
				encVO.getCliente().setNome(rs.getString(2));
				encVO.setEndereco(rs.getString(3));
				encVO.getEntregador().setNome(rs.getString(4));
				encVO.setDataEntrega(Date.valueOf(rs.getString(5)));
				encVO.setHoraEntrega(rs.getString(6));
				encVO.setStatus(st);
				encomendas.add(encVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return encomendas;
	}

	

}
