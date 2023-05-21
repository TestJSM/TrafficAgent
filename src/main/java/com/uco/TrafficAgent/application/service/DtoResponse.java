package com.uco.TrafficAgent.application.service;

public class DtoResponse <T> {

    private T valor;

    public DtoResponse() {}

    public DtoResponse(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }
}
