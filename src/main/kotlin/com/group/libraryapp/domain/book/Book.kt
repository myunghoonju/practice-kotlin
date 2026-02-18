package com.group.libraryapp.domain.book

import com.group.libraryapp.domain.book.field.BookType
import org.springframework.util.StringUtils
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id

// getter is auto-generated in kotlin
@Entity
class Book constructor (
    val name: String,

    @Enumerated(EnumType.STRING)
    val type: BookType,

    //default parameter should be placed bottom
    @Id @GeneratedValue(strategy = IDENTITY)
    val id : Long? = null,
) {

    //init block
    init {
        if (!StringUtils.hasText(name)) {
            throw IllegalArgumentException("이름은 비어 있을 수 없습니다")
        }
    }

    //object mother
    companion object {
        fun fixture(
            name: String = "test name",
            type: BookType = BookType.COMPUTER,
            id: Long? = null,
        ): Book {
            return Book(
                name = name,
                type = type,
                id = id
            )
        }
    }
}