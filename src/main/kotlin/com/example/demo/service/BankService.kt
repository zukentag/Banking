package com.example.demo.service

import com.example.demo.datasource.BankDataSource
import com.example.demo.model.Bank
import org.springframework.stereotype.Service
import javax.sql.DataSource

@Service
class BankService (private val dataSource: BankDataSource) {
    fun getBanks() : Collection<Bank> = dataSource.getBanks()
    fun getBank(accountNumber: String) : Bank = dataSource.getBank(accountNumber)
    fun addBank(bank: Bank) : Bank = dataSource.addBank(bank)

}