package com.victor.view;

import java.awt.Desktop;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.victor.controller.EncomendaController;
import com.victor.vo.EncomendaVO;

import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EntregasFinalizadas {

	private JFrame frmEntregasFinalizadas;
	private JTable table;
	private EncomendaVO e = new EncomendaVO();
	private EncomendaController ec = new EncomendaController();
	private ArrayList<EncomendaVO> listEncomendas = new ArrayList<EncomendaVO>();
	private Date dtSist = new Date();
	JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EntregasFinalizadas window = new EntregasFinalizadas();
					window.frmEntregasFinalizadas.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EntregasFinalizadas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEntregasFinalizadas = new JFrame();
		frmEntregasFinalizadas.setIconImage(Toolkit.getDefaultToolkit().getImage(EntregasFinalizadas.class.getResource("/com/victor/icons/icons8-selecionado-25.png")));
		frmEntregasFinalizadas.setTitle("Entregas finalizadas");
		frmEntregasFinalizadas.setBounds(100, 100, 870, 445);
		frmEntregasFinalizadas.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEntregasFinalizadas.getContentPane().setLayout(null);
		
		JLabel lblEntregasFinalizadas = new JLabel("Entregas finalizadas");
		lblEntregasFinalizadas.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblEntregasFinalizadas.setBounds(10, 11, 217, 34);
		frmEntregasFinalizadas.getContentPane().add(lblEntregasFinalizadas);
		
		JButton btnNewButton = new JButton("Relatório");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(comboBox.getSelectedItem() == "TXT") {
					gerarTXT();
				} else if(comboBox.getSelectedItem() == "PDF") {
					
				}
				
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(EntregasFinalizadas.class.getResource("/com/victor/icons/icons8-xml-de-miniatura-de-espaço-reservado.png")));
		btnNewButton.setBounds(729, 11, 115, 34);
		frmEntregasFinalizadas.getContentPane().add(btnNewButton);
		
		comboBox = new JComboBox<String>();
		comboBox.addItem("TXT");
		comboBox.addItem("PDF");
		comboBox.setBounds(604, 11, 115, 34);
		frmEntregasFinalizadas.getContentPane().add(comboBox);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 56, 834, 339);
		frmEntregasFinalizadas.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Enc.", "Cliente",  "Endere\u00E7o",
						"Entregador", "Data Entrega", "Hora Entrega", "Status" }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		
		JButton btnTeste = new JButton("teste");
		btnTeste.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {  
				
			}
		});
		btnTeste.setBounds(364, 11, 89, 23);
		frmEntregasFinalizadas.getContentPane().add(btnTeste);
		
		try {
			attJTable();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		ec = new EncomendaController();
			listEncomendas = ec.listarEncomendasStatusFinalizadaController();
		double subtotal = 0;
		for (int i = 0; i < listEncomendas.size(); i++) {
			subtotal = listEncomendas.get(i).getProduto().getValor() * listEncomendas.get(i).getQuantidadeProduto();

			md.addRow(new Object[] { listEncomendas.get(i).getIdEncomenda(),
					listEncomendas.get(i).getCliente().getNome(), listEncomendas.get(i).getEndereco(), listEncomendas.get(i).getEntregador().getNome(),
					listEncomendas.get(i).getDataEntrega(), listEncomendas.get(i).getHoraEntrega(), listEncomendas.get(i).getStatus() });
		}
	}
	
	public void gerarTXT() {
		SimpleDateFormat dtf = new SimpleDateFormat("dd-MM-yyyy HH-mm");
		String data = dtf.format(dtSist);
		FileWriter arq;
		try {
			arq = new FileWriter(SelectDir.dir+"\\RELATORIO_ENCOMENDAS-" + data + ".txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf("-------RELATÓRIO-DE-ENCOMENDAS-FINALIZADAS-------%n");
			gravarArq.printf("                      %n");
			for (int i = 0; i < listEncomendas.size(); i++) {
				gravarArq.printf("");
				gravarArq.printf("                      %n");
				gravarArq.printf("-ID ENCOMENDA: %3s%n-Nome cliente: %3s%n-CPF cliente: %3s%n-Nome entregador: %3s%n -Data entrega: %3s%n -Hora entrega: %3s%n -Endereço entrega: %3s%n -Status entrega: %3s%n",
						listEncomendas.get(i).getIdEncomenda(), listEncomendas.get(i).getCliente().getNome(), listEncomendas.get(i).getCliente().getCpf(),
						listEncomendas.get(i).getEntregador().getNome(), listEncomendas.get(i).getDataEntrega(), listEncomendas.get(i).getHoraEntrega(), listEncomendas.get(i).getEndereco(), listEncomendas.get(i).getStatus());
				gravarArq.printf("------------------------------------%n");
			}
			gravarArq.printf("                      %n");
			gravarArq.printf("----------------------------------%n");
			gravarArq.printf("RELATÓRIO EMITIDO EM " + data + "%n");
			gravarArq.printf("----------------------------------%n");
			gravarArq.printf("                      %n");
			gravarArq.printf("Versão 1.0%n");
			
			Desktop desk = Desktop.getDesktop();
			desk.open(new File("C:\\Users\\victo\\OneDrive\\Documentos\\Arquivos de teste java\\RELATORIO_ENCOMENDAS-" + data + ".txt"));

			arq.close();

			JOptionPane.showMessageDialog(null, "Os dados acima foram inseridos com sucesso em " + arq,
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel salvar os arquivos em .txt", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
