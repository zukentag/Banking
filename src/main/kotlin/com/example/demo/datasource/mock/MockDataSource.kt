package com.example.demo.datasource.mock

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockDataSource : BankDataSource {
    val banks = listOf(
        Bank("HDFC",1.0,3.2),
        Bank("ICIC",1.0,3.2),
        Bank("Axis",1.0,3.2)
    )

    override fun getBanks(): Collection<Bank> = banks

}