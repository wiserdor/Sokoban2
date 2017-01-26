package model;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;

import model.data.CellTypes.CellType;
import model.data.IO.CellValueCreator;
import model.data.IO.LevelLoadCreators;
import model.data.IO.LevelSaveCreators;
import model.data.level.Level;
import model.policy.MySokobanPolicy;

public class MyModel extends Observable implements Model {
	private MySokobanPolicy policy;
	private Level l;

	@Override
	public void move(String Arrow) {
		if (policy == null) {
			System.out.println("please load a level");
			return;
		}

		switch (Arrow) {
		case "Right":
		case "right":
			policy.getP().setLocation(policy.getP().getX() + 1, policy.getP().getY());
			policy.getpNext().setLocation(policy.getP().getX() + 1, policy.getP().getY());
			break;
		case "Left":
		case "left":
			policy.getP().setLocation(policy.getP().getX() - 1, policy.getP().getY());
			policy.getpNext().setLocation(policy.getP().getX() - 1, policy.getP().getY());
			break;
		case "Up":
		case "up":
			policy.getP().setLocation(policy.getP().getX(), policy.getP().getY() - 1);
			policy.getpNext().setLocation(policy.getP().getX(), policy.getP().getY() - 1);
			break;
		case "down":
		case "Down":
			policy.getP().setLocation(policy.getP().getX(), policy.getP().getY() + 1);
			policy.getpNext().setLocation(policy.getP().getX(), policy.getP().getY() + 1);
			break;

		default:
			System.out.println("not a valid direction");
			return;
		}
		policy.move();
		this.setChanged();
	}

	public MyModel(Level l,MySokobanPolicy policy) {
		super();
		this.l=l;
		this.policy = policy;
	}

	public MyModel() {
		super();
		this.policy = null;
		this.l = null;
	}

	@Override
	public void load(String path) throws ClassNotFoundException, IOException {
		InputStream in = new FileInputStream(path);
		LevelLoadCreators lc = new LevelLoadCreators();
		this.l = lc.CreateLoader(path).loadLevel(in);
		policy = new MySokobanPolicy(l);

	}

	@Override
	public Character[][] getDisplay() throws IOException {
		if (policy == null) {
			return null;
		}
		Character[][] charBoard = new Character[l.getMaxY()][l.getMaxX()];
		CellType[][] arr = l.getLevelBoard();
		CellValueCreator cvc = new CellValueCreator();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (l.isCharacterPosition(j, i))
					charBoard[i][j] = 'A';
				else
					charBoard[i][j] = cvc.CreateValue(arr[i][j].getClass().toString());
			}
		}
		return charBoard;
	}

	@Override
	public void save(String path) throws IOException {
		if (policy == null) {
			System.out.println("please load a level");
			return;
		}
		OutputStream out = new FileOutputStream(path);
		LevelSaveCreators sc = new LevelSaveCreators();
		sc.Createsaver(path).saveLevel(this.l, out);
	}

}
