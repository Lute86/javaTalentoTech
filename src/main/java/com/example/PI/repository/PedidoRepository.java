package com.example.PI.repository;

import com.example.PI.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    // Buscar pedidos por estado
    List<Pedido> findByEstado(String estado);

    // Buscar pedidos por rango de fechas
    List<Pedido> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    // Buscar pedidos con sus líneas (join fetch para evitar lazy loading)
    @Query("SELECT p FROM Pedido p LEFT JOIN FETCH p.lineas WHERE p.id = :id")
    Pedido findByIdWithLineas(@Param("id") Long id);

    // Buscar todos los pedidos con sus líneas
    @Query("SELECT DISTINCT p FROM Pedido p LEFT JOIN FETCH p.lineas")
    List<Pedido> findAllWithLineas();
}
