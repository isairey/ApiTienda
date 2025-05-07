package com.ejemplo.demo1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.demo1.repository.PedidoRepository;
import com.ejemplo.demo1.model.Pedido;
import java.util.List;

@Service
public class PedidoServices {
    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public void guardarPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public Pedido obtenerPedido(Integer id) {
        return pedidoRepository.findById(id).get();
    }

    public void eliminarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }

    public Pedido actualizarPedido(Integer id, Pedido pedidoActualizado) {
        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedidoExistente.setUsuario(pedidoActualizado.getUsuario());
        pedidoExistente.setProductos(pedidoActualizado.getProductos());
        pedidoExistente.setFechaPedido(pedidoActualizado.getFechaPedido());
        pedidoExistente.setTotal(pedidoActualizado.getTotal());

        return pedidoRepository.save(pedidoExistente);
    }
}
