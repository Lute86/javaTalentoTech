package com.example.PI.service;

import com.example.PI.dto.PedidoDTO;
import com.example.PI.dto.PedidoResponseDTO;
import com.example.PI.dto.LineaPedidoResponseDTO;
import com.example.PI.dto.ProductoResponseDTO;
import com.example.PI.exception.StockInsuficienteException;
import com.example.PI.model.LineaPedido;
import com.example.PI.model.Pedido;
import com.example.PI.model.Producto;
import com.example.PI.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoService productoService;

    public PedidoResponseDTO crear(PedidoDTO dto) {
        Pedido pedido = new Pedido();
        BigDecimal total = BigDecimal.ZERO;

        for (PedidoDTO.LineaPedidoDTO lineaDTO : dto.getLineas()) {
            Producto producto = productoService.buscar(lineaDTO.getProductoId());

            if (producto.getStock() < lineaDTO.getCantidad()) {
                throw new StockInsuficienteException(
                        producto.getNombre(),
                        producto.getStock(),
                        lineaDTO.getCantidad()
                );
            }

            // Actualizar stock
            producto.setStock(producto.getStock() - lineaDTO.getCantidad());

            // Calcular subtotal
            BigDecimal subtotal = producto.getPrecio().multiply(BigDecimal.valueOf(lineaDTO.getCantidad()));

            // Crear línea de pedido
            LineaPedido linea = new LineaPedido(null, producto, lineaDTO.getCantidad(), subtotal, pedido);
            pedido.getLineas().add(linea);

            total = total.add(subtotal);
        }

        pedido.setTotal(total);
        pedido.setEstado("CONFIRMADO");

        Pedido pedidoGuardado = pedidoRepository.save(pedido);
        return convertirADTO(pedidoGuardado);
    }

    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> listar() {
        List<Pedido> pedidos = pedidoRepository.findAllWithLineas();
        return pedidos.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PedidoResponseDTO buscar(Long id) {
        Pedido pedido = pedidoRepository.findByIdWithLineas(id);
        if (pedido == null) {
            throw new RuntimeException("Pedido no encontrado con ID: " + id);
        }
        return convertirADTO(pedido);
    }

    private PedidoResponseDTO convertirADTO(Pedido pedido) {
        List<LineaPedidoResponseDTO> lineasDTO = pedido.getLineas().stream()
                .map(linea -> new LineaPedidoResponseDTO(
                        linea.getId(),
                        linea.getCantidad(),
                        linea.getSubtotal(),
                        new ProductoResponseDTO(
                                linea.getProducto().getId(),
                                linea.getProducto().getNombre(),
                                linea.getProducto().getPrecio(),
                                linea.getProducto().getStock()
                        )
                ))
                .collect(Collectors.toList());

        return new PedidoResponseDTO(
                pedido.getId(),
                pedido.getFecha(),
                pedido.getTotal(),
                pedido.getEstado(),
                lineasDTO
        );
    }
}