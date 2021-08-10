package com.victor.interfaces;

import java.util.ArrayList;

import com.victor.vo.EntregadorVO;
import com.victor.vo.ProdutoVO;

public interface EntregadorDAOI {
	
	public int cadastrarEntregadorDAO(EntregadorVO entregadorVO);
	public int atualizarEntregadorDAO(EntregadorVO entregadorVO);
	public int excluirEntregadorDAO(EntregadorVO entregadorVO);
	public ArrayList<EntregadorVO> listarEntregadoresDAO();

}
