package com.example.PI.exception;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String message) {
        super(message);
    }

    public StockInsuficienteException(String producto, int stockDisponible, int stockSolicitado) {
        super(String.format("Stock insuficiente para %s. Disponible: %d, Solicitado: %d",
                producto, stockDisponible, stockSolicitado));
    }
}