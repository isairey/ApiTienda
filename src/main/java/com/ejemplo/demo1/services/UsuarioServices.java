package com.ejemplo.demo1.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ejemplo.demo1.repository.UsuarioRepository;
import com.ejemplo.demo1.model.Usuario;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

 
 
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id)
            .orElse(null);
    }

    
    public Usuario findByCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo)
            .orElse(null);
    }

    public boolean login(String correo, String contrasena) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByCorreo(correo);
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            return usuario.getPassword().equals(contrasena); // 🔐 Puedes usar encriptación aquí
        }
        return false;
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    

    public void guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    public Usuario obtenerUsuario(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuarioExistente.setNombre(usuarioActualizado.getNombre());
        usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
        usuarioExistente.setPassword(usuarioActualizado.getPassword());
        usuarioExistente.setDireccion(usuarioActualizado.getDireccion());

        return usuarioRepository.save(usuarioExistente);
    }
}
