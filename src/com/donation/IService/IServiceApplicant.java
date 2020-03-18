/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.IService;

import com.donation.Entite.Applicant;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Tarek
 */
public interface IServiceApplicant {
    public void addApplicant(Applicant p) throws SQLException;

    public List<Applicant> getApplicants() throws SQLException;
    
    public List<Applicant> TrierApplicants(int i) throws SQLException;

    public Applicant getById(int id) throws SQLException;

    public void deleteApplicant(Applicant p) throws SQLException;

    public void deleteApplicant(int id) throws SQLException;

    public void updateApplicant(Applicant p) throws SQLException;  
    
    public List<Applicant> SearchApplicants(String character) throws SQLException;
    
}
