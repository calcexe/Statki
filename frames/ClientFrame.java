package frames;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import extras.Player;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ClientFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 246, 173);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("IP");
		textField.setToolTipText("IP");
		textField.setBounds(10, 11, 210, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setText("Port: 1892");
		textField_1.setEditable(false);
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setToolTipText("PORT");
		textField_1.setBounds(10, 42, 210, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Po\u0142\u0105cz");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					InetAddress address = InetAddress.getByName(textField.getText());
					int port = Integer.parseInt(textField_1.getText());
					Socket socket = new Socket(address, port);
					new StartGame(new Player(socket, false)).setVisible(true);
					dispose();
				}
				catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(ClientFrame.this, "Wpisano niepoprawne dane.", "B³¹d",
							JOptionPane.ERROR_MESSAGE);
				}
				catch (UnknownHostException e1) {
					JOptionPane.showMessageDialog(ClientFrame.this, "Nie uda³o siê po³¹czyæ z serwerem.", "B³¹d",
							JOptionPane.INFORMATION_MESSAGE);
				}
				catch (IOException e1) {
					JOptionPane.showMessageDialog(ClientFrame.this, "Nie uda³o siê po³¹czyæ z serwerem.", "B³¹d",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnNewButton.setBounds(10, 73, 210, 23);
		contentPane.add(btnNewButton);

		JButton btnWr = new JButton("Wr\u00F3\u0107");
		btnWr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MenuFrame().setVisible(true);
				dispose();
			}
		});
		btnWr.setBounds(10, 107, 210, 23);
		contentPane.add(btnWr);
	}

}
