package com.java.spring.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java.spring.utils.CustomDateSerializer;


/**
 * Userinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="userinfo"
    ,catalog="easyui"
)

public class Userinfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer age;
     private Date birthday;
     private Integer deptId;


    // Constructors

    /** default constructor */
    public Userinfo() {
    }

    
    /** full constructor */
    public Userinfo(String name, Integer age, Date birthday, Integer deptId) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
        this.deptId = deptId;
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
    
    @Column(name="name")

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="age")

    public Integer getAge() {
        return this.age;
    }
    
    public void setAge(Integer age) {
        this.age = age;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="birthday", length=10)
    @JsonSerialize(using=CustomDateSerializer.class)//用jackson转换json时如果有date类型的处理
    public Date getBirthday() {
        return this.birthday;
    }
    
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    @Column(name="deptId")

    public Integer getDeptId() {
        return this.deptId;
    }
    
    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
   








}