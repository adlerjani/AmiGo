package com.AmiGo.model

import jakarta.persistence.*

@Entity
@Table(name = "post")
class Post (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        val id:Int=0,
        @Column val content:String ="",
        @Column val mediaContent:String ="",
        @Column val created:String ="",
        @OneToOne(mappedBy = "user", cascade = [CascadeType.PERSIST]) val userReference:User
)