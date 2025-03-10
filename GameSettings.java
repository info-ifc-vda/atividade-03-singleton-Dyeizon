import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

public final class GameSettings {
    private final static GameSettings singleton = new GameSettings();
    private final String configPath = "config.json";
    
    private int volume;
    private String resolution;
    private String difficulty;

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public static GameSettings getInstance() {
        return singleton;
    }

    private GameSettings() {
        reset();
        save();
    }

    public void reset() {
        try(FileReader file = new FileReader(configPath)){
            BufferedReader buffer = new BufferedReader(file);
            String jsonString = buffer.readLine();
            JSONObject jsonObject = new JSONObject(jsonString);

            volume = (jsonObject.getInt("volume"));
            resolution = (jsonObject.getString("resolution"));
            difficulty = (jsonObject.getString("difficulty"));

            file.close();
        } catch (Exception e) {
            volume = 50;
            resolution = "1920x1080";
            difficulty = "Medium";
        }
    }

    public void save() {
        JSONObject config = new JSONObject();
        config.put("volume", volume);
        config.put("resolution", resolution);
        config.put("difficulty", difficulty);

        try (FileWriter file = new FileWriter(configPath)) {
            file.write(config.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return 
        "Volume: " + this.volume + "\n" +
        "Resolução: " + this.resolution + "\n" +
        "Dificuldade: " + this.difficulty + "\n";
    }
}