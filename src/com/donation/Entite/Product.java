/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.Entite;

import static java.sql.JDBCType.FLOAT;
import static java.sql.Types.FLOAT;
import java.util.*;
import com.donation.Utils.DataBase;
import com.donation.IService.ProductService;

/**
 *
 * @author DELL
 */
public class Product {

    private int Id_Product;
    private String Name_Product;
    private int Quantity_Total ;
    private float Price_Product;
    private String Description_Product;
    private int Quantity_Remaining;
    private int Id_admin;
    private int Id_Association;

    public Product(String Name_Product, int Quantity_Total, float Price_Product, String Description_Product, int Quantity_Remaining, int Id_admin, int Id_Association) {
        this.Name_Product = Name_Product;
        this.Quantity_Total = Quantity_Total;
        this.Price_Product = Price_Product;
        this.Description_Product = Description_Product;
        this.Quantity_Remaining = Quantity_Remaining;
        this.Id_admin = Id_admin;
        this.Id_Association = Id_Association;
    }

    public Product(int Id_Product, String Name_Product, int Quantity_Total, float Price_Product, String Description_Product, int Quantity_Remaining, int Id_admin, int Id_Association) {
        this.Id_Product = Id_Product;
        this.Name_Product = Name_Product;
        this.Quantity_Total = Quantity_Total;
        this.Price_Product = Price_Product;
        this.Description_Product = Description_Product;
        this.Quantity_Remaining = Quantity_Remaining;
        this.Id_admin = Id_admin;
        this.Id_Association = Id_Association;
    }

    public Product() {
    }

    public int getId_Product() {
        return Id_Product;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public String getName_Product() {
        return Name_Product;
    }

    public void setName_Product(String Name_Product) {
        this.Name_Product = Name_Product;
    }

    public int getQuantity_Total() {
        return Quantity_Total;
    }

    public void setQuantity_Total(int Quantity_Total) {
        this.Quantity_Total = Quantity_Total;
    }

    public float getPrice_Product() {
        return Price_Product;
    }

    public void setPrice_Product(float Price_Product) {
        this.Price_Product = Price_Product;
    }

    public String getDescription_Product() {
        return Description_Product;
    }

    public void setDescription_Product(String Description_Product) {
        this.Description_Product = Description_Product;
    }

    public int getQuantity_Remaining() {
        return Quantity_Remaining;
    }

    public void setQuantity_Remaining(int Quantity_Remaining) {
        this.Quantity_Remaining = Quantity_Remaining;
    }

    public int getId_admin() {
        return Id_admin;
    }

    public void setId_admin(int Id_admin) {
        this.Id_admin = Id_admin;
    }

    public int getId_Association() {
        return Id_Association;
    }

    public void setId_Association(int Id_Association) {
        this.Id_Association = Id_Association;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.Id_Product != other.Id_Product) {
            return false;
        }
        if (this.Quantity_Total != other.Quantity_Total) {
            return false;
        }
        if (Float.floatToIntBits(this.Price_Product) != Float.floatToIntBits(other.Price_Product)) {
            return false;
        }
        if (this.Quantity_Remaining != other.Quantity_Remaining) {
            return false;
        }
        if (this.Id_admin != other.Id_admin) {
            return false;
        }
        if (this.Id_Association != other.Id_Association) {
            return false;
        }
        if (!Objects.equals(this.Name_Product, other.Name_Product)) {
            return false;
        }
        if (!Objects.equals(this.Description_Product, other.Description_Product)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Product{" + "Id_Product=" + Id_Product + ", Name_Product=" + Name_Product + ", Quantity_Total=" + Quantity_Total + ", Price_Product=" + Price_Product + ", Description_Product=" + Description_Product + ", Quantity_Remaining=" + Quantity_Remaining + ", Id_admin=" + Id_admin + ", Id_Association=" + Id_Association + '}';
    }



}
