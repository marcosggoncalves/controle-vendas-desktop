/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConnectionFactory;
import br.com.projeto.model.Cliente;
import br.com.projeto.model.Fornecedor;
import br.com.projeto.model.Funcionario;
import br.com.projeto.model.Utilitarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Marcos Lopes G
 */
public class EmailDao {

    private Connection con;

    public EmailDao() {
        this.con = new ConnectionFactory().getConnection();
    }

    public Cliente consultarEmailCliente(String email) {
        Utilitarios util = new Utilitarios();

        try {
            String sql = "select nome, email from tb_clientes where email like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            Cliente obj = new Cliente();

            if (rs.next()) {
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
            } else {
                util.alert("Sistema de controle PDV - Atenção", "Cliente não encontrado!");
            }

            return obj;

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }

    public Fornecedor consultarEmailFornecedor(String email) {
        Utilitarios util = new Utilitarios();

        try {
            String sql = "select nome, email from tb_fornecedores  where email like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            Fornecedor obj = new Fornecedor();

            if (rs.next()) {
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
            } else {
                util.alert("Sistema de controle PDV - Atenção", "Fornecedor não encontrado!");
            }

            return obj;

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }

    public Funcionario consultarEmailFuncionario(String email) {
        Utilitarios util = new Utilitarios();

        try {
            String sql = "select nome, email from tb_funcionarios where email like ? limit 1";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();

            Funcionario obj = new Funcionario();

            if (rs.next()) {
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
            } else {
                util.alert("Sistema de controle PDV - Atenção", "Funcionário não encontrado!");
            }

            return obj;

        } catch (SQLException e) {
            util.alert("Sistema de controle PDV - Atenção", "Não foi possivel realizar pesquisa, erro encontrado:" + e);

            return null;
        }
    }
}
