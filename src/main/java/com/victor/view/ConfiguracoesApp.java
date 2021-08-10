package com.victor.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Toolkit;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.Box;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class ConfiguracoesApp {

	private JFrame frmConfiguraes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfiguracoesApp window = new ConfiguracoesApp();
					window.frmConfiguraes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConfiguracoesApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConfiguraes = new JFrame();
		frmConfiguraes.setTitle("Configurações");
		frmConfiguraes.setIconImage(Toolkit.getDefaultToolkit().getImage(ConfiguracoesApp.class.getResource("/com/victor/icons/icons8-mixer-de-configurações-vertical-25.png")));
		frmConfiguraes.setBounds(100, 100, 885, 462);
		frmConfiguraes.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConfiguraes.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 177, 401);
		frmConfiguraes.getContentPane().add(scrollPane);
		
		JTree tree = new JTree();
		tree.setModel(new DefaultTreeModel(
			new DefaultMutableTreeNode("Configurações") {
				{
					DefaultMutableTreeNode node_1;
					node_1 = new DefaultMutableTreeNode("Tela");
						node_1.add(new DefaultMutableTreeNode("Fonte"));
						node_1.add(new DefaultMutableTreeNode("Tema"));
						node_1.add(new DefaultMutableTreeNode("Idioma"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Conexão");
						node_1.add(new DefaultMutableTreeNode("Servidor"));
						node_1.add(new DefaultMutableTreeNode("Backup"));
						node_1.add(new DefaultMutableTreeNode("Diretórios"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Atualização");
						node_1.add(new DefaultMutableTreeNode("Buscar atualização de software"));
						node_1.add(new DefaultMutableTreeNode("Versão do software"));
						node_1.add(new DefaultMutableTreeNode("Solicitar atualização"));
						node_1.add(new DefaultMutableTreeNode("Notas do patch"));
					add(node_1);
					node_1 = new DefaultMutableTreeNode("Contato");
						node_1.add(new DefaultMutableTreeNode("Enviar comentários"));
					add(node_1);
					
				}
			}
		));
		scrollPane.setViewportView(tree);
		
		
		JList<String> list = new JList<String>();
		list.setBounds(197, 11, 177, 401);
		frmConfiguraes.getContentPane().add(list);
		
		
	}
}
