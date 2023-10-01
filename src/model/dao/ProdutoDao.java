package model.dao;

import model.entities.Produto;

import java.util.List;

public interface ProdutoDao {

    void insert(Produto obj);

    List<Produto> SelectByNome(Produto obj);

    void deleteByNome(Produto obj);

    void update(Produto obj);
}
