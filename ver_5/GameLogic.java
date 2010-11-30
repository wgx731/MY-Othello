package ver_5;

import java.util.ArrayList;

public class GameLogic {

	final static int ROWS = 8;
	final static int COLS = 8;
	static Chess[][] board = new Chess[ROWS][COLS];

	static void newGame() {
		for (int i = 0; i < ROWS; i++) {
			for (int n = 0; n < COLS; n++) {
				if (i == 3 && n == 3) {
					board[i][n] = Chess.WHITE;
				} else if (i == 3 && n == 4) {
					board[i][n] = Chess.BLACK;
				} else if (i == 4 && n == 3) {
					board[i][n] = Chess.BLACK;
				} else if (i == 4 && n == 4) {
					board[i][n] = Chess.WHITE;
				} else {
					board[i][n] = Chess.EMPTY;
				}
			}
		}
	}

	static void addWhite(int row, int col) throws ChessExistException,
			NotValidMoveException {
		if (board[row][col] != Chess.EMPTY) {
			throw new ChessExistException("Chess already exists!");
		} else if (!checkMove(row, col, Chess.WHITE)) {
			throw new NotValidMoveException("This is not a valid move!");
		} else {
			board[row][col] = Chess.WHITE;
			changeW(row, col);
		}
	}

	static void addBlack(int row, int col) throws ChessExistException,
			NotValidMoveException {
		if (board[row][col] != Chess.EMPTY) {
			throw new ChessExistException("Chess already exists!");
		} else if (!checkMove(row, col, Chess.BLACK)) {
			throw new NotValidMoveException("This is not a valid move!");
		} else {
			board[row][col] = Chess.BLACK;
			changeB(row, col);
		}
	}

	private static ArrayList<Direction> getDirection(int row, int col,
			Chess nextTurn) {
		ArrayList<Direction> insideList = new ArrayList<Direction>();
		if (row == 0 && col == 0) {
			if (board[row][col + 1] == nextTurn) {
				insideList.add(Direction.RIGHT);
			}
			if (board[row + 1][col] == nextTurn) {
				insideList.add(Direction.DOWN);
			}
			if (board[row + 1][col + 1] == nextTurn) {
				insideList.add(Direction.DOWNRIGHT);
			}
		} else if (row == 0 && col == 7) {
			if (board[row][col - 1] == nextTurn) {
				insideList.add(Direction.LEFT);
			}
			if (board[row + 1][col - 1] == nextTurn) {
				insideList.add(Direction.DOWNLEFT);
			}
			if (board[row + 1][col] == nextTurn) {
				insideList.add(Direction.DOWN);
			}
		} else if (row == 7 && col == 0) {

			if (board[row - 1][col] == nextTurn) {
				insideList.add(Direction.UP);
			}
			if (board[row - 1][col + 1] == nextTurn) {
				insideList.add(Direction.UPRIGHT);
			}
			if (board[row][col + 1] == nextTurn) {
				insideList.add(Direction.RIGHT);
			}
		} else if (row == 7 && col == 7) {
			if (board[row - 1][col - 1] == nextTurn) {
				insideList.add(Direction.UPLEFT);
			}
			if (board[row - 1][col] == nextTurn) {
				insideList.add(Direction.UP);
			}
			if (board[row][col - 1] == nextTurn) {
				insideList.add(Direction.LEFT);
			}
		} else if (row == 0) {
			if (board[row][col - 1] == nextTurn) {
				insideList.add(Direction.LEFT);
			}
			if (board[row][col + 1] == nextTurn) {
				insideList.add(Direction.RIGHT);
			}
			if (board[row + 1][col - 1] == nextTurn) {
				insideList.add(Direction.DOWNLEFT);
			}
			if (board[row + 1][col] == nextTurn) {
				insideList.add(Direction.DOWN);
			}
			if (board[row + 1][col + 1] == nextTurn) {
				insideList.add(Direction.DOWNRIGHT);
			}
		} else if (col == 0) {
			if (board[row - 1][col] == nextTurn) {
				insideList.add(Direction.UP);
			}
			if (board[row - 1][col + 1] == nextTurn) {
				insideList.add(Direction.UPRIGHT);
			}
			if (board[row][col + 1] == nextTurn) {
				insideList.add(Direction.RIGHT);
			}
			if (board[row + 1][col] == nextTurn) {
				insideList.add(Direction.DOWN);
			}
			if (board[row + 1][col + 1] == nextTurn) {
				insideList.add(Direction.DOWNRIGHT);
			}
		} else if (row == 7) {
			if (board[row - 1][col - 1] == nextTurn) {
				insideList.add(Direction.UPLEFT);
			}
			if (board[row - 1][col] == nextTurn) {
				insideList.add(Direction.UP);
			}
			if (board[row - 1][col + 1] == nextTurn) {
				insideList.add(Direction.UPRIGHT);
			}
			if (board[row][col - 1] == nextTurn) {
				insideList.add(Direction.LEFT);
			}
			if (board[row][col + 1] == nextTurn) {
				insideList.add(Direction.RIGHT);
			}
		} else if (col == 7) {
			if (board[row - 1][col - 1] == nextTurn) {
				insideList.add(Direction.UPLEFT);
			}
			if (board[row - 1][col] == nextTurn) {
				insideList.add(Direction.UP);
			}
			if (board[row][col - 1] == nextTurn) {
				insideList.add(Direction.LEFT);
			}
			if (board[row + 1][col - 1] == nextTurn) {
				insideList.add(Direction.DOWNLEFT);
			}
			if (board[row + 1][col] == nextTurn) {
				insideList.add(Direction.DOWN);
			}
		} else {
			if (board[row - 1][col - 1] == nextTurn) {
				insideList.add(Direction.UPLEFT);
			}
			if (board[row - 1][col] == nextTurn) {
				insideList.add(Direction.UP);
			}
			if (board[row - 1][col + 1] == nextTurn) {
				insideList.add(Direction.UPRIGHT);
			}
			if (board[row][col - 1] == nextTurn) {
				insideList.add(Direction.LEFT);
			}
			if (board[row][col + 1] == nextTurn) {
				insideList.add(Direction.RIGHT);
			}
			if (board[row + 1][col - 1] == nextTurn) {
				insideList.add(Direction.DOWNLEFT);
			}
			if (board[row + 1][col] == nextTurn) {
				insideList.add(Direction.DOWN);
			}
			if (board[row + 1][col + 1] == nextTurn) {
				insideList.add(Direction.DOWNRIGHT);
			}
		}
		return insideList;
	}

