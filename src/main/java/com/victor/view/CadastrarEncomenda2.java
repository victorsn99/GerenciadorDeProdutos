package com.victor.view;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

public class CadastrarEncomenda2 {

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
	private JTextField tfid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEncomenda2 window = new CadastrarEncomenda2();
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
	public CadastrarEncomenda2() {
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
				evo = ec.listarUltimaEncomendaController();
				setId(evo.getIdEncomenda());
				//listProdutosEncomendas = pec.listarTodosProdutos_EncomendasController(evo.getIdEncomenda());
				lblIdencomenda.setText("Encomenda Nº: " + evo.getIdEncomenda());
				attJTable();
			}
		});
		frmCadEncomenda.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarEncomenda2.class.getResource("/com/victor/icons/icons8-product.png")));
		frmCadEncomenda.setTitle("Cad. Encomenda");
		frmCadEncomenda.setBounds(100, 100, 242, 468);
		frmCadEncomenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmCadEncomenda.getContentPane().setLayout(null);
		
		tfQtd = new JTextField();
		tfQtd.setHorizontalAlignment(SwingConstants.CENTER);
		tfQtd.setColumns(10);
		tfQtd.setBounds(174, 61, 38, 20);
		frmCadEncomenda.getContentPane().add(tfQtd);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setIcon(new ImageIcon(CadastrarEncomenda2.class.getResource("/com/victor/icons/icons8-add.png")));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e1) {
				try {
					pevo = new Produtos_EncomendasVO();
					pevo.getEncomenda().setIdEncomenda(getId());
					pevo.getProduto().setIdProduto(listProdutos.get(cbProduto.getSelectedIndex()).getIdProduto());
					pevo.setQtdProduto(Integer.parseInt(tfQtd.getText()));
					pec.cadastrarProdutoController(pevo);;
					attJTable();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Apenas números em [Qtd]", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		btnNewButton.setBounds(10, 100, 202, 31);
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
		scrollPane.setBounds(10, 150, 202, 229);
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
				frmCadEncomenda.dispose();
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(CadastrarEncomenda2.class.getResource("/com/victor/icons/icons8-selecionado-25.png")));
		btnNewButton_1.setBounds(10, 390, 202, 31);
		frmCadEncomenda.getContentPane().add(btnNewButton_1);
		
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
	
	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		listProdutosEncomendas = pec.listarTodosProdutos_EncomendasController(evo.getIdEncomenda());
		lblIdencomenda.setText("ID Encomenda: " + id);
		for (int i = 0; i < listProdutosEncomendas.size(); i++) {
			md.addRow(new Object[] { listProdutosEncomendas.get(i).getProduto().getNome(), listProdutosEncomendas.get(i).getQtdProduto() });
		}

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	}


