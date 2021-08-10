package com.victor.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.victor.interfaces.ProdutoDAOI;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ProdutoVO;

public class ProdutoDAO implements ProdutoDAOI{
	
	Connection conn;

	public ProdutoDAO() {
		super();
		conn = Banco.getConnection();
		// TODO Auto-generated constructor stub
	}

	public int cadastrarProdutoDAO(ProdutoVO produtoVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "insert into produtos (idCategoria, nomeProduto, valor) values ('" + produtoVO.getCategoria().getIdCategoria() + "', '"
				+ produtoVO.getNome() + "', '" + produtoVO.getValor() + "')";
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

	public int atualizarProdutoDAO(ProdutoVO produtoVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "UPDATE produtos SET idCategoria = '" + produtoVO.getCategoria().getIdCategoria() + "', nomeProduto = '" +produtoVO.getNome()
			+ "', valor = '" + produtoVO.getValor() + "' WHERE idProduto = '" + produtoVO.getIdProduto() + "'"; ;
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

	public int excluirProdutoDAO(ProdutoVO produtoVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "DELETE FROM produtos WHERE idProduto = '" + produtoVO.getIdProduto() + "';";
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

	public ArrayList<ProdutoVO> listarProdutosIdDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		String query = "select produtos.idProduto, produtos.nomeProduto, categorias.nomeCategoria, produtos.valor from produtos " + 
				       "inner join categorias on categorias.idCategoria = produtos.idCategoria";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ProdutoVO prodVO = new ProdutoVO();
				prodVO.setIdProduto(Integer.parseInt(rs.getString(1)));
				prodVO.setNome(rs.getString(2));
				prodVO.getCategoria().setNomeCategoria(rs.getString(3));
				prodVO.setValor(Double.parseDouble(rs.getString(4)));
				produtosVO.add(prodVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro:"+e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtosVO;
	}

	public ArrayList<ProdutoVO> pesquisarProdutosNomeDAO(ProdutoVO produtoVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		String query = "select produtos.idProduto, produtos.nomeProduto, categorias.nomeCategoria, produtos.valor from produtos " + 
				       "inner join categorias on categorias.idCategoria = produtos.idCategoria "
				       + "where produtos.nomeProduto LIKE '" + produtoVO.getNome() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ProdutoVO prodVO = new ProdutoVO();
				prodVO.setIdProduto(Integer.parseInt(rs.getString(1)));
				prodVO.setNome(rs.getString(2));
				prodVO.getCategoria().setNomeCategoria(rs.getString(3));
				prodVO.setValor(Double.parseDouble(rs.getString(4)));
				produtosVO.add(prodVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtosVO;
	}

	public ArrayList<ProdutoVO> pesquisarProdutosIdDAO(ProdutoVO produtoVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		String query = "select produtos.idProduto, produtos.nomeProduto, categorias.nomeCategoria, produtos.valor from produtos " + 
				       "inner join categorias on categorias.idCategoria = produtos.idCategoria "
				       + "where produtos.idProduto LIKE '" + produtoVO.getIdProduto() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ProdutoVO prodVO = new ProdutoVO();
				prodVO.setIdProduto(Integer.parseInt(rs.getString(1)));
				prodVO.setNome(rs.getString(2));
				prodVO.getCategoria().setNomeCategoria(rs.getString(3));
				prodVO.setValor(Double.parseDouble(rs.getString(4)));
				produtosVO.add(prodVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtosVO;
	}

	public ArrayList<ProdutoVO> listarProdutosOrdenadoCategoriaDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		String query = "select produtos.idProduto, produtos.nomeProduto, categorias.nomeCategoria, produtos.valor from produtos " + 
				       "inner join categorias on categorias.idCategoria = produtos.idCategoria "
				       + "order by categorias.nomeCategoria";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ProdutoVO prodVO = new ProdutoVO();
				prodVO.setIdProduto(Integer.parseInt(rs.getString(1)));
				prodVO.setNome(rs.getString(2));
				prodVO.getCategoria().setNomeCategoria(rs.getString(3));
				prodVO.setValor(Double.parseDouble(rs.getString(4)));
				produtosVO.add(prodVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtosVO;
	}

	public ArrayList<ProdutoVO> listarProdutosOrdenadoNomeDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		String query = "select produtos.idProduto, produtos.nomeProduto, categorias.nomeCategoria, produtos.valor from produtos " + 
				       "inner join categorias on categorias.idCategoria = produtos.idCategoria "
				       + "order by produtos.nomeProduto";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ProdutoVO prodVO = new ProdutoVO();
				prodVO.setIdProduto(Integer.parseInt(rs.getString(1)));
				prodVO.setNome(rs.getString(2));
				prodVO.getCategoria().setNomeCategoria(rs.getString(3));
				prodVO.setValor(Double.parseDouble(rs.getString(4)));
				produtosVO.add(prodVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtosVO;
	}

	public ArrayList<ProdutoVO> listarProdutosOrdenadoValorDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<ProdutoVO> produtosVO = new ArrayList<ProdutoVO>();
		String query = "select produtos.idProduto, produtos.nomeProduto, categorias.nomeCategoria, produtos.valor from produtos " + 
				       "inner join categorias on categorias.idCategoria = produtos.idCategoria "
				       + "order by produtos.valor";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				ProdutoVO prodVO = new ProdutoVO();
				prodVO.setIdProduto(Integer.parseInt(rs.getString(1)));
				prodVO.setNome(rs.getString(2));
				prodVO.getCategoria().setNomeCategoria(rs.getString(3));
				prodVO.setValor(Double.parseDouble(rs.getString(4)));
				produtosVO.add(prodVO);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Excessão: " + e, "Falha", JOptionPane.ERROR_MESSAGE);
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtosVO;
	}

}
