package com.ejemplo.demo1.model;


import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "pedido")
    private List<Producto> productos;
    

    @ManyToOne
    private Usuario usuario;

    private LocalDate fechaPedido;
    private double total;

    public Pedido() {}

    public Pedido(Integer id, Usuario usuario, LocalDate fechaPedido, double total) {
        this.id = id;
        this.usuario = usuario;
        this.fechaPedido = fechaPedido;
        this.total = total;
    }
    public List<Producto> getProductos() {
        return productos;
    }
    
    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    // Getters y setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(LocalDate fechaPedido) { this.fechaPedido = fechaPedido; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
