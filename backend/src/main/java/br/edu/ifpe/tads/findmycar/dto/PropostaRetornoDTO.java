package br.edu.ifpe.tads.findmycar.dto;

import java.util.Date;

public class PropostaRetornoDTO {
    private Long idProposta;
    private String nomeConsultor;
    private Long idConsultor;
    private String nomeCliente;
    private Long idCliente;
    private double valorAcordado;
    private Date dataProposta;
    private String tipoServico;
    private String servicoContratado;
    private String localServico;
    public PropostaRetornoDTO() {
    }

    public PropostaRetornoDTO(Long idProposta, String nomeConsultor, Long idConsultor, String nomeCliente, Long idCliente, double valorAcordado, Date dataProposta) {
        this.idProposta = idProposta;
        this.nomeConsultor = nomeConsultor;
        this.idConsultor = idConsultor;
        this.nomeCliente = nomeCliente;
        this.idCliente = idCliente;
        this.valorAcordado = valorAcordado;
        this.dataProposta = dataProposta;
    }

    public Long getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(Long idProposta) {
        this.idProposta = idProposta;
    }

    public String getNomeConsultor() {
        return nomeConsultor;
    }

    public void setNomeConsultor(String nomeConsultor) {
        this.nomeConsultor = nomeConsultor;
    }

    public Long getIdConsultor() {
        return idConsultor;
    }

    public void setIdConsultor(Long idConsultor) {
        this.idConsultor = idConsultor;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public double getValorAcordado() {
        return valorAcordado;
    }

    public void setValorAcordado(double valorAcordado) {
        this.valorAcordado = valorAcordado;
    }

    public Date getDataProposta() {
        return dataProposta;
    }

    public void setDataProposta(Date dataProposta) {
        this.dataProposta = dataProposta;
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
}
