package com.sistemasdistribuidos.soap.rest.reporte.models.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "user")
@ApiModel(description = "Represents an User in the system")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @ApiModelProperty
    private long id;

    @ApiModelProperty
    private String name;

    @ApiModelProperty
    private String lastname;

    @ApiModelProperty
    private String dni;

    @ApiModelProperty
    private String username;

    @ApiModelProperty
    private String password;

    @ApiModelProperty
    private String email;

    @ApiModelProperty
    private String phone;

    @ApiModelProperty
    private String role;


}
