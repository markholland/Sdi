package client;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;

import rmi.EchoInt;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class EchoApplet extends JApplet {
	private JTextField textoHost;
	private JTextField textoSend;
	private JTextField textoReceived;
	private JTextField textoPort;
	private JLabel jLabelStatusResult;
	
	
	public void init() {
	     this.setSize(440, 280);
	  }
	
	public EchoApplet() {
		getContentPane().setLayout(null);
		
		JButton botonEnviar = new JButton("SEND");
		botonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EchoObjectStub es = new EchoObjectStub();
				String host = new String(textoHost.getText());
				String port = new String(textoPort.getText());
				String input = new String(textoSend.getText());
				String output = new String("Error!");
				try { 
					es.setHostAndPort(host, Integer.parseInt(port));
					output = es.echo(input);
					textoReceived.setText(output);
				} catch (RemoteException x) {
					jLabelStatusResult.setText("No se pudo comunicar con el server");
				}
			}
		});
		botonEnviar.setBounds(23, 104, 78, 23);
		getContentPane().add(botonEnviar);
		
		JLabel jLabelHost = new JLabel("Host");
		jLabelHost.setBounds(23, 35, 46, 14);
		getContentPane().add(jLabelHost);
		
		textoHost = new JTextField();
		textoHost.setText("localhost");
		textoHost.setBounds(83, 29, 169, 31);
		getContentPane().add(textoHost);
		
		JLabel jLabelSend = new JLabel("Text to send");
		jLabelSend.setBounds(115, 81, 80, 14);
		getContentPane().add(jLabelSend);
		
		textoSend = new JTextField();
		textoSend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				int key = arg0.getKeyCode();
				if(key == KeyEvent.VK_ENTER) {
					System.out.println("Enter pressed: "+arg0.getKeyChar());
					if(System.getSecurityManager()== null) 
						//Para usar esto hay que definir un fichero de política de seguridad.
							System.setSecurityManager(new RMISecurityManager());
							String output;
					        try {
					          EchoInt obj = (EchoInt) Naming.lookup("echo");  // Obtain reference from name server
					          output = obj.echo(textoSend.getText());	 
					          textoReceived.setText(output);
				          
					        }catch(Exception e) {
					              System.out.println("Error en el cliente de echo RMI : " + e.getMessage());
					              jLabelStatusResult.setText("No se pudo comunicar con el server");
					        }
				}
			}
		});
		textoSend.setBounds(114, 99, 305, 32);
		getContentPane().add(textoSend);
		
		JLabel jLabelReceived = new JLabel("Text received");
		jLabelReceived.setBounds(23, 154, 122, 14);
		getContentPane().add(jLabelReceived);
		
		textoReceived = new JTextField();
		textoReceived.setBounds(23, 179, 396, 32);
		getContentPane().add(textoReceived);
		
		JLabel jLabelStatus = new JLabel("Status:");
		jLabelStatus.setBounds(23, 243, 46, 14);
		getContentPane().add(jLabelStatus);
		
		JLabel jLabelPort = new JLabel("Port");
		jLabelPort.setBounds(263, 35, 46, 14);
		getContentPane().add(jLabelPort);
		
		textoPort = new JTextField();
		textoPort.setText("4000");
		textoPort.setBounds(297, 28, 122, 32);
		getContentPane().add(textoPort);
		
		jLabelStatusResult = new JLabel("");
		jLabelStatusResult.setBounds(62, 243, 357, 14);
		getContentPane().add(jLabelStatusResult);
	}
}
