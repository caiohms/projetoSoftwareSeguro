package dao;

import dao.helper.DatabaseConverter;
import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;
import model.Adm;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class AdmDAO extends GenericDaoImpl<Adm> {

    private static final Connection conn = DatabaseConSingleton.getConn();

    public Adm getAdmFromUsuario(Usuario user) {

        String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();

            if (rs.next()) {
                return DatabaseConverter.convertAdm(rs);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        log.error("Usuario nao encontrado");
        return null;
    }

    @Override
    protected String setTableName() {
        return "adm";
    }

    public boolean save(Adm adm) throws SQLException {

        String insertString = "INSERT INTO " + getTableName() +
                "(email,password)" +
                " VALUES(?,?)";

        PreparedStatement pstm = null;

        try {
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(insertString);

            pstm.setString(5, adm.getEmail());
            pstm.setString(6, adm.getPassword()); // TODO add bcrypt

            log.info("Cadastrando usuario :: " + pstm);

            //Executa a sql para inserção dos dados
            pstm.execute();

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        } finally {
            if (pstm != null)
                pstm.close();
        }

        return true;
    }
    public Adm get(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public boolean update(Adm adm, int id) throws SQLException {
        return true;
    }


    @Override
    public void delete(int id) throws SQLException {

    }

}