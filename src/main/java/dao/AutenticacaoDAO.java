package dao;

import model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutenticacaoDAO {

    private Conexao con;
    private ResultSet rs;
    PreparedStatement ps;

    public AutenticacaoDAO(){
        con = new Conexao();
    }

    public Boolean autenticarUsuario(Usuario u){

        try{
            String query = "SELECT * FROM usuario WHERE email = '" + u.getEmail() + "' AND senha = '" + u.getSenha() + "'";
            ps = con.getConexao().prepareStatement(query);
            this.rs = ps.executeQuery();

            return rs.next();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }

        return false;

    }
}
