package net.fabricmc.todolist;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListManager {

    //INITS

    public boolean showoverlay = loadBoolean();

    public String goalString = loadGoal();

    public int lineNum = loadLineNum();

    public String modeString = loadMode();


    private static final String FILE_NAME_BOOLEAN = "Config_generated_overlay_goal.txt";
    private static final String FILE_NAME_GOAL = "Config_generated_overlay_goal_text.txt";
    private static final String FILE_NAME_MODE = "Config_generated_overlay_mode_text.txt";
    private static final String FILE_NAME_NUM = "Config_generated_list_entry_num.txt";


    //SETTERS
    public static List<String> strings = new ArrayList<>(){
        {
            for (int i = 0; i < loadLineNum(); i++) {
                StringStorage storage = new StringStorage("ListStorage");
                String text = "Something Went Wrong";
                try {
                    text = (i + 1) + ". " + storage.loadStringByIndex(i);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                add(i, text);
            }
        }
    };

    @Environment(EnvType.CLIENT)
    public void setoverlayConfig(boolean b){

        showoverlay = b;
        saveBoolean(showoverlay);
    }
    @Environment(EnvType.CLIENT)
    public void setMode(String b){

        modeString = b;
        saveMode(modeString);
    }

    @Environment(EnvType.CLIENT)
    public void SetGoal(String text){

        this.goalString = text;
        saveGoal(text);
    }

    @Environment(EnvType.CLIENT)
    public void SetLineAmount(int num){

        this.lineNum = num;

        saveLineNum(lineNum);
    }

    //GETTERS

    @Environment(EnvType.CLIENT)
    public int GetLineAmount(){

        return this.lineNum;
    }

    @Environment(EnvType.CLIENT)
    public boolean getoverlayConfig(){

        return this.showoverlay;
    }

    @Environment(EnvType.CLIENT)
    public String getMode(){

        return this.modeString;
    }

    @Environment(EnvType.CLIENT)
    public String GetGoal(){

        return this.goalString;
    }

    // SAVE/LOAD SYSTEM

    @Environment(EnvType.CLIENT)
    public static boolean loadBoolean(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_BOOLEAN))) {
            String line = reader.readLine();
            if (line != null) {
                return Boolean.parseBoolean(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading boolean or file not found (if you're seeing it on a first instance bootup, ignore this): " + e.getMessage());
            try {
                FileWriter writer = new FileWriter(FILE_NAME_BOOLEAN);
                writer.write("false");
            } catch (IOException ignored) {

            }
        }
        return false;

    }

    @Environment(EnvType.CLIENT)
    public static void saveBoolean(boolean a) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_BOOLEAN))) {
            writer.write(String.valueOf(a));
        } catch (IOException e) {
            System.err.println("Error saving boolean: " + e.getMessage());
        }
    }

    @Environment(EnvType.CLIENT)
    public static void saveGoal(String goalString){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_GOAL))) {
            writer.write(String.valueOf(goalString));
        } catch (IOException e) {
            System.err.println("Error saving goal string: " + e.getMessage());
        }
    }

    @Environment(EnvType.CLIENT)
    public static String loadGoal(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_GOAL))) {
            String line = reader.readLine();
            if (line != null) {
                return (line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading goal string or file not found (if you're seeing it on a first instance bootup, ignore this): " + e.getMessage());
            try {
                FileWriter writer = new FileWriter(FILE_NAME_GOAL);
                writer.write(" ");
            } catch (IOException ignored) {

            }

        }
        return "";

    }

    @Environment(EnvType.CLIENT)
    public static void saveMode(String mode){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_MODE))) {
            writer.write(String.valueOf(mode));
        } catch (IOException e) {
            System.err.println("Error saving mode string: " + e.getMessage());
        }
    }

    @Environment(EnvType.CLIENT)
    public static String loadMode(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_MODE))) {
            String line = reader.readLine();
            if (line != null) {
                return (line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading goal string or file not found (if you're seeing it on a first instance bootup, ignore this): " + e.getMessage());
            try {
                FileWriter writer = new FileWriter(FILE_NAME_MODE);
                writer.write("Goal");
            } catch (IOException ignored) {

            }
        }
        return "Goal";

    }

    public static void saveLineNum(int lineNum){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_NUM))) {
            writer.write(String.valueOf(lineNum));
        } catch (IOException e) {
            System.err.println("Error saving list num: " + e.getMessage());
        }
    }
    public static int loadLineNum(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_NUM))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading goal string or file not found (if you're seeing it on a first instance bootup, ignore this): " + e.getMessage());
            try {
                FileWriter writer = new FileWriter(FILE_NAME_NUM);
                writer.write("1");
            } catch (IOException ignored) {

            }
        }
        return 1;

    }


    public static void UpdateList(){
        for (int i = 0; i < loadLineNum(); i++) {
            StringStorage storage = new StringStorage("ListStorage");
            String text = "Something Went Wrong";
            try {
                text = (i + 1) + ". " + storage.loadStringByIndex(i);
            } catch (IOException e) {
                strings.add(i,text);
            }
            strings.add(i, text);
        }
    }





}
