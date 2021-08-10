package com.victor.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.CategoriaController;
import com.victor.controller.EntregadorController;
import com.victor.vo.CategoriaVO;
import com.victor.vo.EntregadorVO;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class GerenciarEntregadores extends JInternalFrame {

	
	private JTable table;
	private EntregadorController ec;
	private EntregadorVO e;
	private ArrayList<EntregadorVO> listEntregadores = new ArrayList<EntregadorVO>();
	private JLabel lblNDeEntregadores;
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_NOME = 1;
	private int selecao, selecaoOrdem;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarEntregadores frame = new GerenciarEntregadores();
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
	public GerenciarEntregadores() {
		setFrameIcon(new ImageIcon(GerenciarEntregadores.class.getResource("/com/victor/icons/icons8-man-to-man.png")));
		setClosable(true);
		setTitle("Gerenciar Entregadores");
		setBounds(100, 100, 553, 446);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(109, 169, 321, 236);
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
		btnEditar.setIcon(new ImageIcon(GerenciarEntregadores.class.getResource("/com/victor/icons/icons8-editar.png")));
		btnEditar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e1) {
				try {
					getItemSelecionadoTable();
					int cnf = JOptionPane.showConfirmDialog(null,
							"Deseja atualizar o cliente de ID: " + e.getId() + "| Nome: " + e.getNome() + "?",
							"Confirma", JOptionPane.YES_NO_OPTION);
					if (cnf == 0) {
						e.setNome(JOptionPane.showInputDialog(null, "Insira o nome", "Nome",
								JOptionPane.INFORMATION_MESSAGE));
						ec = new EntregadorController();
						ec.atualizarEntregadorController(e);
						attJTable();
					} else {

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);				
				}
				
				
			}
		});

		btnEditar.setBounds(109, 91, 123, 33);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setIcon(new ImageIcon(GerenciarEntregadores.class.getResource("/com/victor/icons/icons8-excluir.png")));
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
		btnExcluir.setBounds(307, 92, 123, 32);
		getContentPane().add(btnExcluir);

		JLabel lblGerenciadorDeClientes = new JLabel("Gerenciador de Entregadores");
		lblGerenciadorDeClientes.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGerenciadorDeClientes.setBounds(10, 11, 330, 33);
		getContentPane().add(lblGerenciadorDeClientes);

		lblNDeEntregadores = new JLabel("Nº de Categorias:");
		lblNDeEntregadores.setHorizontalAlignment(SwingConstants.CENTER);
		lblNDeEntregadores.setBounds(208, 144, 123, 14);
		getContentPane().add(lblNDeEntregadores);

		try {
			attJTable();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		ec = new EntregadorController();
		listEntregadores = ec.listarTodosEntregadorController();
		for (int i = 0; i < listEntregadores.size(); i++) {
			md.addRow(
					new Object[] { listEntregadores.get(i).getId(), listEntregadores.get(i).getNome() });
		}
		lblNDeEntregadores.setText("Nº de entregadores: " + listEntregadores.size());
	}

	public void getItemSelecionadoTable() {
		e = new EntregadorVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		int id = (Integer) model.getValueAt(linha, COLUNA_ID);
		e.setId(id);

		// Resgatar nome da linha
		String nome = (String) model.getValueAt(linha, COLUNA_NOME);
		e.setNome(nome);

	}

	public void confirmaExcluir() {
		getItemSelecionadoTable();
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir a categoria " + e.getNome() + "?",
				"Confirma", JOptionPane.YES_NO_OPTION);
		if (conf == 0) {
			ec.excluirEntregadorController(e);
		} else {

		}
	}

	}