	private static void changeB(int row, int col) {
		ArrayList<Direction> insideList = getDirection(row, col, Chess.WHITE);
		int tempr = row, tempc = col;
		for (Direction d : insideList) {
			int endr = -1, endc = -1;
			row = tempr;
			col = tempc;
			switch (d) {
			case UPLEFT:
				while (row > 0 && col > 0) {
					row -= 1;
					col -= 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row > endr && col > endc) {
						board[row][col] = Chess.BLACK;
						row -= 1;
						col -= 1;
					}
				}

				break;
			case UP:
				while (row > 0 ) {
					row -= 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row > endr) {
						board[row][col] = Chess.BLACK;
						row -= 1;
					}
				}
				break;
			case UPRIGHT:
				while (row > 0 && col < 7) {
					row -= 1;
					col += 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row > endr && col < endc) {
						board[row][col] = Chess.BLACK;
						row -= 1;
						col += 1;
					}
				}
				break;
			case LEFT:
				while (col > 0) {
					col -= 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (col > endc) {
						board[row][col] = Chess.BLACK;
						col -= 1;
					}
				}
				break;
			case RIGHT:
				while (col < 7) {
					col += 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (col < endc) {
						board[row][col] = Chess.BLACK;
						col += 1;
					}
				}
				break;
			case DOWNLEFT:
				while (row < 7 && col > 0) {
					row += 1;
					col -= 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row < endr && col > endc) {
						board[row][col] = Chess.BLACK;
						row += 1;
						col -= 1;
					}
				}
				break;
			case DOWN:
				while (row < 7) {
					row += 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row < endr) {
						board[row][col] = Chess.BLACK;
						row += 1;
					}
				}
				break;
			case DOWNRIGHT:
				while (row < 7 && col < 7) {
					row += 1;
					col += 1;
					if (board[row][col] == Chess.BLACK) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row < endr && col < endc) {
						board[row][col] = Chess.BLACK;
						row += 1;
						col += 1;
					}
				}
				break;
			}
		}

	}

	private static void changeW(int row, int col) {
		ArrayList<Direction> insideList = getDirection(row, col, Chess.BLACK);
		int tempr = row, tempc = col;
		for (Direction d : insideList) {
			int endr = -1, endc = -1;
			row = tempr;
			col = tempc;
			switch (d) {
			case UPLEFT:
				while (row > 0 && col > 0) {
					row -= 1;
					col -= 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row > endr && col > endc) {
						board[row][col] = Chess.WHITE;
						row -= 1;
						col -= 1;
					}
				}

				break;
			case UP:
				while (row > 0) {
					row -= 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row > endr) {
						board[row][col] = Chess.WHITE;
						row -= 1;
					}
				}
				break;
			case UPRIGHT:
				while (row > 0 && col < 7) {
					row -= 1;
					col += 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row > endr && col < endc) {
						board[row][col] = Chess.WHITE;
						row -= 1;
						col += 1;
					}
				}
				break;
			case LEFT:
				while (col > 0) {
					col -= 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (col > endc) {
						board[row][col] = Chess.WHITE;
						col -= 1;
					}
				}
				break;
			case RIGHT:
				while (col < 7) {
					col += 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (col < endc) {
						board[row][col] = Chess.WHITE;
						col += 1;
					}
				}
				break;
			case DOWNLEFT:
				while (row < 7 && col > 0) {
					row += 1;
					col -= 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row < endr && col > endc) {
						board[row][col] = Chess.WHITE;
						row += 1;
						col -= 1;
					}
				}
				break;
			case DOWN:
				while (row < 7) {
					row += 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row < endr) {
						board[row][col] = Chess.WHITE;
						row += 1;
					}
				}
				break;
			case DOWNRIGHT:
				while (row < 7 && col < 7) {
					row += 1;
					col += 1;
					if (board[row][col] == Chess.WHITE) {
						endr = row;
						endc = col;
						break;
					} else if (board[row][col] == Chess.EMPTY) {
						break;
					}
				}
				row = tempr;
				col = tempc;
				if (endr != -1 && endc != -1) {
					while (row < endr && col < endc) {
						board[row][col] = Chess.WHITE;
						row += 1;
						col += 1;
					}
				}
				break;
			}
		}

	}
	static ArrayList<Integer> getBPossibleMove() {
		ArrayList<Integer> hpMove = new ArrayList<Integer>();
		for (int row = 0; row < GameLogic.ROWS; row++) {
			for (int col = 0; col < GameLogic.COLS; col++) {
				if (GameLogic.board[row][col] == Chess.BLACK) {
					hpMove.addAll(GameLogic.showPossibleMove(row, col,
							Chess.BLACK));
				}
			}
		}
		return hpMove;
	}

	static ArrayList<Integer> getWPossibleMove() {
		ArrayList<Integer> hpMove = new ArrayList<Integer>();
		for (int row = 0; row < GameLogic.ROWS; row++) {
			for (int col = 0; col < GameLogic.COLS; col++) {
				if (GameLogic.board[row][col] == Chess.WHITE) {
					hpMove.addAll(GameLogic.showPossibleMove(row, col,
							Chess.WHITE));
				}
			}
		}
		return hpMove;
	}

	private static boolean checkMove(int row, int col, Chess currentTurn) {
		ArrayList<Integer> bpMove = new ArrayList<Integer>();
		ArrayList<Integer> wpMove = new ArrayList<Integer>();
		boolean move = false;
		bpMove = getBPossibleMove();
		wpMove = getWPossibleMove();
		row += 1;
		col += 1;
		switch (currentTurn) {
		case BLACK:
			for (int index = 0; index <= bpMove.size() - 2; index += 2) {
				if (row == bpMove.get(index) && col == bpMove.get(index + 1)) {
					move = true;
					break;
				}
			}
			break;
		case WHITE:
			for (int index = 0; index <= wpMove.size() - 2; index += 2) {
				if (row == wpMove.get(index) && col == wpMove.get(index + 1)) {
					move = true;
					break;
				}
			}
			break;
		}
		return move;
	}

	private static ArrayList<Integer> showPossibleMove(int row, int col,
			Chess currentTurn) {
		ArrayList<Integer> possibleMove = new ArrayList<Integer>();
		ArrayList<Direction> insideList = new ArrayList<Direction>();
		Chess nextTurn = null;
		switch (currentTurn) {
		case BLACK:
			nextTurn = Chess.WHITE;
			break;
		case WHITE:
			nextTurn = Chess.BLACK;
			break;
		}
		insideList = getDirection(row, col, nextTurn);
		for (Direction d : insideList) {
			int tempr = row, tempc = col;
			switch (d) {
			case UPLEFT:
				while (tempr > 0 && tempc > 0) {
					tempr -= 1;
					tempc -= 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case UP:
				while (tempr > 0) {
					tempr -= 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case UPRIGHT:
				while (tempr > 0 && tempc < 7) {
					tempr -= 1;
					tempc += 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case LEFT:
				while (tempc > 0) {
					tempc -= 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case RIGHT:
				while (tempc < 7) {
					tempc += 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case DOWNLEFT:
				while (tempr < 7 && tempc > 0) {
					tempr += 1;
					tempc -= 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case DOWN:
				while (tempr < 7) {
					tempr += 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			case DOWNRIGHT:
				while (tempr < 7 && tempc < 7) {
					tempr += 1;
					tempc += 1;
					if (board[tempr][tempc] == currentTurn) {
						break;
					}
					if (board[tempr][tempc] == Chess.EMPTY) {
						possibleMove.add(tempr + 1);
						possibleMove.add(tempc + 1);
						break;
					}
				}
				break;
			}
		}

		return possibleMove;
	}

}

@SuppressWarnings("serial")
class ChessExistException extends RuntimeException {

	public ChessExistException(final String cause) {
		super(cause);
	}
}

@SuppressWarnings("serial")
class NotValidMoveException extends RuntimeException {

	public NotValidMoveException(final String cause) {
		super(cause);
	}
}
