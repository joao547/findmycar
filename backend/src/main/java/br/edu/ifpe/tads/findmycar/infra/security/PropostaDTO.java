package br.edu.ifpe.tads.findmycar.infra.security;

public class PropostaDTO {

    private Long idCliente;
    private Long idConsultor;
    private double valorFechado;

    public PropostaDTO() {
    }

    public PropostaDTO(Long idCliente, Long idConsultor, double valorFechado) {
        this.idCliente = idCliente;
        this.idConsultor = idConsultor;
        this.valorFechado = valorFechado;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public Long getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(Long idConsultor) {
        this.idConsultor = idConsultor;
    }

    public double getValorFechado() {
        return valorFechado;
    }

    public void setValorFechado(double valorFechado) {
        this.valorFechado = valorFechado;
    }
}
