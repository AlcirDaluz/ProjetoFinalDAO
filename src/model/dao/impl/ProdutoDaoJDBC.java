package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.ProdutoDao;
import model.entities.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoDaoJDBC implements ProdutoDao {

    private final Connection conn;
    Scanner entrada = new Scanner(System.in);

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
    public List<Produto> SelectByNome(Produto obj) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT produto.* FROM produto WHERE nome = ? " + "ORDER BY Name");

            st.setString(1, obj.getNome());

            rs = st.executeQuery();

            List<Produto> list = new ArrayList<>();

            while (rs.next()) {
                Produto produto = new Produto();
                list.add(produto);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void deleteByNome(Produto obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("DELETE FROM produto " + "WHERE nome = ?");

            st.setString(1, obj.getNome());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Produto obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("UPDATE produto " + "SET nome = ? " + "WHERE nome = ?");

            st.setString(1, obj.getNome());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getLocalizedMessage());
        } finally {
            DB.closeStatement(st);
        }
    }
}
