package com.ejemplo.demo1.services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.ejemplo.demo1.model.Producto;
import com.ejemplo.demo1.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ProductoServicesTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoServices productoServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListarProductos() {
        Producto producto1 = new Producto(1, "Producto 1", "Descripción 1", 100.0, 10,"");
        Producto producto2 = new Producto(2, "Producto 2", "Descripción 2", 200.0, 20,"");

        when(productoRepository.findAll()).thenReturn(Arrays.asList(producto1, producto2));

        List<Producto> productos = productoServices.listarProductos();

        assertEquals(2, productos.size());
        assertEquals("Producto 1", productos.get(0).getNombre());
        assertEquals("Producto 2", productos.get(1).getNombre());
    }

    @Test
    void testGuardarProducto() {
        Producto producto = new Producto(3, "Producto 3", "Descripción 3", 300.0, 30,"");

        when(productoRepository.save(producto)).thenReturn(producto);

        productoServices.guardarProducto(producto);

        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void testObtenerProducto() {
        Producto producto = new Producto(1, "Producto 1", "Descripción 1", 100.0, 10,"");
        when(productoRepository.findById(1)).thenReturn(Optional.of(producto));

        Producto encontrado = productoServices.obtenerProducto(1);

        assertNotNull(encontrado);
        assertEquals("Producto 1", encontrado.getNombre());
    }

    @Test
    void testEliminarProducto() {
        productoServices.eliminarProducto(1);

        verify(productoRepository, times(1)).deleteById(1);
    }

    @Test
    void testActualizarProducto() {
        Producto productoExistente = new Producto(1, "Producto 1", "Descripción 1", 100.0, 10,"");
        Producto productoActualizado = new Producto(1, "Producto 1", "Descripción 1 Actualizada", 150.0, 15,"");

        when(productoRepository.findById(1)).thenReturn(Optional.of(productoExistente));
        when(productoRepository.save(any(Producto.class))).thenReturn(productoActualizado);

        Producto resultado = productoServices.actualizarProducto(1, productoActualizado);

        assertNotNull(resultado);
        assertEquals(150.0, resultado.getPrecio());
        assertEquals(15, resultado.getStock());
    }
}
