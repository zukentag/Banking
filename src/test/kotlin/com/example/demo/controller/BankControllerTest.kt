package com.example.demo.controller

import com.example.demo.model.Bank
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.env.SpringApplicationJsonEnvironmentPostProcessor
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class BankControllerTest @Autowired constructor (
    var mockMvc : MockMvc,
    var objectMapper : ObjectMapper
){

    @Test
    fun `should return all banks` () {
        mockMvc.get("/api/banks")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
            }

    }

    @Test
    fun `should return a bank with the give accountNumber` () {
        val accountNumber = "HDFC"
        mockMvc.get("/api/banks/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isOk() }
                content { contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.trust") {value("1.0")}
            }

    }

    @Test
    fun `should return NotFound if accountNumber does not exists` () {
        val accountNumber = "not_exist"
        mockMvc.get("/api/banks/$accountNumber")
            .andDo { print() }
            .andExpect {
                status { isNotFound() }

            }

    }

    @Test
    fun `should add a bank`() {
        val newBank = Bank("IDFC", 1.2, 1.2)

        mockMvc.post("/api/banks") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(newBank)
        }
            .andDo { print() }
            .andExpect {
                status { isCreated() }
                content {contentType(MediaType.APPLICATION_JSON) }
                jsonPath("$.accountNumber") {value("IDFC")}
            }
    }

    @Test
    fun `should return BadRequest on adding a bank that exists`() {
        val invalidBank = Bank("IDFC", 1.2, 1.2)

        mockMvc.post("/api/banks") {
            contentType = MediaType.APPLICATION_JSON
            content = ObjectMapper().writeValueAsString(invalidBank)
        }
            .andDo { print() }
            .andExpect {
                status { isBadRequest() }

            }
    }

}