package view;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.text.MaskFormatter;

import com.google.gson.Gson;

import controller.CepDAO;
import model.Cep;
import enums.Images;
import objects.InternalFrame;


public class RegistrationCepScreen extends InternalFrame {

	private static final long serialVersionUID = 1L;
	private JLabel lblInfoCep;
	private JFormattedTextField txtInfoCep;
	private JLabel lblLogradouro;
	private JTextField txtlogradouro;
	private JLabel lblBairro;
	private JTextField txtBairro;
	private JLabel lblCidade;
	private JTextField txtCidade;
	private JLabel lblUf;
	private JTextField txtUf;
	private JButton btnFind;
	private JLabel lblcomplemento;
	private JTextField txtComplemento;
	private JLabel lblNumber;
	private JTextField txtNumber;
	private JButton btnSave;

	public RegistrationCepScreen() {
		super("Consulta CEP", true, true, false, true, 400, 300);
		initComponents();
	}

	private void initComponents() {
		this.setLayout(null);
		this.add(getLblInfoCep());
		this.add(getLblLogradouro());
		this.add(getLblBairro());
		this.add(getLblCidade());
		this.add(getTxtInfoCep());
		this.add(getTxtlogradouro());
		this.add(getTxtBairro());
		this.add(getBtnFind());
		this.add(getTxtCidade());
		this.add(getLblUf());
		this.add(getTxtUf());
		this.add(getLblcomplemento());
		this.add(getTxtComplemento());
		this.add(getLblNumber());
		this.add(getTxtNumber());
		this.add(getBtnSave());
	}

	private JLabel getLblInfoCep() {
		lblInfoCep = new JLabel("Informe o CEP:");
		lblInfoCep.setBounds(10, 10, 90, 30);
		return lblInfoCep;
	}

	private JLabel getLblLogradouro() {
		lblLogradouro = new JLabel("Logradouro:");
		lblLogradouro.setBounds(10, 45, 70, 30);
		return lblLogradouro;
	}

	private JLabel getLblBairro() {
		lblBairro = new JLabel("Bairro:");
		lblBairro.setBounds(10, 80, 40, 30);
		return lblBairro;
	}

	private JLabel getLblCidade() {
		lblCidade = new JLabel("Cidade:");
		lblCidade.setBounds(10, 115, 50, 30);
		return lblCidade;
	}

	private JFormattedTextField getTxtInfoCep() {
		try {
			txtInfoCep = new JFormattedTextField(new MaskFormatter("#####-###"));
		} catch (ParseException ex) {
			Logger.getLogger(SaleScreen.class.getName()).log(Level.SEVERE, null, ex);
		}
		txtInfoCep.setBounds(100, 10, 100, 30);
		txtInfoCep.addKeyListener(this);
		return txtInfoCep;
	}

	private JTextField getTxtlogradouro() {
		txtlogradouro = new JTextField();
		txtlogradouro.setBounds(80, 45, 300, 30);
		txtlogradouro.setEnabled(false);
		return txtlogradouro;
	}

	private JTextField getTxtBairro() {
		txtBairro = new JTextField();
		txtBairro.setBounds(50, 80, 330, 30);
		txtBairro.setEnabled(false);
		return txtBairro;
	}

	private JTextField getTxtCidade() {
		txtCidade = new JTextField();
		txtCidade.setBounds(55, 115, 250, 30);
		txtCidade.setEnabled(false);
		return txtCidade;
	}

	private JLabel getLblUf() {
		lblUf = new JLabel("UF:");
		lblUf.setBounds(320, 115, 30, 30);
		return lblUf;
	}

	private JTextField getTxtUf() {
		txtUf = new JTextField();
		txtUf.setBounds(340, 115, 40, 30);
		txtUf.setEnabled(false);
		return txtUf;
	}

	private JLabel getLblcomplemento() {
		lblcomplemento = new JLabel("Complemento:");
		lblcomplemento.setBounds(10, 150, 80, 30);
		return lblcomplemento;
	}

	private JTextField getTxtComplemento() {
		txtComplemento = new JTextField();
		txtComplemento.setBounds(100, 150, 170, 30);
		return txtComplemento;
	}

	private JLabel getLblNumber() {
		lblNumber = new JLabel("Nº:");
		lblNumber.setBounds(280, 150, 30, 30);
		return lblNumber;
	}

	private JTextField getTxtNumber() {
		txtNumber = new JTextField();
		txtNumber.setBounds(300, 150, 80, 30);
		return txtNumber;
	}

	private JButton getBtnFind() {
		btnFind = new JButton("Pesquisar");
		btnFind.setBounds(220, 10, 160, 30);
		btnFind.addActionListener(this);
		btnFind.setActionCommand("Find");
		return btnFind;
	}

	private JButton getBtnSave() {
		btnSave = new JButton("Salvar", Images.CHECK.getImage());
		btnSave.setBounds(10, 200, 370, 50);
		btnSave.addActionListener(this);
		btnSave.setActionCommand("save");
		return btnSave;
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		InternalFrame frame = (InternalFrame) e.getSource();
		this.centralizaForm(frame);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == Event.ENTER) {
			this.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "Find"));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Find":
			try {
				URL url = new URL("https://viacep.com.br/ws/" + txtInfoCep.getText().replace("-", "") + "/json/");

				BufferedReader br = new BufferedReader(
						new InputStreamReader(url.openConnection().getInputStream(), StandardCharsets.UTF_8));

				String cepAux = "";
				StringBuilder jsonCep = new StringBuilder();

				while ((cepAux = br.readLine()) != null) {
					if (cepAux.contains("\"erro\": true")) {
						JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), "Cep Não existe!",
								"Aviso de Falha", JOptionPane.ERROR_MESSAGE);
						break;
					} else {
						jsonCep.append(cepAux);
					}
				}

				Cep cep = new Gson().fromJson(jsonCep.toString(), Cep.class);

				txtlogradouro.setText(cep.getLogradouro());
				txtBairro.setText(cep.getBairro());
				txtCidade.setText(cep.getLocalidade());
				txtUf.setText(cep.getUf());
				txtComplemento.setText(cep.getComplemento());

			} catch (IOException ex) {
				JOptionPane.showMessageDialog(MainScreen.getInstance().getDesktopPane().getSelectedFrame(), ex.getMessage(),
						"Aviso de Falha", JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "save":
			Cep cep = new Cep(txtInfoCep.getText(), txtlogradouro.getText(), txtBairro.getText(), txtCidade.getText(),
					txtUf.getText(), txtComplemento.getText());
			if (CepDAO.getInstance().save(cep) == true) {
				JOptionPane.showMessageDialog(this, "Endereço Salvo com sucesso");
			}
			this.dispose();
			break;
		}
	}
}
