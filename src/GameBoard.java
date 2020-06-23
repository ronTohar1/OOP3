public class GameBoard {

    TileReciever t= (range)-> {
        return 1;
    };

    protected Tile[][] board;
    public GameBoard(int length,int Height){
             board= new Tile[length][Height];
    }

    public void Tick(){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length){
                board[i][j].Tick();
            }
        }
    }

    public boolean Swap(Position PlayerPos, Position pos2){

        int firstX=PlayerPos.getX();
        int firstY=PlayerPos.getY();
        int secondX=pos2.getX();
        int secondY=pos2.getY();

        Tile tile1=board[firstX][firstY];
        Tile tile2=board[secondX][secondY];

        boolean tilesSwapped= tile1.swapTwoTiles(tile2);
        if(tilesSwapped)
        {
            Tile temp=tile2;
            board[firstX][firstY]=tile1;
            board[secondX][secondY]=tile2;
        }

        return tilesSwapped;
    }
}
