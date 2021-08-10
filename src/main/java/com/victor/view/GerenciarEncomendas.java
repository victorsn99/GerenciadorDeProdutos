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
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.victor.controller.EncomendaController;
import com.victor.controller.Produtos_EncomendasController;
import com.victor.vo.EncomendaVO;
import com.victor.vo.Produtos_EncomendasVO;

public class GerenciarEncomendas extends JInternalFrame {
	private JTable table;
	private JTextField tfPesquisa;
	private JComboBox<String> cbPesquisa;
	private JComboBox<String> cbOrdem;
	private ArrayList<EncomendaVO> listEncomendas = new ArrayList<EncomendaVO>();
	private ArrayList<Produtos_EncomendasVO> listProdutosEncomendas = new ArrayList<Produtos_EncomendasVO>();
	private EncomendaController ec = new EncomendaController();
	private Produtos_EncomendasController epc = new Produtos_EncomendasController();
	private Produtos_EncomendasVO ep = new Produtos_EncomendasVO();
	private EncomendaVO e = new EncomendaVO();
	private JLabel lblNDeClientes;
	private JDesktopPane desktopPane;
	Desktop desk = Desktop.getDesktop();
	private static final int COLUNA_ID = 0;
	private static final int COLUNA_CLIENTE = 1;
	private static final int COLUNA_ENDERECO = 2;
	private static final int COLUNA_ENTREGADOR = 3;
	private static final int COLUNA_DATA = 4;
	private static final int COLUNA_HORA = 5;
	private static final int COLUNA_STATUS = 6;
	int selecao = 0;
	private JComboBox<String> comboBox;
	private Date dtSist = new Date();
	private String data, dataTitulo;
	private JComboBox<String> cbRelatorio;
	private int idEncomenda = 0;
	boolean atttable = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GerenciarEncomendas frame = new GerenciarEncomendas();
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
	public GerenciarEncomendas() {
		setFrameIcon(new ImageIcon(GerenciarEncomendas.class.getResource("/com/victor/icons/icons8-product.png")));
		setClosable(true);
		setTitle("Gerenciar Encomendas");
		setBounds(100, 100, 1029, 534);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 236, 993, 257);
		getContentPane().add(scrollPane);

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID Enc.", "Cliente", "Endere\u00E7o",
				"Entregador", "Data Entrega", "Hora Entrega", "Status" }));
		table.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
		table.getColumnModel().getColumn(0).setPreferredWidth(5);
		table.getColumnModel().getColumn(1).setPreferredWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(240);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		table.setToolTipText("");
		scrollPane.setViewportView(table);

		JButton btnEditar = new JButton("Editar");
		btnEditar.setIcon(new ImageIcon(GerenciarEncomendas.class.getResource("/com/victor/icons/icons8-editar.png")));
		btnEditar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e1) {
				try {
					getItemSelecionadoTable();
					int cnf = JOptionPane.showConfirmDialog(null,
							"Deseja atualizar a encomenda de ID: " + e.getIdEncomenda() + "?", "Confirma",
							JOptionPane.YES_NO_OPTION);
					System.out.println("GERENCIADOR ENCOMENDA: " +  e.getIdEncomenda());
					setIdEncomenda(e.getIdEncomenda());
					System.out.println(getIdEncomenda());
					if (cnf == 0) {
						AtualizarEncomenda1.main(null, e.getIdEncomenda());
					} else {

					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				

			}
		});
		btnEditar.setBounds(10, 123, 108, 31);
		getContentPane().add(btnEditar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir
				.setIcon(new ImageIcon(GerenciarEncomendas.class.getResource("/com/victor/icons/icons8-excluir.png")));
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
		btnExcluir.setBounds(128, 123, 108, 31);
		getContentPane().add(btnExcluir);

		tfPesquisa = new JTextField();
		tfPesquisa.addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyReleased(KeyEvent arg0) {
				if (selecao == 1) {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					EncomendaVO encomendaVO = new EncomendaVO();
					encomendaVO.getCliente().setNome(tfPesquisa.getText());
					ArrayList<EncomendaVO> listEncomendas = ec.pesquisarEncomendasClienteController(encomendaVO);
					md.setNumRows(0);
					double subtotal = 0.0;
					for (int i = 0; i < listEncomendas.size(); i++) {
						subtotal = listEncomendas.get(i).getProduto().getValor()
								* listEncomendas.get(i).getQuantidadeProduto();

						md.addRow(new Object[] { listEncomendas.get(i).getIdEncomenda(),
								listEncomendas.get(i).getCliente().getNome(),
								listEncomendas.get(i).getEndereco(), listEncomendas.get(i).getEntregador().getNome(),
								listEncomendas.get(i).getDataEntrega(), listEncomendas.get(i).getHoraEntrega(),
								listEncomendas.get(i).getStatus() });
					}

				} else if (selecao == 2) {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					EncomendaVO encomendaVO = new EncomendaVO();
					encomendaVO.setDataEntrega(java.sql.Date.valueOf(tfPesquisa.getText()));
					ArrayList<EncomendaVO> listEncomendas = ec.pesquisarEncomendasDataController(encomendaVO);
					md.setNumRows(0);
					double subtotal = 0.0;
					for (int i = 0; i < listEncomendas.size(); i++) {
						subtotal = listEncomendas.get(i).getProduto().getValor()
								* listEncomendas.get(i).getQuantidadeProduto();

						md.addRow(new Object[] { listEncomendas.get(i).getIdEncomenda(),
								listEncomendas.get(i).getCliente().getNome(),
								listEncomendas.get(i).getEndereco(), listEncomendas.get(i).getEntregador().getNome(),
								listEncomendas.get(i).getDataEntrega(), listEncomendas.get(i).getHoraEntrega(),
								listEncomendas.get(i).getStatus() });
					}
				} else if (selecao == 3) {
					DefaultTableModel md = (DefaultTableModel) table.getModel();
					EncomendaVO encomendaVO = new EncomendaVO();
					encomendaVO.getEntregador().setNome(tfPesquisa.getText());
					ArrayList<EncomendaVO> listEncomendas = ec.pesquisarEncomendasEntregadorController(encomendaVO);
					md.setNumRows(0);
					double subtotal = 0.0;
					for (int i = 0; i < listEncomendas.size(); i++) {
						subtotal = listEncomendas.get(i).getProduto().getValor()
								* listEncomendas.get(i).getQuantidadeProduto();

						md.addRow(new Object[] { listEncomendas.get(i).getIdEncomenda(),
								listEncomendas.get(i).getCliente().getNome(),
								listEncomendas.get(i).getEndereco(), listEncomendas.get(i).getEntregador().getNome(),
								listEncomendas.get(i).getDataEntrega(), listEncomendas.get(i).getHoraEntrega(),
								listEncomendas.get(i).getStatus() });
					}
				} else if (cbPesquisa.getSelectedItem() == "ID") {
					if (arg0.getKeyCode() >= 48 && arg0.getKeyCode() <= 57 || arg0.getKeyCode() == 8) {
						DefaultTableModel md = (DefaultTableModel) table.getModel();
						EncomendaVO encomendaVO = new EncomendaVO();
						encomendaVO.setIdEncomenda(Integer.parseInt(tfPesquisa.getText()));
						ArrayList<EncomendaVO> listEncomendas = ec.pesquisarEncomendasIdController(encomendaVO);
						md.setNumRows(0);
						double subtotal = 0.0;
						for (int i = 0; i < listEncomendas.size(); i++) {
							subtotal = listEncomendas.get(i).getProduto().getValor()
									+ listEncomendas.get(i).getQuantidadeProduto();

							md.addRow(new Object[] { listEncomendas.get(i).getIdEncomenda(),
									listEncomendas.get(i).getCliente().getNome(),
									listEncomendas.get(i).getEndereco(),
									listEncomendas.get(i).getEntregador().getNome(),
									listEncomendas.get(i).getDataEntrega(), listEncomendas.get(i).getHoraEntrega(),
									listEncomendas.get(i).getStatus() });
						}
					} else {
						if (arg0.getKeyCode() == 8) {
							tfPesquisa.setText("");
							attJTable();
						}
					}

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
		tfPesquisa.setBounds(364, 100, 149, 20);
		getContentPane().add(tfPesquisa);
		tfPesquisa.setColumns(10);

		JLabel lblPesquisar = new JLabel("Pesquisar:");
		lblPesquisar.setBounds(297, 103, 77, 14);
		getContentPane().add(lblPesquisar);

		cbOrdem = new JComboBox<String>();
		cbOrdem.addItem("ID");
		cbOrdem.addItem("Endereço");
		cbOrdem.addItem("Data de Entrega");
		cbOrdem.addItem("Cliente");
		cbOrdem.addItem("Status");
		cbOrdem.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				attJTable();

			}
		});
		cbOrdem.setBounds(128, 92, 108, 20);
		getContentPane().add(cbOrdem);

		JLabel lblGerenciadorDeClientes = new JLabel("Gerenciador de Encomendas");
		lblGerenciadorDeClientes.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblGerenciadorDeClientes.setBounds(10, 11, 398, 33);
		getContentPane().add(lblGerenciadorDeClientes);

		lblNDeClientes = new JLabel("Encomendas totais:");
		lblNDeClientes.setBounds(364, 131, 129, 14);
		getContentPane().add(lblNDeClientes);

		cbPesquisa = new JComboBox<String>();
		cbPesquisa.addItem("ID");
		cbPesquisa.addItem("Cliente");
		cbPesquisa.addItem("Data Entrega");
		cbPesquisa.addItem("Entregador");
		cbPesquisa.addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent arg0) {
				if (cbPesquisa.getSelectedItem() == "ID") {
					selecao = 4;
				} else if (cbPesquisa.getSelectedItem() == "Cliente") {
					selecao = 1;
				} else if (cbPesquisa.getSelectedItem() == "Data Entrega") {
					selecao = 2;
				} else if (cbPesquisa.getSelectedItem() == "Entregador") {
					selecao = 3;
				}

			}
		});
		cbPesquisa.setBounds(523, 100, 89, 20);
		getContentPane().add(cbPesquisa);

		JLabel lblOrdenadoPor = new JLabel("Ordenado por:");
		lblOrdenadoPor.setHorizontalAlignment(SwingConstants.TRAILING);
		lblOrdenadoPor.setBounds(10, 95, 108, 14);
		getContentPane().add(lblOrdenadoPor);

		JButton btnNewButton = new JButton("Atualizar Status");
		btnNewButton.setIcon(
				new ImageIcon(GerenciarEncomendas.class.getResource("/com/victor/icons/icons8-actualizar.png")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getItemSelecionadoTable();
					e.setStatus(comboBox.getSelectedItem().toString());
					ec.atualizarStatusEncomendaController(e);
					attJTable();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione uma encomenda da tabela", "Erro",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(676, 123, 161, 31);
		getContentPane().add(btnNewButton);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(276, 55, 11, 150);
		getContentPane().add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(622, 55, 11, 150);
		getContentPane().add(separator_1);

		comboBox = new JComboBox<String>();
		comboBox.addItem("Em Produção");
		comboBox.addItem("Separado para Entrega");
		comboBox.addItem("Em Transporte");
		comboBox.setBounds(643, 92, 229, 20);
		getContentPane().add(comboBox);

		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(882, 55, 11, 150);
		getContentPane().add(separator_2);

		JButton btnNewButton_1 = new JButton("OK");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(cbRelatorio.getSelectedItem() == ".txt") {
					gerarTXT();
				} else if(cbRelatorio.getSelectedItem() == ".pdf") {
					gerarPDF();
				}
				
				
			}
		});
		btnNewButton_1.setIcon(
				new ImageIcon(GerenciarEncomendas.class.getResource("/com/victor/icons/icons8-selecionado-25.png")));
		btnNewButton_1.setBounds(903, 123, 100, 31);
		getContentPane().add(btnNewButton_1);

		JLabel lblAtualizarStatus = new JLabel("Atualizar Status da Encomenda");
		lblAtualizarStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAtualizarStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizarStatus.setBounds(643, 67, 229, 14);
		getContentPane().add(lblAtualizarStatus);

		JLabel lblPesquisa = new JLabel("Pesquisa");
		lblPesquisa.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPesquisa.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisa.setBounds(297, 67, 315, 14);
		getContentPane().add(lblPesquisa);

		JLabel lblFunesDaTabela = new JLabel("Funções da tabela");
		lblFunesDaTabela.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFunesDaTabela.setHorizontalAlignment(SwingConstants.CENTER);
		lblFunesDaTabela.setBounds(10, 67, 256, 14);
		getContentPane().add(lblFunesDaTabela);

		cbRelatorio = new JComboBox<String>();
		cbRelatorio.addItem(".txt");
		cbRelatorio.addItem(".pdf");
		cbRelatorio.setBounds(903, 92, 100, 20);
		getContentPane().add(cbRelatorio);

		JLabel lblRelatrio = new JLabel("Relatório");
		lblRelatrio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRelatrio.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatrio.setBounds(903, 67, 100, 14);
		getContentPane().add(lblRelatrio);

		JButton btnVisuProdutos = new JButton("Visualizar Produtos");
		btnVisuProdutos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getItemSelecionadoTable();
					MostrarProdutosEncomenda.main(null, e.getIdEncomenda());
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selecione um item da tabela", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnVisuProdutos
				.setIcon(new ImageIcon(GerenciarEncomendas.class.getResource("/com/victor/icons/icons8-cardápio.png")));
		btnVisuProdutos.setBounds(10, 165, 226, 31);
		getContentPane().add(btnVisuProdutos);

		try {
			attJTable();
			SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		    data = dtf.format(dtSist);
		    SimpleDateFormat dtf2 = new SimpleDateFormat("dd-MM-yyyy");
		    dataTitulo = dtf2.format(dtSist);
		} finally {

		}

	}

	// metodos --------------------------------------------------------

	public void attJTable() {
		DefaultTableModel md = (DefaultTableModel) table.getModel();
		md.setNumRows(0);
		ec = new EncomendaController();
		if (cbOrdem.getSelectedItem() == "Endereço") {
			listEncomendas = ec.listarEncomendasOrdenadaEnderecoController();
		} else if (cbOrdem.getSelectedItem() == "Data de Entrega") {
			listEncomendas = ec.listarEncomendasOrdenadaDataEntregaController();
		} else if (cbOrdem.getSelectedItem() == "Cliente") {
			listEncomendas = ec.listarEncomendasOrdenadaClienteController();
		} else if (cbOrdem.getSelectedItem() == "Status") {
			listEncomendas = ec.listarEncomendasOrdenadaStatusController();
		} else if (cbOrdem.getSelectedItem() == "Entregador") {
			listEncomendas = ec.listarEncomendasOrdenadaEntregadorController();
		} else if (cbOrdem.getSelectedItem() == "ID") {
			listEncomendas = ec.listarTodosEncomendasController();
		}
		double subtotal = 0;
		for (int i = 0; i < listProdutosEncomendas.size(); i++) {
			subtotal = listProdutosEncomendas.get(i).getProduto().getValor()
					* listProdutosEncomendas.get(i).getQtdProduto();
			System.out.println(subtotal);
		}

		for (int i = 0; i < listEncomendas.size(); i++) {
			/*SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
			String dt = dtf.format(listEncomendas.get(i).getDataEntrega());
			Date dataf = null;
			try {
				dataf = dtf.parse(dt);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		    String dataEntrega = dtf.format(listEncomendas.get(i).getDataEntrega());
			md.addRow(
					new Object[] { listEncomendas.get(i).getIdEncomenda(), listEncomendas.get(i).getCliente().getNome(),
							listEncomendas.get(i).getEndereco(), listEncomendas.get(i).getEntregador().getNome(), listEncomendas.get(i).getDataEntrega(),
							listEncomendas.get(i).getHoraEntrega(), listEncomendas.get(i).getStatus() });
		}
		lblNDeClientes.setText("Nº de Encomendas: " + listEncomendas.size());
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
		Date dt = (Date) model.getValueAt(linha, COLUNA_DATA);
		e.setDataEntrega(dt);

		// Resgatar nome da linha
		String hr = (String) model.getValueAt(linha, COLUNA_HORA);
		e.setHoraEntrega(hr);

		// Resgatar nome da linha
		String status = (String) model.getValueAt(linha, COLUNA_STATUS);
		e.setStatus(status);

	}

	public void confirmaExcluir() {
		getItemSelecionadoTable();
		ep.getEncomenda().setIdEncomenda(e.getIdEncomenda());
		int id = ep.getEncomenda().getIdEncomenda();
		int conf = JOptionPane.showConfirmDialog(null, "Deseja excluir a encomenda nº " + e.getIdEncomenda() + "?",
				"Confirma", JOptionPane.YES_NO_OPTION);
		if (conf == 0) {
			epc.excluirTodosProdutosController(ep);
			ec.excluirEncomendaController(e);
		} else {

		}
	}

	public void gerarTXT() {
		FileWriter arq;
		try {
			arq = new FileWriter(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-" + dataTitulo + ".txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf("-------RELATÓRIO-DE-ENCOMENDAS-------%n");
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
			gravarArq.printf("--------------------------------------%n");
			gravarArq.printf("RELATÓRIO EMITIDO EM " + data + "%n");
			gravarArq.printf("--------------------------------------%n");
			gravarArq.printf("                      %n");
			gravarArq.printf("Versão 1.0%n");
			
			Desktop desk = Desktop.getDesktop();
			desk.open(new File(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-" + dataTitulo + ".txt"));

			arq.close();

			JOptionPane.showMessageDialog(null, "Os dados acima foram inseridos com sucesso em " + SelectDir.dir + "\\RELATORIO_ENCOMENDAS-" + dataTitulo + ".txt",
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel salvar os arquivos em .txt", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void gerarPDF() {
		// criação do documento
        Document document = new Document();
        try {
        	Date dt = new Date();
        	SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy HH:MM:ss");
        	String data = dtf.format(dt);
            
            PdfWriter.getInstance(document, new FileOutputStream(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-PDF.pdf"));
            document.open();
            
            // adicionando um parágrafo no documento
            document.add(new Paragraph("RELATÓRIO"));
            for (int i = 0; i < listEncomendas.size(); i++) {
            	SimpleDateFormat dtf2 = new SimpleDateFormat("dd/MM/yyyy");
            	String date = dtf2.format(listEncomendas.get(i).getDataEntrega());
            document.add(new Paragraph("ID: " + listEncomendas.get(i).getIdEncomenda()));
            document.add(new Paragraph("Comprador: " + listEncomendas.get(i).getCliente().getNome()));
            document.add(new Paragraph("Entregador responsável: " + listEncomendas.get(i).getEntregador().getNome()));
            document.add(new Paragraph("Data de entrega: " + date));
            document.add(new Paragraph("Hora de entrega: " + listEncomendas.get(i).getHoraEntrega()));
            document.add(new Paragraph("Status: " + listEncomendas.get(i).getStatus()));
            document.add(new Paragraph("Endereço: " + listEncomendas.get(i).getEndereco()));
            document.add(new Paragraph("                          "));
            
            }
            
            document.add(new Paragraph("Relatório emitido em: " + data));
            Desktop desk = Desktop.getDesktop();
            try {
				desk.open(new File(SelectDir.dir + "\\RELATORIO_ENCOMENDAS-PDF.pdf"));
			} catch (IOException b) {
				b.printStackTrace();
			}
            
        }
        catch(DocumentException de) {
            System.err.println(de.getMessage());
        }
        catch(IOException ioe) {
            System.err.println(ioe.getMessage());
        }
        document.close();
   }

	public int getIdEncomenda() {
		return idEncomenda;
	}

	public void setIdEncomenda(int idEncomenda) {
		this.idEncomenda = idEncomenda;
	}
}
