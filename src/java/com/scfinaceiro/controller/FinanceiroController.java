/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.controller;

import com.scfinaceiro.model.Cliente;
import com.scfinaceiro.model.Financeiro;
import com.scfinaceiro.service.ClienteService;
import com.scfinaceiro.service.FinanceiroService;
import com.scfinaceiro.util.FacesUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Was
 */
@ManagedBean
@ViewScoped
public class FinanceiroController {

    private Financeiro financeiro;
    private List<Financeiro> listaFinanceiro;
    private Date dataMovimento;
    private long idFinanceiro;
    private final FinanceiroService financeiroService;
    private final ClienteService clienteService;
    private List<Cliente> clientes;
    private String filtroNome = "";
    private String tipo = "";

    public FinanceiroController() {
        this.financeiro = new Financeiro();
        this.listaFinanceiro = new ArrayList<>();
        this.financeiroService = new FinanceiroService();
        this.clientes = new ArrayList<>();
        this.clienteService = new ClienteService();

    }

    public void salvarFinanceiro() {
        if (financeiroService.salvarFinanceiro(financeiro)) {
            FacesUtil.saveMessage("Financeiro salvo com sucesso!", "Sucesso");
            limparCampos();
        } else {
            FacesUtil.saveMessage("Ocorreu um erro inesperado ao tentar inserir o financeiro, tente novamente.", "");
        }
    }

    public void listarFinanceiro() {
        if (dataMovimento != null) {
            listaFinanceiro = financeiroService.listarTodoFinaneiro(filtroNome, new java.sql.Date(dataMovimento.getTime()), tipo);
        } else {
            listaFinanceiro = financeiroService.listarTodoFinaneiro(filtroNome,null, tipo);
        }
    }

    public void carregarSelectOneMenuCliente() {
        clientes = clienteService.getAllClientes("");
    }

    public void deletarFinanceiro(long id) {
        if (financeiroService.deletarFinanceiro(id)) {
            FacesUtil.saveMessage("Financeiro deletado com sucesso!", "Sucesso");
            limparCampos();
        } else {
            FacesUtil.saveMessage("Ocorreu um erro inesperado ao tentar deletar este financeiro", "");
        }
    }

    public void limparCampos() {
        financeiro = new Financeiro();
        listarFinanceiro();
    }

    public Financeiro getFinanceiro() {
        return financeiro;
    }

    public void setFinanceiro(Financeiro financeiro) {
        this.financeiro = financeiro;
    }

    public List<Financeiro> getListaFinanceiro() {
        return listaFinanceiro;
    }

    public void setListaFinanceiro(List<Financeiro> listaFinanceiro) {
        this.listaFinanceiro = listaFinanceiro;
    }

    public Date getDataMovimento() {
        return dataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        this.dataMovimento = dataMovimento;
    }

    public long getIdFinanceiro() {
        return idFinanceiro;
    }

    public void setIdFinanceiro(long idFinanceiro) {
        this.idFinanceiro = idFinanceiro;
        if (idFinanceiro != 0) {
            financeiro = financeiroService.getFinanceiroById(idFinanceiro);
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getFiltroNome() {
        return filtroNome;
    }

    public void setFiltroNome(String filtroNome) {
        this.filtroNome = filtroNome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
