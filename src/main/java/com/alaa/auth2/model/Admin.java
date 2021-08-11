package com.alaa.auth2.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@Entity
@PrimaryKeyJoinColumn( name = "id" )
@Builder
@Table



public class Admin extends User{
}
