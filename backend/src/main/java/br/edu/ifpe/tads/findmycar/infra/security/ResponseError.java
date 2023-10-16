package br.edu.ifpe.tads.findmycar.infra.security;

public class ResponseError {
    private Integer status;
    private String msg;
    private Long time;

    public ResponseError(Integer status, String msg, Long time) {
        this.status = status;
        this.msg = msg;
        this.time = time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}