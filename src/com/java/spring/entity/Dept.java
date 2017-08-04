package com.java.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Dept entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="dept"
    ,catalog="easyui"
)

public class Dept  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String code;
     private String name;


    // Constructors

    /** default constructor */
    public Dept() {
    }

    
    /** full constructor */
    public Dept(String code, String name) {
        this.code = code;
        this.name = name;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy=IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="code")

    public String getCode() {
        return this.code;
    }
    
    public void setCode(String code) {
        this.code = code;
    }
    
    @Column(name="name")

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   








}