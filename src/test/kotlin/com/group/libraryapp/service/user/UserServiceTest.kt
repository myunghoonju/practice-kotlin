package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.User
import com.group.libraryapp.repository.user.UserRepository
import com.group.libraryapp.domain.user.loanhistory.LoanStatus
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory
import com.group.libraryapp.repository.loan.UserLoanHistoryRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import com.group.libraryapp.repository.loan.UserLoanHistoryCustom
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    @Autowired private val userService: UserService,
    private val userRepository: UserRepository,
    private val userLoanHistoryRepository: UserLoanHistoryRepository,
    private val userLoanHistoryCustom: UserLoanHistoryCustom,
    service: UserService,
){
    @AfterEach
    fun cleanup(){
        userRepository.deleteAll()
    }

    @Test
    fun save_user_test() {
        //given
        var req = UserCreateRequest("myunghoon", null)

        //when
        userService.saveUser(req)

        //then
        var allUsers = userRepository.findAll()
        assertThat(allUsers).hasSize(1)
        assertThat(allUsers[0].name).isEqualTo("myunghoon")
        assertThat(allUsers[0].age).isNull()
    }

    @Test
    fun get_user_test() {
        //given
        userRepository.saveAll(listOf(
            User("hoon", 19),
            User("myung", 20),
        ))

        //when
        val results = userService.getUsers()

        //then
        assertThat(results).hasSize(2)
        assertThat(results).extracting("name")
                           .containsExactlyInAnyOrder("hoon", "myung")
        assertThat(results).extracting("age")
                           .containsExactlyInAnyOrder(20, 19)
    }

    @Test
    fun update_user_test() {
        //given
        val saved = userRepository.save(User("hoon", 19))
        val req = UserUpdateRequest(saved.id!!, "ju")

        //when
        userService.updateUserName(req)

        //then
        val allUsers = userRepository.findAll()
        assertThat(allUsers[0].name).isEqualTo("ju")
    }

    @Test
    fun 대출_기록이_없는_유저도_포함된_모든_결과를_조회하는_테스트() {
        val saved = userRepository.saveAll(listOf(User("hoon", null), User("myung", 19)))
        userLoanHistoryRepository.saveAll(listOf(
            UserLoanHistory.fixture(saved[0], "A", LoanStatus.BORROW),
            UserLoanHistory.fixture(saved[0], "B", LoanStatus.BORROW),
            UserLoanHistory.fixture(saved[0], "C", LoanStatus.RETURN)))

        var userLoanHistory = userService.getUserLoanHistory()
        val hoon = userLoanHistory.first { it.name == "hoon" }
        assertThat(hoon.name).isEqualTo("hoon")
        assertThat(hoon.books).hasSize(3)
        assertThat(hoon.books).extracting("name").containsExactlyInAnyOrder("A", "B", "C")
        assertThat(hoon.books).extracting("isReturn").containsExactlyInAnyOrder(false, false, true)

        val myung = userLoanHistory.first { it.name == "myung" }
        assertThat(myung.books).isEmpty()
    }
}
