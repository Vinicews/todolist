package br.com.viniciusmartins.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;



 @RestController
 @RequestMapping("/users")
public class UserControler {
    
    @Autowired
    private UserRepositor useRepository;

    /**
     * @param userModel
     * @return
     */
    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.useRepository.findByUsername(userModel.getUsername());

        if(user != null){
            //mensagem de rro
            //status code
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
        }
        
        var passwordHashd = BCrypt.withDefaults()
        .hashToString(12, userModel.getPassword().toCharArray());

        userModel.setPassword(passwordHashd);

         var userCreated = this.useRepository.save(userModel);
         return ResponseEntity.status(HttpStatus.OK).body(userCreated);
    }
    
}
