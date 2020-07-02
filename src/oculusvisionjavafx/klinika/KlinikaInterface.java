/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.klinika;

import java.util.List;
import oculusvisionjavafx.entities.Klinika;

/**
 *
 * @author Qendresa
 */
public interface KlinikaInterface {
    void add(Klinika k);
    void update(Klinika k);
    void delete(Klinika k);
    List<Klinika> getAll();
    Klinika findById(int id);
}
