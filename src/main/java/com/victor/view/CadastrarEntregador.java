package com.victor.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.victor.controller.ClienteController;
import com.victor.controller.EntregadorController;
import com.victor.vo.ClienteVO;
import com.victor.vo.EntregadorVO;

public class CadastrarEntregador extends JInternalFrame {
	private JTextField tfNome;
	private EntregadorController ec;
	private EntregadorVO e;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarEntregador frame = new CadastrarEntregador();
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
	public CadastrarEntregador() {
		setTitle("Cadastrar Entregador");
		setClosable(true);
		setBounds(100, 100, 222, 172);
		getContentPane().setLayout(null);

		tfNome = new JTextField();
		tfNome.setBounds(10, 66, 186, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 41, 46, 14);
		getContentPane().add(lblNome);

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				if (tfNome.getText().length() > 0) {
					ec = new EntregadorController();
					e = new EntregadorVO();
					e.setNome(tfNome.getText());
					ec.cadastrarEntregadorController(e);
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				if (tfNome.getText().length() > 0) {
					
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
				}


			}
		});
		btnCadastrar.setBounds(48, 108, 107, 23);
		getContentPane().add(btnCadastrar);

	}
}
