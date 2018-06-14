package com.matritellabs.utama.exam4.csisanyi;

import java.util.Comparator;

public class TeamComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
        if (o1 == null || o2 == null) {
            throw new NullPointerException("One or both of the parameters are null");
        }

        AgileTeam agileTeam1 = (AgileTeam) o1;
        AgileTeam agileTeam2 = (AgileTeam) o2;

        Integer numberOfMembersWrapperTeam1 = (Integer) agileTeam1.getNumberOfMembers();
        Integer numberOfMembersWrapperTeam2 = (Integer) agileTeam2.getNumberOfMembers();

        if(numberOfMembersWrapperTeam1.compareTo(numberOfMembersWrapperTeam2) == 0){
            if(agileTeam1.getCreationDate().compareTo(agileTeam2.getCreationDate()) == 0) {
                return 0;
            }else {
                return (agileTeam1.getCreationDate().compareTo(agileTeam2.getCreationDate()));
            }
        }else {
            return (numberOfMembersWrapperTeam1.compareTo(numberOfMembersWrapperTeam2));
        }
    }
}
