package br.senac.view;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Douglas
 */
@SuppressWarnings("serial")
public class AboutScreen extends JInternalFrame {

    private final JLabel Usuário_do_Sistema = new JLabel("Usuário do Sistema");
    private final JLabel hostname = new JLabel("hostname");
    private final JLabel os_name = new JLabel("os.name");
    private final JLabel java_version = new JLabel("java.version");
    private final JLabel user_name = new JLabel("user.name");
    private final JLabel java_runtime_version = new JLabel("java.runtime.version");
    private final JLabel os_arch = new JLabel("os.arch");
    private final JLabel JavaVmSpecificationVendor = new JLabel("java.vm.specification.vendor");
    private final JLabel user_country = new JLabel("user.country");
    private final JLabel user_dir = new JLabel("user.dir");
    private final JLabel java_home = new JLabel("java.home");
    private final JPanel painel = new JPanel(null);

    private java.util.Properties p = System.getProperties();
    private final JLabel lblUsuário_do_Sistema = new JLabel();
    private final JLabel lblhostname = new JLabel("hostname");
    private final JLabel lblos_name = new JLabel(System.getProperty("os.name"));
    private final JLabel lbljava_version = new JLabel(p.getProperty("java.version"));
    private final JLabel lbluser_name = new JLabel(p.getProperty("user.name"));
    private final JLabel lbljava_runtime_version = new JLabel(p.getProperty("java.runtime.version"));
    private final JLabel lblos_arch = new JLabel(System.getProperty("os.arch"));
    private final JLabel lblJavaVmSpecificationVendor = new JLabel(p.getProperty("java.vm.specification.vendor"));
    private final JLabel lbluser_country = new JLabel(System.getProperty("user.country"));
    private final JLabel lbluser_dir = new JLabel(System.getProperty("user.dir"));
    private final JLabel lbljava_home = new JLabel(p.getProperty("java.home"));

    public AboutScreen(String user) {
        super("Informações", false, true, false, true);
        setFrameIcon(new ImageIcon(this.getClass().getResource("/Resources/System-computer-icon.png")));
        lblUsuário_do_Sistema.setText(user);
        try {
            initComponents();
            setLayout(null);
            setSize(549, 294);
        } catch (UnknownHostException ex) {
            Logger.getLogger(AboutScreen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComponents() throws UnknownHostException {
        getContentPane().add(painel);
        InetAddress addr = InetAddress.getLocalHost();
        String host = addr.getHostAddress();
        lblhostname.setText(host);

        painel.setSize(515, 240);
        painel.setLocation(10, 10);
        painel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painel.add(Usuário_do_Sistema);
        Usuário_do_Sistema.setBounds(10, 10, 130, 26);
        painel.add(hostname);
        hostname.setBounds(10, 30, 60, 26);
        painel.add(os_name);
        os_name.setBounds(10, 50, 60, 26);
        painel.add(java_version);
        java_version.setBounds(10, 70, 90, 26);
        painel.add(user_name);
        user_name.setBounds(10, 90, 90, 26);
        painel.add(java_runtime_version);
        java_runtime_version.setBounds(10, 110, 130, 26);
        painel.add(os_arch);
        os_arch.setBounds(10, 130, 70, 26);
        painel.add(JavaVmSpecificationVendor);
        JavaVmSpecificationVendor.setBounds(10, 150, 180, 26);
        painel.add(user_country);
        user_country.setBounds(10, 170, 90, 26);
        painel.add(user_dir);
        user_dir.setBounds(10, 190, 90, 26);
        painel.add(java_home);
        java_home.setBounds(10, 210, 100, 26);

        painel.add(lblUsuário_do_Sistema);
        lblUsuário_do_Sistema.setBounds(190, 10, 130, 26);
        painel.add(lblhostname);
        lblhostname.setBounds(190, 30, 100, 26);
        painel.add(lblos_name);
        lblos_name.setBounds(190, 50, 90, 26);
        painel.add(lbljava_version);
        lbljava_version.setBounds(190, 70, 90, 26);
        painel.add(lbluser_name);
        lbluser_name.setBounds(190, 90, 90, 26);
        painel.add(lbljava_runtime_version);
        lbljava_runtime_version.setBounds(190, 110, 130, 26);
        painel.add(lblos_arch);
        lblos_arch.setBounds(190, 130, 70, 26);
        painel.add(lblJavaVmSpecificationVendor);
        lblJavaVmSpecificationVendor.setBounds(190, 150, 180, 26);
        painel.add(lbluser_country);
        lbluser_country.setBounds(190, 170, 90, 26);
        painel.add(lbluser_dir);
        lbluser_dir.setBounds(190, 190, 310, 26);
        painel.add(lbljava_home);
        lbljava_home.setBounds(190, 210, 240, 26);
        pack();
    }
}
