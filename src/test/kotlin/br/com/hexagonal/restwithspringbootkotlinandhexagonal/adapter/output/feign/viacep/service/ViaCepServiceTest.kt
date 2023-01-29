package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.client.ViaCepClient
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.feign.viacep.response.ViaCepResponse
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.errors.DomainErrors.INVALID_CEP_INTEGRATION
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.exceptions.ViaCepPortException
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientAddressPort
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ViaCepServiceTest {

    private val viaCepClient: ViaCepClient = mock()

    private lateinit var clientAddressPort: ClientAddressPort

    private lateinit var clientAddressResponse: ViaCepResponse

    @BeforeEach
    fun setUp() {
        clientAddressPort = ViaCepService(viaCepClient)
        clientAddressResponse = dummyObject()
    }

    @Test
    fun `given a cep then should to get client address`() {
        whenever(viaCepClient.findAddressByZipCode(any())).thenReturn(clientAddressResponse)
        val clientAddress = clientAddressPort.getAddressClient(dummyObject())

        assertNotNull(clientAddress)
        verify(viaCepClient).findAddressByZipCode(any())
    }

    @Test
    fun `given a cep then should to throws exception when cep is invalid or incorrect`() {
        whenever(viaCepClient.findAddressByZipCode(any())).thenThrow(ViaCepPortException::class.java)
        val exception = assertThrows<ViaCepPortException> { clientAddressPort.getAddressClient(dummyObject()) }

        assertEquals(INVALID_CEP_INTEGRATION, exception.message)
        verify(viaCepClient, only()).findAddressByZipCode(any())
    }
}