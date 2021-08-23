package br.com.zupacademy.msPropostas.clients.status;

public enum StatusSolicitacao {

    COM_RESTRICAO,
    SEM_RESTRICAO;

    public StatusProposta verifica() {
        if (this.equals(COM_RESTRICAO))
            return StatusProposta.NAO_ELEGIVEL;

        return StatusProposta.ELEGIVEL;
    }
}
