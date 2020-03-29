package negocio;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GerenciadoraContasTest {
	
	private GerenciadoraContas gerContas;
	private int idContaCorrente1 = 1;
	private int idContaCorrente2 = 2;

	@Before
	public void setUp() throws Exception {

		ContaCorrente ContaCorrente1 = new ContaCorrente(idContaCorrente1, 1000, true);
		ContaCorrente ContaCorrente2 = new ContaCorrente(idContaCorrente2, 1000, true);

		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(ContaCorrente1);
		contasDoBanco.add(ContaCorrente2);

		gerContas = new GerenciadoraContas(contasDoBanco);
	}

	@Test
	public void pesquisaContaTest() {
		
		ContaCorrente conta = gerContas.pesquisaConta(idContaCorrente1);
		
		assertThat(conta.getId(),  is(idContaCorrente1));

	}
	
	@Test  
	public void removeContaTest() {
		
		boolean removeConta = gerContas.removeConta(idContaCorrente1);
		
		assertThat(removeConta, is(true));
		assertThat(gerContas.getContasDoBanco().size(), is(1));
		assertNull(gerContas.pesquisaConta(idContaCorrente1));	
	}
	
	@Test
	public void contaAtiva() {
		
		boolean contaAtiva = gerContas.contaAtiva(idContaCorrente2);
		
		assertThat(contaAtiva, is(true));
	}
	
	@Test
	public void transfereValorTest() {
	
			int idConta1=1;
			int idConta2=2;
		
		ContaCorrente conta1 = new ContaCorrente(idConta1, 1000, true);
		ContaCorrente conta2 = new ContaCorrente(idConta2, 1000, true);
		
		List<ContaCorrente> contasDoBanco = new ArrayList<>();
		contasDoBanco.add(conta1);
		contasDoBanco.add(conta2);
		
		gerContas = new GerenciadoraContas(contasDoBanco);
		
		boolean transferenciaRealizada = gerContas.transfereValor(idConta1, 100, idConta2);
		
		assertThat(transferenciaRealizada, is(true));
		assertThat(conta1.getSaldo(), is(900.0));
		assertThat(conta2.getSaldo(), is(1100.0));

	}
	
	
	 
}
