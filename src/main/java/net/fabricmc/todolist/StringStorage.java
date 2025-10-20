package net.fabricmc.todolist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class StringStorage {

    private final Path filePath;

    public StringStorage(String filename) {
        this.filePath = Paths.get(filename);
    }


    public void saveString(int index, String data) throws IOException {
        List<String> lines = new ArrayList<>();


        if (Files.exists(filePath)) {
            lines = Files.readAllLines(filePath);
        }

        while (lines.size() <= index) {
            lines.add("");
        }

        lines.set(index, data);


        Files.write(filePath, lines);
    }


    public String loadStringByIndex(int index) throws IOException {
        if (!Files.exists(filePath)) {
            return "";
        }

        List<String> lines = Files.readAllLines(filePath);
        if (index < 0 || index >= lines.size()) {
            return "";
        }

        return lines.get(index);
    }

}