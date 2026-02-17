package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    //spring support -> if not exists then return null
    fun findByName(name: String): User?
}