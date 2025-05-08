package com.ejemplo.demo1.controller;


import com.ejemplo.demo1.model.Producto;
import com.ejemplo.demo1.services.ProductoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
@CrossOrigin(origins = "https://soft-torrone-90dd2d.netlify.app") 

@RestController
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoServices productoService;

    // GET: listar todos los productos
    @GetMapping("/traer")
    public List<Producto> listarProductos() {
        return productoService.listarProductos();
    }

    // POST: guardar un producto desde JSON normal
    @PostMapping("/guardar")
    public void guardarProducto(@RequestBody Producto producto) {
        productoService.guardarProducto(producto);
    }

    // DELETE: eliminar por ID
    @DeleteMapping("/eliminar/{id}")
    public void eliminarProducto(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }

    // PUT: actualizar un producto
    @PutMapping("/actualizar/{id}")
    public Producto actualizarProducto(@PathVariable Integer id, @RequestBody Producto producto) {
        return productoService.actualizarProducto(id, producto);
    }

    // POST: guardar producto con imagen (multipart)
    @PostMapping("/guardar-con-imagen")
    public ResponseEntity<String> guardarProductoConImagen(
            @RequestPart("producto") Producto producto,
            @RequestPart("imagen") MultipartFile imagenFile) {

        try {
            // Directorio donde se guardan las imágenes
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);

            // Crear la carpeta si no existe
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Guardar el archivo
            String nombreArchivo = imagenFile.getOriginalFilename();
            Path archivoPath = uploadPath.resolve(nombreArchivo);
            Files.copy(imagenFile.getInputStream(), archivoPath, StandardCopyOption.REPLACE_EXISTING);

            // Asociar imagen al producto y guardar
            producto.setImagen(nombreArchivo);
            productoService.guardarProducto(producto);

            return ResponseEntity.ok("Producto guardado con imagen.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar la imagen.");
        }
    }

    // GET: mostrar imagen del producto
    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<Resource> verImagen(@PathVariable String nombre) {
        try {
            Path archivoPath = Paths.get("uploads").resolve(nombre).toAbsolutePath();
            Resource recurso = new UrlResource(archivoPath.toUri());

            if (!recurso.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // Puedes adaptar según el tipo
                    .body(recurso);

        } catch (MalformedURLException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
