package com.matritellabs.utama.exam4.csisanyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
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

    /** Reader methods @throws IOException or Parsexception, RuntimeException,
     * The method accepts 2 parameters
     * based on the parameters it decides,
     * how to read in from the file
     * based on the read data it creates
     * new AgileTeam objects and
     * adds them to a list*/

    public static List<AgileTeam> readTeamsFromFile(List<String> filePathList, TeamFileFormat fileFormat)
    throws IOException, ParseException, RuntimeException {

        List<AgileTeam> returnList = new ArrayList<>();
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        //* Method, if the TeamFilformat paramaeter is ONE_FILE*/
        if(fileFormat.equals(ONE_FILE)) {
            try {
                Path theFile = Paths.get(filePathList.get(0));
                log.info("reading input file");
                List<String> lineList = Files.readAllLines(theFile);


                for (int i = 0; i < lineList.size(); i++) {
                    String[] splitted = lineList.get(i).split(";");
                    int sizeOfMembersList = Integer.parseInt(splitted[2]);
                    if (splitted.length > 3 + sizeOfMembersList) {
                        String message = " more ; sysmbols than expected";
                        log.error(message);
                        throw new RuntimeException(message);
                    }
                    List<String> membersList = new ArrayList<>();
                    if (splitted.length < 3 + sizeOfMembersList) {
                        String message = "Less members than expected";
                        log.error(message);
                        throw new RuntimeException(message);
                    }
                    for (int j = 3; j < splitted.length; j++) {
                        membersList.add(splitted[j]);
                    }
                    log.info("Creating new AgileTeaminstance, and adding it to the list");
                    returnList.add(new AgileTeam(splitted[0], SDF.parse(splitted[1]), sizeOfMembersList, membersList));
                }

            } catch (IOException e) {
                String message = "File not found";
                log.error(message);
                throw new FileNotFoundException(message);
            }
        }

        //* Method, if the TeamFilformat paramaeter is TWO_FILES*/
        if(fileFormat.equals(TWO_FILES)) {
            try {
                Path teamData = Paths.get(filePathList.get(0));
                log.info("reading input files");
                List<String> lineList = Files.readAllLines(teamData);
                Path nameAndlist = Paths.get(filePathList.get(1));
                List<String> lineList2 = Files.readAllLines(nameAndlist);

                if (lineList.size() != lineList2.size()) {
                    String message = "The two files do not correspond to each other";
                    log.error(message);
                    throw new RuntimeException(message);
                }


                for (int i = 0; i < lineList.size(); i++) {
                    String[] splitted = lineList.get(i).split(";");
                    String[] splitted2 = lineList2.get(i).split(";");

                    if (splitted.length > 3) {
                        String message = " more ; sysmbols than expected";
                        log.error(message);
                        throw new RuntimeException(message);
                    }

                    int sizeOfMembersList = Integer.parseInt(splitted[2]);
                    List<String> membersList = new ArrayList<>();

                    if (splitted2.length > sizeOfMembersList + 1) {
                        String message = " more ; sysmbols than expected";
                        log.error(message);
                        throw new RuntimeException(message);
                    }





                    if (!splitted[0].equals(splitted2[0])) {
                        String message = "Teams are not the same";
                        log.error(message);
                        throw new RuntimeException(message);
                    }

                    for (int j = 1; j < splitted2.length; j++) {
                        membersList.add(splitted2[j]);
                    }

                    returnList.add(new AgileTeam(splitted[0], SDF.parse(splitted[1]), sizeOfMembersList, membersList));
                }

            } catch (IOException io) {
                String message = "File not found";
                log.error(message);
                throw new FileNotFoundException(message);
            }
        }
        log.info("method executed correctly");
        return returnList;

    }

    /**File writer method @throws IOException
     * based on the input parameters
     * it writes the data of the input Lists
     * objects to a file, in a format
     * described by the fileformat parameter*/
    public static void writeTeamToFile(List<String> filePathlist, TeamFileFormat fileFormat,
                                       String teamName, Date creationDate, int numberOfMembers, List<String> listOfMembers )
    throws IOException {


        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");

        //* Method, if the TeamFilformat paramaeter is ONE_FILE*/
        if (fileFormat.equals(ONE_FILE)) {
            Path fileToWrite = Paths.get(filePathlist.get(0));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite.toFile()));

            if(numberOfMembers != listOfMembers.size()) {
                String message = "number of members and actual members do not correspont";
                log.error(message);
                throw new RuntimeException(message);
            }

            String formattedDate = SDF.format(creationDate);
            String membersString = "";
            for (int i = 0; i < listOfMembers.size(); i++) {
                membersString = membersString + listOfMembers.get(i) + ";";
            }

            log.info("writing in progress");
            writer.write(teamName + ";" + formattedDate + ";" + numberOfMembers + ";" + membersString);
            writer.close();
            log.info("method executed correctly");
        }
        //* Method, if the TeamFilformat paramaeter is TWO_FILES*/
        if(fileFormat.equals(TWO_FILES)) {
            Path fileToWrite = Paths.get(filePathlist.get(0));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileToWrite.toFile()));
            Path fileToWrite2 = Paths.get(filePathlist.get(1));
            BufferedWriter writer2 = new BufferedWriter(new FileWriter(fileToWrite2.toFile()));

            if(numberOfMembers != listOfMembers.size()) {
                String message = "number of members and actual members do not correspont";
                log.error(message);
                throw new RuntimeException(message);
            }

            String formattedDate = SDF.format(creationDate);
            String membersString = "";
            for (int i = 0; i < listOfMembers.size(); i++) {
                membersString = membersString + listOfMembers.get(i) + ";";
            }

            log.info("writing in progress");
            writer.write(teamName + ";" + formattedDate + ";" + numberOfMembers);
            writer2.write(teamName + ";" + membersString) ;


            writer.close();
            writer2.close();
            log.info("method executed correctly");
        }
    }
}
