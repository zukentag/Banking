package com.example.demo.controller

import com.example.demo.model.Bank
import com.example.demo.service.BankService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/banks")
class BankController(private val service : BankService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound (e : NoSuchElementException) : ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest (e : IllegalArgumentException) : ResponseEntity<String> {
        return ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
    }

    @GetMapping
    fun getBanks() :Collection<Bank> {
        return service.getBanks()
    }

    @GetMapping("/{accountNumber}")
    fun getBanks(@PathVariable accountNumber:String) : Bank {
        return service.getBank(accountNumber)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addBank(@RequestBody bank: Bank) : Bank {
        return service.addBank(bank)
    }

    @PatchMapping("/{accountNumber}")
    fun patchBank(@PathVariable accountNumber: String, @RequestBody bank:Bank) : Bank {
        return service.patchBank(accountNumber,bank)

    }

    @DeleteMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBank(@PathVariable accountNumber: String) {
        return service.deleteBank(accountNumber)
    }
}