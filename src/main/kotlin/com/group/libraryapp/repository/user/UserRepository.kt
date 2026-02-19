package com.group.libraryapp.repository.user

import com.group.libraryapp.domain.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>, UserRepositoryCustom {

    //spring support -> if not exists then return null
    fun findByName(name: String): User?

//    replaced by querydsl
//    @Query("select distinct u from User u left join fetch UserLoanHistory as h on u.id = h.user.id")
//    fun findAllHistories(): List<User>
}