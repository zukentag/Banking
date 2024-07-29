package com.example.demo.service

import com.example.demo.datasource.BankDataSource

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BankServiceTest {

    private val dataSource: BankDataSource = mockk()
    private val bankService = BankService(dataSource)

    @Test
    fun `should call its dataSource to get banks`() {


        every { dataSource.getBanks() } returns emptyList()

        val banks = bankService.getBanks()

        verify(exactly = 1) { dataSource.getBanks() }

    }
}
