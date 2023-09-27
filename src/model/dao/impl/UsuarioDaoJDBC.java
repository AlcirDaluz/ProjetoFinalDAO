package model.dao.impl;

import db.DB;
import db.DbException;
import model.entities.Usuario;

import java.sql.*;

public class UsuarioDaoJDBC {

    private final Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    public void insert(Usuario obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO usuario " + "(nome, sobrenome, login, senha, confirmacaosenha, adm) " + "VALUES " + "(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());
            st.setString(2, obj.getSobrenome());
            st.setString(3, obj.getLogin());
            st.setString(4, obj.getSenha());
            st.setString(5, obj.getConfirmacaoSenha());
            st.setBoolean(6, obj.getAdm());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getLocalizedMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
