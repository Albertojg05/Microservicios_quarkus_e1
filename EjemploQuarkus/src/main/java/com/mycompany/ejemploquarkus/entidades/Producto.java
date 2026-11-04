package com.mycompany.ejemploquarkus.entidades;

/**
 *
 * @author jgzarate
 */
public class Producto {
    public Long id;
    public String name;
    public double price;

    public Producto() {
    }

    public Producto(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
}
