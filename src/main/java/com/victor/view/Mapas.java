package com.victor.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.victor.controller.ClienteController;
import com.victor.maps.controller.PesquisaLocalController;
import com.victor.vo.ClienteVO;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Toolkit;
import javax.swing.JSeparator;

public class Mapas {

	private JFrame frmMapa;
	private ClienteVO c = new ClienteVO();
	private ClienteController cc = new ClienteController();
	private ArrayList<ClienteVO> listClientes = new ArrayList<ClienteVO>();
	private JComboBox<String> cbCliente;
	private int zoom = 14;
	private JLabel lblX;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mapas window = new Mapas();
					window.frmMapa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mapas() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMapa = new JFrame();
		frmMapa.setIconImage(Toolkit.getDefaultToolkit().getImage(Mapas.class.getResource("/com/victor/icons/icons8-waypoint-map.png")));
		frmMapa.setTitle("Mapa");
		frmMapa.setBounds(100, 100, 640, 728);
		frmMapa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmMapa.getContentPane().setLayout(null);
		
		JLabel lblDigiteUmLocal = new JLabel("Escolha um cliente:");
		lblDigiteUmLocal.setBounds(10, 23, 121, 15);
		frmMapa.getContentPane().add(lblDigiteUmLocal);
		
		final JLabel label = new JLabel("");
		label.setBounds(10, 80, 604, 598);
		frmMapa.getContentPane().add(label);
		
		JButton maisZoom = new JButton("");
		maisZoom.setBounds(127, 49, 44, 27);
		maisZoom.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				zoom += 1;
				try {
					int pos = cbCliente.getSelectedItem().toString().indexOf("- ");
					BufferedImage imagemMapa = PesquisaLocalController.buscaImagemMapa(cbCliente.getSelectedItem().toString().substring(pos), zoom);
					label.setIcon(new ImageIcon(imagemMapa));
					lblX.setText(zoom + "X");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		maisZoom.setIcon(new ImageIcon(Mapas.class.getResource("/com/victor/icons/icons8-soma.png")));
		maisZoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		frmMapa.getContentPane().add(maisZoom);
		
		cbCliente = new JComboBox<String>();
		cbCliente.setBounds(127, 20, 382, 20);
		cbCliente.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				try {
					int pos = cbCliente.getSelectedItem().toString().indexOf("- ");
					BufferedImage imagemMapa = PesquisaLocalController.buscaImagemMapa(cbCliente.getSelectedItem().toString().substring(pos));
					System.out.println(cbCliente.getSelectedItem().toString().substring(pos));
					label.setIcon(new ImageIcon(imagemMapa));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		frmMapa.getContentPane().add(cbCliente);
		
		JButton menosZoom = new JButton("");
		menosZoom.setBounds(181, 49, 44, 27);
		menosZoom.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				zoom -= 1;
				try {
					int pos = cbCliente.getSelectedItem().toString().indexOf("- ");
					BufferedImage imagemMapa = PesquisaLocalController.buscaImagemMapa(cbCliente.getSelectedItem().toString().substring(pos), zoom);
					label.setIcon(new ImageIcon(imagemMapa));
					lblX.setText(zoom + "X");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		menosZoom.setIcon(new ImageIcon(Mapas.class.getResource("/com/victor/icons/icons8-subtração.png")));
		frmMapa.getContentPane().add(menosZoom);
		
		JLabel lblZoom = new JLabel("Zoom:");
		lblZoom.setBounds(10, 54, 90, 15);
		lblZoom.setHorizontalAlignment(SwingConstants.TRAILING);
		frmMapa.getContentPane().add(lblZoom);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(549, 11, 65, 50);
		lblNewLabel.setIcon(new ImageIcon(Mapas.class.getResource("/com/victor/icons/icons8-mapa-de-localização-50.png")));
		frmMapa.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 80, 604, 2);
		frmMapa.getContentPane().add(separator);
		
		lblX = new JLabel();
		lblX.setBounds(240, 54, 46, 14);
		frmMapa.getContentPane().add(lblX);
		
		try {
			attJCBC();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void attJCBC() {
		listClientes = cc.listarTodosClientesController();
		for (int i = 0; i < listClientes.size(); i++) {
			cbCliente.addItem(listClientes.get(i).getNome() + " - " + listClientes.get(i).getEndereco());
		}
	}
	
	public JComboBox getCbCliente() {
		return cbCliente;
	}
	public JLabel getLblX() {
		return lblX;
	}
}
