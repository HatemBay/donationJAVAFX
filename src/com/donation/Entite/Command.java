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

/**
 *
 * @author DELL
 */
public class Command {
   private int Id_Command;
   private int Id_Product;
   private int Quantity_Product;
   private int Paid;
   private java.sql.Date Date_Command;

    public Command() {
    }

   /* public Command(int Id_Command, int Id_Product, int Quantity_Product, int Paid, String Date_Command) {
        this.Id_Command = Id_Command;
        this.Id_Product = Id_Product;
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
    }

    @Override
    public String toString() {
        return "Command{" + "Id_Command=" + Id_Command + ", Id_Product=" + Id_Product + ", Quantity_Product=" + Quantity_Product + ", Paid=" + Paid + ", Date_Command=" + Date_Command + '}';
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
        final Command other = (Command) obj;
        if (this.Id_Command != other.Id_Command) {
            return false;
        }
        if (this.Id_Product != other.Id_Product) {
            return false;
        }
        if (this.Quantity_Product != other.Quantity_Product) {
            return false;
        }
        if (this.Paid != other.Paid) {
            return false;
        }
        if (!Objects.equals(this.Date_Command, other.Date_Command)) {
            return false;
        }
        return true;
    }

    public int getId_Command() {
        return Id_Command;
    }

    public void setId_Command(int Id_Command) {
        this.Id_Command = Id_Command;
    }

    public int getId_Product() {
        return Id_Product;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public int getQuantity_Product() {
        return Quantity_Product;
    }

    public void setQuantity_Product(int Quantity_Product) {
        this.Quantity_Product = Quantity_Product;
    }

    public int getPaid() {
        return Paid;
    }

    public void setPaid(int Paid) {
        this.Paid = Paid;
    }

    public String getDate_Command() {
        return Date_Command;
    }

    public void setDate_Command(String Date_Command) {
        this.Date_Command = Date_Command;
    }/*
    
    */

    public Command(int Id_Command, int Id_Product, int Quantity_Product, int Paid, java.sql.Date Date_Command) {
        this.Id_Command = Id_Command;
        this.Id_Product = Id_Product;
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
    }

    public Command(int Id_Product, int Quantity_Product, int Paid, java.sql.Date Date_Command) {
        this.Id_Product = Id_Product;
        this.Quantity_Product = Quantity_Product;
        this.Paid = Paid;
        this.Date_Command = Date_Command;
    }

  

    @Override
    public String toString() {
        return "Command{" + "Id_Command=" + Id_Command + ", Id_Product=" + Id_Product + ", Quantity_Product=" + Quantity_Product + ", Paid=" + Paid + ", Date_Command=" + Date_Command + '}';
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
        final Command other = (Command) obj;
        if (this.Id_Command != other.Id_Command) {
            return false;
        }
        if (this.Id_Product != other.Id_Product) {
            return false;
        }
        if (this.Quantity_Product != other.Quantity_Product) {
            return false;
        }
        if (this.Paid != other.Paid) {
            return false;
        }
        if (!Objects.equals(this.Date_Command, other.Date_Command)) {
            return false;
        }
        return true;
    }

    public void setId_Command(int Id_Command) {
        this.Id_Command = Id_Command;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public void setQuantity_Product(int Quantity_Product) {
        this.Quantity_Product = Quantity_Product;
    }

    public void setPaid(int Paid) {
        this.Paid = Paid;
    }

    public void setDate_Command(java.sql.Date Date_Command) {
        this.Date_Command = Date_Command;
    }

    public int getId_Command() {
        return Id_Command;
    }

    public int getId_Product() {
        return Id_Product;
    }

    public int getQuantity_Product() {
        return Quantity_Product;
    }

    public int getPaid() {
        return Paid;
    }

    public java.sql.Date getDate_Command() {
        return Date_Command;
    }
}
