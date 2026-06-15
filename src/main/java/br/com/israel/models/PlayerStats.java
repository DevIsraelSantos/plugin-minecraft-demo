package br.com.israel.models;

public class PlayerStats {
    private Integer games;
    private Integer wins;

    public PlayerStats(
            Integer games,
            Integer wins) {
        this.games = games;
        this.wins = wins;
    }

    private void addGame() {
        this.games += 1;
    }

    public void addVictory() {
        this.wins += 1;
        this.addGame();
    }

    public void addDefeat() {
        this.addGame();
    }

    public Integer getGames() {
        return games;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getLosses() {
        return this.games - this.wins;
    }

    public String getPercentage() {
        if (this.games == 0) {
            return "0%";
        }

        double value = (double) this.wins * 100 / this.games;

        return String.format("%.2f%%", value);
    }

}
