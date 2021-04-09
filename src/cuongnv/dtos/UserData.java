/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongnv.dtos;

import java.rmi.Naming;
import java.util.List;
import cuongnv.interfaces.RegistrationInterface;

/**
 *
 * @author redra
 */
public class UserData {
    //Client
    RegistrationInterface iServer = null;

    public UserData() {
        try {
            iServer = (RegistrationInterface) Naming.lookup("rmi://localhost:25585/fhc");
            //Connect once
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean createUser(UserDTO dto){
        boolean check = false;
        try {
            check = iServer.createRegistration(dto);
        } catch (Exception e) {
        }
        return check;
    }
    
    public boolean deleteUser(String id) {
        boolean check = false;
        try {
            check = iServer.removeUser(id);
        } catch (Exception e) {
        }
        return check;
    }
    
    public boolean updateUser(UserDTO dto){
        boolean check =  false;
        try {
            check = iServer.updateUser(dto);
        } catch (Exception e) {
        }
        return check;
    }
    
    public List<UserDTO> getAll(){
        List<UserDTO> list =null;
        try {
            list = iServer.getAllUser();
        } catch (Exception e) {
        }
        return list;
    }
    
    public UserDTO findUserById(String id){
        try {
            return iServer.findByID(id);
        } catch (Exception e) {
        }
        return null;
    }
}
