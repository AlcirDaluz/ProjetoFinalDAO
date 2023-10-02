package model.dao;

import db.DB;
import model.dao.impl.ProdutoDaoJDBC;
import model.dao.impl.UsuarioDaoJDBC;

public class DaoFactory {

    public static ProdutoDao createProdutoDao() {
        return new ProdutoDaoJDBC(DB.getConnection());
    }

    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJDBC(DB.getConnection());
    }
}