/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuongnv.interfaces;

import java.rmi.Remote;
import java.util.List;
import cuongnv.dtos.UserDTO;

public interface RegistrationInterface extends Remote {
    boolean createRegistration(UserDTO dto);
    UserDTO findByID(String id);
    List<UserDTO> getAllUser();
    boolean removeUser (String id);
    boolean updateUser (UserDTO dto);
}
