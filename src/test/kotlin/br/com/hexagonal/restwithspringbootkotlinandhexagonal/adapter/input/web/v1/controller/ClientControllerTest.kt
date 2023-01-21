package br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.controller

import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.CreateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.adapter.input.web.v1.api.request.UpdateClientRequest
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.domain.Client
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.CreateClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.DeleteClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.FindClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.application.port.input.UpdateClientUseCase
import br.com.hexagonal.restwithspringbootkotlinandhexagonal.helper.dummyObject
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.*
import java.util.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class ClientControllerTest {

    private lateinit var clientController: ClientController

    private val createClientUseCase: CreateClientUseCase = mock()
    private val updateClientUseCase: UpdateClientUseCase = mock()
    private val findClientUseCase: FindClientUseCase = mock()
    private val deleteClientUseCase: DeleteClientUseCase = mock()

    private lateinit var client: Client


    @BeforeEach
    fun setUp() {
        clientController = ClientController(
            createClientUseCase,
            updateClientUseCase,
            findClientUseCase,
            deleteClientUseCase
        )
        client = dummyObject()
    }

    @Test
    fun `given a request to insert an client then it should insert it`() {
        val createClientRequest = dummyObject<CreateClientRequest>().copy(documentType = "CPF")

        whenever(createClientUseCase.execute(any())).thenReturn(client)
        val clientResponse = clientController.create(createClientRequest)

        assertNotNull(clientResponse)
        verify(createClientUseCase, only()).execute(
            check {
                assertEquals(createClientRequest.name, it.name)
                assertEquals(createClientRequest.email, it.email)
                assertEquals(createClientRequest.documentNumber, it.document.number)
                assertEquals(createClientRequest.address!!.zipCode, it.address!!.zipCode)
                assertEquals(createClientRequest.address!!.number, it.address!!.number)
            }
        )
    }

    @Test
    fun `given a request to update an client then it should update it`() {
        val updateClientRequest = dummyObject<UpdateClientRequest>().copy(documentType = "CPF")
        val clientId = UUID.randomUUID()

        whenever(updateClientUseCase.execute(any())).thenReturn(client)
        val clientResponse = clientController.update(clientId, updateClientRequest)

        assertNotNull(clientResponse)
        verify(updateClientUseCase, only()).execute(
            check {
                assertEquals(clientId, it.id)
                assertEquals(updateClientRequest.name, it.name)
                assertEquals(updateClientRequest.email, it.email)
                assertEquals(updateClientRequest.documentNumber, it.document.number)
                assertEquals(updateClientRequest.gender, it.gender)
                assertEquals(updateClientRequest.salary, it.salary)
            }
        )
    }

    @Test
    fun `given a request to find an client then it should found it`() {
        whenever(findClientUseCase.execute(any())).thenReturn(client)
        val clientResponse = clientController.findById(dummyObject())

        assertNotNull(clientResponse)
        verify(findClientUseCase).execute(any())
    }

    @Test
    fun `given a request to find all an client then it should find all it`() {
        whenever(findClientUseCase.execute()).thenReturn(listOf(client))
        clientController.findAll()

        verify(findClientUseCase).execute()
    }

    @Test
    fun `given a request to delete an client then it should delete it`() {
        clientController.delete(dummyObject())

        verify(deleteClientUseCase).execute(any())
    }

}