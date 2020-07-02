/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.alergjite;

import java.util.Date;
import java.util.List;
import oculusvisionjavafx.entities.Alergjite;
import oculusvisionjavafx.entities.Ilac;
import oculusvisionjavafx.entities.Pacienti;


/**
 *
 * @author Qendresa
 */
public interface AlergjiteInterface{
    void add(Alergjite a);
    void update(Alergjite a);
    void delete(Alergjite a);
    List<Alergjite> getAll();
    Alergjite findById(int id);
    boolean update(Integer id, int rrezikshmeria
        , Date dataNodhjes,String verejtuarNga,
        String pershkrimiShtes,Pacienti pacientiID,
        Ilac ilacID);
}
