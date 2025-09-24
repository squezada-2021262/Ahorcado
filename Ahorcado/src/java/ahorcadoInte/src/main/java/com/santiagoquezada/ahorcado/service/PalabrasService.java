//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.service;

import com.santiagoquezada.ahorcado.model.Palabras;
import java.util.List;

public interface PalabrasService {
    List<Palabras> getAllPalabras();

    Palabras getPalabraById(Integer id);

    Palabras savePalabra(Palabras palabra);

    String updatePalabra(Integer id, Palabras palabra);

    String deletePalabra(Integer id);
}
