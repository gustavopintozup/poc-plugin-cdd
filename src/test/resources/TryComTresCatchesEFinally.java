import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class TryComTresCatchesEFinally {
    public int getPlayerScore(String playerFile) {
        try (Scanner contents = new Scanner(new File(playerFile)) ) {
            return Integer.parseInt(contents.nextLine());
        } catch (FileNotFoundException e) {
            return 0;
        } catch (IOException e) {
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        } finally {
            return 0;
        }
    }
}