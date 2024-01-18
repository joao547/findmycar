package br.edu.ifpe.tads.findmycar.dto;

public class PropostaDTO {

    private Long idCliente;
    private Long idConsultor;
    private double valorFechado;

    private String tipoServico;
    private String servicoContratado;
    private String localServico;

    public PropostaDTO() {
    }

    public PropostaDTO(Long idCliente, Long idConsultor, double valorFechado) {
        this.idCliente = idCliente;
        this.idConsultor = idConsultor;
        this.valorFechado = valorFechado;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getServicoContratado() {
        return servicoContratado;
    }

    public void setServicoContratado(String servicoContratado) {
        this.servicoContratado = servicoContratado;
    }

    public String getLocalServico() {
        return localServico;
    }

    public void setLocalServico(String localServico) {
        this.localServico = localServico;
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
