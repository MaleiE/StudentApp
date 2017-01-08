package com.malei.entities;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
abstract public  class Model implements Serializable,Cloneable {


    private Long id;

    public Model() {
    }

    public Model(Long id) {
        this.id = id;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
