package com.santiagoquezada.ahorcado.controller;

import com.santiagoquezada.ahorcado.model.Palabras;
import com.santiagoquezada.ahorcado.service.PalabrasService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palabras")
public class PalabrasController {
    private final PalabrasService palabrasService;

    public PalabrasController(PalabrasService palabrasService) {
        this.palabrasService = palabrasService;
    }

    @GetMapping
    public List<Palabras> getAllPalabras() {
        return this.palabrasService.getAllPalabras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPalabraById(@PathVariable Integer id) {
        Palabras palabra = this.palabrasService.getPalabraById(id);
        if (palabra == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontró la palabra con id " + id);
        }
        return ResponseEntity.ok(palabra);
    }

    @PostMapping
    public ResponseEntity<String> createPalabra(@Valid @RequestBody Palabras palabra) {
        this.palabrasService.savePalabra(palabra);
        return new ResponseEntity<>("Se agrego exitosamente", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePalabra(@PathVariable Integer id, @Valid @RequestBody Palabras palabra) {
        String result = this.palabrasService.updatePalabra(id, palabra);
        if (result != null && result.contains("no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok("Se actualizo exitosamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePalabra(@PathVariable Integer id) {
        String result = this.palabrasService.deletePalabra(id);
        if (result != null && result.contains("no encontrada")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok("Se elimino exitosamente");
    }
}
