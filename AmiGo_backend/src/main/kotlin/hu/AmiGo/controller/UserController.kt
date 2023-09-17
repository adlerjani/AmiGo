package hu.AmiGo.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@CrossOrigin
@RestController
@RequestMapping("/api/user/")
public class UserController (
//        private val userService: UserService
) {
    @GetMapping("/health")
    fun getHealthTest(): String {
        return "Hello";
    }
//    @PostMapping("/register")
//    fun registerUser(@RequestBody userDTO: UserRegistrationDTO?): ResponseEntity<*> {
//        userService.registerUser(userDTO)
//        return ResponseEntity.ok("User registered successfully")
//    }
}