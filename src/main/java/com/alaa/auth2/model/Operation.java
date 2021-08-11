package com.alaa.auth2.model;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode()
@Entity
@Builder
@Table

public class Operation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    @Column
    private String nomDocument ;
    @Column
    private String nomClient ;
    @Column
    private String logoClient ;
    @Column
    private Date dateTest ;
    @Column
    private String documentOriginal ;
    @Column
    private String documentModifié ;

    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur ;



}
