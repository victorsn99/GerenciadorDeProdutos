package com.victor.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FinalizarEntrega2 {

	private JFrame frmFinalizarEntrega;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinalizarEntrega2 window = new FinalizarEntrega2();
					window.frmFinalizarEntrega.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FinalizarEntrega2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFinalizarEntrega = new JFrame();
		frmFinalizarEntrega.setIconImage(Toolkit.getDefaultToolkit().getImage(FinalizarEntrega2.class.getResource("/com/victor/icons/icons8-verified-account.png")));
		frmFinalizarEntrega.setTitle("Finalizar Entrega - Fim");
		frmFinalizarEntrega.setBounds(100, 100, 450, 300);
		frmFinalizarEntrega.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFinalizarEntrega.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				frmFinalizarEntrega.dispose();
				
			}
		});
		btnNewButton.setIcon(new ImageIcon(FinalizarEntrega2.class.getResource("/com/victor/icons/icons8-selecionado-25.png")));
		btnNewButton.setBounds(111, 167, 212, 30);
		frmFinalizarEntrega.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Entrega(s) finalizada(s)");
		lblNewLabel.setIcon(new ImageIcon(FinalizarEntrega2.class.getResource("/com/victor/icons/icons8-verified-account.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 69, 414, 37);
		frmFinalizarEntrega.getContentPane().add(lblNewLabel);
		
		JLabel lblVocPodeConferir = new JLabel("Você pode conferir todas as entregas finalizadas clicando na aba");
		lblVocPodeConferir.setHorizontalAlignment(SwingConstants.CENTER);
		lblVocPodeConferir.setBounds(10, 208, 414, 14);
		frmFinalizarEntrega.getContentPane().add(lblVocPodeConferir);
		
		JLabel lblentregasFinalizadas = new JLabel("[entregas finalizadas]");
		lblentregasFinalizadas.setHorizontalAlignment(SwingConstants.CENTER);
		lblentregasFinalizadas.setBounds(10, 223, 414, 14);
		frmFinalizarEntrega.getContentPane().add(lblentregasFinalizadas);
		
		JButton btnGerarCupomFiscal = new JButton("Gerar cupom fiscal e fechar");
		btnGerarCupomFiscal.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		btnGerarCupomFiscal.setIcon(new ImageIcon(FinalizarEntrega2.class.getResource("/com/victor/icons/icons8-xml-de-miniatura-de-espaço-reservado.png")));
		btnGerarCupomFiscal.setBounds(111, 126, 212, 30);
		frmFinalizarEntrega.getContentPane().add(btnGerarCupomFiscal);
	}
	
	/*public void gerarTXT() {
		FileWriter arq;
		try {
			String nomeArq = JOptionPane.showInputDialog(null, "Insira o nome do txt", "Nome do TXT",
					JOptionPane.OK_CANCEL_OPTION);
			arq = new FileWriter("C:\\Users\\assun\\Documents\\Arquivos de teste java\\" + nomeArq + ".txt");
			PrintWriter gravarArq = new PrintWriter(arq);
			gravarArq.printf("-------CUPOM-FISCAL-------%n");
			gravarArq.printf("                      %n");
			for (int i = 0; i < listProdutos.size(); i++) {
				gravarArq.printf("-Cliente: %3s%n", listProdutos.get(i).getNomeCliente());
				gravarArq.printf("                      %n");
				gravarArq.printf("-ID: %3s%n-Codigo: %3s%n-Valor: %3s%n-Nome Produto: %3s%n",
						listProdutos.get(i).getId(), listProdutos.get(i).getCodigo(), listProdutos.get(i).getValor(),
						listProdutos.get(i).getNomeProduto());
				gravarArq.printf("------------------------------------%n");
			}
			gravarArq.printf("                      %n");
			gravarArq.printf("                      %n");
			gravarArq.printf("-Subtotal: %3s%n", totalCompra);
			gravarArq.printf("                      %n");
			gravarArq.printf("-Endereço de entrega: %3s%n", end.getEndereco());
			gravarArq.printf("-Frete: %3s%n", "email (EM BREVE AQUI)");
			gravarArq.printf("                      %n");
			gravarArq.printf("MercadoTech 1.57.555%n");

			arq.close();

			JOptionPane.showMessageDialog(null, "Os dados acima foram inseridos com sucesso em " + nomeArq + ".txt.",
					"Sucesso", JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "N�o foi poss�vel salvar os arquivos em .txt", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}
	}*/
}
