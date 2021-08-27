package com.alaa.auth2.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Role implements Serializable {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @Column
    private String name ;

}
