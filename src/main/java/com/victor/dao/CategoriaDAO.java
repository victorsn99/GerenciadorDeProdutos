package com.victor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.interfaces.CategoriaDAOI;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ClienteVO;

public class CategoriaDAO implements CategoriaDAOI {
	
	Connection conn;

	public CategoriaDAO() {
		super();
		conn = Banco.getConnection();
		// TODO Auto-generated constructor stub
	}

	public int cadastrarCategoriaDAO(CategoriaVO categoriaVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "INSERT INTO categorias(nomeCategoria) VALUES ('" + categoriaVO.getNomeCategoria() +"');";
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

	public int atualizarCategoriaDAO(CategoriaVO categoriaVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "UPDATE categorias SET nomeCategoria = '" + categoriaVO.getNomeCategoria() +"' WHERE idCategoria = '" + categoriaVO.getIdCategoria() + "';";
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

	public int excluirCategoriaDAO(CategoriaVO categoriaVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "DELETE FROM categorias WHERE idCategoria = '" + categoriaVO.getIdCategoria() + "'";
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

	public ArrayList<CategoriaVO> listarTodasCategoriasDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<CategoriaVO> categoriasVO = new ArrayList<CategoriaVO>();
		String query = "SELECT * FROM categorias";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CategoriaVO categoriaVO = new CategoriaVO();
				categoriaVO.setIdCategoria(Integer.parseInt(rs.getString(1)));
				categoriaVO.setNomeCategoria(rs.getString(2));
				categoriasVO.add(categoriaVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return categoriasVO;
	}

	public ArrayList<CategoriaVO> pesquisarCategoriasNomeDAO(CategoriaVO categoriaVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<CategoriaVO> categoriasVO = new ArrayList<CategoriaVO>();
		String query = "SELECT * FROM categorias WHERE nomeCategoria LIKE '" + categoriaVO.getNomeCategoria() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CategoriaVO catVO = new CategoriaVO();
				catVO.setIdCategoria(Integer.parseInt(rs.getString(1)));
				catVO.setNomeCategoria(rs.getString(2));
				categoriasVO.add(catVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return categoriasVO;
	}
	
	public ArrayList<CategoriaVO> pesquisarCategoriasIdDAO(CategoriaVO categoriaVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<CategoriaVO> categoriasVO = new ArrayList<CategoriaVO>();
		String query = "SELECT * FROM categorias WHERE idCategoria = '" + categoriaVO.getIdCategoria() + "'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CategoriaVO catVO = new CategoriaVO();
				catVO.setIdCategoria(Integer.parseInt(rs.getString(1)));
				catVO.setNomeCategoria(rs.getString(2));
				categoriasVO.add(catVO);
				System.out.println(catVO.getIdCategoria() + " - " + catVO.getNomeCategoria());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return categoriasVO;
	}

	public ArrayList<CategoriaVO> ordenarCategoriasNomeDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<CategoriaVO> categoriasVO = new ArrayList<CategoriaVO>();
		String query = "SELECT categorias.idCategoria, categorias.nomeCategoria FROM categorias ORDER BY categorias.nomeCategoria";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CategoriaVO catVO = new CategoriaVO();
				catVO.setIdCategoria(Integer.parseInt(rs.getString(1)));
				catVO.setNomeCategoria(rs.getString(2));
				categoriasVO.add(catVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return categoriasVO;
	}

}
