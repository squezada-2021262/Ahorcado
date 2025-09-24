//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.santiagoquezada.ahorcado.repository;

import com.santiagoquezada.ahorcado.model.Palabras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabrasRepository extends JpaRepository<Palabras, Integer> {
}
