package net.fabricmc.todolist;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.*;

public class ListManager {
    private boolean showoverlay = loadBoolean();
    private String goalAddedString = loadGoal();

    private static final String FILE_NAME_BOOLEAN = "Config_generated_overlay_goal.txt";
    private static final String FILE_NAME_GOAL = "Config_generated_overlay_goal_text.txt";
    private static final String FILE_NAME_MODE = "Config_generated_overlay_mode_text.txt";
    private static final String FILE_NAME_NUM = "Config_generated_list_entry_num.txt";

    @Environment(EnvType.CLIENT)
    public void setoverlayConfig(boolean b){
        showoverlay = b;
        saveBoolean(showoverlay);
    }
    @Environment(EnvType.CLIENT)
    public void setMode(String b){
        saveMode(b);
    }

    @Environment(EnvType.CLIENT)
    public boolean getoverlayConfig(){
        return loadBoolean();
    }


    @Environment(EnvType.CLIENT)
    public static boolean loadBoolean(){
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME_BOOLEAN))) {
            String line = reader.readLine();
            if (line != null) {
                return Boolean.parseBoolean(line);
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading boolean or file not found: " + e.getMessage());
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
            System.err.println("Error loading goal string or file not found: " + e.getMessage());
        }
        return "";

    }

    @Environment(EnvType.CLIENT)
    public static void saveMode(String mode){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME_MODE))) {
            writer.write(String.valueOf(mode));
        } catch (IOException e) {
            System.err.println("Error saving goal string: " + e.getMessage());
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
            System.err.println("Error loading goal string or file not found: " + e.getMessage());
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
            System.err.println("Error loading goal string or file not found: " + e.getMessage());
        }
        return 1;

    }

    @Environment(EnvType.CLIENT)
    public String GetGoal(){
        return loadGoal();
    }
    public void SetGoal(String text){
        this.goalAddedString = text;
        saveGoal(text);
    }

    @Environment(EnvType.CLIENT)
    public String getMode(){
        return loadMode();
    }



}
