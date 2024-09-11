package br.edu.univille.poo.jpa.controller;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FiltroDTO {
    private LocalDate dtInicio;
    private LocalDate dtFinal;
}
