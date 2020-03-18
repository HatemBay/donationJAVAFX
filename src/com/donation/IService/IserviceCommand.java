/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.donation.IService;

import com.donation.Entite.Command;
import com.donation.Entite.CommandLine;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author DELL
 */
public interface IserviceCommand {
    public void addCommand(Command c)throws SQLException;
    public void deleteCommand(int id)throws SQLException;
    public void updateCommand(Command c)throws SQLException;
    public List<Command> getCommand() throws SQLException;
    public Command getById(int id)throws SQLException;
}
