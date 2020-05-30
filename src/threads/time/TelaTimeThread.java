package threads.time;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaTimeThread extends JDialog {
	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel = new JPanel(new GridBagLayout());//Painel de componentes
	private JLabel descricaoHora = new JLabel("Time Thred 1");
	private JTextField mostraTempo = new JTextField();
	private JLabel descricaoHora2 = new JLabel("Time Thred 2");
	private JTextField mostraTempo2 = new JTextField();
	private JButton botaoStart = new JButton("Start");
	private JButton botaoStop = new JButton("Stop");
	
	private Runnable runnableTheread = new Runnable() {
		public void run() {
			while(true) {
				mostraTempo.setText(new SimpleDateFormat("dd/MM/yyyy hh:mm.ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	
	private Runnable runnableThread2 = new Runnable() {
		
		@Override
		public void run() {
			while(true) {
				
				mostraTempo2.setText(new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(Calendar.getInstance().getTime()));
				try {
					Thread.sleep(1 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	};
	
	Thread threadTime1;
	Thread threadTime2;
	
	
	
	public TelaTimeThread() {//Executa o que estiver dentro nom omento da abertuda ou instanciação da classe
		//Configurações iniciais da janela
		setTitle("Time com Thread");
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
		
		
		descricaoHora.setPreferredSize(new Dimension(200, 25));
		jPanel.add(descricaoHora, gridBagConstraints);
		
		mostraTempo.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy++;
		mostraTempo.setEditable(false);
		jPanel.add(mostraTempo, gridBagConstraints);
		
		descricaoHora2.setPreferredSize(new Dimension(200, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(descricaoHora2, gridBagConstraints);
		
		mostraTempo2.setPreferredSize(new Dimension(200,25));
		gridBagConstraints.gridy ++;
		mostraTempo2.setEditable(false);
		jPanel.add(mostraTempo2, gridBagConstraints);
		
		gridBagConstraints.gridwidth = 1;
		
		botaoStart.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridy ++;
		jPanel.add(botaoStart, gridBagConstraints);
		
		botaoStop.setPreferredSize(new Dimension(92, 25));
		gridBagConstraints.gridx ++;
		botaoStop.setEnabled(false);
		jPanel.add(botaoStop, gridBagConstraints);
		
		
		botaoStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {//Executa o clique no botão
				threadTime1 = new Thread(runnableTheread);
				threadTime1.start();
				threadTime2 = new Thread(runnableThread2);
				threadTime2.start();
				botaoStop.setEnabled(true);
				botaoStart.setEnabled(false);
			}
		});
		
		botaoStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				threadTime1.stop();
				threadTime2.stop();
				botaoStart.setEnabled(true);
				botaoStop.setEnabled(false);
				
			}
		});
		
		add(jPanel, BorderLayout.WEST);
		setVisible(true);//Torna a tela visivel para o usuário
	}

}
