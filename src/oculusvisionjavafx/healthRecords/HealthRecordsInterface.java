/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.healthRecords;

import java.util.Date;
import java.util.List;
import oculusvisionjavafx.entities.HealthRecords;
import oculusvisionjavafx.entities.Pacienti;
import oculusvisionjavafx.entities.Stafi;

/**
 *
 * @author Qendresa
 */
public interface HealthRecordsInterface {
    
    void add(HealthRecords h);
    void update(HealthRecords h);
    void delete(HealthRecords h);
    List<HealthRecords> getAll();
    HealthRecords findById(int id);
    boolean update(Integer id, Pacienti pacientiID, Stafi stafiID,
                    Date data, String pershkrimi);
}
