package threads.exerciciopratico;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaExercicioPratico extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel = new JPanel(new GridBagLayout());//Painel de componentes
	private JLabel labelDadosCliente = new JLabel("Dados do cliente:");
	private JLabel labelNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField();
	private JLabel labelEmail = new JLabel("E-mail");
	private JTextField txtEmail = new JTextField();
	private JLabel labelDadosProduto = new JLabel("Produto:");
	private JLabel labelNomeProduto = new JLabel("Nome");
	private JTextField txtNomeProduto = new JTextField();
	private JLabel labelValorProduto = new JLabel("Valor");
	private JTextField txtValorProduto = new JTextField();
	private JButton botaoAddLista = new JButton("Add Lista");
	private JButton botaoComprar = new JButton("Comprar");
	
	private ConcurrentLinkedQueue<Cliente> clientes = new ConcurrentLinkedQueue<Cliente>();
	private ConcurrentLinkedQueue<Cliente> clientes2 = new ConcurrentLinkedQueue<Cliente>();
	private Cliente clienteCompra;
	private ThreadNotifcaGerenteClientesCadastrados threadGerenteClientesCadastrados = new ThreadNotifcaGerenteClientesCadastrados();
	
	public TelaExercicioPratico() {//Executa o que estiver dentro nom omento da abertuda ou instanciação da classe
		//Configurações iniciais da janela
		setTitle("");
		setSize(new Dimension(440, 440));
		setLocationRelativeTo(null);
		setResizable(false);
		
		//Configurações de componentes da tela
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();//Controlador de posicionamento de componentes na tela
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		
		labelDadosCliente.setPreferredSize(new Dimension(200, 25));
		jPanel.add(labelDadosCliente, gridBagConstraints);
		
		labelNome.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(labelNome, gridBagConstraints);
		
		txtNome.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(txtNome, gridBagConstraints);
		
		labelEmail.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(labelEmail, gridBagConstraints);
		
		txtEmail.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy ++;
		jPanel.add(txtEmail, gridBagConstraints);
		
		gridBagConstraints.gridwidth = 1;
		
		labelDadosProduto.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy ++;
		jPanel.add(labelDadosProduto, gridBagConstraints);
		
		labelNomeProduto.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(labelNomeProduto, gridBagConstraints);
		
		txtNomeProduto.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		jPanel.add(txtNomeProduto, gridBagConstraints);
		
		labelValorProduto.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(labelValorProduto, gridBagConstraints);
		
		
		txtValorProduto.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy ++;
		jPanel.add(txtValorProduto, gridBagConstraints);
		
		
		botaoAddLista.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(botaoAddLista, gridBagConstraints);
		
		botaoComprar.setPreferredSize(new Dimension(92, 25));
		botaoComprar.setEnabled(false);
		gridBagConstraints.gridx ++;
		jPanel.add(botaoComprar, gridBagConstraints);
		
		botaoAddLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botaoComprar.setEnabled(true);
				txtNome.setEditable(false);
				txtEmail.setEditable(false);
				Integer idCliente = Utils.gerarIdCliente(threadGerenteClientesCadastrados.getClientesFila());
				Cliente cliente = Utils.recuperarClientePorIdOuPorNome(0,txtNome.getText(), threadGerenteClientesCadastrados.getClientesFila(), false);
				if(cliente == null) {
				cliente = new Cliente(idCliente, txtNome.getText(), txtEmail.getText());
				threadGerenteClientesCadastrados.cadastrarCliente(cliente);
			  }
				clienteCompra = cliente;
				Produto produto = new Produto(Utils.gerarIdProduto(cliente.getProdutos()), txtNomeProduto.getText(), Double.parseDouble(txtValorProduto.getText()));
				cliente.addProduto(produto);
			}
		});
		botaoComprar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				botaoAddLista.setEnabled(false);
				botaoComprar.setEnabled(false);
				txtNome.setText("");
				txtNome.setEditable(false);
				txtEmail.setText("");
				txtEmail.setEditable(false);
				txtNomeProduto.setText("");
				txtNomeProduto.setEditable(false);
				txtValorProduto.setText("");
				txtValorProduto.setEditable(false);
				if(Utils.pagamento(clienteCompra)) {
				JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso " + clienteCompra.getNome() + ", nota fiscal será enviada por e-mail!");
				txtNome.setEditable(true);
				txtEmail.setEditable(true);
				txtNomeProduto.setEditable(true);
				txtValorProduto.setEditable(true);
				botaoAddLista.setEnabled(true);
				clientes.add(clienteCompra);
				clientes2.add(clienteCompra);
				}
	
				
				Iterator<Cliente> clienteIterator = clientes.iterator();
				while(clienteIterator.hasNext()) {
					Cliente cliente = clienteIterator.next();
					new Thread(() -> {
						System.out.println("-------------------------------------------------------");
						System.out.println("Dando baixa em estoque no banco de dados...");
						Iterator<Produto> iteratorProdutos = cliente.getProdutos().iterator();
						while(iteratorProdutos.hasNext()) {
								Produto prod = iteratorProdutos.next();
								for(int i = 0; i < 3; i++) {
									System.out.println("Dando baixa no produto " + prod.getNome() + " de código " + prod.getIdProduto() + "...");
									try {
										Thread.sleep(1 * 2000);
									}catch(InterruptedException ei) {
										ei.printStackTrace();
									}
								}
								iteratorProdutos.remove();;
						}
						System.out.println("Estoque no banco de dados atualizado com sucesso após o pedido de " + cliente.getNome() + "!");
						System.out.println("-------------------------------------------------------");
					
					}).start();
					clienteIterator.remove();
				}
				
				Iterator<Cliente> clientesIterator = clientes2.iterator();
				while(clientesIterator.hasNext()) {
					Cliente cliente = clientesIterator.next();
					new Thread(() ->  {
						Double valorTotalPedido = Utils.getValorTotalPedido(cliente.getProdutos());
						System.out.println("-------------------------------------------------------");
						System.out.println("Enviando nota fiscal por e-mail para " + cliente.getEmail() + "...");
						System.out.println("Corpo do email:");
						System.out.println("==================== COMPRA REALIZADA COM SUCESSO ====================");
						System.out.println("Compra realizada com sucesso!!! Dados do pedido:");
						System.out.println("Cliente:" + cliente.getNome());
						System.out.println("Produtos adquiridos:");
						Iterator<Produto> produtosIterator = cliente.getProdutos().iterator();
						while(produtosIterator.hasNext()) {
							    Produto prod = produtosIterator.next();
								System.out.println(" - Produto: " + prod.getNome());
								System.out.println(String.format(" - Valor: %.2f", prod.getValorProduto()));
								produtosIterator.remove();
								try {
									Thread.sleep(1 * 3000);
								}catch(InterruptedException e2) {
									e2.printStackTrace();
								}
							}
					
						System.out.println(String.format("%s o valor total de seu pedido foi de %.2f ", cliente.getNome(), valorTotalPedido));
						System.out.println("Email enviado para " + cliente.getEmail() + " com sucesso!!!");
						System.out.println("-------------------------------------------------------");
						
					}).start();
					clientesIterator.remove();
				}
			}});
		
		
		add(jPanel, BorderLayout.WEST);
		setVisible(true);
	}


	public Cliente getClienteCompra() {
		return clienteCompra;
	}


	public void setClienteCompra(Cliente clienteCompra) {
		this.clienteCompra = clienteCompra;
	}
}


