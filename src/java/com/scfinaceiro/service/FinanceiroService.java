/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.service;

import com.scfinaceiro.dao.FinanceiroDAO;
import com.scfinaceiro.model.Financeiro;
import com.scfinanceiroClausulaSQL.ClausulaWhere;
import com.scfinanceiroClausulaSQL.GeneroCondicaoWhere;
import com.scfinanceiroClausulaSQL.OperacaoCondicaoWhere;
import com.scfinanceiroClausulaSQL.TipoCondicaoWhere;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Was
 */
public class FinanceiroService {

    public boolean salvarFinanceiro(Financeiro c) {
        FinanceiroDAO financeiroDAO = new FinanceiroDAO();
        if (c.getIdFinanceiro() == 0) {
            return financeiroDAO.salvarFinanceiro(c);

        } else {
            return financeiroDAO.alterarFinanceiro(c);
        }
    }

    public List<Financeiro> listarTodoFinaneiro(String nome, Date dataMovimento, String tipo) {
        FinanceiroDAO financeiroDAO = new FinanceiroDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, nome, TipoCondicaoWhere.Texto);
        if (dataMovimento != null) {
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "data_movimento", GeneroCondicaoWhere.igual, String.valueOf(dataMovimento), TipoCondicaoWhere.Texto);
        }
        if (tipo != "") {
            condicao.AdicionarCondicao(OperacaoCondicaoWhere.and, "tipo", GeneroCondicaoWhere.igual, tipo, TipoCondicaoWhere.Texto);
        }
        return financeiroDAO.getAllFinanceiros(condicao);
    }

    public Financeiro getFinanceiroById(long id) {
        FinanceiroDAO financeiroDAO = new FinanceiroDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "id_financeiro", GeneroCondicaoWhere.igual, String.valueOf(id), TipoCondicaoWhere.Numero);
        return financeiroDAO.getAllFinanceiros(condicao).get(0);
    }

    public boolean deletarFinanceiro(long id) {
        FinanceiroDAO financeiroDAO = new FinanceiroDAO();
        return financeiroDAO.deletarFinanceiro(id);
    }

}
