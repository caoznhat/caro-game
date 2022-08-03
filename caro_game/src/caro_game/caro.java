package caro_game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class caro extends JFrame implements ActionListener{
	
	Color background_cl = Color.white;
	Color x_cl = Color.red;
	Color y_cl = Color.blue;
	int column = 20, row = 30, count = 0;
	boolean tick[][] = new boolean[column + 2][row + 2];
	Container cn;
	JPanel pn, pn2;
	JLabel lb;
	JButton newGame_bt, exit_bt;
	private JButton b[][] = new JButton[column + 2][row + 2];
	
	public caro(String s) {
		super(s);
		cn =this.getContentPane();
		pn = new JPanel();
		pn.setLayout(new GridLayout(column,row));
		for (int i = 0; i <= column + 1; i++)
			for (int j = 0; j <= row + 1; j++) {
				b[i][j] = new JButton(" ");
				b[i][j].setActionCommand(i + " " + j);
				b[i][j].setBackground(background_cl);
				b[i][j].addActionListener(this);
				tick[i][j] = true;
			}
		for (int i = 1; i <= column; i++)
			for (int j = 1; j <= row; j++)
				pn.add(b[i][j]);
		lb = new JLabel("X First");
		newGame_bt = new JButton("New Game");
		exit_bt = new JButton("Exit");
		newGame_bt.addActionListener(this);
		exit_bt.addActionListener(this);
		exit_bt.setForeground(x_cl);
		cn.add(pn);
		pn2 = new JPanel();
		pn2.setLayout(new FlowLayout());
		pn2.add(lb);
		pn2.add(newGame_bt);
		pn2.add(exit_bt);
		cn.add(pn2,"North");
		this.setVisible(true);
		this.setSize(1400, 1000);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public boolean checkWin(int i, int j) {
		int d = 0, k = i, h;
		// Check horizontal
		while (b[k][j].getText() == b[i][j].getText()) {
			d++;
			k++;
		}
		k = i - 1;
		while (b[k][j].getText() == b[i][j].getText()) {
			d++;
			k--;
		}
		if (d > 4) return true;
		d = 0; h = j;
		// Check vertical
		while(b[i][h].getText() == b[i][j].getText()) {
			d++;
			h++;
		}
		h = j - 1;
		while(b[i][h].getText() == b[i][j].getText()) {
			d++;
			h--;
		}
		if (d > 4) return true;
		// Check diagonal
		h = i; k = j; d = 0;
		while (b[i][j].getText() == b[h][k].getText()) {
			d++;
			h++;
			k++;
		}
		h = i - 1; k = j - 1;
		while (b[i][j].getText() == b[h][k].getText()) {
			d++;
			h--;
			k--;
		}
		if (d > 4) return true;
		// Another zone (Diagonal)
		h = i; k = j; d = 0;
		while (b[i][j].getText() == b[h][k].getText()) {
			d++;
			h++;
			k--;
		}
		h = i - 1; k = j + 1;
		while (b[i][j].getText() == b[h][k].getText()) {
			d++;
			h--;
			k++;
		}
		if (d > 4) return true;
		return false;
	}
	public void addPoint(int i, int j) {
		if (count % 2 == 0) {
			b[i][j].setText("X");
			b[i][j].setForeground(x_cl);
			lb.setText("O's turn!");
		}
		else {
			b[i][j].setText("O");
			b[i][j].setForeground(y_cl);
			lb.setText("X's turn!");
		}
		tick[i][j] = false;
		count  = 1 - count;
		b[i][j].setBackground(Color.GRAY);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "New Game") {
			new caro("Caro Game");
			this.dispose();
		} 
		else
		if (e.getActionCommand() == "Exit") {
			System.exit(0);;
		}
		else {
			String s = e.getActionCommand();
			int k = s.indexOf(32);
			int i = Integer.parseInt(s.substring(0, k));
			int j = Integer.parseInt(s.substring(k + 1, s.length()));
			if (tick[i][j]) {
				addPoint(i, j);
			}
			if (checkWin(i, j)) {
				lb.setBackground(Color.MAGENTA);
				lb.setText(b[i][j].getText() + " WIN");
				for (i = 1; i <= column; i++)
					for (j = 1; j <= row; j++) 
						b[i][j].setEnabled(false);
				newGame_bt.setBackground(Color.YELLOW);
			}
		}
	}
	public static void main(String[] args) {
		new caro("Caro Demo");
	}
}
