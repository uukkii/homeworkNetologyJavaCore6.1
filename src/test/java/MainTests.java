import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class MainTests {
    private static Main sut;

    private static final String time = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("dd.MM.yyyy - HH:mm:ss "));

    @BeforeEach
    public void init() {
        System.out.println(time + "- test started");
    }

    @BeforeAll
    public static void started() {
        System.out.println(time + "- tests started");
        sut = new Main();
    }

    @AfterEach
    public void finished() {
        System.out.println(time + "- test finished");
    }

    @AfterAll
    public static void finishedAll() {
        System.out.println(time + "- tests finished");
    }

    @Test
    public void testDeleteThrow() {
        String dir = "Games/BestGame/savegames/save4.dat";
        var expected = NoSuchFileException.class;
        assertThrows(expected,
                () -> sut.delete(dir));
    }

    @Test
    public void testZipFiles() {
        List<String> savesList = new ArrayList<>();
        sut.zipFiles("Games/BestGame/savegames/saves.zip", savesList);
        boolean result = Files.exists(Path.of("Games/BestGame/savegames/saves.zip"));
        assertTrue(result);
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testSaveDoesNotThrow(String dir, GameProgress gameProgress) {
        assertDoesNotThrow(
                () -> sut.saveGame(dir, gameProgress));
    }

    private static Stream<Arguments> source() {
        return Stream.of(Arguments.of("Games/BestGame/savegames/save1.dat", new GameProgress(100, 2, 1, 1.0),
                Arguments.of("Games/BestGame/savegames/save2.dat", new GameProgress(80, 5, 4, 112.5))));
    }
}
