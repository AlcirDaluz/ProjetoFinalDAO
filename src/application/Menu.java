package application;

import model.dao.DaoFactory;
import model.dao.UsuarioDao;
import model.entities.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Menu {

    public Menu() {

    }

    public static void menu() {

        PreparedStatement st = null;
        ResultSet rs = null;
        Scanner entrada = new Scanner(System.in);
        String opcao;
        UsuarioDao usuarioDao = DaoFactory.createUsuarioDao();

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
                    usuario.setAdm("false");

                    usuarioDao.validarSenha(usuario);

                    usuarioDao.insertUsuario(usuario);

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
                    usuario.setAdm("true");

                    usuarioDao.validarSenha(usuario);

                    usuarioDao.insertUsuarioAdm(usuario);

                    System.out.println("Usuário administrativo cadastrado com sucesso!");
                }
            }
            case "b", "B" -> {

                System.out.println("Informe o login:");
                String entradaLogin = entrada.next();
                System.out.println("Informe a senha:");
                String entradaSenha = entrada.next();

                usuarioDao.loginUsuario(entradaLogin, entradaSenha);
            }
            case "c", "C" -> {

                System.out.println("Informe o login:");
                String entradaLogin = entrada.next();
                System.out.println("Informe a senha:");
                String entradaSenha = entrada.next();

                usuarioDao.loginAdm(entradaLogin, entradaSenha);
            }
            default -> System.out.println("Opção inexistente!");
        }
    }
}
