package server.Games;



public enum Games {

    TicTacToe();
    //NavalBattle;




    public static Games getGamesByID(Integer gameid){
        //Do some logic here
        return TicTacToe;
    }
}
