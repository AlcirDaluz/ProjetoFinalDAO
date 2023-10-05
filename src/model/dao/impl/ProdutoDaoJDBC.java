package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProdutoDao;
import model.entities.Produto;

import java.sql.*;

public class ProdutoDaoJDBC implements ProdutoDao {

    private final Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Produto obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO produto " + "(nome) " + "VALUES " + "(?)", Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getNome());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getLocalizedMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void SelectByNome(String nome) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM produto WHERE nome = ? " + "ORDER BY nome");

            st.setString(1, nome);

            rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("Produto encontrado!");
            } else {
                System.out.println("Produto não encontrado!");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void deleteByNome(String nome) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("DELETE FROM produto " + "WHERE nome = ?");

            st.setString(1, nome);

            st.executeUpdate();

            System.out.println("Produto deletado com sucesso!");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(String nomeAtual, String nomeAtualizado) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("UPDATE produto " + "SET nome = ? " + "WHERE nome = ?");

            st.setString(1, nomeAtualizado);
            st.setString(2, nomeAtual);

            st.executeUpdate();

            System.out.println("Produto atualizado!");
        } catch (SQLException e) {
            throw new DbException(e.getLocalizedMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
