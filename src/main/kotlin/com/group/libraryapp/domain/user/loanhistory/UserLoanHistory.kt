package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class UserLoanHistory constructor(

    @ManyToOne
    val user: User,

    val bookName: String,

    @Enumerated(EnumType.STRING)
    var status: LoanStatus = LoanStatus.BORROW,

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null
) {

    fun returned() {
        this.status = LoanStatus.RETURN
    }

    companion object {
        fun fixture (
            user: User,
            bookName: String = "alice",
            status: LoanStatus = LoanStatus.BORROW,
            id: Long? = null
        ): UserLoanHistory {
            return UserLoanHistory(
                user = user,
                bookName = bookName,
                status = status,
                id = id
            )
        }
    }
}