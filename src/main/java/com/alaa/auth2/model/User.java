package com.alaa.auth2.model;

import com.sun.istack.Nullable;
import lombok.*;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="\"user\"")
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
    @Nullable
    @ManyToMany(fetch=FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>() ;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "user")
    @Nullable
    private List<Operation> operations ;



}
