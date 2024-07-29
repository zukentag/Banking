package com.example.demo.datasource.mock

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockDataSource : BankDataSource {
    var banks = mutableListOf(
        Bank("HDFC",1.0,3.2),
        Bank("ICIC",1.0,3.2),
        Bank("Axis",1.0,3.2)
    )

    override fun getBanks(): Collection<Bank> = banks
    override fun getBank(accountNumber: String): Bank = banks.first { it.accountNumber == accountNumber }

    override fun addBank(bank: Bank): Bank {
        if(banks.any {it.accountNumber == bank.accountNumber}){
            throw IllegalArgumentException("Similar bank exists")
        }
        banks.add(bank)
        return bank
    }




}