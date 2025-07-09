package com.example.PI.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.List;

public class PedidoDTO {
    @NotEmpty(message = "El pedido debe contener al menos una línea")
    @Valid
    private List<LineaPedidoDTO> lineas;

    public PedidoDTO() {}

    public PedidoDTO(List<LineaPedidoDTO> lineas) {
        this.lineas = lineas;
    }

    public List<LineaPedidoDTO> getLineas() { return lineas; }
    public void setLineas(List<LineaPedidoDTO> lineas) { this.lineas = lineas; }

    public static class LineaPedidoDTO {
        @NotNull(message = "El ID del producto es obligatorio")
        private Long productoId;

        @NotNull(message = "La cantidad es obligatoria")
        @Min(value = 1, message = "La cantidad debe ser mayor a 0")
        private Integer cantidad;

        public LineaPedidoDTO() {}

        public LineaPedidoDTO(Long productoId, Integer cantidad) {
            this.productoId = productoId;
            this.cantidad = cantidad;
        }

        public Long getProductoId() { return productoId; }
        public void setProductoId(Long productoId) { this.productoId = productoId; }
        public Integer getCantidad() { return cantidad; }
        public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
    }
}