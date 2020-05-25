/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.Utilitarios;
import br.com.projeto.view.FrmLogin;
import br.com.projeto.view.FrmMenu;
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
public class FuncionarioDao {

    private Connection con;

    public FuncionarioDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public void save(Funcionario obj) {
        Utilitarios util = new Utilitarios();  
        try{
            String sqlExist = "select*from tb_funcionarios where cpf = ?  limit 1";

            PreparedStatement stmtExist = con.prepareStatement(sqlExist);
            stmtExist.setString(1, obj.getCpf());

            ResultSet cadastroExist = stmtExist.executeQuery();

            if (cadastroExist.next()) {
                util.alert("Sistema de controle PDV - Atenção", "Cadastro já existe em nossa base de dados !");
            } else {
                String sql = "insert into tb_funcionarios (nome, rg, cpf, email, telefone, celular, cep, endereco, numero, complemento, bairro, cidade, estado, senha, nivel_acesso, cargo)"
                        + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

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
                stmt.setString(14, obj.getSenha());
                stmt.setString(15, obj.getNivel_acesso());
                stmt.setString(16, obj.getCargo());

                stmt.execute();
                stmt.close();

                util.alert("Sistema de controle PDV - Atenção", "Cadastro realizado com sucesso !");
            }
        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar cadastro, erro encontrado:" + e);
        }
    }

    public void edit(Funcionario obj) {
        Utilitarios util = new Utilitarios();
        try{
            String sql = "update tb_funcionarios set nome =?, rg =?, cpf =?, email =?, telefone =?, celular =?, "
                    + "cep =?, endereco =?, numero =?, complemento =?, bairro =?, cidade =?, estado =?, senha=?, nivel_acesso=?, cargo=? where id =?";

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
            stmt.setString(14, obj.getSenha());
            stmt.setString(15, obj.getNivel_acesso());
            stmt.setString(16, obj.getCargo());
            stmt.setInt(17, obj.getId());

            stmt.execute();
            stmt.close();

            util.alert("Sistema de controle PDV - Atenção", "Cadastro alterado com sucesso !");

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel alterar cadastro, erro encontrado:" + e);
        }
    }

    public void delete(Funcionario obj) {
        Utilitarios util = new Utilitarios();
        
        try{

            String sql = "delete from tb_funcionarios where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getId());

            stmt.execute();
            stmt.close();

            util.alert("Sistema de controle PDV - Atenção", "Cadastro excluido com sucesso!");

        } catch (SQLException e) {
           util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar exclusão do cadastro, erro encontrado:" + e);
        }
    }

    public List<Funcionario> listarFuncionarios() {
        Utilitarios util = new Utilitarios();
        
        try{
            List<Funcionario> lista = new ArrayList();

            String sql = "select*from tb_funcionarios";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

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
                obj.setSenha(rs.getString("senha"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setCargo(rs.getString("cargo"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {

            util.alert("Sistema de controle PDV - Atenção", "Erro: " + e);

            return null;
        }
    }

    public Funcionario consultaPorNome(String nome) {
        Utilitarios util = new Utilitarios();
        
        try{
            String sql = "select*from tb_funcionarios where nome like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Funcionario obj = new Funcionario();

            if (rs.next()) {
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
                obj.setSenha(rs.getString("senha"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setCargo(rs.getString("cargo"));
            } else {
                util.alert("Sistema de controle PDV - Atenção", "Cadastro não encontrado!");
            }

            return obj;

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }

    public List<Funcionario> buscaClientePorNome(String nome) {
        
        Utilitarios util = new Utilitarios();
        
        try{
            List<Funcionario> lista = new ArrayList();

            String sql = "select*from tb_funcionarios where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Funcionario obj = new Funcionario();

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
                obj.setSenha(rs.getString("senha"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
                obj.setCargo(rs.getString("cargo"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException e) {

            util.alert("Sistema de controle PDV - Atenção", "Erro: " + e);

            return null;
        }
    }

    public void efetuarLogin(String email, String senha) {

        Utilitarios util = new Utilitarios();

        try{
            String sql = "select*from tb_funcionarios where email = ? and senha =?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, senha);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                util.alert("Sistema de controle PDV - Atenção", "Seja bem vindo ao Sistema");

                FrmMenu tela = new FrmMenu();
                tela.usuarioLogado = rs.getString("email");
                tela.setVisible(true);
            } else {
                util.alert("Sistema de controle PDV - Atenção", "Dados fornecido estão incorretos, tente novamente !");
                new FrmLogin().setVisible(true);
            }

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Erro: " + e);
        }
    }

    public void trocarSenha(String usuario, String senhaAntiga, String senhaNova, String confirmarSenha) {

        Utilitarios util = new Utilitarios();

        try{
            String sql = "select*from tb_funcionarios where senha = ? and email = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, senhaAntiga);
            stmt.setString(2, usuario);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (!confirmarSenha.equals(senhaNova)) {
                    util.alert("Sistema de controle PDV - Atenção", "Senhas não confere !");
                } else {
                    String sqlUpdate = "update tb_funcionarios set senha = ? where email = ?";

                    stmt = con.prepareStatement(sqlUpdate);
                    stmt.setString(1, senhaNova);
                    stmt.setString(2, usuario);
                    stmt.execute();
                    stmt.close();

                    util.alert("Sistema de controle PDV", "Senha alterada com sucesso !");

                    System.exit(0);
                }
            } else {
                util.alert("Sistema de controle PDV", "Senha antiga não confere !");
            }
        } catch (SQLException e) {
            util.alert("Sistema de controle PDV", "Erro: " + e);
        }
    }

    public void efetuarLogin(String email, char[] senha) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
