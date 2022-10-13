package dao;

import dao.helper.DatabaseConverter;
import database.DatabaseConSingleton;
import lombok.extern.slf4j.Slf4j;
import model.Comprador;
import model.Usuario;
import model.Vendedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class VendedorDAO extends GenericDaoImpl<Vendedor> {
    private static final Connection conn = DatabaseConSingleton.getConn();

    public Vendedor getVendedorFromUsuario(Usuario user) {

        String selectionString = "SELECT * FROM " + getTableName() + " WHERE email = ?";

        ResultSet rs = null;

        try (PreparedStatement ps = conn.prepareStatement(selectionString)) {
            ps.setString(1, user.getEmail());
            rs = ps.executeQuery();

            if (rs.next()) {
                return DatabaseConverter.convertVendedor(rs);
            }

        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }

        log.error("Usuario nao encontrado");
        return null;
    }

    @Override
    protected String setTableName() {
        return "vendedor";
    }

    @Override
    public boolean save(Vendedor vendedor) throws SQLException {

        String insertString = "INSERT INTO " + getTableName() +
                "(nome,idade,sexo,cpf,email,password,telefone)" +
                " VALUES(?,?,?,?,?,?,?)";

        PreparedStatement pstm = null;

        try {
            //Cria um PreparedStatment, classe usada para executar a query
            pstm = conn.prepareStatement(insertString);

            pstm.setString(1, vendedor.getNome());
            pstm.setString(2, vendedor.getIdade());
            pstm.setString(3, vendedor.getSexo());
            pstm.setString(4, vendedor.getCpf());
            pstm.setString(5, vendedor.getEmail());
            pstm.setString(6, vendedor.getPassword()); // TODO add bcrypt
            pstm.setString(7, vendedor.getTelefone());

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


    @Override
    public Vendedor get(int id) throws SQLException {
        //TODO
        return null;
    }

    @Override
    public void update(Vendedor vendedor) throws SQLException {
        //TODO
    }

    @Override
    public void delete(int id) throws SQLException {
        //TODO
    }
}
