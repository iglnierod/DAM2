/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.aldComputerService;

import java.util.ArrayList;
import model.aldComputerService.computers.Computer;
import model.aldComputerService.service.Service;

/**
 *
 * @author rodri
 */
public interface IAldComputerService {

    public ArrayList<Service> getServices(Computer computer);
}
