package com.victor.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.victor.view.CadastrarProduto;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class Principal {

	private JFrame frmSistemaDeCompras;
	private CadastrarProduto cp;
	private CadastrarCliente cc;
	private CadastrarCategoria ccat;
	private CadastrarEntregador cent;
	private GerenciarProdutos gp;
	private GerenciarClientes gc;
	private GerenciarEncomendas ge;
	private GerenciarCategorias gcat;
	private GerenciarEntregadores gcent;
	private JDesktopPane desktopPane;
	Desktop desk = Desktop.getDesktop();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmSistemaDeCompras.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSistemaDeCompras = new JFrame();
		frmSistemaDeCompras.setResizable(false);
		frmSistemaDeCompras.setIconImage(Toolkit.getDefaultToolkit().getImage(Principal.class.getResource("/com/victor/icons/icons8-birthday.png")));
		frmSistemaDeCompras.setTitle("partyEX ");
		frmSistemaDeCompras.setBounds(100, 100, 1050, 622);
		frmSistemaDeCompras.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmSistemaDeCompras.setJMenuBar(menuBar);
		
		JMenu mnClientes = new JMenu("Clientes");
		mnClientes.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-person.png")));
		menuBar.add(mnClientes);
		
		JMenuItem mntmAdicionar = new JMenuItem("Adicionar");
		mntmAdicionar.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-add.png")));
		mntmAdicionar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				cc = new CadastrarCliente();
				desktopPane.removeAll();
				desktopPane.add(cc);
				cc.setLocation(0, 0);
				cc.show();
				
			}
		});
		mnClientes.add(mntmAdicionar);
		
		JMenuItem mntmGerenciar = new JMenuItem("Gerenciar");
		mntmGerenciar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				gc = new GerenciarClientes();
				desktopPane.removeAll();
				desktopPane.add(gc);
				gc.setLocation(0, 0);
				gc.show();
				
			}
		});
		mntmGerenciar.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-suporte.png")));
		mnClientes.add(mntmGerenciar);
		
		JMenu mnProdutos = new JMenu("Produtos");
		mnProdutos.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-products.png")));
		menuBar.add(mnProdutos);
		
		JMenuItem mntmAdicionar_1 = new JMenuItem("Adicionar");
		mntmAdicionar_1.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-add.png")));
		mntmAdicionar_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				cp = new CadastrarProduto();
				desktopPane.removeAll();
				desktopPane.add(cp);
				cp.setLocation(0, 0);
				cp.show();
				
			}
		});
		mnProdutos.add(mntmAdicionar_1);
		
		JMenuItem mntmGerenciar_1 = new JMenuItem("Gerenciar");
		mntmGerenciar_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				gp = new GerenciarProdutos();
				desktopPane.removeAll();
				desktopPane.add(gp);
				gp.setLocation(0, 0);
				gp.show();
				
			}
		});
		mntmGerenciar_1.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-suporte.png")));
		mnProdutos.add(mntmGerenciar_1);
		
		JMenu mnEntregadores = new JMenu("Entregadores");
		mnEntregadores.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-man-to-man.png")));
		menuBar.add(mnEntregadores);
		
		JMenuItem mntmAdicionar_4 = new JMenuItem("Adicionar");
		mntmAdicionar_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				cent = new CadastrarEntregador();
				desktopPane.removeAll();
				desktopPane.add(cent);
				cent.setLocation(0, 0);
				cent.show();
				
			}
		});
		mntmAdicionar_4.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-add.png")));
		mnEntregadores.add(mntmAdicionar_4);
		
		JMenuItem mntmGerenciar_4 = new JMenuItem("Gerenciar");
		mntmGerenciar_4.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				gcent = new GerenciarEntregadores();
				desktopPane.removeAll();
				desktopPane.add(gcent);
				gcent.setLocation(0, 0);
				gcent.show();
				
			}
		});
		mntmGerenciar_4.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-suporte.png")));
		mnEntregadores.add(mntmGerenciar_4);
		
		JMenu mnEncomendas = new JMenu("Encomendas");
		mnEncomendas.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-product.png")));
		menuBar.add(mnEncomendas);
		
		JMenuItem mntmAdicionar_2 = new JMenuItem("Adicionar");
		mntmAdicionar_2.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-add.png")));
		mntmAdicionar_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				CadastrarEncomenda1.main(null);
				
			}
		});
		mnEncomendas.add(mntmAdicionar_2);
		
		JMenuItem mntmGerenciar_2 = new JMenuItem("Gerenciar");
		mntmGerenciar_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				ge = new GerenciarEncomendas();
				desktopPane.removeAll();
				desktopPane.add(ge);
				ge.setLocation(0, 0);
				ge.show();
				
			}
		});
		mntmGerenciar_2.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-suporte.png")));
		mnEncomendas.add(mntmGerenciar_2);
		
		JMenuItem mntmFinalizarPedido = new JMenuItem("Finalizar Pedido");
		mntmFinalizarPedido.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				FinalizarEntrega1.main(null);
				
			}
		});
		
		JMenuItem mntmAlterarStatusDa = new JMenuItem("Alterar Status da Entrega");
		mntmAlterarStatusDa.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				AtualizarStatusEncomenda.main(null);
				
			}
		});
		mntmAlterarStatusDa.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-actualizar.png")));
		mnEncomendas.add(mntmAlterarStatusDa);
		mntmFinalizarPedido.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-entrega-25.png")));
		mnEncomendas.add(mntmFinalizarPedido);
		
		JMenuItem mntmEntregasFinalizadas = new JMenuItem("Entregas Finalizadas");
		mntmEntregasFinalizadas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				EntregasFinalizadas.main(null);
				
			}
		});
		mntmEntregasFinalizadas.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-lista-de-tarefas-25.png")));
		mnEncomendas.add(mntmEntregasFinalizadas);
		
		JMenu mnCategorias = new JMenu("Categorias");
		mnCategorias.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-cardápio.png")));
		menuBar.add(mnCategorias);
		
		JMenuItem mntmAdicionar_3 = new JMenuItem("Adicionar");
		mntmAdicionar_3.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-add.png")));
		mntmAdicionar_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ccat = new CadastrarCategoria();
				desktopPane.removeAll();
				desktopPane.add(ccat);
				ccat.setLocation(0, 0);
				ccat.show();
				
			}
		});
		mnCategorias.add(mntmAdicionar_3);
		
		JMenuItem mntmGerenciar_3 = new JMenuItem("Gerenciar");
		mntmGerenciar_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				gcat = new GerenciarCategorias();
				desktopPane.removeAll();
				desktopPane.add(gcat);
				gcat.setLocation(0, 0);
				gcat.show();
				
			}
		});
		mntmGerenciar_3.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-suporte.png")));
		mnCategorias.add(mntmGerenciar_3);
		
		JMenu mnMapas = new JMenu("Mapas");
		mnMapas.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-waypoint-map.png")));
		menuBar.add(mnMapas);
		
		JMenuItem mntmConsultarEndereo = new JMenuItem("Consultar no Mapa");
		mntmConsultarEndereo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Mapas.main(null);
				
			}
		});
		mntmConsultarEndereo.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-search-more.png")));
		mnMapas.add(mntmConsultarEndereo);
		
		JMenu mnConfiguraes = new JMenu("Configurações");
		mnConfiguraes.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-configurações-25.png")));
		menuBar.add(mnConfiguraes);
		
		JMenuItem mntmConfiguraesDoApp = new JMenuItem("Configurações do App");
		mntmConfiguraesDoApp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConfiguracoesApp.main(null);
			}
		});
		mntmConfiguraesDoApp.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-mixer-de-configurações-vertical-25.png")));
		mnConfiguraes.add(mntmConfiguraesDoApp);
		
		JMenuItem mntmDiretrioDosRelatrios = new JMenuItem("Diretório dos relatórios");
		mntmDiretrioDosRelatrios.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				SelectDir.main(null);
				
			}
		});
		mntmDiretrioDosRelatrios.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-abrir-pasta.png")));
		mnConfiguraes.add(mntmDiretrioDosRelatrios);
		
		JMenuItem mntmBancoDeDados = new JMenuItem("Banco de dados");
		mntmBancoDeDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Servidor.main(null);
			}
		});
		mntmBancoDeDados.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-banco-de-dados-25.png")));
		mnConfiguraes.add(mntmBancoDeDados);
		
		JMenu mnSobre = new JMenu("Sobre");
		mnSobre.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-sobre.png")));
		menuBar.add(mnSobre);
		
		JMenuItem mntmSobre = new JMenuItem("Sobre");
		mntmSobre.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				Sobre.main(null);
				
			}
		});
		mntmSobre.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-terms-and-conditions.png")));
		mnSobre.add(mntmSobre);
		
		JMenuItem mntmSaibaMais = new JMenuItem("Saiba Mais");
		mntmSaibaMais.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/icons8-link-externo.png")));
		mnSobre.add(mntmSaibaMais);
		frmSistemaDeCompras.getContentPane().setLayout(null);
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		desktopPane.setBounds(0, 0, 1046, 566);
		frmSistemaDeCompras.getContentPane().add(desktopPane);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/com/victor/icons/PartyExNoIcon.png")));
		lblNewLabel.setBounds(181, 82, 843, 397);
		desktopPane.add(lblNewLabel);
		
		
	}
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}
}
