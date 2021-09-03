package br.com.zupacademy.msPropostas.clients.avisoviagem;

import br.com.zupacademy.msPropostas.clients.cartao.Cartao;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class AvisoViagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;
    private String destino;
    private LocalDateTime criadoEm;
    private String ip;
    private String userAgent;


    @ManyToOne
    private Cartao cartao;

    public AvisoViagem(String destino, String ip, String userAgent, LocalDate validoAte, Cartao cartao) {
        this.destino = destino;
        this.ip = ip;
        this.userAgent = userAgent;
        this.validoAte = validoAte;
        this.cartao = cartao;
        this.criadoEm = LocalDateTime.now();
    }

//    public String getIp() {
//        return ip;
//    }
//
//    public String getUserAgent() {
//        return userAgent;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
