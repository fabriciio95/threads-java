package threads.fila;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaFilaThread extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel = new JPanel(new GridBagLayout());//Painel de componentes
	private JLabel labelNome = new JLabel("Nome");
	private JTextField txtNome = new JTextField();
	private JLabel labelEmail = new JLabel("E-mail");
	private JTextField txtEmail = new JTextField();
	private JButton botaoAddLista = new JButton("Add Lista");
	private JButton botaoStop = new JButton("Stop");
	
	private ImplementacaoFilaThread implFilaThread = new ImplementacaoFilaThread();
	
	
	public TelaFilaThread() {//Executa o que estiver dentro nom omento da abertuda ou instanciação da classe
		//Configurações iniciais da janela
		setTitle("");
		setSize(new Dimension(240, 240));
		setLocationRelativeTo(null);
		setResizable(false);
		
		//Configurações de componentes da tela
		
		GridBagConstraints gridBagConstraints = new GridBagConstraints();//Controlador de posicionamento de componentes na tela
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.insets = new Insets(5, 10, 5, 5);
		gridBagConstraints.anchor = GridBagConstraints.WEST;
		
		
		labelNome.setPreferredSize(new Dimension(200, 25));
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
		
		botaoAddLista.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(botaoAddLista, gridBagConstraints);
		
		botaoStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx ++;
		jPanel.add(botaoStop, gridBagConstraints);
		
		botaoAddLista.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				
				if(implFilaThread == null) {
					implFilaThread = new ImplementacaoFilaThread();
					implFilaThread.start();
				}else {
				
				for(int i = 0; i < 5; i++) {	//Simlunado 100 processos pesados em massa
					ObjetoFilaThread objetoFilaThread = new ObjetoFilaThread();
					objetoFilaThread.setNome(txtNome.getText());
					objetoFilaThread.setEmail(txtEmail.getText());
					objetoFilaThread.setPosicao(i);
					ImplementacaoFilaThread.add(objetoFilaThread);
				}
			   }
			}
		});
		
		botaoStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				implFilaThread.stop();
				implFilaThread = null;// Matando a Thread que foi estopada, para que depois seja criada outra Thread e naõ sempre se trabalhar com a mesma instancia de Thread
			}
		});
		
		implFilaThread.start();
		add(jPanel, BorderLayout.WEST);
		setVisible(true);//Torna a tela visivel para o usuário
	}

}
