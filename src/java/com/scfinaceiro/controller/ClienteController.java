/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scfinaceiro.controller;

import com.scfinaceiro.model.Cliente;
import com.scfinaceiro.service.ClienteService;
import com.scfinaceiro.util.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Was
 */
@ManagedBean
@ViewScoped
public class ClienteController implements Serializable {

    private Cliente cliente;
    private List<Cliente> clientes;
    private long tela;
    private String filtro = "";
    private final ClienteService clienteService;
    private long idCliente;

    public ClienteController() {
        this.cliente = new Cliente();
        this.clientes = new ArrayList<>();
        this.clienteService = new ClienteService();
    }

    public void mudarTela(long pTela) {
        tela = pTela;
    }

    public void salvarCliente() {
        if (this.clienteService.salvarCliente(cliente)) {
            FacesUtil.saveMessage("Cliente salvo com sucesso!", "Sucesso");
            limparCampos();
        } else {
            FacesUtil.saveMessage("Ocorreu um erro inesperado ao tentar cadastrar esse cliente, tente novamente.!", "");
        }
    }

    public void listarClientes() {
        clientes = this.clienteService.getAllClientes(filtro);
    }

    public void deletarCliente(long id) {
        if (this.clienteService.deletarCliente(id)) {
            FacesUtil.saveMessage("Cliente deletado com sucesso!", "Sucesso");
            limparCampos();
        } else {
            FacesUtil.saveMessage("Ocorreu um erro inesperado ao tentar deletar esse cliente, tente novamente.!", "");
        }
    }

    private void limparCampos() {
        tela = 0;
        listarClientes();
        cliente = new Cliente();
    }

    //getter and setter
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public long getTela() {
        return tela;
    }

    public void setTela(long tela) {
        this.tela = tela;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
        if (idCliente != 0) {
            cliente = clienteService.getClienteById(idCliente);
        }
    }

}
