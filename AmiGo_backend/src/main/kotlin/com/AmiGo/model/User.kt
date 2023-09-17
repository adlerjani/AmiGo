package com.AmiGo.model

import com.AmiGo.controller.dto.UserResponseDto
import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "user")
class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    val id:Int=0,
    @Column val password:String ="",
    @Column val profileURL:String ="",
    @Column val full_name:String ="",
    @Column val email:String ="",
    @Column val birth:String="",
    @Column val bio:String ="",
    )

fun User.toResponseDto(): UserResponseDto{
    return UserResponseDto(full_name,email,profileURL,birth,bio)
}