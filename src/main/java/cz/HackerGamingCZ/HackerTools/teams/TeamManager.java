package cz.HackerGamingCZ.HackerTools.teams;


import cz.HackerGamingCZ.HackerTools.HackerTools;
import cz.HackerGamingCZ.HackerTools.players.HTPlayer;

import java.util.ArrayList;

public class TeamManager {

    private boolean teamDamage = false;
    private boolean teamChat = true;

    private ArrayList<Team> teams = new ArrayList<>();

    public void disableTeamDamage() {
        teamDamage = false;
    }

    public void enableTeamDamage() {
        teamDamage = true;
    }


    public void disableTeamChat() {
        teamChat = false;
    }

    public void enableTeamChat() {
        teamChat = true;
    }

    public boolean isTeamDamage() {
        return teamDamage;
    }

    public boolean isTeamChat() {
        return teamChat;
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public ArrayList<Team> getTeamsInGame() {
        ArrayList<Team> teams = new ArrayList<>();
        for (Team team : this.teams) {
            if (team.isIngameTeam()) {
                teams.add(team);
            }
        }
        return teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addAllPlayersIntoTeam() {
        for (HTPlayer htPlayer : HackerTools.getPlugin().getPlayerManager().getPlayers().values()) {
            if (htPlayer.getTeam() != null || htPlayer.getTeam() == HackerTools.getPlugin().getSpectatorTeam()) {
                continue;
            }
            if (!htPlayer.getPlayer().isOnline()) {
                continue;
            }
            for (Team team : teams) {
                if(team == HackerTools.getPlugin().getSpectatorTeam()){
                    continue;
                }
                if (team.canJoin(htPlayer.getPlayer(), false)) {
                    team.join(htPlayer);
                    break;
                }
            }
        }
    }
}
