public class GameBoard {

    TileReciever t= (range)-> {
        return 1;
    };

    protected Tile[][] board;
    public GameBoard(Tile[][] tiles){
             board=tiles;
    }

    public String PrintBoard(){
        String  toReturn= "";
        for(int i=0;i<board.length;i++){

            for(int j=0;j<board[i].length;j++){
                toReturn+=(board[i][j].toString());
            }
            toReturn+=("\n");
        }
        return toReturn;
    }
    public boolean Swap(Unit unit, Position pos2){

        Position UnitPos=unit.position;
        if(isValidPosition(UnitPos)&&isValidPosition(pos2)) {
            int firstX = UnitPos.getX();
            int firstY = UnitPos.getY();
            int secondX = pos2.getX();
            int secondY = pos2.getY();

            Tile tile2 = board[secondX][secondY];

            boolean tilesSwapped = unit.swapTwoTiles(tile2);
            if (tilesSwapped) {
                board[firstX][firstY] = tile2;
                board[secondX][secondY] = unit;
            }

            return tilesSwapped;
        }
        return false;
    }

    public void Kill(Position pos){
        if(isValidPosition(pos)) {
            Empty empty = new Empty();
            empty.SetPosition(pos.getX(),pos.getY());
            board[pos.getX()][pos.getY()]=empty;
        }
    }

    private boolean isValidPosition(Position pos){
        if(pos.getX()>=board.length||pos.getX()<0||pos.getY()>=board[pos.getX()].length || pos.getY()<0)
            return false;
        return true;
    }
}
