package com.matritellabs.utama.exam4.csisanyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.matritellabs.utama.exam4.csisanyi.TeamFileFormat.ONE_FILE;
import static com.matritellabs.utama.exam4.csisanyi.TeamFileFormat.TWO_FILES;

public class TeamLoader {

    private static Logger log = LoggerFactory.getLogger(TeamLoader.class);

    public static List<AgileTeam> readTeamsFromFile(List<String> filePathList, TeamFileFormat fileFormat)
    throws IOException, ParseException, RuntimeException {

        List<AgileTeam> returnList = new ArrayList<>();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        if(fileFormat.equals(ONE_FILE)) {

            Path theFile = Paths.get(filePathList.get(0));
            List<String> lineList = Files.readAllLines(theFile);

            for(int i = 0; i < lineList.size(); i++) {
                String[] splitted = lineList.get(i).split(";");
                int sizeOfMembersList = Integer.parseInt(splitted[2]);
                if(splitted.length > 3 + sizeOfMembersList) {
                    throw new RuntimeException();
                }
                List<String> membersList = new ArrayList<>();
                for (int j = 3; j < splitted.length; j++) {
                    membersList.add(splitted[j]);
                }

                returnList.add(new AgileTeam(splitted[0],SDF.parse(splitted[1]), sizeOfMembersList,membersList));
            }

        }

        if(fileFormat.equals(TWO_FILES)) {

            Path teamData = Paths.get(filePathList.get(0));
            List<String> lineList = Files.readAllLines(teamData);
            Path nameAndlist = Paths.get(filePathList.get(1));
            List<String> lineList2 = Files.readAllLines(nameAndlist);

            if (lineList.size() != lineList2.size()) {
                throw new RuntimeException();
            }

            for(int i = 0; i < lineList.size(); i++) {
                String[] splitted = lineList.get(i).split(";");
                String[] splitted2 = lineList2.get(i).split(";");

                int sizeOfMembersList = Integer.parseInt(splitted[2]);
                List<String> membersList = new ArrayList<>();

                if(!splitted[0].equals(splitted2[0])) {
                    throw new RuntimeException();
                }

                for (int j = 1; j < splitted2.length; j++) {
                    membersList.add(splitted2[j]);
                }

                returnList.add(new AgileTeam(splitted[0],SDF.parse(splitted[1]), sizeOfMembersList,membersList));
                }
        }
        return returnList;

    }

    public static void writeTeamToFile(List<String> filePathlist, TeamFileFormat fileFormat,
                                       String teamName, Date creationDate, int numberOfMembers, List<String> listOfMembers )
    throws IOException {


        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        if (fileFormat.equals(ONE_FILE)) {
            Path fileToWrite = Paths.get(filePathlist.get(0));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite.toFile()));

            String formattedDate = SDF.format(creationDate);
            String membersString = "";
            for (int i = 0; i < listOfMembers.size(); i++) {
                membersString = membersString + listOfMembers.get(i) + ";";
            }

            writer.write(teamName + ";" + formattedDate + ";" + numberOfMembers + ";" + membersString);
            writer.close();
        }

        if(fileFormat.equals(TWO_FILES)) {
            Path fileToWrite = Paths.get(filePathlist.get(0));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite.toFile()));
            Path fileToWrite2 = Paths.get(filePathlist.get(1));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(fileToWrite2.toFile()));

            String formattedDate = SDF.format(creationDate);
            String membersString = "";
            for (int i = 0; i < listOfMembers.size(); i++) {
                membersString = membersString + listOfMembers.get(i) + ";";
            }

            writer.write(teamName + ";" + formattedDate + ";" + numberOfMembers);
            writer2.write(teamName + ";" + membersString) ;

            writer.close();
            writer2.close();
        }
    }
}
