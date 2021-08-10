package com.victor.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.victor.controller.CategoriaController;
import com.victor.controller.ProdutoController;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ProdutoVO;

public class CadastrarProduto extends JInternalFrame {
	private JTextField tfnome;
	private JTextField tfvalor;
	private JComboBox<String> cb;
	private ProdutoVO p;
	private ProdutoController pc;
	private CategoriaController cc = new CategoriaController();
	private CategoriaVO c;
	private ArrayList<CategoriaVO> listCategorias = new ArrayList<CategoriaVO>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastrarProduto frame = new CadastrarProduto();
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
	public CadastrarProduto() {
		setTitle("Cadastrar Produto");
		setClosable(true);
		setBounds(100, 100, 238, 309);
		getContentPane().setLayout(null);

		tfnome = new JTextField();
		tfnome.setBounds(10, 46, 195, 20);
		getContentPane().add(tfnome);
		tfnome.setColumns(10);

		tfvalor = new JTextField();
		tfvalor.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void keyReleased(KeyEvent arg0) {
				if (arg0.getKeyCode() >= 48 && arg0.getKeyCode() <= 57 || arg0.getKeyCode() == 8) {
					
				} else {
					JOptionPane.showMessageDialog(null, "Apenas números no valor", "ERRO", JOptionPane.ERROR_MESSAGE);
					tfvalor.setText("");
				}
			}
			
			public void keyPressed(KeyEvent e) {
				
			}
		});
		tfvalor.setColumns(10);
		tfvalor.setBounds(10, 115, 195, 20);
		getContentPane().add(tfvalor);

		/*
		 * cb = new JComboBox<CategoriaVO>();
		 * 
		 * ComboBoxModel<CategoriaVO> modelCombo = new
		 * DefaultComboBoxModel<CategoriaVO>( (CategoriaVO[]) listCat.toArray());
		 * cb.setBounds(10, 187, 195, 20); cb.setModel(modelCombo);
		 * getContentPane().add(cb);
		 */

		cb = new JComboBox<String>();
		cb.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(arg0.getItem());
			}
		});
		cb.setBounds(10, 187, 195, 20);
		getContentPane().add(cb);

		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
					if (tfnome.getText().length() > 0 || tfvalor.getText().length() > 0) {
						p = new ProdutoVO();
						pc = new ProdutoController();
						p.setNome(tfnome.getText());
						p.setValor(Double.parseDouble(tfvalor.getText()));
						p.getCategoria().setIdCategoria(listCategorias.get(cb.getSelectedIndex()).getIdCategoria());
						pc.cadastrarProdutoController(p);
					} else {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos", "ERRO", JOptionPane.ERROR_MESSAGE);
					}
				
			}
		});
		btnNewButton.setBounds(64, 231, 89, 23);
		getContentPane().add(btnNewButton);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 21, 46, 14);
		getContentPane().add(lblNome);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 90, 46, 14);
		getContentPane().add(lblValor);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 162, 94, 14);
		getContentPane().add(lblCategoria);

		try {
			attJCB();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	protected void attJCB() {
		listCategorias = cc.listarTodasCategoriasController();
		for (int i = 0; i < listCategorias.size(); i++) {
			cb.addItem(listCategorias.get(i).getNomeCategoria());
		}
		}

	}


