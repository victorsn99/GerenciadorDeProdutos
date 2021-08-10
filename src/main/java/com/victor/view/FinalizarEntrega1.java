package com.victor.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.EncomendaController;
import com.victor.vo.EncomendaVO;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JProgressBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FinalizarEntrega1 {

	private JFrame frmFinalizarEntrega;
	private JTable table;
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_CLIENTE = 1;
	private static final int COLUNA_ENDERECO = 2;
	private static final int COLUNA_ENTREGADOR = 3;
	private static final int COLUNA_DATA = 4;
	private static final int COLUNA_HORA = 5;
	private static final int COLUNA_STATUS = 6;
	private ArrayList<EncomendaVO> listEncomendas = new ArrayList<EncomendaVO>();
	ArrayList<EncomendaVO> encomendasNovaTabela = new ArrayList<EncomendaVO>();
	private EncomendaController ec = new EncomendaController();
	private EncomendaVO e = new EncomendaVO();
	double subtotal = 0;
	private Date dataSist = new Date();
	private JLabel lblSelecioneAsEncomendas;
	private SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalizarEntrega1 window = new FinalizarEntrega1();
					window.frmFinalizarEntrega.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinalizarEntrega1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinalizarEntrega = new JFrame();
		frmFinalizarEntrega.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				attJTable();
				lblSelecioneAsEncomendas.setText("Selecione as encomendas a serem entregues hoje: " + dtf.format(dataSist));
			}
		});
		frmFinalizarEntrega.setTitle("Finalizar Entrega - Escolha as encomendas");
		frmFinalizarEntrega.setIconImage(Toolkit.getDefaultToolkit().getImage(FinalizarEntrega1.class.getResource("/com/victor/icons/icons8-entrega-25.png")));
		frmFinalizarEntrega.setBounds(100, 100, 1019, 458);
		frmFinalizarEntrega.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmFinalizarEntrega.getContentPane().setLayout(null);

		lblSelecioneAsEncomendas = new JLabel("Selecione as encomendas a serem entregues hoje: dd/MM/aaaa");
		lblSelecioneAsEncomendas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSelecioneAsEncomendas.setBounds(10, 11, 883, 38);
		frmFinalizarEntrega.getContentPane().add(lblSelecioneAsEncomendas);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 97, 983, 246);
		frmFinalizarEntrega.getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Enc.", "Cliente", "Endere\u00E7o",
						"Entregador", "Data Entrega", "Hora Entrega", "Status" }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		JButton btnFinalizar = new JButton("Finalizar");
		btnFinalizar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try {
					getItemSelecionadoTable();
					if (e.getStatus().equalsIgnoreCase("Em Transporte")) {
					e.setStatus("Entrega Finalizada");
					ec.atualizarStatusEncomendaController(e);
					frmFinalizarEntrega.dispose();
					FinalizarEntrega2.main(null);
					} else {
						JOptionPane.showMessageDialog(null, "A entrega tem que estar EM TRANSPORTE para ser finalizada", "ERRO", JOptionPane.ERROR_MESSAGE);				
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);				
				}
				
				
			}
		});
		btnFinalizar.setIcon(new ImageIcon(FinalizarEntrega1.class.getResource("/com/victor/icons/icons8-verified-account.png")));
		btnFinalizar.setBounds(812, 354, 181, 54);
		frmFinalizarEntrega.getContentPane().add(btnFinalizar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FinalizarEntrega1.class.getResource("/com/victor/icons/icons8-parte-3.png")));
		lblNewLabel.setBounds(961, 11, 32, 38);
		frmFinalizarEntrega.getContentPane().add(lblNewLabel);

	}

	protected void attJTable() {
		SimpleDateFormat dtfAtual = new SimpleDateFormat("yyyy-MM-dd");
		String dt = dtfAtual.format(dataSist);
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		ec = new EncomendaController();
		System.out.println(dt);
		listEncomendas = ec.listarEncomendasDataAtualController(dt);
		subtotal = 0;
		for (int i = 0; i < listEncomendas.size(); i++) {
			String dtTable = dtf.format(listEncomendas.get(i).getDataEntrega());

			md.addRow(
					new Object[] { listEncomendas.get(i).getIdEncomenda(), listEncomendas.get(i).getCliente().getNome(),
						    listEncomendas.get(i).getEndereco(),
							listEncomendas.get(i).getEntregador().getNome(), dtTable,
							listEncomendas.get(i).getHoraEntrega(), listEncomendas.get(i).getStatus() });
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
			String c = (String) model.getValueAt(linha, COLUNA_CLIENTE);
			e.getCliente().setNome(c);

			// Resgatar nome da linha
			String end = (String) model.getValueAt(linha, COLUNA_ENDERECO);
			e.setEndereco(end);
			// Resgatar nome da linha
			String ent = (String) model.getValueAt(linha, COLUNA_ENTREGADOR);
			e.getEntregador().setNome(ent);

			// Resgatar nome da linha
			String dt = (String) model.getValueAt(linha, COLUNA_DATA);
			SimpleDateFormat dtf = new SimpleDateFormat("yyyy-MM-dd");
		    Date dataEntrega = null;
			try {
				dataEntrega = dtf.parse(dt);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.setDataEntrega(dataEntrega);

			// Resgatar nome da linha
			String hr = (String) model.getValueAt(linha, COLUNA_HORA);
			e.setHoraEntrega(hr);

			// Resgatar nome da linha
			String status = (String) model.getValueAt(linha, COLUNA_STATUS);
			e.setStatus(status);

		}

	}

	

