package model.dao.impl;

import db.DB;
import db.DbException;
import model.entities.Usuario;

import java.sql.*;
import java.util.Scanner;

public class UsuarioDaoJDBC {

    private final Connection conn;
    Scanner entrada = new Scanner(System.in);

    public UsuarioDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    void menu() {

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

    public void loginUsuario(Usuario obj) {

        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");

            st.setString(1, obj.getLogin());
            st.setString(2, obj.getSenha());

            rs = st.executeQuery();

            if (rs.next()) {
                TelaCliente telaCliente1 = new TelaCliente();
                telaCliente1.MenuTelaCliente();
            } else {
                System.out.println("Login inválido.");
                System.out.println("Deseja tentar novamente?");
                System.out.println("a. Sim");
                System.out.println("b. Não");
                tentarNovamente = entrada.next();
                if (tentarNovamente.equalsIgnoreCase("b")) {
                    System.out.println("Deseja voltar ao menu inicial?");
                    System.out.println("a. Sim");
                    System.out.println("b. Não");
                    voltarTelaInicial = entrada.next();
                    if (voltarTelaInicial.equalsIgnoreCase("a")) {
                        Menu voltar = new Menu();
                        voltar.introMenu();
                    }
                } else {
                    System.out.println("Informe o login:");
                    String entradaLogin = entrada.next();
                    System.out.println("Informe a senha:");
                    String entradaSenha = entrada.next();
                    LoginUsuario(entradaLogin, entradaSenha);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void validarSenha(Usuario usuario) {

        while (!usuario.getSenha().equalsIgnoreCase(usuario.getConfirmacaoSenha())) {
            System.out.println("A senha informada é diferente da senha confirmada!");
            System.out.println("Confirme a senha:");
            usuario.setConfirmacaoSenha(entrada.next());
        }
    }
}
