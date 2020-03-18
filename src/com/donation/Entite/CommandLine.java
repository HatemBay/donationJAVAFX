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
public class CommandLine {
   private int Id_CommandLine,
               Id_Command,
               Id_Product; 
   private java.sql.Date Date_liv;
   private String State_Command;
   private String Type_Command;

public CommandLine() {}

    public void setId_CommandLine(int Id_CommandLine) {
        this.Id_CommandLine = Id_CommandLine;
    }

    public void setId_Command(int Id_Command) {
        this.Id_Command = Id_Command;
    }

    public void setId_Product(int Id_Product) {
        this.Id_Product = Id_Product;
    }

    public void setDate_liv(java.sql.Date Date_liv) {
        this.Date_liv = Date_liv;
    }

    public void setState_Command(String State_Command) {
        this.State_Command = State_Command;
    }

    public void setType_Command(String Type_Command) {
        this.Type_Command = Type_Command;
    }

    @Override
    public String toString() {
        return "CommandLine{" + "Id_CommandLine=" + Id_CommandLine + ", Id_Command=" + Id_Command + ", Id_Product=" + Id_Product + ", Date_liv=" + Date_liv + ", State_Command=" + State_Command + ", Type_Command=" + Type_Command + '}';
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
        final CommandLine other = (CommandLine) obj;
        if (this.Id_CommandLine != other.Id_CommandLine) {
            return false;
        }
        if (this.Id_Command != other.Id_Command) {
            return false;
        }
        if (this.Id_Product != other.Id_Product) {
            return false;
        }
        if (!Objects.equals(this.State_Command, other.State_Command)) {
            return false;
        }
        if (!Objects.equals(this.Type_Command, other.Type_Command)) {
            return false;
        }
        if (!Objects.equals(this.Date_liv, other.Date_liv)) {
            return false;
        }
        return true;
    }


    public int getId_CommandLine() {
        return Id_CommandLine;
    }

    public int getId_Command() {
        return Id_Command;
    }

    public int getId_Product() {
        return Id_Product;
    }

    public java.sql.Date getDate_liv() {
        return Date_liv;
    }

    public String getState_Command() {    
        return State_Command;
    }

    /* public CommandLine(int Id_CommandLine,int Id_Command, int Id_Product, String Date_liv, String State_Command, String Type_Command) {
    this.Id_CommandLine=Id_CommandLine;
    this.Id_Command = Id_Command;
    this.Id_Product = Id_Product;
    this.Date_liv = Date_liv;
    this.State_Command = State_Command;
    this.Type_Command = Type_Command;
    }
    @Override
    public String toString() {
    return "CommandLine{" + "Id_CommandLine=" + Id_CommandLine + ", Id_Command=" + Id_Command + ", Id_Product=" + Id_Product + ", Date_liv=" + Date_liv + ", State_Command=" + State_Command + ", Type_Command=" + Type_Command + '}';
    }
    public int getId_CommandLine() {
    return Id_CommandLine;
    }
    public void setId_CommandLine(int Id_CommandLine) {
    this.Id_CommandLine = Id_CommandLine;
    }
    public void setId_Command(int Id_Command) {
    this.Id_Command = Id_Command;
    }
    public void setId_Product(int Id_Product) {
    this.Id_Product = Id_Product;
    }
    public void setDate_liv(String Date_liv) {
    this.Date_liv = Date_liv;
    }
    public void setState_Command(String State_Command) {
    this.State_Command = State_Command;
    }
    public void setType_Command(String Type_Command) {
    this.Type_Command = Type_Command;
    }
    public int getId_Command() {
    return Id_Command;
    }
    public int getId_Product() {
    return Id_Product;
    }
    public String getDate_liv() {
    return Date_liv;
    }
    public String getState_Command() {
    return State_Command;
    }
    public String getType_Command() {
    return Type_Command;
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
    final CommandLine other = (CommandLine) obj;
    if (this.Id_CommandLine != other.Id_CommandLine) {
    return false;
    }
    if (this.Id_Command != other.Id_Command) {
    return false;
    }
    if (this.Id_Product != other.Id_Product) {
    return false;
    }
    if (!Objects.equals(this.Date_liv, other.Date_liv)) {
    return false;
    }
    if (!Objects.equals(this.State_Command, other.State_Command)) {
    return false;
    }
    if (!Objects.equals(this.Type_Command, other.Type_Command)) {
    return false;
    }
    return true;
    }*/
    public String getType_Command() {
        return Type_Command;
    }

    public CommandLine(int Id_CommandLine, int Id_Command, int Id_Product, java.sql.Date Date_liv, String State_Command, String Type_Command) {
        this.Id_CommandLine = Id_CommandLine;
        this.Id_Command = Id_Command;
        this.Id_Product = Id_Product;
        this.Date_liv = Date_liv;
        this.State_Command = State_Command;
        this.Type_Command = Type_Command;
    }

    public CommandLine(int Id_Command, int Id_Product, java.sql.Date Date_liv, String State_Command, String Type_Command) {
        this.Id_Command = Id_Command;
        this.Id_Product = Id_Product;
        this.Date_liv = Date_liv;
        this.State_Command = State_Command;
        this.Type_Command = Type_Command;
    }

}
