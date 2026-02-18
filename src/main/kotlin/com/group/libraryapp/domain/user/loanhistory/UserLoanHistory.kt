package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.domain.user.loanhistory.LoanStatus.BORROW
import com.group.libraryapp.domain.user.loanhistory.LoanStatus.RETURN
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
    var status: LoanStatus = BORROW,

    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null
) {
    val isReturned: Boolean
        get() = this.status == RETURN

    fun returned() {
        this.status = RETURN
    }

    companion object {
        fun fixture (
            user: User,
            bookName: String = "alice",
            status: LoanStatus = BORROW,
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