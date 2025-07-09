package com.example.PI.dto;

import java.math.BigDecimal;

public class LineaPedidoResponseDTO {
    private Long id;
    private Integer cantidad;
    private BigDecimal subtotal;
    private ProductoResponseDTO producto;

    public LineaPedidoResponseDTO() {}

    public LineaPedidoResponseDTO(Long id, Integer cantidad, BigDecimal subtotal, ProductoResponseDTO producto) {
        this.id = id;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
        this.producto = producto;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }
    public ProductoResponseDTO getProducto() { return producto; }
    public void setProducto(ProductoResponseDTO producto) { this.producto = producto; }
}