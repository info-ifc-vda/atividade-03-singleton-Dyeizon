public class Game {
    public static void main(String[] args) {
        GameSettings settings = GameSettings.getInstance();

         settings.setVolume(155);
         settings.setResolution("1080x720");
         settings.setDifficulty("Hard");
         settings.save();

        System.out.println(settings);

    }
}