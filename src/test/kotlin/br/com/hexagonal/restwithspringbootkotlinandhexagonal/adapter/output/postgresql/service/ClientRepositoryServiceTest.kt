package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.service

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.exceptions.NotFoundException
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.converter.toEntity
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.output.postgresql.repository.ClientRepository
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.output.ClientRepositoryPort
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ClientRepositoryServiceTest {

    private val clientRepository: ClientRepository = mock()

    private lateinit var clientRepositoryPort: ClientRepositoryPort

    private lateinit var client: Client


    @BeforeEach
    fun setUp() {
        clientRepositoryPort = ClientRepositoryService(clientRepository)
        client = dummyObject()
    }

    @Test
    fun `given an use case to save a new client then should save it`() {
        clientRepositoryPort.save(client)
        verify(clientRepository, times(1)).save(any())
    }

    @Test
    fun `given an use case to update client then should throw a NotFoundException`() {
      assertThrows<NotFoundException> {
          clientRepositoryPort.update(client)
      }
        verify(clientRepository, times(0)).save(any())
        verify(clientRepository, times(1)).findByIdAndActiveTrue(any())
    }

    @Test
    fun `given an use case to update client then should update it`() {
        whenever(clientRepository.findByIdAndActiveTrue(any())).thenReturn(Optional.of(client.toEntity()))
        clientRepositoryPort.update(client)

        verify(clientRepository, times(1)).save(any())
        verify(clientRepository, times(1)).findByIdAndActiveTrue(any())
    }

    @Test
    fun `given an use case to findAll clients then should found it`() {
        whenever(clientRepository.findAllAndActiveTrue()).thenReturn(listOf(client.toEntity()))
        clientRepositoryPort.findAllAndActiveTrue()

        verify(clientRepository,  times(1)).findAllAndActiveTrue()
    }

    @Test
    fun `given an use case to find a client then should found it`() {
        whenever(clientRepository.findByIdAndActiveTrue(any())).thenReturn(Optional.of(client.toEntity()))
        val result = clientRepositoryPort.findByIdAndActiveTrue(client.id)

        assertNotNull(result)
        assertEquals(client.id, result.id)
        assertEquals(client.name, result.name)
        verify(clientRepository, times(1)).findByIdAndActiveTrue(any())
    }

    @Test
    fun `given an use case to find a client then should throw a NotFoundException`() {
        assertThrows<NotFoundException> {
            clientRepositoryPort.findByIdAndActiveTrue(client.id)
        }
        verify(clientRepository, times(1)).findByIdAndActiveTrue(any())
    }

    @Test
    fun `given an use case to delete a client then should deleted it`() {
        whenever(clientRepository.findByIdAndActiveTrue(any())).thenReturn(Optional.of(client.toEntity()))
        clientRepositoryPort.delete(client.id)

        verify(clientRepository, times(1)).save(any())
        verify(clientRepository, times(1)).findByIdAndActiveTrue(any())
    }

    @Test
    fun `given an use case to delete a client then should throw a NotFoundException`() {
        assertThrows<NotFoundException> {
            clientRepositoryPort.delete(client.id)
        }
        verify(clientRepository, times(1)).findByIdAndActiveTrue(any())
    }

}