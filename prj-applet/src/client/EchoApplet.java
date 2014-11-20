package client;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.RemoteException;

public class EchoApplet extends JApplet {
	private JTextField textoHost;
	private JTextField textoSend;
	private JTextField textoReceived;
	private JTextField textoPort;
	private JLabel jLabelStatusResult;
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
