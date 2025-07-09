package com.example.PI.repository;

import com.example.PI.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    // Buscar productos por nombre (case insensitive)
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos con stock disponible
    @Query("SELECT p FROM Producto p WHERE p.stock > :minStock")
    List<Producto> findByStockGreaterThan(@Param("minStock") Integer minStock);

    // Verificar si existe un producto con el nombre especificado
    boolean existsByNombreIgnoreCase(String nombre);
}
