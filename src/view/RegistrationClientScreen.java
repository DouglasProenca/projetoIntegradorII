package view;

import controller.ClientDAO;
import model.Client;
import model.User;
import objects.Images;
import objects.InternalFrame;
import objects.TextField;
import objects.ValidateCpf;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;

@SuppressWarnings("serial")
public class RegistrationClientScreen extends InternalFrame implements DocumentListener {

	private JLabel lblName;
	private JLabel lblCPF;
	private JButton btnCheck;
	private JButton btnClose;
	private TextField txtName;
	private JFormattedTextField txtCPF;
	private JPanel panel;
	private final ValidateCpf valCPF = new ValidateCpf();
	private JCheckBox checkCEP;

	public RegistrationClientScreen() throws ParseException {
		super("Cadastrar Cliente", false, true, false, false, 500, 400);
		this.initComponents();
	}

	private void initComponents() throws ParseException {
		this.setLayout(null);
		this.getContentPane().add(getPanelCadastro());
	}

	private JPanel getPanelCadastro() throws ParseException {
		panel = new JPanel(null);
		panel.add(getTxtName());
		panel.setBounds(10, 10, 480, 380);
		panel.add(getLblName());
		panel.add(getTxtCPF());
		panel.add(getLblCPF());
		panel.add(getBtnClose());
		panel.add(getBtnCheck());
		panel.add(getCheckCEP());
		return panel;
	}

	private TextField getTxtName() {
		txtName = new TextField("Letter");
		txtName.setBounds(100, 30, 350, 25);
		txtName.getDocument().addDocumentListener(this);
		return txtName;
	}

	private JLabel getLblName() {
		lblName = new JLabel("Nome:");
		lblName.setBounds(25, 30, 40, 25);
		return lblName;
	}

	private JFormattedTextField getTxtCPF() throws ParseException {
	    txtCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCPF.setBounds(100, 80, 350, 25);
		txtCPF.getDocument().addDocumentListener(this);
		return txtCPF;
	}

	private JLabel getLblCPF() {
		lblCPF = new JLabel("CPF:");
		lblCPF.setBounds(25, 80, 230, 25);
		return lblCPF;
	}

	private JButton getBtnClose() {
		btnClose = new JButton("Cancelar", Images.CLOSE.getImage());
		btnClose.setBounds(250, 250, 200, 40);
		btnClose.addActionListener(this);
		btnClose.setActionCommand("close");
		return btnClose;
	}

	private JButton getBtnCheck() {
		btnCheck = new JButton("Salvar", Images.CHECK.getImage());
		btnCheck.setBounds(20, 250, 200, 40);
		btnCheck.addActionListener(this);
		btnCheck.setActionCommand("save");
		btnCheck.setEnabled(false);
		return btnCheck;
	}

	private JCheckBox getCheckCEP() {
		checkCEP = new JCheckBox("Adicionar CEP");
		checkCEP.setBounds(20, 120, 200, 30);
		return checkCEP;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		this.centralizaForm(frame);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "save":
			String cpf = txtCPF.getText().replaceAll("\\.", "").replaceAll("\\-", "");
			if (valCPF.isCPF(cpf)) {
				Client client = new Client(0, txtName.getText(), valCPF.imprimeCPF(cpf),
						String.valueOf(User.getInstance().getId()), new Date());
				boolean save = ClientDAO.getInstance().save(client);
				if (save) {
				JOptionPane.showMessageDialog(this, "Cliente salvo com sucesso!");
				} else {
					break;
				}
				if (checkCEP.isSelected()) {
					RegistrationCepScreen rbs = new RegistrationCepScreen();
					getParent().add(rbs);
					rbs.setVisible(true);
				}
				this.dispose();
			} else {
				JOptionPane.showMessageDialog(this, "CPF inválido!", "Erro de Cadastro", JOptionPane.ERROR_MESSAGE);
			}
			break;
		default:
			this.dispose();
			break;
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		btnCheck.setEnabled(warn());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		btnCheck.setEnabled(warn());
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		btnCheck.setEnabled(warn());
	}

	private boolean warn() {
		if (txtName.getText().length() >= 1
				&& txtCPF.getText().replaceAll("\\.", "").replaceAll("\\-", "").trim().length() >= 11) {
			return true;
		}
		return (false);
	}
}
