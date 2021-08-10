package com.victor.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;

import com.victor.controller.CategoriaController;
import com.victor.vo.CategoriaVO;

import javax.swing.JButton;
import javax.swing.JLabel;

public class CadastrarCategoria extends JInternalFrame {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarCategoria frame = new CadastrarCategoria();
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
	public CadastrarCategoria() {
		setTitle("Cadastrar Categoria");
		setClosable(true);
		setBounds(100, 100, 226, 164);
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 52, 190, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				CategoriaVO c = new CategoriaVO();
				CategoriaController cc = new CategoriaController();
				c.setNomeCategoria(textField.getText());
				cc.cadastrarCategoriaController(c);
				
			}
		});
		btnCadastrar.setBounds(61, 94, 89, 23);
		getContentPane().add(btnCadastrar);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 27, 46, 14);
		getContentPane().add(lblNome);

	}
}
