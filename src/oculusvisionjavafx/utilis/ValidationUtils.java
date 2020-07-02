/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oculusvisionjavafx.utilis;

import oculusvisionjavafx.entities.Pacienti;

/**
 *
 * @author Qendresa
 */
public class ValidationUtils {
    
    public static boolean isEmptyOrNull(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    public static boolean isSelected(int n){
        return n == 0;
    }
    }


