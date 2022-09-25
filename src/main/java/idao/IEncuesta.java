/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idao;

import models.Encuesta;
import models.Usuario;
/**
 *
 * @author eliza
 */
public interface IEncuesta {
    
    public Usuario ObtenerUsuario(int id);
    public Encuesta GuardarEncuesta(Encuesta encuesta);
}
