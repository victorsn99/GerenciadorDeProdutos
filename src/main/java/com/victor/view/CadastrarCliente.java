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
import com.victor.vo.ClienteVO;
import javax.swing.ImageIcon;

public class CadastrarCliente extends JInternalFrame {
	private JTextField tfNome;
	private JTextField tfCpf;
	private JTextField tfTel;
	private JTextField tfEnd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarCliente frame = new CadastrarCliente();
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
	public CadastrarCliente() {
		setFrameIcon(new ImageIcon(CadastrarCliente.class.getResource("/com/victor/icons/icons8-add.png")));
		setTitle("Cadastrar Cliente");
		setClosable(true);
		setBounds(100, 100, 238, 375);
		getContentPane().setLayout(null);
		
		tfNome = new JTextField();
		tfNome.setBounds(10, 46, 195, 20);
		getContentPane().add(tfNome);
		tfNome.setColumns(10);
		
		tfCpf = new JTextField();
		tfCpf.setBounds(10, 115, 195, 20);
		tfCpf.setColumns(10);
		getContentPane().add(tfCpf);
		
		JButton btCadastrar = new JButton("Cadastrar");
		btCadastrar.setIcon(new ImageIcon(CadastrarCliente.class.getResource("/com/victor/icons/icons8-add.png")));
		btCadastrar.setBounds(10, 289, 195, 31);
		btCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if (tfNome.getText().length() > 0 || tfCpf.getText().length() > 0 || tfTel.getText().length() > 0 || tfEnd.getText().length() > 0) {
								ClienteVO c = new ClienteVO();
								ClienteController cc = new ClienteController();
								c.setNome(tfNome.getText());
								c.setCpf(tfCpf.getText());
								c.setTelefone(tfTel.getText());
								c.setEndereco(tfEnd.getText());
								cc.cadastrarClienteController(c);
				} else {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				
				
				
			}
		});
		getContentPane().add(btCadastrar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 21, 46, 14);
		getContentPane().add(lblNome);
		
		JLabel lblValor = new JLabel("CPF");
		lblValor.setBounds(10, 90, 46, 14);
		getContentPane().add(lblValor);
		
		tfTel = new JTextField();
		tfTel.setBounds(10, 181, 195, 20);
		tfTel.setColumns(10);
		getContentPane().add(tfTel);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 161, 72, 14);
		getContentPane().add(lblTelefone);
		
		tfEnd = new JTextField();
		tfEnd.setBounds(11, 246, 195, 20);
		tfEnd.setColumns(10);
		getContentPane().add(tfEnd);
		
		JLabel lblEndereco = new JLabel("Endereco");
		lblEndereco.setBounds(12, 228, 72, 14);
		getContentPane().add(lblEndereco);

	}
}
