package com.matritellabs.utama.exam4.csisanyi;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {

        List<String> fileList = new ArrayList<>();
        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/agile_twofile_file1.txt");
        fileList.add("/home/sanyi/workspace/agile_team_handler/src/main/java/com/matritellabs/utama/exam4/resources/agile_twofile_file2.txt");

        try {
            TeamLoader.readTeamsFromFile(fileList, TeamFileFormat.TWO_FILES);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
