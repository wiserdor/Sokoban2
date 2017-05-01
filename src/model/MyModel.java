package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
	public String move(String Arrow) {
		if (policy == null) {
			System.out.println("please load a level");
			return "please load a level";
		}
		policy.move(Arrow);

		return policy.getMsg();

	}

	public MyModel(Level l, MySokobanPolicy policy) {
		super();
		this.l = l;
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
		for (int i = 0; i < l.getMaxY(); i++) {
			for (int j = 0; j < l.getMaxX(); j++) {
				if (policy.getL().isCharacterPosition(j, i))
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

	@Override
	public boolean isFinished() {
		return l.isFinished();
	}

	@Override
	public int countSteps() {
		return l.getSteps();
	}

}
