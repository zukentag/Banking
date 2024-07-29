package com.example.demo.datasource

import com.example.demo.datasource.mock.MockDataSource
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MockDataSourceTest{

    private val mockDataSource = MockDataSource()

    @Test
    fun `should provide a collections of banks` () {
        val banks = mockDataSource.getBanks()

        assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide some mock data` () {
        val banks = mockDataSource.getBanks()

        assertThat(banks).allMatch { it.accountNumber.isNotBlank()}
        assertThat(banks).anyMatch { it.trust != 0.0}
        assertThat(banks).allMatch { it.transactionFee != 0.0}
    }
}