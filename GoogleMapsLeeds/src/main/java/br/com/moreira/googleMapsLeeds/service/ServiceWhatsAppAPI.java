package br.com.moreira.googleMapsLeeds.service;

public class ServiceWhatsAppAPI {



    public boolean startSession(){
        //http://localhost:3000/session/start/{NomeSessão}
        //iniciar sessão
        //Verifica se a sessão esta iniciada
        return true;
    }

    private boolean verifySessio(){
        //http://localhost:3000/session/status/{NomeSessão}
        //  *"sucess": true
        //
        //verificar se a sessão esta ativa
        return true;
    }

    public boolean verifyContact(String contact){
        //http://localhost:3000/client/getContactById/{NomeSessão}
        //  *"isUser": true
        //
        //Verificar se o contato está cadastrado
        //
        //adicionar @c.us no final do contato
        //realizar requisição
        //retornar true ou false
        return true;
    }
}
