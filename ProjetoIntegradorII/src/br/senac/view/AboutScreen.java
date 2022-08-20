package br.senac.view;

import br.senac.model.User;
import br.senac.objects.InternalFrame;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Douglas
 */
public class AboutScreen extends InternalFrame {

    private JLabel Usuário_do_Sistema;
    private JLabel hostname;
    private JLabel os_name;
    private JLabel java_version;
    private JLabel user_name;
    private JLabel java_runtime_version;
    private JLabel os_arch;
    private JLabel JavaVmSpecificationVendor;
    private JLabel user_country;
    private JLabel user_dir;
    private JLabel java_home;
    private JPanel painel;
    private java.util.Properties p = System.getProperties();
    private JLabel lblUsuário_do_Sistema;
    private JLabel lblhostname;
    private JLabel lblos_name;
    private JLabel lbljava_version;
    private JLabel lbluser_name;
    private JLabel lbljava_runtime_version;
    private JLabel lblos_arch;
    private JLabel lblJavaVmSpecificationVendor;
    private JLabel lbluser_country;
    private JLabel lbluser_dir;
    private JLabel lbljava_home;

    public AboutScreen() {
        super("Informações", false, true, false, true, 549, 294);
        initComponents();

    }

    private void initComponents() {
        this.setLayout(null);
        this.getContentPane().add(getPainel());
    }


    private JPanel getPainel() {
        painel = new JPanel(null);
        painel.setSize(515, 240);
        painel.setLocation(10, 10);
        painel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        painel.add(getLbljava_home());
        painel.add(getLbluser_dir());
        painel.add(getLbluser_country());
        painel.add(getLblJavaVmSpecificationVendor());
        painel.add(getLblos_arch());
        painel.add(getlbLjava_runtime_version());
        painel.add(getLbluser_name());
        painel.add(getLbljava_version());
        painel.add(getLblos_name());
        painel.add(getLblhostname());
        painel.add(getLblUsuário_do_Sistema());
        painel.add(getJava_home());
        painel.add(getUser_dir());
        painel.add(getUser_country());
        painel.add(getJavaVmSpecificationVendor());
        painel.add(getOs_arch());
        painel.add(getJava_runtime_version());
        painel.add(getUser_name());
        painel.add(getJava_version());
        painel.add(getOs_name());
        painel.add(getHostname());
        painel.add(getUsuario_do_Sistema());
        return painel;
    }

    private JLabel getLbljava_home() {
        lbljava_home = new JLabel(p.getProperty("java.home"));
        lbljava_home.setBounds(190, 210, 240, 26);
        return lbljava_home;
    }

    private JLabel getLbluser_dir() {
        lbluser_dir = new JLabel(System.getProperty("user.dir"));
        lbluser_dir.setBounds(190, 190, 310, 26);
        return lbluser_dir;
    }

    private JLabel getLbluser_country() {
        lbluser_country = new JLabel(System.getProperty("user.country"));
        lbluser_country.setBounds(190, 170, 90, 26);
        return lbluser_country;
    }

    private JLabel getLblJavaVmSpecificationVendor() {
        lblJavaVmSpecificationVendor = new JLabel(p.getProperty("java.vm.specification.vendor"));
        lblJavaVmSpecificationVendor.setBounds(190, 150, 180, 26);
        return lblJavaVmSpecificationVendor;
    }

    private JLabel getLblos_arch() {
        lblos_arch = new JLabel(System.getProperty("os.arch"));
        lblos_arch.setBounds(190, 130, 70, 26);
        return lblos_arch;
    }

    private JLabel getlbLjava_runtime_version() {
        lbljava_runtime_version = new JLabel(p.getProperty("java.runtime.version"));
        lbljava_runtime_version.setBounds(190, 110, 130, 26);
        return lbljava_runtime_version;
    }

    private JLabel getLbluser_name() {
        lbluser_name = new JLabel(p.getProperty("user.name"));
        lbluser_name.setBounds(190, 90, 90, 26);
        return lbluser_name;
    }

    private JLabel getLbljava_version() {
        lbljava_version = new JLabel(p.getProperty("java.version"));
        lbljava_version.setBounds(190, 70, 90, 26);
        return lbljava_version;
    }

    private JLabel getLblos_name() {
        lblos_name = new JLabel(System.getProperty("os.name"));
        lblos_name.setBounds(190, 50, 90, 26);
        return lblos_name;
    }

    private JLabel getLblhostname() {
        lblhostname = new JLabel("hostname");
        lblhostname.setBounds(190, 30, 100, 26);
        InetAddress addr = null;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(MainScreen.desktopPane.getSelectedFrame(), ex.getMessage(),
                    "Aviso de Falha", JOptionPane.ERROR_MESSAGE);
        }
        String host = addr.getHostAddress();
        lblhostname.setText(host);
        return lblhostname;
    }

    private JLabel getLblUsuário_do_Sistema() {
        lblUsuário_do_Sistema = new JLabel();
        lblUsuário_do_Sistema.setBounds(190, 10, 130, 26);
        lblUsuário_do_Sistema.setText(User.getInstance().getUser());
        return lblUsuário_do_Sistema;
    }

    private JLabel getJava_home() {
        java_home = new JLabel("java.home");
        java_home.setBounds(10, 210, 100, 26);
        return java_home;
    }

    private JLabel getUser_dir() {
        user_dir = new JLabel("user.dir");
        user_dir.setBounds(10, 190, 90, 26);
        return user_dir;
    }

    private JLabel getUser_country() {
        user_country = new JLabel("user.country");
        user_country.setBounds(10, 170, 90, 26);
        return user_country;
    }

    private JLabel getJavaVmSpecificationVendor() {
        JavaVmSpecificationVendor = new JLabel("java.vm.specification.vendor");
        JavaVmSpecificationVendor.setBounds(10, 150, 180, 26);
        return JavaVmSpecificationVendor;
    }

    private JLabel getOs_arch() {
        os_arch = new JLabel("os.arch");
        os_arch.setBounds(10, 130, 70, 26);
        return os_arch;
    }

    private JLabel getJava_runtime_version() {
        java_runtime_version = new JLabel("java.runtime.version");
        java_runtime_version.setBounds(10, 110, 130, 26);
        return java_runtime_version;
    }

    private JLabel getUser_name() {
        user_name = new JLabel("user.name");
        user_name.setBounds(10, 90, 90, 26);
        return user_name;
    }

    private JLabel getJava_version() {
        java_version = new JLabel("java.version");
        java_version.setBounds(10, 70, 90, 26);
        return java_version;
    }

    private JLabel getOs_name() {
        os_name = new JLabel("os.name");
        os_name.setBounds(10, 50, 60, 26);
        return os_name;
    }

    private JLabel getHostname() {
        hostname = new JLabel("hostname");
        hostname.setBounds(10, 30, 60, 26);
        return hostname;
    }

    private JLabel getUsuario_do_Sistema() {
        Usuário_do_Sistema = new JLabel("Usuário do Sistema");
        Usuário_do_Sistema.setBounds(10, 10, 130, 26);
        return Usuário_do_Sistema;
    }

}
