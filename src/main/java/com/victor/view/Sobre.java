package com.victor.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Sobre {

	private JFrame frmSobre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sobre window = new Sobre();
					window.frmSobre.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Sobre() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSobre = new JFrame();
		frmSobre.getContentPane().setBackground(Color.WHITE);
		frmSobre.getContentPane().setForeground(Color.WHITE);
		frmSobre.setIconImage(Toolkit.getDefaultToolkit().getImage(Sobre.class.getResource("/com/victor/icons/icons8-terms-and-conditions.png")));
		frmSobre.setTitle("Sobre");
		frmSobre.setBounds(100, 100, 705, 576);
		frmSobre.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSobre.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Sobre.class.getResource("/com/victor/icons/PartyExNoIcon.png")));
		label.setBounds(0, 0, 689, 257);
		frmSobre.getContentPane().add(label);
		
		JLabel lblAutorVictorSwoboda = new JLabel("Autor: Victor Swoboda Neto");
		lblAutorVictorSwoboda.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAutorVictorSwoboda.setHorizontalAlignment(SwingConstants.CENTER);
		lblAutorVictorSwoboda.setBounds(209, 309, 269, 14);
		frmSobre.getContentPane().add(lblAutorVictorSwoboda);
		
		JLabel lblSenacAds = new JLabel("Senac ADS 2017/2");
		lblSenacAds.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSenacAds.setHorizontalAlignment(SwingConstants.CENTER);
		lblSenacAds.setBounds(209, 334, 269, 14);
		frmSobre.getContentPane().add(lblSenacAds);
		
		JLabel lblGerenciadorDeEncomendas = new JLabel("Versão 1.0");
		lblGerenciadorDeEncomendas.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblGerenciadorDeEncomendas.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciadorDeEncomendas.setBounds(209, 359, 269, 14);
		frmSobre.getContentPane().add(lblGerenciadorDeEncomendas);
		
		JLabel lblvictorsn = new JLabel("/victor.sn99");
		lblvictorsn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblvictorsn.setIcon(new ImageIcon(Sobre.class.getResource("/com/victor/icons/icons8-facebook.png")));
		lblvictorsn.setBounds(10, 411, 156, 47);
		frmSobre.getContentPane().add(lblvictorsn);
		
		JLabel lblvictorsn_1 = new JLabel("@victor.sn99");
		lblvictorsn_1.setIcon(new ImageIcon(Sobre.class.getResource("/com/victor/icons/icons8-instagram-velho.png")));
		lblvictorsn_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblvictorsn_1.setBounds(163, 411, 156, 47);
		frmSobre.getContentPane().add(lblvictorsn_1);
		
		JLabel lblinvictorsn = new JLabel("/in/victor.sn99");
		lblinvictorsn.setIcon(new ImageIcon(Sobre.class.getResource("/com/victor/icons/icons8-linkedin.png")));
		lblinvictorsn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblinvictorsn.setBounds(329, 411, 170, 47);
		frmSobre.getContentPane().add(lblinvictorsn);
		
		JLabel label_3 = new JLabel("(48)988686767");
		label_3.setIcon(new ImageIcon(Sobre.class.getResource("/com/victor/icons/icons8-whatsapp.png")));
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		label_3.setBounds(509, 411, 180, 47);
		frmSobre.getContentPane().add(label_3);
		
		JLabel lblVictorswobodaoutlookcom = new JLabel("victor.swoboda99@outlook.com");
		lblVictorswobodaoutlookcom.setHorizontalAlignment(SwingConstants.CENTER);
		lblVictorswobodaoutlookcom.setIcon(new ImageIcon(Sobre.class.getResource("/com/victor/icons/icons8-e-mail-50.png")));
		lblVictorswobodaoutlookcom.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblVictorswobodaoutlookcom.setBounds(0, 469, 689, 64);
		frmSobre.getContentPane().add(lblVictorswobodaoutlookcom);
	}

}
