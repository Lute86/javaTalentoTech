package com.example.PI.service;

import com.example.PI.dto.ProductoDTO;
import com.example.PI.exception.ProductoNotFoundException;
import com.example.PI.model.Producto;
import com.example.PI.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public Producto crear(ProductoDTO dto) {
        Producto producto = new Producto(null, dto.getNombre(), dto.getPrecio(), dto.getStock());
        return repository.save(producto);
    }

    @Transactional(readOnly = true)
    public List<Producto> listar() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Producto buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductoNotFoundException(id));
    }

    public Producto actualizar(Long id, ProductoDTO dto) {
        Producto producto = buscar(id);
        producto.setNombre(dto.getNombre());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        return repository.save(producto);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ProductoNotFoundException(id);
        }
        repository.deleteById(id);
    }
}