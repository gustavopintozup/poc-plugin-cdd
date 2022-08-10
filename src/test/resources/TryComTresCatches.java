import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

class TryComTresCatches {
    public int getPlayerScore(String playerFile) {
        try (Scanner contents = new Scanner(new File(playerFile)) ) {
            return Integer.parseInt(contents.nextLine());
        } catch (FileNotFoundException e) {
           throw new RuntimeException("bla bla");
        } catch (IOException e) {
            throw new RuntimeException("bla bla");
        } catch (NumberFormatException e) {
            throw new RuntimeException("bla bla");
        }
    }
}