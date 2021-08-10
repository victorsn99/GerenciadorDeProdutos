package com.victor.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.CategoriaController;
import com.victor.controller.ClienteController;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ClienteVO;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class GerenciarCategorias extends JInternalFrame {
	private JTable table;
	private JTextField tfPesquisa;
	private CategoriaController cc;
	private CategoriaVO c;
	private ArrayList<CategoriaVO> listCategorias = new ArrayList<CategoriaVO>();
	private JComboBox<String> cbOrdem;
	private JComboBox<String> cbPesquisa;
	private JLabel lblNDeClientes;
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_NOME = 1;
	private int selecao;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarCategorias frame = new GerenciarCategorias();
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
	/**
	 * 
	 */
	public GerenciarCategorias() {
		setFrameIcon(new ImageIcon(GerenciarCategorias.class.getResource("/com/victor/icons/icons8-cardápio.png")));
		setClosable(true);
		setTitle("Gerenciar Categorias");
		setBounds(100, 100, 620, 412);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(137, 135, 322, 236);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome" }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(GerenciarCategorias.class.getResource("/com/victor/icons/icons8-editar.png")));
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					getItemSelecionadoTable();
					int cnf = JOptionPane.showConfirmDialog(null,
							"Deseja atualizar o cliente de ID: " + c.getIdCategoria() + "| Nome: " + c.getNomeCategoria() + "?",
							"Confirma", JOptionPane.YES_NO_OPTION);
					if (cnf == 0) {
						c.setNomeCategoria(JOptionPane.showInputDialog(null, "Insira o nome", "Nome",
								JOptionPane.INFORMATION_MESSAGE));
						cc = new CategoriaController();
						cc.atualizarCategoriaController(c);
						attJTable();
					} else {

					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});

		btnEditar.setBounds(10, 92, 117, 32);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(GerenciarCategorias.class.getResource("/com/victor/icons/icons8-excluir.png")));
		btnExcluir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try {
					confirmaExcluir();
					attJTable();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnExcluir.setBounds(137, 92, 121, 32);
		getContentPane().add(btnExcluir);

		tfPesquisa = new JTextField();
		tfPesquisa.setBounds(348, 93, 149, 20);
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (cbPesquisa.getSelectedItem() == "Nome") {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					CategoriaVO categoriaVO = new CategoriaVO();
					categoriaVO.setNomeCategoria(tfPesquisa.getText());
					ArrayList<CategoriaVO> listCategoriasNome = cc.pesquisarCategoriaPorNomeController(categoriaVO);
					md.setNumRows(0);
					for (int i = 0; i < listCategoriasNome.size(); i++) {
						md.addRow(new Object[] { listCategoriasNome.get(i).getIdCategoria(),
								listCategoriasNome.get(i).getNomeCategoria()
								});
					}
				} else if (cbPesquisa.getSelectedItem() == "ID") {
					if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57) {
						DefaultTableModel md = (DefaultTableModel) table.getModel();
						CategoriaVO categoriaVO = new CategoriaVO();
						categoriaVO.setIdCategoria(Integer.parseInt(tfPesquisa.getText()));
						ArrayList<CategoriaVO> listCategoriasId = cc.pesquisarCategoriaPorIdController(categoriaVO);
						md.setNumRows(0);
						for (int i = 0; i < listCategoriasId.size(); i++) {
							md.addRow(new Object[] { listCategoriasId.get(i).getIdCategoria(),
									listCategoriasId.get(i).getNomeCategoria()});
						}
					} else {
						if (e.getKeyCode() != 8 ) {
						JOptionPane.showMessageDialog(null, "Apenas números", "Erro", JOptionPane.ERROR_MESSAGE);
						tfPesquisa.setText("");
						}
					}

				}
				if (e.getKeyCode() == 8) {
					tfPesquisa.setText("");
					attJTable();
				}
			}
		});
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPesquisar.setBounds(268, 92, 70, 14);
		getContentPane().add(lblPesquisar);

		cbOrdem = new JComboBox<String>();
		cbOrdem.addItem("ID");
		cbOrdem.addItem("Nome");
		cbOrdem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				attJTable();

			}
		});
		cbOrdem.setBounds(109, 61, 149, 20);
		getContentPane().add(cbOrdem);

		JLabel lblGerenciadorDeClientes = new JLabel("Gerenciador de Categorias");
		lblGerenciadorDeClientes.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGerenciadorDeClientes.setBounds(10, 11, 330, 33);
		getContentPane().add(lblGerenciadorDeClientes);

		lblNDeClientes = new JLabel("Nº de Categorias:");
		lblNDeClientes.setBounds(417, 25, 177, 14);
		getContentPane().add(lblNDeClientes);

		JLabel lblOrdenarPor = new JLabel("Ordenar por: ");
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOrdenarPor.setBounds(10, 64, 89, 14);
		getContentPane().add(lblOrdenarPor);

		cbPesquisa = new JComboBox<String>();

		cbPesquisa.addItem("ID");
		cbPesquisa.addItem("Nome");
		cbPesquisa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cbPesquisa.getSelectedItem() == "ID") {
					selecao = 1;
				} else if (cbPesquisa.getSelectedItem() == "Nome") {
					selecao = 2;
				}

			}
		});
		cbPesquisa.setBounds(507, 93, 85, 20);
		getContentPane().add(cbPesquisa);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(268, 55, 5, 69);
		getContentPane().add(separator);

		try {
			attJTable();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		cc = new CategoriaController();
		if (cbOrdem.getSelectedItem() == "ID") {
			listCategorias = cc.listarTodasCategoriasController();
		} else if (cbOrdem.getSelectedItem() == "Nome") {
			listCategorias = cc.listarCategoriaOrdenadoNomeController();
		}
		for (int i = 0; i < listCategorias.size(); i++) {
			md.addRow(
					new Object[] { listCategorias.get(i).getIdCategoria(), listCategorias.get(i).getNomeCategoria() });
		}
		lblNDeClientes.setText("Nº de categorias: " + listCategorias.size());
	}

	public void getItemSelecionadoTable() {
		c = new CategoriaVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		int id = (Integer) model.getValueAt(linha, COLUNA_ID);
		c.setIdCategoria(id);

		// Resgatar nome da linha
		String nome = (String) model.getValueAt(linha, COLUNA_NOME);
		c.setNomeCategoria(nome);

	}

	public void confirmaExcluir() {
		getItemSelecionadoTable();
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir a categoria " + c.getNomeCategoria() + "?",
				"Confirma", JOptionPane.YES_NO_OPTION);
		if (conf == 0) {
			cc.excluirCategoriaController(c);
		} else {

		}
	}

	public JComboBox getCbOrdem() {
		return cbOrdem;
	}

	public JComboBox getCbPesquisa() {
		return cbPesquisa;
	}

	public JLabel getLblNDeClientes() {
		return lblNDeClientes;
	}
}
