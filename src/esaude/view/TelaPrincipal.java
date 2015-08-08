package esaude.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import esaude.controller.Controller;
import esaude.util.LookAndFeel;

import javax.swing.LayoutStyle.ComponentPlacement;

public class TelaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private static JTextPane editorPane;
	private JButton btEnviar;
	private JButton btCancelar;
	private JTextField dataGeracao;
	private JLabel ldataGeracao;
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		initialize();
	}

	private void initialize() {
		controller = new Controller();
		new LookAndFeel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage(
				TelaPrincipal.class.getResource("/imagens/network.png")));
		setTitle("Exportador de dados para o e-SUS - v1.7.1");
		setBounds(100, 100, 618, 372);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.WHITE);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		this.ldataGeracao = new JLabel("Data de Geração do Arquivo:");
		this.dataGeracao = new JTextField(100);
		this.dataGeracao.setEditable(false);
		this.dataGeracao.setText(new SimpleDateFormat("dd/MM/yyyy")
				.format(new Date(System.currentTimeMillis())));
				this.btEnviar = new JButton("GERAR ARQUIVO THRIFT");
		this.btEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				enviar();
			}
		});
		this.btCancelar = new JButton("CANCELAR ARQUIVO THRIFT");
		this.btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelar();
			}

		});

		this.scrollPane = new JScrollPane();
		scrollPane.setAutoscrolls(true);

		editorPane = new JTextPane();
		editorPane.setEditable(false);
		DefaultCaret caret = (DefaultCaret) editorPane.getCaret();
		editorPane.setCaret(caret);

		this.scrollPane.setViewportView(editorPane);
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane
				.createParallelGroup(Alignment.LEADING)
				.addGroup(
						gl_contentPane.createSequentialGroup()
								.addComponent(ldataGeracao)
								.addComponent(dataGeracao).addContainerGap()
								.addComponent(btEnviar)
								.addComponent(btCancelar)
								.addContainerGap(511, Short.MAX_VALUE))
				.addComponent(scrollPane, Alignment.TRAILING,
						GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_contentPane
						.createSequentialGroup()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE,
								543, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(ldataGeracao).addComponent(dataGeracao)
						.addComponent(btEnviar).addComponent(btCancelar)
						.addGap(0)));
		contentPane.setLayout(gl_contentPane);
	}

	private void enviar() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		controller.geraArquivos();
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public static void enviaLog(String log) {
		editorPane.setText(editorPane.getText() + "\n" + log);
	}

	private void cancelar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/YYYY");
		Date dataGeracao;
		try {
			dataGeracao = sdf.parse(this.dataGeracao.getText());
			controller.cancelaEnvio(dataGeracao);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no cancelamento:" + e.getStackTrace());
		}
		
	}

}
