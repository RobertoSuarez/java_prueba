package idao;

import java.util.List;
import models.Autor;


public interface IAutor {
    public Autor ObtenerAutor(int id);
    public List<Autor> ObtenerAutoresPublicacionID(int id);
    public int GuardarAutor(Autor autor);
}
