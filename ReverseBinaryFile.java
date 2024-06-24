import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ReverseBinaryFile {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Формат запуска: java ReverseBinaryFile <входной_файл> <выходной_файл>");
            return;
        }
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(args[0]));
        } catch (IOException e) {
            System.err.println("Ошибка доступа к входному файлу." + e.getMessage());
        }
        final String REGEX = ", ";
        try {
            try (BufferedWriter out = new BufferedWriter(new FileWriter(args[1]))) {
                assert lines != null;
                for (int lineInd = lines.size() - 1; lineInd >= 0; lineInd--) {
                    String[] buffer = lines.get(lineInd).split(REGEX);
                    for (int i = buffer.length - 1; i >= 0; i--) {
                        out.write(String.format("%s%s",
                                i == buffer.length - 1 ? "" : ", ",
                                buffer[i]));
                    }
                    out.write("\n" );
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка доступа к выходному файлу." + e.getMessage());
        }
        System.out.println("Файл успешно перевернут");
    }
}
