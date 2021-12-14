# Домашнее задание к занятию 2.1: Тестирование кода и Unit-тесты
## Задача 1. "Использование JUnit"

v. 1.0.

Задача реализована. Написаны следующие тесты:


**Тест метода delete(), проверяющий удаление несуществующего файла:**
```
@Test
public void testDeleteThrow() {
String dir = "Games/BestGame/savegames/save4.dat";
var expected = NoSuchFileException.class;
assertThrows(expected,
() -> sut.delete(dir));
}
```
**Тест метода zipFiles(), проверяющий возможность архивации файлов:**
```
    @Test
    public void testZipFiles() {
        List<String> savesList = new ArrayList<>();
        sut.zipFiles("Games/BestGame/savegames/saves.zip", savesList);
        boolean result = Files.exists(Path.of("Games/BestGame/savegames/saves.zip"));
        assertTrue(result);
    }
```

**Тест метода saveGame(), проверяющий возможность сохранения игрового процесса:**
```
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
```

Вывод в консоли после исполнения:
```
14.12.2021 - 16:41:54 - tests started
14.12.2021 - 16:41:54 - test started
14.12.2021 - 16:41:54 - test finished
14.12.2021 - 16:41:54 - test started
14.12.2021 - 16:41:54 - test finished
14.12.2021 - 16:41:54 - test started
14.12.2021 - 16:41:54 - test finished
14.12.2021 - 16:41:54 - tests finished
```