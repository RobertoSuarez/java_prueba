package idao;

import java.util.List;
import models.Usuario;

public interface IUsuario {
    public Usuario ObtenerUsuario(int id);
    public Usuario Login(String username, String password);
    public List<Usuario> ObtenerPostulantesNoHabilitados();
    public boolean AprobarPostulante(int id);
}
