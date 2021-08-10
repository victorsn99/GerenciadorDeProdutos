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
import com.victor.vo.Produtos_EncomendasVO;

public class Produto_EncomendaDAO {
	
	Connection conn;

	public Produto_EncomendaDAO() {
		super();
		conn = Banco.getConnection();
		// TODO Auto-generated constructor stub
	}

	public int cadastrarProduto_EncomendaDAO(Produtos_EncomendasVO produtos_encomendasVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "insert into produtos_encomendas (idEncomenda, idProduto, quantidade) values ('" 
		+ produtos_encomendasVO.getEncomenda().getIdEncomenda() + "', '"
				+ produtos_encomendasVO.getProduto().getIdProduto() + "', '" + produtos_encomendasVO.getQtdProduto() + "')";
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

	public int atualizarProduto_EncomendaDAO(Produtos_EncomendasVO produtos_encomendasVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "UPDATE produtos_encomendas SET idProduto = '" + produtos_encomendasVO.getProduto().getIdProduto()
			+ "', quantidade = '" + produtos_encomendasVO.getQtdProduto() + "' "
			+ "WHERE idEncomenda = '" + produtos_encomendasVO.getEncomenda().getIdEncomenda() + "'";
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

	public int excluirProduto_EncomendaDAO(Produtos_EncomendasVO produtos_encomendasVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "DELETE FROM produtos_encomendas WHERE idProdutos_encomendas = '" + produtos_encomendasVO.getIdProd_Enc() + "';";
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
	
	public int excluirTodosProdutos_EncomendaDAO(Produtos_EncomendasVO produtos_encomendasVO) {
		Statement stmt = Banco.getStatement(conn);
		int r = 0;
		String query = "DELETE FROM produtos_encomendas WHERE produtos_encomendas.idEncomenda = '" + produtos_encomendasVO.getEncomenda().getIdEncomenda() + "';";
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
	
	public ArrayList<Produtos_EncomendasVO> listarTodosProdutos_EncomendasIdDAO(int id) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<Produtos_EncomendasVO> produtos_encomendaVO = new ArrayList<Produtos_EncomendasVO>();
		String query = "select produtos.nomeProduto, produtos.valor ,produtos_encomendas.quantidade from produtos_encomendas " + 
				       "inner join produtos on produtos.idProduto = produtos_encomendas.idProduto "
				       + "inner join encomendas on encomendas.idEncomenda = produtos_encomendas.idEncomenda "+
				       "WHERE encomendas.idEncomenda = '" + id + "'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Produtos_EncomendasVO prodVO = new Produtos_EncomendasVO();
				prodVO.getProduto().setNome(rs.getString(1));
				prodVO.getProduto().setValor(Double.parseDouble(rs.getString(2)));
				prodVO.setQtdProduto(Integer.parseInt(rs.getString(3)));
				produtos_encomendaVO.add(prodVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtos_encomendaVO;
	}
	
	public ArrayList<Produtos_EncomendasVO> listarProdutos_EncomendasCadastroDAO() {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<Produtos_EncomendasVO> produtos_encomendaVO = new ArrayList<Produtos_EncomendasVO>();
		String query = "select encomendas.idEncomenda, produtos.nomeProduto, produtos.valor, produtos_encomendas.quantidade from produtos_encomendas " + 
				       "inner join produtos on produtos.idProduto = produtos_encomendas.idProduto "
				       + "inner join encomendas on encomendas.idEncomenda = produtos_encomendas.idEncomenda "
				       + "WHERE encomendas.idEncomenda = max(encomendas.idEncomenda)";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Produtos_EncomendasVO prodVO = new Produtos_EncomendasVO();
				prodVO.getEncomenda().setIdEncomenda(Integer.parseInt(rs.getString(1)));
				prodVO.getProduto().setNome(rs.getString(2));
				prodVO.getProduto().setValor(Double.parseDouble(rs.getString(3)));
				prodVO.setQtdProduto(Integer.parseInt(rs.getString(4)));
				produtos_encomendaVO.add(prodVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro:"+e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtos_encomendaVO;
	}

	public ArrayList<Produtos_EncomendasVO> pesquisarProdutos_EncomendasNomeDAO(Produtos_EncomendasVO produtos_encomendasVO) {
		Statement stmt = Banco.getStatement(conn);
		ResultSet rs = null;
		ArrayList<Produtos_EncomendasVO> produtos_encVO = new ArrayList<Produtos_EncomendasVO>();
		String query = "select encomendas.idEncomenda, produtos.nomeProduto, produtos.valor, produtos_encomendas.quantidade from produtos_encomendas " + 
				       "inner join produtos on produtos.idProduto = produtos_encomendas.idProduto "
				       + "inner join encomendas on encomendas.idEncomenda = produtos_encomendas.idEncomenda "
				       + "WHERE produtos.nomeProduto LIKE '" + produtos_encomendasVO.getProduto().getNome() + "%'";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				Produtos_EncomendasVO prodVO = new Produtos_EncomendasVO();
				prodVO.getEncomenda().setIdEncomenda(Integer.parseInt(rs.getString(1)));
				prodVO.getProduto().setNome(rs.getString(2));
				prodVO.getProduto().setValor(Double.parseDouble(rs.getString(3)));
				prodVO.setQtdProduto(Integer.parseInt(rs.getString(4)));
				produtos_encVO.add(prodVO);
			}
		} catch (SQLException e) {
			System.out.println("Erro:"+e.getMessage());
		} finally {
			Banco.closeConnection(conn);
			Banco.closeStatement(stmt);
			Banco.closeResultSet(rs);
		}
		return produtos_encVO;
	}
}
