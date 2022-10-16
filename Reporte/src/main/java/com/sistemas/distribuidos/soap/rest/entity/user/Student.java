package com.sistemas.distribuidos.soap.rest.entity.user;

import com.sistemas.distribuidos.soap.rest.entity.general.Exam;
import com.sistemas.distribuidos.soap.rest.entity.general.Matter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Student extends User {

}
