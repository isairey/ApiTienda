package com.ejemplo.demo1.controller;
import com.ejemplo.demo1.model.Carrito;
import com.ejemplo.demo1.services.CarritoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {
    @Autowired
    private CarritoServices carritoService;

    @GetMapping("/traer")
    public List<Carrito> listarCarritos() {
        return carritoService.listarCarritos();
    }

    @PostMapping("/guardar")
    public void guardarCarrito(@RequestBody Carrito carrito) {
        carritoService.guardarCarrito(carrito);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarCarrito(@PathVariable Integer id) {
        carritoService.eliminarCarrito(id);
    }

    @PutMapping("/actualizar/{id}")
    public Carrito actualizarCarrito(@PathVariable Integer id, @RequestBody Carrito carrito) {
        return carritoService.actualizarCarrito(id, carrito);
    }
}
