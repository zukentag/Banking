package com.example.demo.datasource

import com.example.demo.model.Bank

interface BankDataSource {
    fun getBanks(): Collection<Bank>
    fun getBank(accountNumber:String): Bank
    fun addBank(bank: Bank): Bank
    fun patchBank(accountNumber: String, bank: Bank): Bank
    fun deleteBank(accountNumber: String)
}