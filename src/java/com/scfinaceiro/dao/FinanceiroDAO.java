/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.dao;

import com.scfinaceiro.conexao.Conexao;
import com.scfinaceiro.model.Financeiro;
import com.scfinanceiroClausulaSQL.ClausulaWhere;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Was
 */
public class FinanceiroDAO {

    public boolean salvarFinanceiro(Financeiro c) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            String sql = " INSERT INTO Financeiro (id_cliente,valor,tipo,forma_pagamento,data_movimento,historico,situacao) VALUES (?,?,?,?,?,?,?) ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, c.getIdCliente());
            ps.setBigDecimal(2, c.getValor());
            ps.setString(3, c.getTipo());
            ps.setString(4, c.getFormaPagamento());
            try {
                ps.setDate(5, new Date(c.getDataMovimento().getTime()));
            } catch (Exception e) {
                ps.setDate(5, null);
            }
            ps.setString(6, c.getHistorico());
            ps.setString(7, c.getSituacao());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar inserir o financeiro. \n Motivo: " + ex.getMessage());
        }

    }

    public boolean alterarFinanceiro(Financeiro c) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            String sql = "UPDATE Financeiro SET id_cliente = ?,valor = ?,tipo = ?,forma_pagamento = ?, data_movimento = ?,historico =?,situacao = ? where id_financeiro = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, c.getIdCliente());
            ps.setBigDecimal(2, c.getValor());
            ps.setString(3, c.getTipo());
            ps.setString(4, c.getFormaPagamento());
            try {
                ps.setDate(5, new Date(c.getDataMovimento().getTime()));
            } catch (Exception e) {
                ps.setDate(5, null);
            }
            ps.setString(6, c.getHistorico());
            ps.setString(7,c.getSituacao());
            ps.setLong(8, c.getIdFinanceiro());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar alterar o financeiro. \n Motivo: " + ex.getMessage());
        }

    }

    public List<Financeiro> getAllFinanceiros(ClausulaWhere sClausula) {
        Conexao conexao = new Conexao();
        Connection conn = conexao.conectar();
        String sql = " SELECT financeiro.*,cliente.nome\n"
                + " FROM financeiro inner join cliente on cliente.id_cliente = financeiro.id_cliente" + sClausula.montarsClausula();
        System.out.println(sql);
        List<Financeiro> clientes = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Financeiro c = new Financeiro();
                c.setIdFinanceiro(rs.getLong("id_financeiro"));
                c.setIdCliente(rs.getLong("id_cliente"));
                c.setTipo(rs.getString("tipo"));
                c.setFormaPagamento(rs.getString("forma_pagamento"));
                c.setValor(rs.getBigDecimal("valor"));
                c.setDataMovimento(rs.getDate("data_movimento"));
                c.setClienteNome(rs.getString("nome"));
                c.setHistorico(rs.getString("historico"));
                c.setSituacao(rs.getString("situacao"));
                clientes.add(c);
            }

            rs.close();
            ps.close();
            return clientes;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar listar todos os financeiro. \n Motivo: " + ex.getMessage());
        }
    }

    public boolean deletarFinanceiro(long idFinanceiro) {
        try {
            Conexao conexao = new Conexao();
            Connection conn = conexao.conectar();
            String sql = "DELETE FROM Financeiro WHERE ID_Financeiro = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, idFinanceiro);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao tentar deletar este financeiro. \n Motivo: " + ex.getMessage());
        }
    }

}
