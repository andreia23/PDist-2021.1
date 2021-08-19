package com.gugawag.pdist.servlets;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.ejbs.UsuarioService;
import com.gugawag.pdist.model.Mensagem;
import com.gugawag.pdist.model.Usuario;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet{

    @EJB(lookup="java:module/mensagemService")
    private MensagemService mensagemService;

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response)
            throws javax.servlet.ServletException, IOException {
        String operacao = request.getParameter("oper");
        PrintWriter resposta = response.getWriter();
        switch (operacao) {
            case "1": {
                long idMensagem = Integer.parseInt(request.getParameter("idMensagem"));
                String texto = request.getParameter("texto");
                mensagemService.inserir(idMensagem, texto);
            }
            case "2": {
                for(Mensagem mensagem: mensagemService.listar()){
                    resposta.println(mensagem.getTexto());
                }
                break;
            }
            case "3": {
                long idMensagem = Integer.parseInt(request.getParameter("idMensagem"));
                String mensagem = mensagemService.pesquisarPorId(idMensagem);
                resposta.println(mensagem);
            }
            @Elshadai19#

        }
    }

}
