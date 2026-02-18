package com.group.libraryapp.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository: JpaRepository<User, Long> {

    //spring support -> if not exists then return null
    fun findByName(name: String): User?

    @Query("select distinct u from User u left join fetch UserLoanHistory as h on u.id = h.user.id")
    fun findAllHistories(): List<User>
}