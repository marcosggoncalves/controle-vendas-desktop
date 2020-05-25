/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedor;
import br.com.projeto.model.Produto;
import br.com.projeto.model.Utilitarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcos Lopes G
 */
public class produtoDao {

    private Connection con;

    public produtoDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void save(Produto obj) {
        Utilitarios util = new Utilitarios();
        try {
            String sql = "insert into tb_produtos(descricao, preco, qtd_estoque, for_id) values (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());

            stmt.execute();
            stmt.close();

            util.alert("Sistema de controle PDV - Atenção", "Produto cadastrado com sucesso !");
        } catch (Exception e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar cadastro, erro encontrado:" + e);
        }
    }

    public void edit(Produto obj) {
        Utilitarios util = new Utilitarios();
        try {
            String sql = "update tb_produtos set descricao = ?, preco = ?, qtd_estoque = ?, for_id = ? where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getDescricao());
            stmt.setDouble(2, obj.getPreco());
            stmt.setInt(3, obj.getQtd_estoque());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setInt(5, obj.getId());

            stmt.execute();
            stmt.close();

            util.alert("Sistema de controle PDV - Atenção", "Produto alterado com sucesso !");
        } catch (Exception e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar alteração, erro encontrado:" + e);
        }
    }

    public void delete(Produto obj) {
        Utilitarios util = new Utilitarios();
        try {

            String sql = "delete from tb_produtos where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            util.alert("Sistema de controle PDV - Atenção", "Produto excluido com sucesso!");

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar exclusão do produto, erro encontrado:" + e);
        }
    }

    public List<Produto> listarProdutos() {
        Utilitarios util = new Utilitarios();
        try {
            List<Produto> lista = new ArrayList();

            String sql = "select*from tb_produtos p, tb_fornecedores f where  p.for_id = f.id";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {

            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel listar produtos, erro encontrado:" + e);

            return null;
        }
    }

    public List<Produto> buscaProdutoPorDescricao(String desc) {
        Utilitarios util = new Utilitarios();
        try {
            List<Produto> lista = new ArrayList();

            String sql = "select*from tb_produtos p, tb_fornecedores f where  p.for_id = f.id and p.descricao like ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, desc);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto obj = new Produto();
                Fornecedor f = new Fornecedor();

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {

            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }

    public Produto consultarPorDescricao(String desc) {
        Utilitarios util = new Utilitarios();
        try {

            String sql = "select*from tb_produtos p, tb_fornecedores f where  p.for_id = f.id and p.descricao like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, desc);

            ResultSet rs = stmt.executeQuery();

            Produto obj = new Produto();
            Fornecedor f = new Fornecedor();

            if (rs.next()) {

                obj.setId(rs.getInt("p.id"));
                obj.setDescricao(rs.getString("p.descricao"));
                obj.setPreco(rs.getDouble("p.preco"));
                obj.setQtd_estoque(rs.getInt("p.qtd_estoque"));

                f.setNome(rs.getString("f.nome"));
                obj.setFornecedor(f);

            }

            return obj;

        } catch (SQLException e) {

            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }
}
