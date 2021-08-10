package com.victor.view;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.ProdutoController;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ClienteVO;
import com.victor.vo.EncomendaVO;
import com.victor.vo.ProdutoVO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;

public class GerenciarProdutos extends JInternalFrame {
	private JTable table;
	private JTextField tfPesquisa;
	private JProgressBar progressBar;
	private JComboBox<String> cbPesquisa;
	private JComboBox<String> cbOrdem;
	private JLabel lblNDeClientes;
	private ArrayList<ProdutoVO> listProdutos;
	private ProdutoController pc;
	private ProdutoVO p;
	private JDesktopPane desktopPane;
	Desktop desk = Desktop.getDesktop();
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_NOME = 1;
	private static final int COLUNA_CAT = 2;
	private static final int COLUNA_VALOR = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarProdutos frame = new GerenciarProdutos();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciarProdutos() {
		setFrameIcon(new ImageIcon(GerenciarProdutos.class.getResource("/com/victor/icons/icons8-products.png")));
		setClosable(true);
		setTitle("Gerenciar Produtos");
		setBounds(100, 100, 709, 412);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 673, 236);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Nome", "Categoria", "Valor (R$)" }));
		table.getColumnModel().getColumn(0).setPreferredWidth(68);
		table.getColumnModel().getColumn(1).setPreferredWidth(158);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(GerenciarProdutos.class.getResource("/com/victor/icons/icons8-editar.png")));
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					getItemSelecionadoTable();
					AtualizarProduto.main(null, p.getIdProduto());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnEditar.setBounds(10, 92, 110, 32);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(GerenciarProdutos.class.getResource("/com/victor/icons/icons8-excluir.png")));
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					confirmaExcluir();
					attJTable();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnExcluir.setBounds(130, 92, 110, 32);
		getContentPane().add(btnExcluir);

		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent arg0) {
				if (cbPesquisa.getSelectedItem() == "ID" || arg0.getKeyCode() == 8) {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					ProdutoVO produtoVO = new ProdutoVO();
					produtoVO.setIdProduto(Integer.parseInt(tfPesquisa.getText()));
					ArrayList<ProdutoVO> listProdutos = pc.pesquisarProdutoPorIdController(produtoVO);
					md.setNumRows(0);
					for (int i = 0; i < listProdutos.size(); i++) {

						md.addRow(new Object[] { listProdutos.get(i).getIdProduto(), listProdutos.get(i).getNome(),
								listProdutos.get(i).getCategoria().getNomeCategoria(),
								listProdutos.get(i).getValor() });
					}

				} else if (cbPesquisa.getSelectedItem() == "Nome" || arg0.getKeyCode() == 8) {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					ProdutoVO produtoVO = new ProdutoVO();
					produtoVO.setNome(tfPesquisa.getText());
					ArrayList<ProdutoVO> listProdutos = pc.pesquisarProdutoPorNomeController(produtoVO);
					md.setNumRows(0);
					for (int i = 0; i < listProdutos.size(); i++) {

						md.addRow(new Object[] { listProdutos.get(i).getIdProduto(), listProdutos.get(i).getNome(),
								listProdutos.get(i).getCategoria().getNomeCategoria(),
								listProdutos.get(i).getValor() });
					}
				}
				
			}
			
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 8) {
					tfPesquisa.setText("");
					attJTable();
				}
				
			}
		});
		tfPesquisa.setBounds(330, 61, 149, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(261, 64, 73, 14);
		getContentPane().add(lblPesquisar);

		cbPesquisa = new JComboBox<String>();
		cbPesquisa.addItem("ID");
		cbPesquisa.addItem("Nome");
		cbPesquisa.setBounds(491, 61, 85, 20);
		getContentPane().add(cbPesquisa);

		JLabel lblGerenciadorDeClientes = new JLabel("Gerenciador de Produtos");
		lblGerenciadorDeClientes.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGerenciadorDeClientes.setBounds(10, 11, 309, 33);
		getContentPane().add(lblGerenciadorDeClientes);

		lblNDeClientes = new JLabel("Estoque total:");
		lblNDeClientes.setBounds(330, 101, 177, 14);
		getContentPane().add(lblNDeClientes);

		cbOrdem = new JComboBox<String>();
		cbOrdem.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				attJTable();
				
			}
		});
		cbOrdem.addItem("ID");
		cbOrdem.addItem("Nome");
		cbOrdem.addItem("Categoria");
		cbOrdem.addItem("Valor");
		cbOrdem.setBounds(130, 61, 110, 20);
		getContentPane().add(cbOrdem);
		
		JLabel lblOrdenadoPor = new JLabel("Ordenado por:");
		lblOrdenadoPor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOrdenadoPor.setBounds(10, 64, 110, 14);
		getContentPane().add(lblOrdenadoPor);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(250, 55, 14, 69);
		getContentPane().add(separator);

		try {
			attJTable();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// metodos --------------------------------------------------------

	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		pc = new ProdutoController();
		if (cbOrdem.getSelectedItem() == "ID") {
			listProdutos = pc.listarTodosProdutosController();
		} else if (cbOrdem.getSelectedItem() == "Nome") {
			listProdutos = pc.listarProdutoOrdenadoNomeController();
		} else if (cbOrdem.getSelectedItem() == "Categoria") {
			listProdutos = pc.listarProdutoOrdenadoCategoriaController();
		} else if (cbOrdem.getSelectedItem() == "Valor") {
			listProdutos = pc.listarProdutoOrdenadoValorController();
		}
		for (int i = 0; i < listProdutos.size(); i++) {
			md.addRow(new Object[] { listProdutos.get(i).getIdProduto(), listProdutos.get(i).getNome(),
					listProdutos.get(i).getCategoria().getNomeCategoria(),
					listProdutos.get(i).getValor() });
		}
		lblNDeClientes.setText("Nº de Produtos: " + listProdutos.size());
	}

	public void getItemSelecionadoTable() {
		p = new ProdutoVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		int id = (Integer) model.getValueAt(linha, COLUNA_ID);
		p.setIdProduto(id);

		// Resgatar nome da linha
		String nome = (String) model.getValueAt(linha, COLUNA_NOME);
		p.setNome(nome);

		// Resgatar valor da linha
		String cat = (String) model.getValueAt(linha, COLUNA_CAT);
		p.getCategoria().setNomeCategoria(cat);;

		// Resgatar nome da linha
		double valor = (Double) model.getValueAt(linha, COLUNA_VALOR);
		p.setValor(valor);

	}

	public void confirmaExcluir() {
		getItemSelecionadoTable();
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir o produto " + p.getNome() + "?", "Confirma",
				JOptionPane.YES_NO_OPTION);
		if (conf == 0) {
			pc.excluirProdutoController(p);
		} else {

		}
	}

	
}
