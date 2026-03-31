package br.com.infnet.GuildaAventureiros.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Aventureiro {
    private long id;
    private String nome;
    private String classe;
    private int nivel;
    private boolean ativo;
    private Companheiro companheiro;

}
