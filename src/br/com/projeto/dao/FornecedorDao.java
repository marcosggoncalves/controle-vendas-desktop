/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Fornecedor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcos Lopes G
 */
public class FornecedorDao {

    private Connection con;

    public FornecedorDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void save(Fornecedor obj) {
        try {

            String sqlExist = "select*from tb_fornecedores where cnpj = ?  limit 1";

            PreparedStatement stmtExist = con.prepareStatement(sqlExist);
            stmtExist.setString(1, obj.getCpf());

            ResultSet cadastroExist = stmtExist.executeQuery();

            if (cadastroExist.next()) {
                JOptionPane.showMessageDialog(null, "Fornecedor já existe em nossa base de dados !");
            } else {
                String sql = "insert into tb_fornecedores (nome, cnpj, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getCnpj());
                stmt.setString(3, obj.getEmail());
                stmt.setString(4, obj.getTelefone());
                stmt.setString(5, obj.getCelular());
                stmt.setString(6, obj.getCep());
                stmt.setString(7, obj.getEndereco());
                stmt.setInt(8, obj.getNumero());
                stmt.setString(9, obj.getComplemento());
                stmt.setString(10, obj.getBairro());
                stmt.setString(11, obj.getCidade());
                stmt.setString(12, obj.getEstado());

                stmt.execute();
                stmt.close();

                JOptionPane.showMessageDialog(null, "Fornecedor cadastrado com sucesso !");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar cadastro, erro encontrado:" + e);
        }
    }

    public void edit(Fornecedor obj) {
        try {
            String sql = "update tb_fornecedores set nome =?, cnpj =?, email =?, telefone =?, celular =?, "
                    + "cep =?, endereco =?, numero =?, complemento =?, bairro =?, cidade =?, estado =? where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getEstado());
            stmt.setInt(13, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso !");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar cadastro, erro encontrado:" + e);
        }
    }

    public void delete(Fornecedor obj) {
        try {

            String sql = "delete from tb_fornecedores where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Fornecedor excluido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar exclusão do cadastro, erro encontrado:" + e);
        }
    }

    public List<Fornecedor> listarFornecedores() {
        try {
            List<Fornecedor> lista = new ArrayList();

            String sql = "select*from tb_fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Não foi possivel listar cadastro, erro encontrado:" + e);

            return null;
        }
    }
    
    public Fornecedor consultaPorNome(String nome){
        try {
            String sql = "select*from tb_fornecedores where nome like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Fornecedor obj = new Fornecedor();
            
            if(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
            }else{
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            }
            
            return obj;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar pesquisa, erro encontrado:" + e);
            
            return null;
        }
    }

    public List<Fornecedor> buscaFornecedorPorNome(String nome) {
        try {
            List<Fornecedor> lista = new ArrayList();

            String sql = "select*from tb_fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }
}
