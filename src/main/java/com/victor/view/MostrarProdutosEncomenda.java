package com.victor.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.ProdutoController;
import com.victor.controller.Produtos_EncomendasController;
import com.victor.vo.EncomendaVO;
import com.victor.vo.Produtos_EncomendasVO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MostrarProdutosEncomenda {

	private JFrame frmProdutosDaEncomenda;
	private JTable table;
	private JTextField tfPesquisa;
	private Produtos_EncomendasVO pecvo = new Produtos_EncomendasVO();
	private ArrayList<Produtos_EncomendasVO> listProdutos = new ArrayList<Produtos_EncomendasVO>();
	private Produtos_EncomendasController pec = new Produtos_EncomendasController();
	private GerenciarEncomendas ge = new GerenciarEncomendas();
	private int id;
	private JLabel lblSubtotalR;
	private final int COLUNA_NOME = 1;
	private final int COLUNA_VALOR = 2;
	private final int COLUNA_QTD = 3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MostrarProdutosEncomenda window = new MostrarProdutosEncomenda(id);
					window.frmProdutosDaEncomenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public MostrarProdutosEncomenda(int id) {
		setId(id);
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmProdutosDaEncomenda = new JFrame();
		frmProdutosDaEncomenda.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				attJTable();
			}
		});
		frmProdutosDaEncomenda.setIconImage(Toolkit.getDefaultToolkit().getImage(MostrarProdutosEncomenda.class.getResource("/com/victor/icons/icons8-tabela-de-conteúdo.png")));
		frmProdutosDaEncomenda.setTitle("Produtos da encomenda");
		frmProdutosDaEncomenda.setBounds(100, 100, 450, 459);
		frmProdutosDaEncomenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmProdutosDaEncomenda.getContentPane().setLayout(null);
		
		JLabel lblProdutosDaEncomenda = new JLabel("Produtos da Encomenda Nº: " + getId());
		lblProdutosDaEncomenda.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblProdutosDaEncomenda.setBounds(10, 11, 337, 29);
		frmProdutosDaEncomenda.getContentPane().add(lblProdutosDaEncomenda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 414, 259);
		frmProdutosDaEncomenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Nome", "Valor" , "Qtd." }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);
		
		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent arg0) {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					Produtos_EncomendasVO encomendaVO = new Produtos_EncomendasVO();
					encomendaVO.getProduto().setNome(tfPesquisa.getText());
					ArrayList<Produtos_EncomendasVO> listEncomendas = pec.pesquisarProdutoPorNomeController(encomendaVO);
					md.setNumRows(0);
					for (int i = 0; i < listEncomendas.size(); i++) {
						md.addRow(new Object[] { listEncomendas.get(i).getProduto().getNome(), listEncomendas.get(i).getProduto().getValor()
								, listEncomendas.get(i).getQtdProduto() });
					}
				 
			}

			public void keyPressed(KeyEvent arg0) {
				System.out.println(arg0.getKeyCode());
				if (arg0.getKeyCode() == 8) {
					tfPesquisa.setText("");
					attJTable();
				}

			}
		});
		tfPesquisa.setBounds(222, 75, 202, 20);
		frmProdutosDaEncomenda.getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);
		
		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPesquisar.setBounds(150, 78, 62, 14);
		frmProdutosDaEncomenda.getContentPane().add(lblPesquisar);
		
		lblSubtotalR = new JLabel("Subtotal: R$ ");
		lblSubtotalR.setIcon(new ImageIcon(MostrarProdutosEncomenda.class.getResource("/com/victor/icons/icons8-dólar-americano-25.png")));
		lblSubtotalR.setHorizontalAlignment(SwingConstants.TRAILING);
		lblSubtotalR.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubtotalR.setBounds(20, 383, 404, 26);
		frmProdutosDaEncomenda.getContentPane().add(lblSubtotalR);
		
		JButton btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					AtualizarProdutosEncomendas.main(null, getId());
					frmProdutosDaEncomenda.dispose();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
		btnEditar.setIcon(new ImageIcon(MostrarProdutosEncomenda.class.getResource("/com/victor/icons/icons8-editar.png")));
		btnEditar.setBounds(10, 69, 130, 33);
		frmProdutosDaEncomenda.getContentPane().add(btnEditar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MostrarProdutosEncomenda.class.getResource("/com/victor/icons/icons8-ordem-de-compra-64.png")));
		lblNewLabel.setBounds(357, 0, 77, 74);
		frmProdutosDaEncomenda.getContentPane().add(lblNewLabel);
	}
	
	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		pec = new Produtos_EncomendasController();
		listProdutos = pec.listarTodosProdutos_EncomendasController(getId());
		double subtotal = 0.0;
		for (int i = 0; i < listProdutos.size(); i++) {
			subtotal = subtotal + (listProdutos.get(i).getProduto().getValor()
					* listProdutos.get(i).getQtdProduto());
			md.addRow(
					new Object[] { listProdutos.get(i).getProduto().getNome(), listProdutos.get(i).getProduto().getValor(),
							listProdutos.get(i).getQtdProduto() });
		}
		lblSubtotalR.setText("Subtotal: R$ " + subtotal);
	}
	
	public void getItemSelecionadoTable() {
		pecvo = new Produtos_EncomendasVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		String nome = (String) model.getValueAt(linha, COLUNA_NOME);
	    pecvo.getProduto().setNome(nome);;

		// Resgatar nome da linha
		double valor = (Double) model.getValueAt(linha, COLUNA_VALOR);
		pecvo.getProduto().setValor(valor);;

		// Resgatar nome da linha
		int qtd = (Integer) model.getValueAt(linha, COLUNA_QTD);
		pecvo.setQtdProduto(qtd);;
		

	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public JLabel getLblSubtotalR() {
		return lblSubtotalR;
	}
}
