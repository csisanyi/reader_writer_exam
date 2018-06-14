package com.matritellabs.utama.exam4.csisanyi;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        List<String> fileList = new ArrayList<>();
        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/write_twofile_file1.txt");
        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/write_twofile_file2.txt");
        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/agile_one.txt");
        List<String> memberList = new ArrayList<>();
        memberList.add("Sandor");
        memberList.add("Jozsef");
        memberList.add("Benedek");


//
//        try {
//            TeamLoader.readTeamsFromFile(fileList,TeamFileFormat.ONE_FILE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
////        try {
//            TeamLoader.readTeamsFromFile(fileList, TeamFileFormat.TWO_FILES);
            SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        try {
            TeamLoader.writeTeamToFile(fileList,TeamFileFormat.TWO_FILES,"TeamHozzakAMeleget",SDF.parse("1986-08-27"),3,memberList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }


//        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/agile_onefile.txt");
//        try {
//            TeamLoader.readTeamsFromFile(fileList,TeamFileFormat.ONE_FILE);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//
//        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/agile_twofile_file1.txt");
//        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/agile_twofile_file2.txt");
//        try {
//            TeamLoader.readTeamsFromFile(fileList,TeamFileFormat.TWO_FILES);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
