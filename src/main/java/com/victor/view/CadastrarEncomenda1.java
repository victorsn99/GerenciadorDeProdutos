package com.victor.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.victor.controller.ClienteController;
import com.victor.controller.EncomendaController;
import com.victor.controller.EntregadorController;
import com.victor.controller.ProdutoController;
import com.victor.vo.ClienteVO;
import com.victor.vo.EncomendaVO;
import com.victor.vo.EntregadorVO;
import com.victor.vo.ProdutoVO;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CadastrarEncomenda1 {

	private JFrame frmCadEncomenda;
	private JTextField tfEndereco;
	private JTextField tfAno;
	private ArrayList<ProdutoVO> listProdutos = new ArrayList<ProdutoVO>();
	private ArrayList<ClienteVO> listClientes = new ArrayList<ClienteVO>();
	private ArrayList<EntregadorVO> listEntregadores = new ArrayList<EntregadorVO>();
	private EncomendaController ec = new EncomendaController();
	private ClienteController cc = new ClienteController(); 
	private EntregadorController entc = new EntregadorController(); 
	private JComboBox<String> cbCliente;
	private JComboBox<Integer> cbDia;
	private JComboBox<String> cbMes;
	private JComboBox<String> cbEntregador;
	int max = 0;
	private JComboBox<Integer> cbHora;
	private JComboBox<Integer> cbAno;
	private JComboBox<Integer> cbMin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEncomenda1 window = new CadastrarEncomenda1();
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
	public CadastrarEncomenda1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frmCadEncomenda = new JFrame();
		frmCadEncomenda.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {

			}
		});
		frmCadEncomenda.setIconImage(Toolkit.getDefaultToolkit().getImage(CadastrarEncomenda1.class.getResource("/com/victor/icons/icons8-product.png")));
		frmCadEncomenda.setTitle("Cad. Encomenda");
		frmCadEncomenda.setBounds(100, 100, 248, 434);
		frmCadEncomenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cbCliente = new JComboBox<String>();
		cbCliente.setBounds(10, 40, 202, 20);
		cbCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				tfEndereco.setText(listClientes.get(cbCliente.getSelectedIndex()).getEndereco());
				
			}
		});
		cbCliente.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				System.out.println(listClientes.get(cbCliente.getSelectedIndex()).getIdCliente());
				
			}
		});
		frmCadEncomenda.getContentPane().setLayout(null);
		frmCadEncomenda.getContentPane().add(cbCliente);
		
		JButton btnNewButton = new JButton("Prosseguir");
		btnNewButton.setBounds(36, 353, 154, 32);
		btnNewButton.setIcon(new ImageIcon(CadastrarEncomenda1.class.getResource("/com/victor/icons/icons8-duplo-para-a-direita.png")));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e1) {
				int dia, mes, ano;
				dia = cbDia.getSelectedIndex() + 1;
				mes = cbMes.getSelectedIndex() + 1;
				ano = cbAno.getSelectedIndex() + 2018;
				String montarHora = null;
				if (cbHora.getSelectedIndex() < 10) {
					montarHora = "0" + (cbHora.getSelectedItem()) + ":" +(cbMin.getSelectedItem()) + ":00";
				} else {
				montarHora = (cbHora.getSelectedItem()) + ":" +(cbMin.getSelectedItem()) + ":00";
				}
				System.out.println(montarHora);
				EncomendaController ec = new EncomendaController();
				EncomendaVO e = new EncomendaVO();
				e.getCliente().setIdCliente(listClientes.get(cbCliente.getSelectedIndex()).getIdCliente());
				e.getEntregador().setId(listEntregadores.get(cbEntregador.getSelectedIndex()).getId());
				e.setEndereco(tfEndereco.getText());
				e.setDataEntrega(Date.valueOf(ano + "-" + mes + "-" + dia));
				e.setHoraEntrega(montarHora);
				e.setStatus("Em produção");
				ec.cadastrarEncomendaController(e);
				CadastrarEncomenda2.main(null);
				frmCadEncomenda.dispose();
				
				
			}
		});
		frmCadEncomenda.getContentPane().add(btnNewButton);
		
		JLabel lblValor = new JLabel("-----Data da Entrega-----");
		lblValor.setBounds(10, 205, 202, 14);
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		frmCadEncomenda.getContentPane().add(lblValor);
		
		JLabel lblCategoria = new JLabel("Cliente");
		lblCategoria.setBounds(10, 15, 60, 14);
		frmCadEncomenda.getContentPane().add(lblCategoria);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(10, 71, 78, 14);
		frmCadEncomenda.getContentPane().add(lblEndereo);
		
		tfEndereco = new JTextField();
		tfEndereco.setBounds(10, 96, 202, 20);
		tfEndereco.setColumns(10);
		frmCadEncomenda.getContentPane().add(tfEndereco);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setBounds(10, 230, 46, 14);
		lblDia.setHorizontalAlignment(SwingConstants.CENTER);
		frmCadEncomenda.getContentPane().add(lblDia);
		
		cbDia = new JComboBox<Integer>();
		cbDia.setBounds(10, 255, 46, 20);
		for (int j = 1; j <= 31; j++) {
			cbDia.addItem(j);	
		}
		frmCadEncomenda.getContentPane().add(cbDia);
		
		cbMes = new JComboBox<String>();
		cbMes.setBounds(67, 255, 89, 20);
		cbMes.addItem("Janeiro"); cbMes.addItem("Fevereiro"); cbMes.addItem("Março"); cbMes.addItem("Abril"); cbMes.addItem("Maio"); 
		cbMes.addItem("Junho"); cbMes.addItem("Julho"); cbMes.addItem("Agosto"); cbMes.addItem("Setembro"); cbMes.addItem("Outubro");
		cbMes.addItem("Novembro"); cbMes.addItem("Dezembro");
		frmCadEncomenda.getContentPane().add(cbMes);
		
		JLabel lblMs = new JLabel("Mês");
		lblMs.setBounds(67, 230, 89, 14);
		lblMs.setHorizontalAlignment(SwingConstants.CENTER);
		frmCadEncomenda.getContentPane().add(lblMs);
		
		cbAno = new JComboBox<Integer>();
		for (int i = 2018; i < 2051; i++) {
			cbAno.addItem(i);
		}
		cbAno.setBounds(166, 255, 56, 20);
		frmCadEncomenda.getContentPane().add(cbAno);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setBounds(166, 230, 46, 14);
		lblAno.setHorizontalAlignment(SwingConstants.CENTER);
		frmCadEncomenda.getContentPane().add(lblAno);
		
		cbEntregador = new JComboBox<String>();
		cbEntregador.setBounds(10, 163, 202, 20);
		frmCadEncomenda.getContentPane().add(cbEntregador);
		
		JLabel lblEntregador = new JLabel("Entregador");
		lblEntregador.setBounds(10, 138, 146, 14);
		frmCadEncomenda.getContentPane().add(lblEntregador);
		
		JLabel lblHora = new JLabel("Hora              Minuto  ");
		lblHora.setBounds(0, 286, 232, 20);
		lblHora.setHorizontalAlignment(SwingConstants.CENTER);
		frmCadEncomenda.getContentPane().add(lblHora);
		
		cbHora = new JComboBox<Integer>();
		for (int i = 0; i < 24 ; i++) {
			cbHora.addItem(i);
		}
		cbHora.setBounds(56, 312, 46, 20);
		frmCadEncomenda.getContentPane().add(cbHora);
		
		cbMin = new JComboBox<Integer>();
		cbMin.addItem(00);
		cbMin.addItem(10);
		cbMin.addItem(20);
		cbMin.addItem(30);
		cbMin.addItem(40);
		cbMin.addItem(50);
		cbMin.setBounds(121, 312, 46, 20);
		frmCadEncomenda.getContentPane().add(cbMin);
		
		try {
			attJCBC();
			attJCBE();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected void attJCBC() {
		listClientes = cc.listarTodosClientesController();
		for (int i = 0; i < listClientes.size(); i++) {
			cbCliente.addItem(listClientes.get(i).getNome());
		}
	}
	
	protected void attJCBE() {
		listEntregadores = entc.listarTodosEntregadorController();
		for (int i = 0; i < listEntregadores.size(); i++) {
			cbEntregador.addItem(listEntregadores.get(i).getNome());
		}
	}
	public JComboBox getComboBox() {
		return cbHora;
	}
	public JComboBox getComboBox_1() {
		return cbMin;
	}
	}

