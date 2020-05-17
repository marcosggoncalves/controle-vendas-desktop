/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
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
public class ClientesDao {

    private Connection con;

    public ClientesDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void save(Cliente obj) {
        try {

            String sqlExist = "select*from tb_clientes where cpf = ?  limit 1";

            PreparedStatement stmtExist = con.prepareStatement(sqlExist);
            stmtExist.setString(1, obj.getCpf());

            ResultSet cadastroExist = stmtExist.executeQuery();

            if (cadastroExist.next()) {
                JOptionPane.showMessageDialog(null, "Cadastro já existe em nossa base de dados !");
            } else {
                String sql = "insert into tb_clientes (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement stmt = con.prepareStatement(sql);

                stmt.setString(1, obj.getNome());
                stmt.setString(2, obj.getRg());
                stmt.setString(3, obj.getCpf());
                stmt.setString(4, obj.getEmail());
                stmt.setString(5, obj.getTelefone());
                stmt.setString(6, obj.getCelular());
                stmt.setString(7, obj.getCep());
                stmt.setString(8, obj.getEndereco());
                stmt.setInt(9, obj.getNumero());
                stmt.setString(10, obj.getComplemento());
                stmt.setString(11, obj.getBairro());
                stmt.setString(12, obj.getCidade());
                stmt.setString(13, obj.getEstado());

                stmt.execute();
                stmt.close();

                JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso !");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar cadastro, erro encontrado:" + e);
        }
    }

    public void edit(Cliente obj) {
        try {
            String sql = "update tb_clientes set nome =?, rg =?, cpf =?, email =?, telefone =?, celular =?, "
                    + "cep =?, endereco =?, numero =?, complemento =?, bairro =?, cidade =?, estado =? where id =?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getRg());
            stmt.setString(3, obj.getCpf());
            stmt.setString(4, obj.getEmail());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCep());
            stmt.setString(8, obj.getEndereco());
            stmt.setInt(9, obj.getNumero());
            stmt.setString(10, obj.getComplemento());
            stmt.setString(11, obj.getBairro());
            stmt.setString(12, obj.getCidade());
            stmt.setString(13, obj.getEstado());
            stmt.setInt(14, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro alterado com sucesso !");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel alterar cadastro, erro encontrado:" + e);
        }
    }

    public void delete(Cliente obj) {
        try {

            String sql = "delete from tb_clientes where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastro excluido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar exclusão do cadastro, erro encontrado:" + e);
        }
    }

    public List<Cliente> listarClientes() {
        try {
            List<Cliente> lista = new ArrayList();

            String sql = "select*from tb_clientes";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
    
    public Cliente consultaPorNome(String nome){
        try {
            String sql = "select*from tb_clientes where nome like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            
            ResultSet rs = stmt.executeQuery();
            Cliente obj = new Cliente();
            
            if(rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
                JOptionPane.showMessageDialog(null, "Cadastro não encontrado!");
            }
            
            return obj;
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Não foi possivel realizar pesquisa, erro encontrado:" + e);
            
            return null;
        }
    }

    public List<Cliente> buscaClientePorNome(String nome) {
        try {
            List<Cliente> lista = new ArrayList();

            String sql = "select*from tb_clientes where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cliente obj = new Cliente();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
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
