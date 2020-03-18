/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.IService;

import com.donation.Entite.Product;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author DELL
 */
public interface IserviceProduct {
    public void addProduct(Product p)throws SQLException;
    public void deleteProduct(int id)throws SQLException;
    public void updateProduct(Product p)throws SQLException;
    public List<Product> getProduct() throws SQLException;
    public Product getById(int id)throws SQLException;
}
