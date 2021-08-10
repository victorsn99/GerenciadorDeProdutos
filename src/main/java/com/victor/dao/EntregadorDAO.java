package com.victor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.victor.interfaces.EntregadorDAOI;
import com.victor.vo.EntregadorVO;

public class EntregadorDAO implements EntregadorDAOI{
	
	Connection conn;
	
	public EntregadorDAO() {
		super();
		conn = Banco.getConnection();
	}

	public int cadastrarEntregadorDAO(EntregadorVO entregadorVO) {
		Statement stmt = Banco.getStatement(conn);
		int r =0;
		String query = "INSERT INTO entregadores (nomeEntregador) VALUES ('" + entregadorVO.getNome() + "')";
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

	public int atualizarEntregadorDAO(EntregadorVO entregadorVO) {
		Statement stmt = Banco.getStatement(conn);
		int r =0;
		String query = "UPDATE entregadores SET nomeEntregador = '" + entregadorVO.getNome() + "' WHERE idEntregador = '" + entregadorVO.getId() + "'";
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

	public int excluirEntregadorDAO(EntregadorVO entregadorVO) {
		Statement stmt = Banco.getStatement(conn);
		int r =0;
		String query = "DELETE FROM entregadores where idEntregador = '" + entregadorVO.getId() + "'";
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

	public ArrayList<EntregadorVO> listarEntregadoresDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<EntregadorVO> entregadores = new ArrayList<EntregadorVO>();
		String query = "SELECT * FROM entregadores";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				EntregadorVO entVO = new EntregadorVO();
				entVO.setId(Integer.parseInt(rs.getString(1)));
				entVO.setNome(rs.getString(2));
				entregadores.add(entVO);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return entregadores;
	}
	
}
