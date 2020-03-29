/**
 * 
 */
package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tamyres
 * 
 *
 */
public class GerenciadoraClientesTest {

	private GerenciadoraClientes gerClientes;
	private int idCliente1 = 1;
	private int idCliente2 = 2;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

		/*------------------------ Criando um cen�rio -----------------------*/
		// Criando alguns clientes
		Cliente cliente1 = new Cliente(idCliente1, "Tamyres", 22, "tamyres@gmail.com", 14, true);
		Cliente cliente2 = new Cliente(idCliente2, "Carla", 20, "carla@gmail.com", 14, true);

		// inserindo os clientes criados na lista clientesDoBanco
		List<Cliente> clientesDoBanco = new ArrayList<>();
		clientesDoBanco.add(cliente1);
		clientesDoBanco.add(cliente2);

		gerClientes = new GerenciadoraClientes(clientesDoBanco);
	}

	@After
	public void tearDown() {
		gerClientes.limpa();

	}

	/**
	 * 
	 * Teste b�sico da pesquisa de um Cliente a partir do seu ID.]
	 * 
	 */
	@Test
	public void testPesquisaClienteTest() {

		// execu��o
		Cliente cliente = gerClientes.pesquisaCliente(idCliente1);

		// verifica��o
		assertThat(cliente.getId(), is(idCliente1));
	}

	/**
	 * Teste b�sico da pesquisa por um cliente que n�o existe
	 * 
	 * 
	 */
	@Test
	public void testPesquisaClienteInexistente() {

		// execu��o
		Cliente cliente = gerClientes.pesquisaCliente(1001);

		// verifica��o
		assertNull(null);

	}

	/**
	 * Teste b�sico da remo��o de um cliente a partir do seu ID
	 * 
	 */
	@Test
	public void testRemoveClienteTest() {

		// execu��o
		boolean clienteRemovido = gerClientes.removeCliente(idCliente2);

		// verifica��o
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente2));

	}

	/**
	 * 
	 * Teste b�sico da remo��o de um cliente que n�o existe
	 * 
	 */
	@Test
	public void testRemoveClienteInexistente() {

		// execu��o
		boolean removeCliente = gerClientes.removeCliente(1011);

		// verifica��o
		assertThat(removeCliente, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));

	}

	/**
	 * Valida��o da idade de um cliente quando a mesma est� no intervalo permitido
	 * 
	 */
	@Test
	public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

		// Cen�rio
		Cliente cliente = new Cliente(10, "Maria Aparecida", 25, "maria@gmail.com", 13, true);

		// Execu��o
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);

	}

	@Test
	public void testClienteIdadeAceitavel_1() throws IdadeNaoPermitidaException {

		// Cen�rio
		Cliente cliente = new Cliente(11, "Thais", 18, "thais@gmail.com", 13, true);

		// Execu��o
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);

	}

	@Test
	public void testClienteIdadeAceitavel_2() throws IdadeNaoPermitidaException {

		// Cen�rio
		Cliente cliente = new Cliente(12, "Santos", 17, "santos@gmail.com", 13, true);

		try {
			gerClientes.validaIdade(cliente.getIdade());
			fail();
		} catch (Exception e) {
			assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
		}

	}

	@Test
	public void testClienteIdadeAceitavel_3() throws IdadeNaoPermitidaException {

		// Cen�rio
		Cliente cliente = new Cliente(13, "Cida", 65, "cida@gmail.com", 13, true);
		
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);

	}

	@Test
	public void testClienteIdadeAceitavel_4() throws IdadeNaoPermitidaException {

		// Cen�rio
		Cliente cliente = new Cliente(14, "fulano", 66, "fulano@gmail.com", 13, true);

	try {
		gerClientes.validaIdade(cliente.getIdade());
		fail();
	} catch (Exception e) {
		assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
	}
	}

}
