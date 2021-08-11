package com.alaa.auth2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance( strategy = InheritanceType.JOINED )
@Table(name = "user" , schema = "public")
public class User implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id ;
    @Column
    private String name ;
    @Column
    private String username ;
    @Column
    private String password ;
    @Column
    private String photo ;
    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>() ;
}
