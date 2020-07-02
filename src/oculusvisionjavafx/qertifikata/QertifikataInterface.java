/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.qertifikata;

import java.util.Date;
import java.util.List;
import oculusvisionjavafx.entities.Llojiqertifikates;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.entities.Qertifikata;
import oculusvisionjavafx.entities.Stafi;

/**
 *
 * @author Qendresa
 */
public interface QertifikataInterface {

    void add(Qertifikata q);
    void update(Qertifikata q);
    void delete(Qertifikata q);
    List<Qertifikata> getAll();
    Qertifikata findById(int id);
    public boolean update(Integer id, Pacienti pacienti, String shenimet, Date dataLeshimit,
                             String semundjet, String simptomat,
                             Llojiqertifikates llojiqertifikatesID);
}
