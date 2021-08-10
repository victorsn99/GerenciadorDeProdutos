package com.victor.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.sql.Date;

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
import javax.swing.ImageIcon;

public class AtualizarEncomenda1 {

	private JFrame frmAtualizarEncomenda;
	private JTextField tfEndereco;
	private ArrayList<ProdutoVO> listProdutos = new ArrayList<ProdutoVO>();
	private ArrayList<ClienteVO> listClientes = new ArrayList<ClienteVO>();
	private ArrayList<EntregadorVO> listEntregadores = new ArrayList<EntregadorVO>();
	private ClienteController cc = new ClienteController(); 
	private EntregadorController ec = new EntregadorController(); 
	private ProdutoController pc = new ProdutoController();
	private JComboBox<String> cbCliente;
	private JComboBox<Integer> cbDia;
	private JComboBox<String> cbMes;
	private EncomendaVO e = new EncomendaVO();
	private JComboBox<String> cbEntregador;
	private JTextField tfHora;
	private JComboBox<Integer> cbHora;
	private JComboBox<Integer> cbMin;
	int id;
	private JLabel lblDataHora;
	private JComboBox<Integer> cbAno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarEncomenda1 window = new AtualizarEncomenda1(id);
					window.frmAtualizarEncomenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public AtualizarEncomenda1(int id) {
		setId(id);
		System.out.println(id);
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtualizarEncomenda = new JFrame();
		frmAtualizarEncomenda.setIconImage(Toolkit.getDefaultToolkit().getImage(AtualizarEncomenda1.class.getResource("/com/victor/icons/icons8-editar.png")));
		frmAtualizarEncomenda.setBounds(100, 100, 318, 451);
		frmAtualizarEncomenda.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		cbCliente = new JComboBox<String>();
		cbCliente.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				tfEndereco.setText(listClientes.get(cbCliente.getSelectedIndex()).getEndereco());
				System.out.println("ID " + id);
				frmAtualizarEncomenda.setTitle("Atualizar Encomenda " + id);
				
			}
		});
		frmAtualizarEncomenda.getContentPane().setLayout(null);
		cbCliente.setBounds(10, 40, 282, 20);
		frmAtualizarEncomenda.getContentPane().add(cbCliente);
		
		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.setIcon(new ImageIcon(AtualizarEncomenda1.class.getResource("/com/victor/icons/icons8-actualizar.png")));
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
				e.setIdEncomenda(id);
				e.getCliente().setIdCliente(listClientes.get(cbCliente.getSelectedIndex()).getIdCliente());
				e.getEntregador().setId(listEntregadores.get(cbEntregador.getSelectedIndex()).getId());
				e.setEndereco(tfEndereco.getText());
				e.setDataEntrega(Date.valueOf(ano + "-" + mes + "-" + dia));
				e.setHoraEntrega(montarHora);
				e.setStatus("Em produção");
				ec.atualizarEncomendaController(e);
				frmAtualizarEncomenda.dispose();
				
				
				
			}
		});
		btnNewButton.setBounds(92, 367, 117, 34);
		frmAtualizarEncomenda.getContentPane().add(btnNewButton);
		
		JLabel lblValor = new JLabel("-----Data da Entrega-----");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setBounds(10, 222, 282, 14);
		frmAtualizarEncomenda.getContentPane().add(lblValor);
		
		JLabel lblCategoria = new JLabel("Cliente");
		lblCategoria.setBounds(10, 15, 60, 14);
		frmAtualizarEncomenda.getContentPane().add(lblCategoria);
		
		JLabel lblEndereo = new JLabel("Endereço");
		lblEndereo.setBounds(10, 88, 78, 14);
		frmAtualizarEncomenda.getContentPane().add(lblEndereo);
		
		tfEndereco = new JTextField();
		tfEndereco.setColumns(10);
		tfEndereco.setBounds(10, 113, 282, 20);
		frmAtualizarEncomenda.getContentPane().add(tfEndereco);
		
		JLabel lblDia = new JLabel("Dia");
		lblDia.setHorizontalAlignment(SwingConstants.CENTER);
		lblDia.setBounds(32, 247, 66, 14);
		frmAtualizarEncomenda.getContentPane().add(lblDia);
		
		cbDia = new JComboBox<Integer>();
		for (int j = 1; j <= 31; j++) {
			cbDia.addItem(j);	
		}
		cbDia.setBounds(32, 272, 66, 20);
		frmAtualizarEncomenda.getContentPane().add(cbDia);
		
		cbMes = new JComboBox<String>();
		cbMes.addItem("Janeiro"); cbMes.addItem("Fevereiro"); cbMes.addItem("Março"); cbMes.addItem("Abril"); cbMes.addItem("Maio"); 
		cbMes.addItem("Junho"); cbMes.addItem("Julho"); cbMes.addItem("Agosto"); cbMes.addItem("Setembro"); cbMes.addItem("Outubro");
		cbMes.addItem("Novembro"); cbMes.addItem("Dezembro");
		cbMes.setBounds(108, 272, 89, 20);
		frmAtualizarEncomenda.getContentPane().add(cbMes);
		
		JLabel lblMs = new JLabel("Mês");
		lblMs.setHorizontalAlignment(SwingConstants.CENTER);
		lblMs.setBounds(108, 247, 89, 14);
		frmAtualizarEncomenda.getContentPane().add(lblMs);
		
		JLabel lblAno = new JLabel("Ano");
		lblAno.setHorizontalAlignment(SwingConstants.CENTER);
		lblAno.setBounds(207, 247, 66, 14);
		frmAtualizarEncomenda.getContentPane().add(lblAno);
		
		cbEntregador = new JComboBox<String>();
		cbEntregador.setBounds(10, 180, 282, 20);
		frmAtualizarEncomenda.getContentPane().add(cbEntregador);
		
		JLabel lblEntregador = new JLabel("Entregador");
		lblEntregador.setBounds(10, 155, 146, 14);
		frmAtualizarEncomenda.getContentPane().add(lblEntregador);
		
		cbHora = new JComboBox<Integer>();
		for (int i = 0; i < 24 ; i++) {
			cbHora.addItem(i);
		}
		cbHora.setBounds(79, 327, 46, 20);
		frmAtualizarEncomenda.getContentPane().add(cbHora);
		
		cbMin = new JComboBox<Integer>();
		cbMin.addItem(00);
		cbMin.addItem(10);
		cbMin.addItem(20);
		cbMin.addItem(30);
		cbMin.addItem(40);
		cbMin.addItem(50);
		cbMin.setBounds(179, 327, 46, 20);
		frmAtualizarEncomenda.getContentPane().add(cbMin);
		
		lblDataHora = new JLabel("Hora                      Minuto");
		lblDataHora.setHorizontalAlignment(SwingConstants.CENTER);
		lblDataHora.setBounds(10, 303, 282, 14);
		frmAtualizarEncomenda.getContentPane().add(lblDataHora);
		
		cbAno = new JComboBox<Integer>();
		for (int i = 2018; i < 2051; i++) {
			cbAno.addItem(i);
		}
		cbAno.setBounds(207, 272, 66, 20);
		frmAtualizarEncomenda.getContentPane().add(cbAno);
		
		try {
			attJCBC();
			attJCBE();
			System.out.println("Atualizar encomenda: " + id);
		} catch (Exception e) {
			System.out.println("Error:"+e.getMessage());
		}

	}
	
	protected void attJCBC() {
		listClientes = cc.listarTodosClientesController();
		for (int i = 0; i < listClientes.size(); i++) {
			cbCliente.addItem(listClientes.get(i).getNome());
		}
	}
	
	protected void attJCBE() {
		listEntregadores = ec.listarTodosEntregadorController();
		for (int i = 0; i < listEntregadores.size(); i++) {
			cbEntregador.addItem(listEntregadores.get(i).getNome());
		}
	}
	public void setId(int id){
		this.id = id;
	}
	public JComboBox getComboBox() {
		return cbAno;
	}
	}


