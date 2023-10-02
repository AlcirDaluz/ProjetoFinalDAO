package model.dao.impl;

import db.DB;
import db.DbException;
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
    Scanner entrada = new Scanner(System.in);
    ProdutoDao produtoDao = DaoFactory.createProdutoDao();
    UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void menu() {

        String opcao;

        System.out.println("a. Cadastrar Novo Usuário");
        System.out.println("b. Efetuar Login - Usuário");
        System.out.println("c. Efetuar Login - Administrativo");
        opcao = entrada.next();

        switch (opcao) {
            case "a", "A" -> {
                System.out.println("a. Cadastrar Novo Usuário Comum");
                System.out.println("b. Cadastrar Novo Usuário Administrativo");
                opcao = entrada.next();
                if (opcao.equalsIgnoreCase("a")) {

                    Usuario usuario = new Usuario();

                    System.out.println("Informe o nome:");
                    usuario.setNome(entrada.next());
                    System.out.println("Informe o sobrenome:");
                    usuario.setSobrenome(entrada.next());
                    System.out.println("Informe o login:");
                    usuario.setLogin(entrada.next());
                    System.out.println("Informe a senha:");
                    usuario.setSenha(entrada.next());
                    System.out.println("Confirme a senha:");
                    usuario.setConfirmacaoSenha(entrada.next());

                    validarSenha(usuario);

                    System.out.println("Usuário comum cadastrado com sucesso!");
                } else if (opcao.equalsIgnoreCase("b")) {

                    Usuario usuario = new Usuario();

                    System.out.println("Informe o nome:");
                    usuario.setNome(entrada.next());
                    System.out.println("Informe o sobrenome:");
                    usuario.setSobrenome(entrada.next());
                    System.out.println("Informe o login:");
                    usuario.setLogin(entrada.next());
                    System.out.println("Informe a senha:");
                    usuario.setSenha(entrada.next());
                    System.out.println("Confirme a senha:");
                    usuario.setConfirmacaoSenha(entrada.next());
                    usuario.setAdm(true);

                    validarSenha(usuario);
                    System.out.println("Usuário administrativo cadastrado com sucesso!");
                }
            }
            case "b", "B" -> {

                System.out.println("Informe o login:");
                String entradaLogin = entrada.next();
                System.out.println("Informe a senha:");
                String entradaSenha = entrada.next();

            }
            case "c", "C" -> {

                System.out.println("Informe o login:");
                String entradaLogin = entrada.next();
                System.out.println("Informe a senha:");
                String entradaSenha = entrada.next();

            }
            default -> System.out.println("Opção incorreta! Tente novamente!");
        }
    }

    @Override
    public void insert(Usuario obj) {

        PreparedStatement st = null;

        try {
            st = conn.prepareStatement("INSERT INTO usuario " + "(nome, sobrenome, login, senha, confirmacaosenha, adm) " + "VALUES " + "(?, ?, ?, ?, ?, ?)");

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

    @Override
    public void validarSenha(Usuario usuario) {

        while (!usuario.getSenha().equalsIgnoreCase(usuario.getConfirmacaoSenha())) {
            System.out.println("A senha informada é diferente da senha confirmada!");
            System.out.println("Confirme a senha:");
            usuario.setConfirmacaoSenha(entrada.next());
        }
    }

    @Override
    public void loginUsuario(Usuario obj) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");

            st.setString(1, obj.getLogin());
            st.setString(2, obj.getSenha());

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
    public void loginAdm(Usuario obj) {

    }
}
