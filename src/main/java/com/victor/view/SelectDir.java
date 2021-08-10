package com.victor.view;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Desktop;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;

public class SelectDir {

	private JFrame frmSelecionarDiretrio;
	private JTextField tf;
	public static String dir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectDir window = new SelectDir();
					window.frmSelecionarDiretrio.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SelectDir() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSelecionarDiretrio = new JFrame();
		frmSelecionarDiretrio.setIconImage(Toolkit.getDefaultToolkit().getImage(SelectDir.class.getResource("/com/victor/icons/icons8-abrir-pasta.png")));
		frmSelecionarDiretrio.setTitle("Selecionar diretório");
		frmSelecionarDiretrio.setBounds(100, 100, 450, 267);
		frmSelecionarDiretrio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSelecionarDiretrio.getContentPane().setLayout(null);
		
		tf = new JTextField();
		tf.setBounds(10, 48, 294, 20);
		frmSelecionarDiretrio.getContentPane().add(tf);
		tf.setColumns(10);
		
		JLabel lblCadastrarRelatriosNesse = new JLabel("Cadastrar relatórios nesse diretório?");
		lblCadastrarRelatriosNesse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarRelatriosNesse.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastrarRelatriosNesse.setBounds(10, 11, 414, 26);
		frmSelecionarDiretrio.getContentPane().add(lblCadastrarRelatriosNesse);
		
		JButton btnNewButton = new JButton("Pasta");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int i = file.showSaveDialog(null);
				if (i == 1) {
					tf.setText("");
				} else {
					File arquivo = file.getSelectedFile();
					tf.setText(arquivo.getPath());
				}
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(SelectDir.class.getResource("/com/victor/icons/icons8-abrir-pasta.png")));
		btnNewButton.setBounds(314, 47, 110, 23);
		frmSelecionarDiretrio.getContentPane().add(btnNewButton);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				setDir(tf.getText());
				System.out.println(dir);
				frmSelecionarDiretrio.dispose();
				
			}
		});
		btnOk.setIcon(new ImageIcon(SelectDir.class.getResource("/com/victor/icons/icons8-selecionado-25.png")));
		btnOk.setBounds(118, 79, 186, 33);
		frmSelecionarDiretrio.getContentPane().add(btnOk);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 123, 414, 9);
		frmSelecionarDiretrio.getContentPane().add(separator);
		
		JLabel lblAbrirRelatrios = new JLabel("Abrir relatórios");
		lblAbrirRelatrios.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbrirRelatrios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAbrirRelatrios.setBounds(10, 143, 414, 26);
		frmSelecionarDiretrio.getContentPane().add(lblAbrirRelatrios);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JFileChooser file = new JFileChooser();
				file.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int i = file.showSaveDialog(null);
				if (i == 1) {
					tf.setText("");
				} else {
					File arquivo = file.getSelectedFile();
					Desktop desk = Desktop.getDesktop();
					try {
						desk.open(new File(arquivo.getPath()));
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(SelectDir.class.getResource("/com/victor/icons/icons8-visualizar-arquivo-25.png")));
		btnNewButton_1.setBounds(118, 180, 186, 37);
		frmSelecionarDiretrio.getContentPane().add(btnNewButton_1);
		
		
	}

	public String getDir() {
		return dir;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}
	
	
}
