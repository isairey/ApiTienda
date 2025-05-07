package com.ejemplo.demo1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.demo1.repository.CarritoRepository;
import com.ejemplo.demo1.model.Carrito;
import java.util.List;

@Service
public class CarritoServices {
    @Autowired
    private CarritoRepository carritoRepository;

    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    public void guardarCarrito(Carrito carrito) {
        carritoRepository.save(carrito);
    }

    public Carrito obtenerCarrito(Integer id) {
        return carritoRepository.findById(id).get();
    }

    public void eliminarCarrito(Integer id) {
        carritoRepository.deleteById(id);
    }

    public Carrito actualizarCarrito(Integer id, Carrito carritoActualizado) {
        Carrito carritoExistente = carritoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carrito no encontrado"));

        carritoExistente.setUsuario(carritoActualizado.getUsuario());
        carritoExistente.setProducto(carritoActualizado.getProducto());
        carritoExistente.setCantidad(carritoActualizado.getCantidad());

        return carritoRepository.save(carritoExistente);
    }
}
