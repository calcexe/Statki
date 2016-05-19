package frames;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import extras.Player;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import java.awt.Component;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ServerFrame extends JFrame {

	private JPanel contentPane;
	private JLabel label;

	public ServerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 267, 138);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		label = new JLabel("localhost:1892");
		
		try {
			label.setText(InetAddress.getLocalHost().getHostAddress() + ":1892");
		}
		catch (UnknownHostException e1) {
			JOptionPane.showMessageDialog(this, "Nie mo¿na pobraæ lokalnego adresu IP.");
		}
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setBounds(9, 11, 236, 14);
		contentPane.add(label);

		JButton btnRozpocznijGr = new JButton("Rozpocznij gr\u0119");
		btnRozpocznijGr.setEnabled(false);
		btnRozpocznijGr.setBounds(5, 36, 240, 23);
		contentPane.add(btnRozpocznijGr);

		JButton btnWr = new JButton("Wr\u00F3\u0107");
		btnWr.setBounds(5, 70, 240, 23);
		btnWr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuFrame().setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnWr);

		Thread listen = new Thread(new Runnable() {

			@Override
			public void run() {
				try(ServerSocket serverSocket = new ServerSocket(1892)) {
					Socket client = serverSocket.accept();

					new StartGame(new Player(client, true)).setVisible(true);
					//new GameFrame(new Player(client)).setVisible(true);
					dispose();
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		listen.start();
	}

}
