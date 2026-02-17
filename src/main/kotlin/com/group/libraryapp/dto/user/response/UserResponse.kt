package com.group.libraryapp.dto.user.response

import com.group.libraryapp.domain.user.User

class UserResponse constructor(
    val id: Long,
    val name: String,
    val age: Int?,
) {
    //best
    companion object {
        fun of (user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                name = user.name,
                age = user.age,
            )
        }
    }

// better
//  constructor(user: User) : this (
//      id = user.id!!,
//      name = user.name,
//      age = user.age,
//  )
}
