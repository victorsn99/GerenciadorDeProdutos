package com.victor.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.victor.controller.CategoriaController;
import com.victor.controller.ProdutoController;
import com.victor.vo.CategoriaVO;
import com.victor.vo.ProdutoVO;
import java.awt.Toolkit;

public class AtualizarProduto {

	private JFrame frmAtualizarProduto;
	private JTextField tfnome;
	private JTextField tfvalor;
	private JComboBox<String> cb;
	private ProdutoVO p;
	private ProdutoController pc;
	private CategoriaController cc = new CategoriaController();
	private CategoriaVO c;
	private ArrayList<CategoriaVO> listCategorias = new ArrayList<CategoriaVO>();
	int id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, final int id) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtualizarProduto window = new AtualizarProduto(id);
					window.frmAtualizarProduto.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AtualizarProduto(int id) {
		setId(id);
		System.out.println("getid : " + getId());
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAtualizarProduto = new JFrame();
		frmAtualizarProduto.setIconImage(Toolkit.getDefaultToolkit().getImage(AtualizarProduto.class.getResource("/com/victor/icons/icons8-actualizar.png")));
		frmAtualizarProduto.setTitle("Atualizar Produto");
		frmAtualizarProduto.setBounds(100, 100, 302, 313);
		frmAtualizarProduto.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAtualizarProduto.getContentPane().setLayout(null);
		
		tfnome = new JTextField();
		tfnome.setBounds(10, 46, 266, 20);
		frmAtualizarProduto.getContentPane().add(tfnome);
		tfnome.setColumns(10);

		tfvalor = new JTextField();
		tfvalor.setColumns(10);
		tfvalor.setBounds(10, 115, 266, 20);
		frmAtualizarProduto.getContentPane().add(tfvalor);

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
		cb.setBounds(10, 187, 266, 20);
		frmAtualizarProduto.getContentPane().add(cb);

		JButton btnNewButton = new JButton("Atualizar");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				p = new ProdutoVO();
				pc = new ProdutoController();
				p.setIdProduto(id);
				p.setNome(tfnome.getText());
				p.setValor(Double.parseDouble(tfvalor.getText()));
				p.getCategoria().setIdCategoria(listCategorias.get(cb.getSelectedIndex()).getIdCategoria());
				pc.atualizarProdutoController(p);
			}
		});
		btnNewButton.setBounds(95, 240, 89, 23);
		frmAtualizarProduto.getContentPane().add(btnNewButton);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 21, 46, 14);
		frmAtualizarProduto.getContentPane().add(lblNome);

		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(10, 90, 46, 14);
		frmAtualizarProduto.getContentPane().add(lblValor);

		JLabel lblCategoria = new JLabel("Categoria");
		lblCategoria.setBounds(10, 158, 434, 20);
		frmAtualizarProduto.getContentPane().add(lblCategoria);

		try {
			attJCB();
			frmAtualizarProduto.setTitle("Atualizar produto: " + id);
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	}


