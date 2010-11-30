package ver_5;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.util.Random;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GUI {

	// Frame
	private JFrame MainF;

	// All the Buttons
	private JButton Ngame = new JButton("Start New Game");
	private JButton PWithCom = new JButton("Start playing with com");
	private boolean withcom = false;
	private String comTurn = "None";
	private JButton Hint = new JButton("Show/Hide Hint");
	private boolean Hintshown = false;
	private JButton Pass = new JButton("Pass");

	// All the Labels
	private JLabel Bnum = new JLabel("Black: ");
	private JLabel Wnum = new JLabel("White: ");
	private JLabel Move = new JLabel("TURN");

	// Display area
	private JPanel display = new JPanel();
	private String welcome = "Let's play a game of Othello";
	private ArrayList<Integer> pMove = new ArrayList<Integer>();
	private MyListener mouselistener;

	JInternalFrame graphic = new JInternalFrame(welcome) {
		int width = 50, height = 50;

		@Override
		public void paint(Graphics g) {
			super.paint(g);
			g.setColor(Color.BLUE);
			g.setFont(new Font("Courier New", Font.PLAIN, 14));
			g.drawString(welcome, 50, 20);
			g.setColor(Color.WHITE);
			for (int col = 0; col < 8; col++) {
				g.drawString("C" + (col + 1), (col * 50) + 70, 450);
			}
			for (int row = 0; row < 8; row++) {
				g.drawString("R" + (row + 1), 20, (row * 50) + 55);
			}

			for (int r = 0; r < GameLogic.ROWS; r++) {
				for (int c = 0; c < GameLogic.COLS; c++) {
					if (GameLogic.board[r][c] == Chess.WHITE) {
						g.setColor(Color.WHITE);
						g.fillOval((c * 50) + 50, (r * 50) + 30, width, height);
					} else if (GameLogic.board[r][c] == Chess.BLACK) {
						g.setColor(Color.BLACK);
						g.fillOval((c * 50) + 50, (r * 50) + 30, width, height);
					}
					g.setColor(Color.BLACK);
					g.drawRect((c * 50) + 50, (r * 50) + 30, width, height);
				}
			}
			if (Hintshown == false) {
				pMove = null;
			} else {
				pMove = null;
				if (Move.getText().equals("BLACK MOVE")) {
					pMove = GameLogic.getBPossibleMove();
					for (int index = 0; index <= pMove.size() - 2; index += 2) {
						g.setColor(Color.RED);
						g.drawOval(((pMove.get(index + 1) - 1) * 50) + 50,
								((pMove.get(index) - 1) * 50) + 30, 50, 50);
					}
				} else if (Move.getText().equals("WHITE MOVE")) {
					pMove = GameLogic.getWPossibleMove();
					for (int index = 0; index <= pMove.size() - 2; index += 2) {
						g.setColor(Color.RED);
						g.drawOval(((pMove.get(index + 1) - 1) * 50) + 50,
								((pMove.get(index) - 1) * 50) + 30, 50, 50);
					}
				}
			}
		}

	};

	private class MyListener extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {
			int col = (e.getX() - 50) / 50;
			int row = (e.getY() - 30) / 50;
			// System.out.println("x"+col+"y"+row);
			if (withcom == true) {
				if (Move.getText() != comTurn){
					HumanMove(row, col);
				}
			} else {
				HumanMove(row, col);
			}
			update();
		}

	}

	private void HumanMove(int row, int col) {
		if (Move.getText().equals("BLACK MOVE")) {
			try {
				GameLogic.addBlack(row, col);
				Move.setText("WHITE MOVE");
			} catch (ChessExistException cee) {
				JOptionPane.showMessageDialog(null, cee.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (NotValidMoveException nvme) {
				JOptionPane.showMessageDialog(null, nvme.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IndexOutOfBoundsException iobe) {
				JOptionPane.showMessageDialog(null,
						"This is not a valid move!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else if (Move.getText().equals("WHITE MOVE")) {
			try {
				GameLogic.addWhite(row, col);
				Move.setText("BLACK MOVE");
			} catch (ChessExistException cee) {
				JOptionPane.showMessageDialog(null, cee.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (NotValidMoveException nvme) {
				JOptionPane.showMessageDialog(null, nvme.getMessage(), "Error",
						JOptionPane.ERROR_MESSAGE);
			} catch (IndexOutOfBoundsException iobe) {
				JOptionPane.showMessageDialog(null,
						"This is not a valid move!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void ComputerMove() {
		if (Move.getText().equals(comTurn)) {
			ArrayList<Integer> cMove = new ArrayList<Integer>();
			if (comTurn.equals("BLACK MOVE")) {
				cMove = GameLogic.getBPossibleMove();
			} else if (comTurn.equals("WHITE MOVE")) {
				cMove = GameLogic.getWPossibleMove();
			}
			if (cMove.size() > 0) {

				Random rd = new Random();
				int rdnum = rd.nextInt(cMove.size());
				int crow = 0;
				int ccol = 0;
				if (rdnum % 2 == 0) {
					crow = cMove.get(rdnum) - 1;
					ccol = cMove.get(rdnum + 1) - 1;
				} else {
					ccol = cMove.get(rdnum) - 1;
					crow = cMove.get(rdnum - 1) - 1;
				}
				if (comTurn.equals("BLACK MOVE")) {
					GameLogic.addBlack(crow, ccol);
					Move.setText("WHITE MOVE");
				} else if (comTurn.equals("WHITE MOVE")) {
					GameLogic.addWhite(crow, ccol);
					Move.setText("BLACK MOVE");
				}
			} else {
				if (comTurn.equals("BLACK MOVE")) {
					Move.setText("WHITE MOVE");
				} else if (comTurn.equals("WHITE MOVE")) {
					Move.setText("BLACK MOVE");
				}
			}
		}
	}

	void Init() {
		MainF = new JFrame("Othello");
		MainF.setLayout(new BorderLayout());
		// Add Display
		JPanel west = new JPanel();
		west.setLayout(new BorderLayout());
		west.setBorder(new TitledBorder("Display Area"));
		display.setLayout(new GridLayout(1, 1));
		graphic.setBackground(Color.GREEN);
		graphic.removeAll();
		graphic.setVisible(true);
		display.add(graphic);
		west.add(display, BorderLayout.CENTER);
		JPanel wdown = new JPanel();
		wdown.add(Bnum);
		wdown.add(Pass);
		wdown.add(Wnum);
		Hint.setEnabled(false);
		wdown.add(Hint);
		wdown.add(PWithCom);
		west.add(wdown, BorderLayout.SOUTH);
		// Add Menu Items
		JPanel east = new JPanel();
		east.setLayout(new GridLayout(2, 1));
		east.setBorder(new TitledBorder("Menu Items"));
		Ngame.setEnabled(false);
		east.add(Ngame);
		Move.setHorizontalAlignment(SwingConstants.CENTER);
		east.add(Move);
		// Set Button enabled
		Pass.setEnabled(false);
		// Add to Main Frame
		MainF.add(west, BorderLayout.CENTER);
		MainF.add(east, BorderLayout.WEST);
		MainF.setBounds(200, 100, 650, 580);
		MainF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainF.setVisible(true);
	}

	void AddEvent() {
		this.Ngame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				GameLogic.newGame();
				Move.setText("BLACK MOVE");
				Pass.setEnabled(true);
				mouselistener = new MyListener();
				if (graphic.getMouseListeners() != null){
					for (MouseListener mouselistener : graphic.getMouseListeners()){
						graphic.removeMouseListener(mouselistener);
					}
				}
				graphic.addMouseListener(mouselistener);
				update();
			}

		});
		this.Pass.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Move.getText().equals("BLACK MOVE")) {
					ComputerMove();
					Move.setText("WHITE MOVE");
				} else if (Move.getText().equals("WHITE MOVE")) {
					ComputerMove();
					Move.setText("BLACK MOVE");
				}
				graphic.repaint();
				update();
			}

		});

		this.Hint.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (Hintshown == false) {
					Hintshown = true;
				} else {
					Hintshown = false;
				}
				update();

			}

		});

		this.PWithCom.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				withcom = false;
				if (withcom == false) {
					withcom = true;
					boolean validinput = false;
					while (!validinput) {
						String input = GUIConsole
								.readString("Computer Turn: 'B' for Black, 'W' for White.");
						if (input.equals("B")) {
							comTurn = "BLACK MOVE";
							validinput = true;
						} else if (input.equals("W")) {
							comTurn = "WHITE MOVE";
							validinput = true;
						} else {
							GUIConsole.display("Sorry, you choice is wrong!");
							validinput = false;
						}
					}
				} 
				Hint.setEnabled(true);
				Ngame.setEnabled(true);
				PWithCom.setEnabled(false);
			}
		});
	}

	GUI() {
		Init();
		AddEvent();
	}

	void update() {
		ComputerMove();
		graphic.repaint();
		int Bcount = 0;
		int Wcount = 0;
		for (int r = 0; r < GameLogic.ROWS; r++) {
			for (int c = 0; c < GameLogic.COLS; c++) {
				if (GameLogic.board[r][c] == Chess.WHITE) {
					Wcount += 1;
				} else if (GameLogic.board[r][c] == Chess.BLACK) {
					Bcount += 1;
				}
			}
		}
		this.Bnum.setText("Black: " + Bcount);
		this.Wnum.setText("White: " + Wcount);
		if (Bcount + Wcount == 64 || Bcount == 0 || Wcount == 0) {
			checkwin(Bcount, Wcount);
			Move.setText("TURN");
			Pass.setEnabled(false);
			PWithCom.setEnabled(true);
			Ngame.setEnabled(false);
			graphic.removeMouseListener(mouselistener);
		}
	}

	public void checkwin(int Bcount, int Wcount) {
		if (Bcount > Wcount) {
			JOptionPane.showMessageDialog(null, "Black WIN!", "Winner:",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (Wcount > Bcount) {
			JOptionPane.showMessageDialog(null, "White WIN!", "Winner:",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Tie Game!", "Winner:",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
