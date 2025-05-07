package com.ejemplo.demo1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.demo1.repository.ProductoRepository;
import com.ejemplo.demo1.model.Producto;
import java.util.List;

@Service
public class ProductoServices {
    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public Producto obtenerProducto(Integer id) {
        return productoRepository.findById(id).get();
    }

    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }

    public Producto actualizarProducto(Integer id, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());

        return productoRepository.save(productoExistente);
    }
}
