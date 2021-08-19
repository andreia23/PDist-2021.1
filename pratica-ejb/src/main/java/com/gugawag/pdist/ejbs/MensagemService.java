package com.gugawag.pdist.ejbs;


import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.io.Serializable;
import java.util.List;

@Stateless(name = "mensagemService")
@Remote
public class MensagemService implements Serializable {

    @EJB
    private MensagemDAO mensagemDAO;

    public List<Mensagem> listar() {
        return mensagemDAO.listar();
    }

    public void inserir(long id, String texto) {
        Mensagem novaMensagem = new Mensagem(id, texto);
        mensagemDAO.inserir(novaMensagem);
    }

    public String pesquisarPorId(long idMensagem){
        return mensagemDAO.pesquisarPorId(idMensagem);
    }

}
