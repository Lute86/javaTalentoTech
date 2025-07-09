package com.example.PI.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Long id;
    private LocalDateTime fecha;
    private BigDecimal total;
    private String estado;
    private List<LineaPedidoResponseDTO> lineas;

    public PedidoResponseDTO() {}

    public PedidoResponseDTO(Long id, LocalDateTime fecha, BigDecimal total, String estado, List<LineaPedidoResponseDTO> lineas) {
        this.id = id;
        this.fecha = fecha;
        this.total = total;
        this.estado = estado;
        this.lineas = lineas;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public List<LineaPedidoResponseDTO> getLineas() { return lineas; }
    public void setLineas(List<LineaPedidoResponseDTO> lineas) { this.lineas = lineas; }
}