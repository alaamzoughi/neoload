package com.alaa.auth2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "transformed_file")
public class TransformedFile implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    @Column
    private String fileName ;
    @Column
    private String fileDownloadUri ;
    @Column
    private String fileType ;
    @Column
    private Long size ;

    @OneToOne()
    @Nullable
    @JoinColumn(name = "operation_id")
    @JsonIgnore
    private Operation operation ;
}
