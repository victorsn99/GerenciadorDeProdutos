package com.victor.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
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
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.ClienteController;
import com.victor.vo.ClienteVO;
import com.victor.vo.ProdutoVO;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class GerenciarClientes extends JInternalFrame {
	private JTable table;
	private JTextField tfPesquisa;
	private ClienteVO c;
	private ClienteController cc;
	private ArrayList<ClienteVO> listClientes;
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_NOME = 1;
	private static final int COLUNA_CPF = 2;
	private static final int COLUNA_TEL = 3;
	private static final int COLUNA_END = 4;
	private JComboBox<String> cbOrdem;
	private int contClientes, selecao;
	private JLabel lblNDeClientes;
	private JComboBox<String> cbPesquisa;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarClientes frame = new GerenciarClientes();
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
	public GerenciarClientes() {
		setFrameIcon(new ImageIcon(GerenciarClientes.class.getResource("/com/victor/icons/icons8-person.png")));
		setClosable(true);
		setTitle("Gerenciar Clientes");
		setBounds(100, 100, 612, 412);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 135, 576, 236);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nome", "CPF", "Telefone", "Endereço" }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(GerenciarClientes.class.getResource("/com/victor/icons/icons8-editar.png")));
		btnEditar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					getItemSelecionadoTable();
					int cnf = JOptionPane.showConfirmDialog(null,
							"Deseja atualizar o cliente de ID: " + c.getIdCliente() + "| Nome: " + c.getNome() + "?",
							"Confirma", JOptionPane.YES_NO_OPTION);
					if (cnf == 0) {
						c.setNome(JOptionPane.showInputDialog(null, "Insira o nome", "Nome",
								JOptionPane.INFORMATION_MESSAGE));
						c.setCpf(JOptionPane.showInputDialog(null, "Insira o cpf", "CPF", JOptionPane.INFORMATION_MESSAGE));
						c.setTelefone(JOptionPane.showInputDialog(null, "Insira o telefone", "Telefone",
								JOptionPane.INFORMATION_MESSAGE));
						c.setEndereco(JOptionPane.showInputDialog(null, "Insira o Endereço", "Endereço",
								JOptionPane.INFORMATION_MESSAGE));
						cc = new ClienteController();
						cc.atualizarClienteController(c);
						attJTable();
					} else {

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnEditar.setBounds(10, 92, 112, 32);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(GerenciarClientes.class.getResource("/com/victor/icons/icons8-excluir.png")));
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				try {
					confirmaExcluir();
					attJTable();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnExcluir.setBounds(132, 92, 107, 32);
		getContentPane().add(btnExcluir);

		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (cbPesquisa.getSelectedItem() == "Nome") {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					ClienteVO clienteVO = new ClienteVO();
					clienteVO.setNome(tfPesquisa.getText());
					ArrayList<ClienteVO> listClientesNome = cc.pesquisarClientePorNomeController(clienteVO);
					md.setNumRows(0);
					for (int i = 0; i < listClientesNome.size(); i++) {
						md.addRow(new Object[] { listClientesNome.get(i).getIdCliente(),
								listClientesNome.get(i).getNome(), listClientesNome.get(i).getCpf(),
								listClientesNome.get(i).getTelefone(), listClientesNome.get(i).getEndereco() });
					}
				} else if (cbPesquisa.getSelectedItem() == "CPF") {
					if (e.getKeyCode() >= 48 && e.getKeyCode() <= 57 || e.getKeyCode() == 8) {
						DefaultTableModel md = (DefaultTableModel) table.getModel();
						ClienteVO clienteVO = new ClienteVO();
						clienteVO.setCpf(tfPesquisa.getText());
						ArrayList<ClienteVO> listClientesNome = cc.pesquisarClientePorCpfController(clienteVO);
						md.setNumRows(0);
						for (int i = 0; i < listClientesNome.size(); i++) {
							md.addRow(new Object[] { listClientesNome.get(i).getIdCliente(),
									listClientesNome.get(i).getNome(), listClientesNome.get(i).getCpf(),
									listClientesNome.get(i).getTelefone(), listClientesNome.get(i).getEndereco() });
						}
					} else {
						if (e.getKeyCode() == 8) {
							tfPesquisa.setText("");
							attJTable();
						}
						JOptionPane.showMessageDialog(null, "Apenas números", "Erro", JOptionPane.ERROR_MESSAGE);
						tfPesquisa.setText("");
					}

				}
				if (e.getKeyCode() == 8) {
					tfPesquisa.setText("");
					attJTable();
				}
			}
		});
		tfPesquisa.setBounds(340, 92, 149, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setHorizontalAlignment(SwingConstants.TRAILING);
		lblPesquisar.setBounds(267, 95, 70, 14);
		getContentPane().add(lblPesquisar);

		cbOrdem = new JComboBox<String>();
		cbOrdem.setSelectedIndex(-1);
		cbOrdem.addItem("ID");
		cbOrdem.addItem("Nome");
		cbOrdem.addItem("CPF");
		cbOrdem.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				attJTable();

			}
		});
		cbOrdem.setBounds(132, 61, 107, 20);
		getContentPane().add(cbOrdem);

		JLabel lblGerenciadorDeClientes = new JLabel("Gerenciador de Clientes");
		lblGerenciadorDeClientes.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGerenciadorDeClientes.setBounds(10, 11, 268, 33);
		getContentPane().add(lblGerenciadorDeClientes);

		lblNDeClientes = new JLabel("Nº de Clientes:");
		lblNDeClientes.setBounds(350, 25, 177, 14);
		getContentPane().add(lblNDeClientes);

		JLabel lblOrdenarPor = new JLabel("Ordenar por:");
		lblOrdenarPor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOrdenarPor.setBounds(10, 64, 89, 14);
		getContentPane().add(lblOrdenarPor);

		cbPesquisa = new JComboBox<String>();
		cbPesquisa.addItem("Nome");
		cbPesquisa.addItem("CPF");
		cbPesquisa.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
			}
		});
		cbPesquisa.setBounds(499, 92, 87, 20);
		getContentPane().add(cbPesquisa);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(249, 61, 8, 63);
		getContentPane().add(separator);

		// listar na abertura

		try {
			attJTable();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// metodos --------------------------------------------------------

	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		cc = new ClienteController();
		if (cbOrdem.getSelectedIndex() == 0) {
			listClientes = cc.listarTodosClientesController();
		} else if (cbOrdem.getSelectedIndex() == 1) {
			listClientes = cc.listarClienteOrdenadoNomeController();
		} else if (cbOrdem.getSelectedIndex() == 2) {
			listClientes = cc.listarClienteOrdenadoCpfController();
		}
		for (int i = 0; i < listClientes.size(); i++) {
			md.addRow(new Object[] { listClientes.get(i).getIdCliente(), listClientes.get(i).getNome(),
					listClientes.get(i).getCpf(), listClientes.get(i).getTelefone(), listClientes.get(i).getEndereco() });
		}
		lblNDeClientes.setText("Nº de clientes: " + listClientes.size());
	}

	public void getItemSelecionadoTable() {
		c = new ClienteVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		int id = (Integer) model.getValueAt(linha, COLUNA_ID);
		c.setIdCliente(id);

		// Resgatar nome da linha
		String nome = (String) model.getValueAt(linha, COLUNA_NOME);
		c.setNome(nome);

		// Resgatar valor da linha
		String cpf = (String) model.getValueAt(linha, COLUNA_CPF);
		c.setCpf(cpf);

		// Resgatar valor da linha
		String tel = (String) model.getValueAt(linha, COLUNA_TEL);
		c.setTelefone(tel);

		// Resgatar valor da linha
		String end = (String) model.getValueAt(linha, COLUNA_END);
		c.setTelefone(end);

	}

	public void confirmaExcluir() {
		getItemSelecionadoTable();
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir o produto " + c.getNome() + "?", "Confirma",
				JOptionPane.YES_NO_OPTION);
		if (conf == 0) {
			cc.excluirClienteController(c);
		} else {

		}
	}

	public JComboBox getCb() {
		return cbOrdem;
	}

	public JLabel getLblNDeClientes() {
		return lblNDeClientes;
	}

	public JComboBox getCbPesquisa() {
		return cbPesquisa;
	}
}
