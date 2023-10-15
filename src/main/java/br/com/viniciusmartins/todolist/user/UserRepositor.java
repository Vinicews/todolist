package br.com.viniciusmartins.todolist.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface UserRepositor extends JpaRepository<UserModel, UUID>{
    UserModel findByUsername(String username);
   
    
}
