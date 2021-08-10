package com.victor.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import com.victor.controller.EncomendaController;
import com.victor.controller.ProdutoController;
import com.victor.controller.Produtos_EncomendasController;
import com.victor.vo.EncomendaVO;
import com.victor.vo.ProdutoVO;
import com.victor.vo.Produtos_EncomendasVO;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JSeparator;
import java.awt.event.WindowFocusListener;

public class AtualizarProdutosEncomendas {

	private JFrame frmCadEncomenda;
	private JTextField tfQtd;
	private ArrayList<ProdutoVO> listProdutos = new ArrayList<ProdutoVO>();
	private ArrayList<Produtos_EncomendasVO> listProdutosEncomendas = new ArrayList<Produtos_EncomendasVO>();
	private ArrayList<EncomendaVO> listEncomendas = new ArrayList<EncomendaVO>();
	private Produtos_EncomendasController pec = new Produtos_EncomendasController();
	private Produtos_EncomendasVO pevo = new Produtos_EncomendasVO();
	private ProdutoController pc = new ProdutoController();
	private EncomendaVO evo = new EncomendaVO();
	private JComboBox<String> cbProduto;
	private EncomendaVO e = new EncomendaVO();
	private EncomendaController ec = new EncomendaController();
	private int id;
	private JTable table;
	private JLabel lblIdencomenda;
	private int max = 0;
	private final int COLUNA_NOME = 0;
	private final int COLUNA_QTD = 1;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarProdutosEncomendas window = new AtualizarProdutosEncomendas(id);
					window.frmCadEncomenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AtualizarProdutosEncomendas(int id) {
		setId(id);
		System.out.println("GETID: " + getId());
		System.out.println("ID: "+id);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCadEncomenda = new JFrame();
		frmCadEncomenda.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				listProdutosEncomendas = pec.listarTodosProdutos_EncomendasController(getId());
				lblIdencomenda.setText("Encomenda Nº: " + getId());
				attJTable();
			}
		});
		frmCadEncomenda.setIconImage(Toolkit.getDefaultToolkit().getImage(AtualizarProdutosEncomendas.class.getResource("/com/victor/icons/icons8-product.png")));
		frmCadEncomenda.setTitle("Att. Produtos");
		frmCadEncomenda.setBounds(100, 100, 237, 566);
		frmCadEncomenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadEncomenda.getContentPane().setLayout(null);
		
		tfQtd = new JTextField();
		tfQtd.setHorizontalAlignment(SwingConstants.CENTER);
		tfQtd.setColumns(10);
		tfQtd.setBounds(174, 61, 38, 20);
		frmCadEncomenda.getContentPane().add(tfQtd);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setIcon(new ImageIcon(AtualizarProdutosEncomendas.class.getResource("/com/victor/icons/icons8-add.png")));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e1) {
				try {
					pevo = new Produtos_EncomendasVO();
					pevo.getEncomenda().setIdEncomenda(getId());
					pevo.getProduto().setIdProduto(listProdutos.get(cbProduto.getSelectedIndex()).getIdProduto());
					pevo.setQtdProduto(Integer.parseInt(tfQtd.getText()));
					pec.cadastrarProdutoController(pevo);
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Insira apenas números em [QTD]", "ERRO", JOptionPane.ERROR_MESSAGE);
				} finally {
					attJTable();
				}
				
				
			}
		});
		btnNewButton.setBounds(10, 92, 202, 31);
		frmCadEncomenda.getContentPane().add(btnNewButton);
		
		JLabel lblNome = new JLabel("Produto");
		lblNome.setBounds(10, 36, 46, 14);
		frmCadEncomenda.getContentPane().add(lblNome);
		
		JLabel lblQuantidade = new JLabel("Qtd.");
		lblQuantidade.setBounds(174, 36, 38, 14);
		frmCadEncomenda.getContentPane().add(lblQuantidade);
		
		cbProduto = new JComboBox<String>();
		cbProduto.setBounds(10, 61, 146, 20);
		frmCadEncomenda.getContentPane().add(cbProduto);
		
		lblIdencomenda = new JLabel("IDENCOMENDA");
		lblIdencomenda.setHorizontalAlignment(SwingConstants.CENTER);
		lblIdencomenda.setBounds(10, 11, 202, 14);
		frmCadEncomenda.getContentPane().add(lblIdencomenda);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 244, 202, 229);
		frmCadEncomenda.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] {"Produto", "Qtd." }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);
		
		JButton btnNewButton_1 = new JButton("Concluído");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				MostrarProdutosEncomenda mpe = new MostrarProdutosEncomenda(getId());
				MostrarProdutosEncomenda.main(null, getId());
				mpe.attJTable();
				frmCadEncomenda.dispose();
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(AtualizarProdutosEncomendas.class.getResource("/com/victor/icons/icons8-selecionado-25.png")));
		btnNewButton_1.setBounds(10, 484, 202, 31);
		frmCadEncomenda.getContentPane().add(btnNewButton_1);
		
		JButton btnExcEsp = new JButton("Excluir específico");
		btnExcEsp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					confirmaExcluir();
					attJTable();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnExcEsp.setIcon(new ImageIcon(AtualizarProdutosEncomendas.class.getResource("/com/victor/icons/icons8-excluir.png")));
		btnExcEsp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnExcEsp.setBounds(10, 147, 202, 31);
		frmCadEncomenda.getContentPane().add(btnExcEsp);
		
		JButton btnExcluirTodos = new JButton("Excluir TODOS");
		btnExcluirTodos.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					confirmaExcluirTodos();
					attJTable();
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnExcluirTodos.setIcon(new ImageIcon(AtualizarProdutosEncomendas.class.getResource("/com/victor/icons/icons8-remover.png")));
		btnExcluirTodos.setBounds(10, 189, 202, 31);
		frmCadEncomenda.getContentPane().add(btnExcluirTodos);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 231, 202, 2);
		frmCadEncomenda.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 134, 201, 2);
		frmCadEncomenda.getContentPane().add(separator_1);
		
		try {
			attJCBP();
			attJTable();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected void attJCBP() {
		listProdutos = pc.listarTodosProdutosController();
		System.out.println("Tamanho: " + listProdutos.size());
		
		for (int i = 0; i < listProdutos.size(); i++) {
			cbProduto.addItem(listProdutos.get(i).getNome());
		}
	}
	
	public void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		lblIdencomenda.setText("ID Encomenda: " + id);
		for (int i = 0; i < listProdutosEncomendas.size(); i++) {
			md.addRow(new Object[] { listProdutosEncomendas.get(i).getProduto().getNome(), listProdutosEncomendas.get(i).getQtdProduto() });
		}
	}
	
	public void getItemSelecionadoTable() {
		pevo = new Produtos_EncomendasVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		String nome = (String) model.getValueAt(linha, COLUNA_NOME);
	    pevo.getProduto().setNome(nome);

		// Resgatar nome da linha
		int qtd = (Integer) model.getValueAt(linha, COLUNA_QTD);
		pevo.setQtdProduto(qtd);;
		

	}
	
	public void confirmaExcluir() {
		getItemSelecionadoTable();
		pevo.getEncomenda().setIdEncomenda(getId());
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir " + pevo.getProduto().getNome() + "?",
				"Confirma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (conf == 0) {
			pec.excluirProdutoController(pevo);
		} else {

		}
	}
	
	public void confirmaExcluirTodos() {
		pevo.getEncomenda().setIdEncomenda(getId());
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir TODOS os produtos?",
				"Confirma", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (conf == 0) {
			pec.excluirTodosProdutosController(pevo);
		} else {

		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	}


