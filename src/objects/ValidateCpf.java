package objects;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Douglas
 */
public class ValidateCpf {

    public ValidateCpf() {
    }

    public boolean isCPF(String CPF) {
    	Connection conexao = ConnectionManager.getInstance().getConexao();
    	
    	boolean isCPF = false;
    	
    	try {
			PreparedStatement instrucaoSQL = conexao.prepareStatement("SELECT dbo.fn_isCPF(?) AS isCPF");
			instrucaoSQL.setString(1, CPF);
			
			ResultSet rs = instrucaoSQL.executeQuery();
			
			while (rs.next()) {
				if(rs.getInt("isCPF") == 1) {
					isCPF = true;
				}
			}
			
		} catch (SQLException e) {
			return isCPF;
		}
    	return isCPF;
    }

    public String imprimeCPF(String CPF) {
        return (CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "."
                + CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }
}
