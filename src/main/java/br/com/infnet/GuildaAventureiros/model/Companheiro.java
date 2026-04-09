package br.com.infnet.GuildaAventureiros.model;

<<<<<<< HEAD
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
=======
import lombok.AllArgsConstructor;
import lombok.Getter;
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
import lombok.Setter;

@Getter
@Setter
<<<<<<< HEAD
@NoArgsConstructor
@AllArgsConstructor@Entity
@Table(name = "companheiros", schema = "aventura")
public class Companheiro    {
    @Id
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "aventureiro_id")
    private Aventureiro aventureiro;

    @Column(nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Especie especie;

    @Column(nullable = false)
=======
@AllArgsConstructor
public class Companheiro    {
    private String nome;
    private Especie especie;
>>>>>>> 8f4b7c951cd22e54449dae4fb03e01db9c4068c4
    private Integer lealdade;
}
