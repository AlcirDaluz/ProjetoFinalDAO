package model.dao.impl;

import application.Menu;
import db.DB;
import db.DbException;
import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import model.dao.UsuarioDao;
import model.entities.Produto;
import model.entities.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UsuarioDaoJDBC implements UsuarioDao {

    private final Connection conn;

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insertUsuario(Usuario obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO usuario " + "(nome, sobrenome, login, senha, confirmacaosenha) " + "VALUES " + "(?, ?, ?, ?, ?)");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getSobrenome());
            st.setString(3, obj.getLogin());
            st.setString(4, obj.getSenha());
            st.setString(5, obj.getConfirmacaoSenha());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getLocalizedMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void insertUsuarioAdm(Usuario obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO usuario " + "(nome, sobrenome, login, senha, confirmacaosenha, adm) " + "VALUES " + "(?, ?, ?, ?, ?, ?)");

            st.setString(1, obj.getNome());
            st.setString(2, obj.getSobrenome());
            st.setString(3, obj.getLogin());
            st.setString(4, obj.getSenha());
            st.setString(5, obj.getConfirmacaoSenha());
            st.setString(6, obj.getAdm());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getLocalizedMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void validarSenha(Usuario usuario) {

        Scanner entrada = new Scanner(System.in);

        while (!usuario.getSenha().equals(usuario.getConfirmacaoSenha())) {
            System.out.println("A senha informada é diferente da senha confirmada!");
            System.out.println("Confirme a senha:");
            usuario.setConfirmacaoSenha(entrada.next());
        }
    }

    @Override
    public void loginUsuario(String login, String senha) {

        PreparedStatement st = null;
        ResultSet rs = null;
        Scanner entrada = new Scanner(System.in);

        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");

            st.setString(1, login);
            st.setString(2, senha);

            rs = st.executeQuery();

            if (rs.next()) {
                String opcaoMenuCliente;
                System.out.println("a. Cadastrar novo produto");
                opcaoMenuCliente = entrada.next();
                if (opcaoMenuCliente.equalsIgnoreCase("a")) {
                    String nomeProduto;
                    System.out.println("Insira o nome do produto que deseja cadastrar:");
                    nomeProduto = entrada.next();
                    Produto p = new Produto(nomeProduto);
                    ProdutoDao produtoDao = DaoFactory.createProdutoDao();
                    produtoDao.insert(p);
                    System.out.println("Produto cadastrado com sucesso!");
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void loginAdm(String login, String senha) {

        PreparedStatement st = null;
        ResultSet rs = null;
        Scanner entrada = new Scanner(System.in);

        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ? AND adm = 'true'");

            st.setString(1, login);
            st.setString(2, senha);

            rs = st.executeQuery();

            if (rs.next()) {
                String opcaoMenuAdmin;
                System.out.println("a. Cadastrar novo produto");
                System.out.println("b. Buscar produto");
                System.out.println("c. Remover produto");
                System.out.println("d. Atualizar dados de produto existente");
                opcaoMenuAdmin = entrada.next();
                switch (opcaoMenuAdmin) {
                    case "a", "A":
                        String nomeProduto;
                        System.out.println("Insira o nome do produto que deseja cadastrar:");
                        nomeProduto = entrada.next();
                        Produto p = new Produto(nomeProduto);
                        ProdutoDao produtoDao = DaoFactory.createProdutoDao();
                        produtoDao.insert(p);
                        System.out.println("Produto cadastrado com sucesso!");
                        break;
                    case "b", "B":
                        String nomeProdutoSelect;
                        System.out.println("Insira o nome do produto que deseja buscar:");
                        nomeProdutoSelect = entrada.next();
                        ProdutoDao produtoDaoSelect = DaoFactory.createProdutoDao();
                        produtoDaoSelect.SelectByNome(nomeProdutoSelect);
                        break;
                }
            } else {
                System.out.println("Este usuário não é administrador, você será redirecionado ao menu inicial");
                Menu.menu();
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
