package com.victor.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.EncomendaController;
import com.victor.vo.EncomendaVO;

import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

public class AtualizarStatusEncomenda {

	private JFrame frmAtualizarStatus;
	private EncomendaVO e = new EncomendaVO();
	private EncomendaController ec = new EncomendaController();
	private JComboBox<String> comboBox;
	private ArrayList<EncomendaVO> listEncomendas = new ArrayList<EncomendaVO>();
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_CLIENTE = 1;
	private JTable table;
	private JLabel lblAtualizarStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarStatusEncomenda window = new AtualizarStatusEncomenda();
					window.frmAtualizarStatus.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AtualizarStatusEncomenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtualizarStatus = new JFrame();
		frmAtualizarStatus.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				e.getIdEncomenda();
			}
		});
		frmAtualizarStatus.setIconImage(Toolkit.getDefaultToolkit().getImage(AtualizarStatusEncomenda.class.getResource("/com/victor/icons/icons8-actualizar.png")));
		frmAtualizarStatus.setTitle("Atualizar Status");
		frmAtualizarStatus.setBounds(100, 100, 317, 389);
		frmAtualizarStatus.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAtualizarStatus.getContentPane().setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("Em Produção");
		comboBox.addItem("Separado para Entrega");
		comboBox.addItem("Em Transporte");
		comboBox.setBounds(10, 70, 182, 20);
		frmAtualizarStatus.getContentPane().add(comboBox);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(10, 50, 182, 14);
		frmAtualizarStatus.getContentPane().add(lblStatus);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					getItemSelecionadoTable();
					e.setStatus(comboBox.getSelectedItem().toString());
					ec.atualizarStatusEncomendaController(e);
					attJTable();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione uma encomenda da tabela", "Erro", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnConfirmar.setBounds(205, 69, 89, 23);
		frmAtualizarStatus.getContentPane().add(btnConfirmar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 106, 281, 234);
		frmAtualizarStatus.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Enc.", "Status" }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);
		
		lblAtualizarStatus = new JLabel("Atualizar Status");
		lblAtualizarStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAtualizarStatus.setBounds(10, 11, 201, 28);
		frmAtualizarStatus.getContentPane().add(lblAtualizarStatus);
		
		try {
			attJTable();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public JComboBox getComboBox() {
		return comboBox;
	}
	
	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		ec = new EncomendaController();
			listEncomendas = ec.listarTodosEncomendasController();
		for (int i = 0; i < listEncomendas.size(); i++) {

			md.addRow(new Object[] { listEncomendas.get(i).getIdEncomenda(), listEncomendas.get(i).getStatus() });
		}
		
	}
	
	public void getItemSelecionadoTable() {
		e = new EncomendaVO();

		// Resgatar linhas da tabela
		DefaultTableModel model;
		model = (DefaultTableModel) table.getModel();
		int linha = table.getSelectedRow();

		// Resgatar código da linha selecionada
		int id = (Integer) model.getValueAt(linha, COLUNA_ID);
		e.setIdEncomenda(id);

		// Resgatar nome da linha
		String s = (String) model.getValueAt(linha, COLUNA_CLIENTE);
		e.setStatus(s);
		
	}
}
