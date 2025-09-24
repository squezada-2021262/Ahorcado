//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.service;

import com.santiagoquezada.ahorcado.model.Palabras;
import com.santiagoquezada.ahorcado.repository.PalabrasRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PalabrasServiceImpl implements PalabrasService {
    private final PalabrasRepository palabrasRepository;

    public PalabrasServiceImpl(PalabrasRepository palabrasRepository) {
        this.palabrasRepository = palabrasRepository;
    }

    public List<Palabras> getAllPalabras() {
        return this.palabrasRepository.findAll();
    }

    public Palabras getPalabraById(Integer id) {
        return (Palabras)this.palabrasRepository.findById(id).orElse((Palabras) null);
    }

    public Palabras savePalabra(Palabras palabra) {
        return (Palabras)this.palabrasRepository.save(palabra);
    }

    public String updatePalabra(Integer id, Palabras palabra) {
        Palabras existingPalabra = (Palabras)this.palabrasRepository.findById(id).orElse((Palabras) null);
        if (existingPalabra != null) {
            existingPalabra.setPalabra(palabra.getPalabra());
            existingPalabra.setPista1(palabra.getPista1());
            existingPalabra.setPista2(palabra.getPista2());
            existingPalabra.setPista3(palabra.getPista3());
            this.palabrasRepository.save(existingPalabra);
            return "Se han actualizado los datos de la palabra.";
        } else {
            return "No se pudo actualizar la palabra. El ID no existe.";
        }
    }

    public String deletePalabra(Integer id) {
        if (this.palabrasRepository.existsById(id)) {
            this.palabrasRepository.deleteById(id);
            return "Se ha eliminado la palabra de la base de datos.";
        } else {
            return "No se pudo eliminar la palabra. El ID no existe.";
        }
    }
}
