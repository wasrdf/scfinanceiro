/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.service;

import com.scfinaceiro.dao.ClienteDAO;
import com.scfinaceiro.model.Cliente;
import com.scfinanceiroClausulaSQL.ClausulaWhere;
import com.scfinanceiroClausulaSQL.GeneroCondicaoWhere;
import com.scfinanceiroClausulaSQL.OperacaoCondicaoWhere;
import com.scfinanceiroClausulaSQL.TipoCondicaoWhere;
import java.util.List;

/**
 *
 * @author Was
 */
public class ClienteService {

    

    public boolean salvarCliente(Cliente c) {
        ClienteDAO clienteDAO = new ClienteDAO();
        if (c.getIdCliente() == 0) {
            return clienteDAO.salvarCliente(c);

        } else {
            return clienteDAO.alterarCliente(c);
        }
    }

    public List<Cliente> getAllClientes(String nome) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "nome", GeneroCondicaoWhere.contem, nome, TipoCondicaoWhere.Texto);
        return clienteDAO.getAllClientes(condicao);
    }
    
    
      public Cliente getClienteById(long id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        ClausulaWhere condicao = new ClausulaWhere();
        condicao.AdicionarCondicao(OperacaoCondicaoWhere.vazio, "id_cliente", GeneroCondicaoWhere.igual, String.valueOf(id), TipoCondicaoWhere.Numero);
        return clienteDAO.getAllClientes(condicao).get(0);
    }

    public boolean deletarCliente(long id) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.deletarCliente(id);
    }

}
