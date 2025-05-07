package com.ejemplo.demo1.controller;


import com.ejemplo.demo1.model.Pedido;
import com.ejemplo.demo1.services.PedidoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoServices pedidoService;

    @GetMapping("/traer")
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    @PostMapping("/guardar")
    public void guardarPedido(@RequestBody Pedido pedido) {
        pedidoService.guardarPedido(pedido);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarPedido(@PathVariable Integer id) {
        pedidoService.eliminarPedido(id);
    }

    @PutMapping("/actualizar/{id}")
    public Pedido actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        return pedidoService.actualizarPedido(id, pedido);
    }
}

