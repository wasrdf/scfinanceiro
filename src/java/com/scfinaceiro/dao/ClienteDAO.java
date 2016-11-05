/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.dao;

import com.scfinaceiro.conexao.Conexao;
import com.scfinaceiro.model.Cliente;
import com.scfinanceiroClausulaSQL.ClausulaWhere;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Was
 */
public class ClienteDAO {

    public boolean salvarCliente(Cliente c) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            String sql = " INSERT INTO CLIENTE (NOME,TELEFONE,EMAIL) VALUES (?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar inserir o cliente. \n Motivo: " + ex.getMessage());
        }

    }

    public boolean alterarCliente(Cliente c) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            String sql = "UPDATE CLIENTE SET NOME = ?, TELEFONE = ?,EMAIL = ? where id_cliente = ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, c.getNome());
            ps.setString(2, c.getTelefone());
            ps.setString(3, c.getEmail());
            ps.setLong(4, c.getIdCliente());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar alterar o cliente. \n Motivo: " + ex.getMessage());
        }

    }

    public List<Cliente> getAllClientes(ClausulaWhere sClausula) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        String sql = "SELECT * FROM CLIENTE " + sClausula.montarsClausula();
        List<Cliente> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setNome(rs.getString("nome"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setIdCliente(rs.getLong("id_cliente"));
                clientes.add(c);
            }

            rs.close();
            ps.close();
            return clientes;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar listar todos os cliente. \n Motivo: " + ex.getMessage());
        }
    }

    public boolean deletarCliente(long idCliente) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, idCliente);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar deletar este cliente. \n Motivo: " + ex.getMessage());
        }
    }

}
