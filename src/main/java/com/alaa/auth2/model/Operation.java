package com.alaa.auth2.model;


import com.sun.istack.Nullable;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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
    private String dateTest ;

    @Nullable
    @OneToOne()
    private UploadedOrginalFile uploadedOrginalFile ;

    @Nullable
    @OneToOne()
    private TransformedFile transformedFile ;

    @Nullable
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user ;






}
