package com.alaa.auth2.model;


import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn( name = "id" )
@Builder
@Table

public class Utilisateur extends User{

    @Column
    @Nullable
    private String poste ;


    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "utilisateur")
    @Nullable
    private List<Operation> operations ;

    }

