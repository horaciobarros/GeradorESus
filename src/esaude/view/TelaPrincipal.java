package esaude.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.text.DefaultCaret;

import esaude.controller.Controller;
import esaude.util.LookAndFeel;

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
	private JCheckBox chbCadastroIndividual;
	private JCheckBox chbCadastroDomiciliar;
	private JCheckBox chbAtendimentoIndividual;
	private JCheckBox chbAtendimentoOdontologico;
	private JCheckBox chbAtividadeColetiva;
	private JCheckBox chbFichaProcedimento;
	private JCheckBox chbVisitaDomiciliar;
	private JCheckBox chbConsumoAlimentar;

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
		setTitle("Exportador de dados para o e-SUS - v2.1.04");
		setBounds(100, 100, 698, 445);
		this.contentPane = new JPanel();
		this.contentPane.setBackground(Color.WHITE);
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);

		this.ldataGeracao = new JLabel("Data de Geração do Arquivo:");
		this.dataGeracao = new JTextField(100);
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
		
		JPanel panel = new JPanel();
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(ldataGeracao)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(dataGeracao, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btEnviar, GroupLayout.PREFERRED_SIZE, 171, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btCancelar)
							.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 666, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ldataGeracao)
						.addComponent(dataGeracao, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btCancelar)
						.addComponent(btEnviar)))
		);
		
		chbCadastroIndividual = new JCheckBox("Gerar Cadastro Individual");
		chbCadastroIndividual.setSelected(true);
		
		chbCadastroDomiciliar = new JCheckBox("Gerar Cadastro Domiciliar");
		chbCadastroDomiciliar.setSelected(true);
		
		chbAtendimentoIndividual = new JCheckBox("Gerar Atendimento Individual");
		chbAtendimentoIndividual.setSelected(true);
		
		chbAtendimentoOdontologico = new JCheckBox("Gerar Atendimento Odontologico");
		chbAtendimentoOdontologico.setSelected(true);
		
		chbAtividadeColetiva = new JCheckBox("Gerar Atividade Coletiva");
		chbAtividadeColetiva.setSelected(true);
		
		chbFichaProcedimento = new JCheckBox("Gerar Ficha Procedimento");
		chbFichaProcedimento.setSelected(true);
		
		chbVisitaDomiciliar = new JCheckBox("Gerar Visita Domiciliar");
		chbVisitaDomiciliar.setSelected(true);
		
		chbConsumoAlimentar = new JCheckBox("Gerar Consumo Alimentar");
		chbConsumoAlimentar.setSelected(true);
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(chbAtendimentoOdontologico, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(chbConsumoAlimentar, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(chbAtendimentoIndividual, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
								.addComponent(chbCadastroIndividual)
								.addComponent(chbCadastroDomiciliar, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))
							.addGap(12)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(chbVisitaDomiciliar, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
								.addComponent(chbFichaProcedimento, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE)
								.addComponent(chbAtividadeColetiva, GroupLayout.PREFERRED_SIZE, 201, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(240, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chbCadastroIndividual)
						.addComponent(chbAtividadeColetiva))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chbCadastroDomiciliar)
						.addComponent(chbFichaProcedimento))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chbAtendimentoIndividual)
						.addComponent(chbVisitaDomiciliar))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(chbAtendimentoOdontologico)
						.addComponent(chbConsumoAlimentar))
					.addContainerGap(51, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		contentPane.setLayout(gl_contentPane);
	}

	private void enviar() {
		this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		enviaLog("== Iniciando processamento de envio ===");
		controller.geraArquivos(chbCadastroIndividual.isSelected(), chbCadastroDomiciliar.isSelected(), chbAtendimentoIndividual.isSelected(), chbAtendimentoOdontologico.isSelected(), chbAtividadeColetiva.isSelected(), chbFichaProcedimento.isSelected(), chbVisitaDomiciliar.isSelected(), chbConsumoAlimentar.isSelected());
		this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	public static void enviaLog(String log) {
		editorPane.setText(editorPane.getText() + "\n" + log);
	}

	private void cancelar() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dataGeracao;
		try {
			dataGeracao = sdf.parse(this.dataGeracao.getText());
			controller.cancelaEnvio(dataGeracao, chbCadastroIndividual.isSelected(), chbCadastroDomiciliar.isSelected(), chbAtendimentoIndividual.isSelected(), chbAtendimentoOdontologico.isSelected(), chbAtividadeColetiva.isSelected(), chbFichaProcedimento.isSelected(), chbVisitaDomiciliar.isSelected(), chbConsumoAlimentar.isSelected());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro no cancelamento");
		}
		
	}
}
