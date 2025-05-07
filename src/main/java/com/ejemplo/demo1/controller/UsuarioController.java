package com.ejemplo.demo1.controller;
import com.ejemplo.demo1.model.Usuario;
import com.ejemplo.demo1.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.HashMap;


import java.util.List;
@CrossOrigin(origins = "https://681b8a083114250008319cce--soft-torrone-90dd2d.netlify.app/") 


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioService;

    @GetMapping("/traer")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    @PostMapping("/guardar")
    public void guardarUsuario(@RequestBody Usuario usuario) {
        usuarioService.guardarUsuario(usuario);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioService.eliminarUsuario(id);
    }

    @PutMapping("/actualizar/{id}")
    public Usuario actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarUsuario(@RequestBody Map<String, String> datos) {
        String correoOriginal = datos.get("correo");
        String nuevoNombre = datos.get("nombre");
        String nuevoCorreo = datos.get("correo");
        String nuevaDireccion = datos.get("direccion");
        String nuevaContrasena = datos.get("password");
    
        Usuario usuario = usuarioService.findByCorreo(correoOriginal);
        if (usuario != null) {
            usuario.setNombre(nuevoNombre);
            usuario.setCorreo(nuevoCorreo);
            usuario.setDireccion(nuevaDireccion);
            usuario.setPassword(nuevaContrasena); // Cambiar contraseña si es necesario
    
            usuarioService.save(usuario); // Guardar los cambios en la base de datos
    
            return ResponseEntity.ok(usuario); // Devolver el usuario actualizado
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }
    

  /*   @PostMapping("/login")
public ResponseEntity<String> login(@RequestBody Usuario usuario) {
    boolean acceso = usuarioService.login(usuario.getCorreo(), usuario.getPassword());
    if (acceso) {
        return ResponseEntity.ok("Login exitoso");
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
    }
}*/

@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Usuario usuario) {
    try {
        boolean acceso = usuarioService.login(usuario.getCorreo(), usuario.getPassword());
        if (acceso) {
            Usuario usuarioEncontrado = usuarioService.findByCorreo(usuario.getCorreo());

            if (usuarioEncontrado == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Login exitoso");
            response.put("nombre", usuarioEncontrado.getNombre());
            response.put("correo", usuarioEncontrado.getCorreo());

            return ResponseEntity.ok("Login exitoso");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Correo o contraseña incorrectos");
        }
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + e.getMessage());
    }
}



@GetMapping("/datos")
public ResponseEntity<?> obtenerDatosUsuario(@RequestParam String correo) {
    Usuario usuario = usuarioService.findByCorreo(correo);
    if (usuario != null) {
        return ResponseEntity.ok(usuario);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
    }
}








}
