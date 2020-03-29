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

		/*------------------------ Criando um cenário -----------------------*/
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
	 * Teste básico da pesquisa de um Cliente a partir do seu ID.]
	 * 
	 */
	@Test
	public void testPesquisaClienteTest() {

		// execução
		Cliente cliente = gerClientes.pesquisaCliente(idCliente1);

		// verificação
		assertThat(cliente.getId(), is(idCliente1));
	}

	/**
	 * Teste básico da pesquisa por um cliente que não existe
	 * 
	 * 
	 */
	@Test
	public void testPesquisaClienteInexistente() {

		// execução
		Cliente cliente = gerClientes.pesquisaCliente(1001);

		// verificação
		assertNull(null);

	}

	/**
	 * Teste básico da remoção de um cliente a partir do seu ID
	 * 
	 */
	@Test
	public void testRemoveClienteTest() {

		// execução
		boolean clienteRemovido = gerClientes.removeCliente(idCliente2);

		// verificação
		assertThat(clienteRemovido, is(true));
		assertThat(gerClientes.getClientesDoBanco().size(), is(1));
		assertNull(gerClientes.pesquisaCliente(idCliente2));

	}

	/**
	 * 
	 * Teste básico da remoção de um cliente que não existe
	 * 
	 */
	@Test
	public void testRemoveClienteInexistente() {

		// execução
		boolean removeCliente = gerClientes.removeCliente(1011);

		// verificação
		assertThat(removeCliente, is(false));
		assertThat(gerClientes.getClientesDoBanco().size(), is(2));

	}

	/**
	 * Validação da idade de um cliente quando a mesma está no intervalo permitido
	 * 
	 */
	@Test
	public void testClienteIdadeAceitavel() throws IdadeNaoPermitidaException {

		// Cenário
		Cliente cliente = new Cliente(10, "Maria Aparecida", 25, "maria@gmail.com", 13, true);

		// Execução
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);

	}

	@Test
	public void testClienteIdadeAceitavel_1() throws IdadeNaoPermitidaException {

		// Cenário
		Cliente cliente = new Cliente(11, "Thais", 18, "thais@gmail.com", 13, true);

		// Execução
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);

	}

	@Test
	public void testClienteIdadeAceitavel_2() throws IdadeNaoPermitidaException {

		// Cenário
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

		// Cenário
		Cliente cliente = new Cliente(13, "Cida", 65, "cida@gmail.com", 13, true);
		
		boolean idadeValida = gerClientes.validaIdade(cliente.getIdade());

		assertTrue(idadeValida);

	}

	@Test
	public void testClienteIdadeAceitavel_4() throws IdadeNaoPermitidaException {

		// Cenário
		Cliente cliente = new Cliente(14, "fulano", 66, "fulano@gmail.com", 13, true);

	try {
		gerClientes.validaIdade(cliente.getIdade());
		fail();
	} catch (Exception e) {
		assertThat(e.getMessage(), is(IdadeNaoPermitidaException.MSG_IDADE_INVALIDA));
	}
	}

}
