/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.donation.IService;

import java.sql.SQLException;
import java.util.List;
import com.donation.Entite.Offer;

/**
 *
 * @author tarek
 */
public interface IServiceOffer {
    
    public void addOffer(Offer p) throws SQLException;

    public List<Offer> getOffers() throws SQLException;
    
    public List<Offer> TrierOffers(int i) throws SQLException;

    public Offer getById(int id) throws SQLException;

    public void deleteOffer(Offer p) throws SQLException;

    public void deleteOffer(int id) throws SQLException;

    public void updateOffer(Offer p) throws SQLException;   
    
    public List<Offer> SearchOffers(String character) throws SQLException;
    
    public List<String> SearchOffersNames(String character) throws SQLException;
    
    
    
}
