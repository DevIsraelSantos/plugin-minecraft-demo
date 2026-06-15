package br.com.israel.models;

public class PlayerStats {
    private Integer games;
    private Integer wins;
    private Integer blocksBroken;
    private Boolean scoreboardEnabled;

    public PlayerStats(
            Integer games,
            Integer wins,
            Integer blocksBroken,
            Boolean scoreboardEnabled) {

        this.games = games;
        this.wins = wins;
        this.blocksBroken = blocksBroken;
        this.scoreboardEnabled = scoreboardEnabled;
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

    public void addBlockBroken() {
        this.blocksBroken++;
    }

    public Integer getBlocksBroken() {
        return blocksBroken;
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

    public void setScoreboardEnabled(Boolean scoreboardEnabled) {
        this.scoreboardEnabled = scoreboardEnabled;
    }

    public Boolean isScoreboardEnabled() {
        return scoreboardEnabled;
    }

}
