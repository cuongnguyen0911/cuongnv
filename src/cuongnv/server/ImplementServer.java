package cuongnv.server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import cuongnv.dtos.UserDTO;
import cuongnv.interfaces.RegistrationInterface;



public class ImplementServer implements RegistrationInterface, Serializable{
   
    List<UserDTO> users = new ArrayList<>();
    int count = 0;
    
    public void writeToFile() throws IOException {
        FileWriter fw = null;
        PrintWriter pw = null;
        
        try {
            String data = "";
            fw = new FileWriter("data.txt");
            pw = new PrintWriter(fw);
            for(UserDTO dto : users){
                data = dto.getRegistrationID() + "$" + dto.getFullName() + "$" + dto.getAge() 
                        + "$" + (dto.isGender() ? 1 : 0) + "$" + dto.getEmail() + "$" + dto.getPhone() 
                        + "$" + dto.getAddress() + "$" + dto.getNumOfMember() + "$" + dto.getNumOfAdult() 
                        + "$" + dto.getNumOfChildren();
                pw.println(data);
                pw.flush();
            }
        } catch (Exception e) {
        } finally {
            pw.close();
            fw.close();
        }
    }
    @Override 
     public  boolean createRegistration(UserDTO dto){
        boolean check = false;
        users.add(dto);
        try {
            String data="";
            data = dto.getRegistrationID() + "$" + dto.getFullName() + "$" + dto.getAge() 
                        + "$" + (dto.isGender() ? 1 : 0) + "$" + dto.getEmail() + "$" + dto.getPhone() 
                        + "$" + dto.getAddress() + "$" + dto.getNumOfMember() + "$" + dto.getNumOfAdult() 
                        + "$" + dto.getNumOfChildren();
            FileWriter fw = new FileWriter("data.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(data);
            pw.flush();
            pw.close();
            fw.close();
            writeToFile();
            check =true;
        } catch (Exception e) {
        }
        return check;
    }

    @Override
    public UserDTO findByID(String id){
        for (UserDTO dto: users){
            if(dto.getRegistrationID().equals(id)){
                return dto;
            }
        }
        return  null;
    }
    
    @Override
    public ArrayList<UserDTO> getAllUser(){
        try {
            if(users.isEmpty()){
                FileReader fr = new FileReader("data.txt");
                BufferedReader br = new BufferedReader(fr);
                String line;
                StringTokenizer stk;
                String registrationID, fullName, email, phone, address;
                int age, numOfMember, numOfChildren,numOfAdult;
                boolean gender = false;
                int checkGender;
                while((line = br.readLine()) != null){
                    stk = new StringTokenizer(line, "$");
                    
                    registrationID = stk.nextToken();
                    fullName = stk.nextToken();
                    age = Integer.parseInt(stk.nextToken());
                    checkGender = Integer.parseInt(stk.nextToken());
                    if(checkGender == 1 ){
                        gender = true;
                    }else if(checkGender == 0){
                        gender = false;
                    }
                    email = stk.nextToken();
                    phone = stk.nextToken();
                    fullName = stk.nextToken();
                    address = stk.nextToken();
                    numOfMember = Integer.parseInt(stk.nextToken());
                    numOfAdult = Integer.parseInt(stk.nextToken());
                    numOfChildren = Integer.parseInt(stk.nextToken());
                    
                    users.add(new UserDTO(registrationID, fullName, email, phone, address, gender, age, numOfMember, numOfChildren, numOfAdult));
                }
            }
            return (ArrayList<UserDTO>) users;
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    public boolean removeUser(String id){
        boolean check = false;
        try {
            for(int i =0; i<users.size(); i++){
                if(users.get(i).getRegistrationID().equals(id)){
                    users.remove(i);
                }
            }
            writeToFile();
            check = true;
        } catch (Exception e) {
        }
        return check;
    }
    
    @Override
    public boolean updateUser(UserDTO dto){
        try {
            for(UserDTO newdto : users){
                if(newdto.getRegistrationID().equals(dto.getRegistrationID())){
                    newdto.setFullName(dto.getFullName());
                    newdto.setAge(dto.getAge());
                    newdto.setGender(dto.isGender());
                    newdto.setEmail(dto.getEmail());
                    newdto.setPhone(dto.getPhone());
                    newdto.setAddress(dto.getAddress());
                    newdto.setNumOfMember(dto.getNumOfMember());
                    newdto.setNumOfAdult(dto.getNumOfAdult());
                    newdto.setNumOfChildren(dto.getNumOfChildren());
                }
            }
            writeToFile();
            return true;
        } catch (Exception e) {
        }
        return false;
    }
}