package no.ntnu.nauybeng.sudoku;

import android.content.Context;
import android.util.Log;

import com.google.common.collect.ImmutableMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BoardStorageUtil {

    public static Map<String, Integer> difficultyBoardMap = ImmutableMap.of(
            "easy", R.raw.boards_easy,
            "medium", R.raw.boards_medium,
            "hard", R.raw.boards_hard
    );

    public static boolean addBoard(Context context, String difficulty, String boardString) {
        return writeBoards(context, difficulty, readBoards(context, difficulty) + boardString);
    }

    private static boolean writeBoards(Context context, String difficulty, String content) {
        try {
            FileWriter writer = new FileWriter(new File(context.getFilesDir(), difficulty));
            writer.append(content);
            writer.flush();
            writer.close();
            return true;
        } catch (Exception e) {
            Log.d("FILE WRITE", e.getMessage());
            return false;
        }
    }

    public static List<String> readBoardsAsList(Context context, String difficulty) {
        return Arrays.asList(readBoards(context, difficulty).split("\n"));
    }

    public static String readBoards(Context context, String difficulty) {
        try {
            FileInputStream fileInputStream = context.openFileInput(difficulty);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            InputStream inputStream = context.getResources().openRawResource(difficultyBoardMap.get(difficulty));
            writeBoards(context, difficulty, inputStreamToString(inputStream));
            return readBoards(context, difficulty); // what could go wrong
        } catch (Exception e) {
            Log.d("FILE READ", e.getMessage());
            return null;
        }
    }

    private static String inputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
