package com.matritellabs.utama.exam4.csisanyi;

import java.util.Date;
import java.util.List;
import java.util.Objects;

public class AgileTeam {

    private String teamName;
    private Date creationDate;
    private int numberOfMembers;
    private List<String> listOfMembers;

    public AgileTeam(String teamName, Date creationDate, int numberOfMembers, List<String> listOfMembers) {
        this.teamName = teamName;
        this.creationDate = creationDate;
        this.numberOfMembers = numberOfMembers;
        this.listOfMembers = listOfMembers;
    }

    public String getTeamName() {
        return teamName;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public List<String> getListOfMembers() {
        return listOfMembers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgileTeam agileTeam = (AgileTeam) o;

        if(!agileTeam.getTeamName().equals(this.getTeamName())){return false;}
        if(agileTeam.getNumberOfMembers() != (this.getNumberOfMembers())){return false;}
        return (agileTeam.getListOfMembers().equals(getListOfMembers()));

    }

    @Override
    public int hashCode() {
        int hash = getTeamName().hashCode();

        hash = 31 * hash + getCreationDate().hashCode();
        Integer numberOfMembersWrapper = (Integer) getNumberOfMembers();
        hash = 31 * hash + numberOfMembersWrapper.hashCode();
        hash = 31 * hash + getListOfMembers().hashCode();
        return hash;

    }
}
