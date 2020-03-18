/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.IService;

import com.donation.Entite.CommandLine;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface IserviceCommandLine {
    public void addCommand(CommandLine c)throws SQLException;
    public void deleteCommand(int id)throws SQLException;
    public void updateCommand(CommandLine c)throws SQLException;
    public List<CommandLine> getCommand() throws SQLException;
    public CommandLine getById(int id)throws SQLException;
}
