import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class Task implements Runnable {
    private static final String COMMA_DELIMITER = ",";

    public void run() {
        System.out.println(
                "Number " + " Current time : "
                        + java.time.LocalDateTime.now());
        readCSV(CSVSync.file);
    }

    private static void readCSV(String file) {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
            System.out.println("count of records " + records.size());
            int count = records.size();
            int countInDatabase = getCountFromDatabase();
            System.out.println("count of records in DB " + countInDatabase);
            if (count > countInDatabase)
                updateRecords(records, count, countInDatabase);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void updateRecords(List<List<String>> records, int count, int countInDatabase) {

        StringBuffer insertQuery = new StringBuffer();
        String insertPrefix = "INSERT INTO sync.records VALUES ";
        insertQuery.append(insertPrefix);
        System.out.println("Inserting into db");
        for (int i = countInDatabase; i < count; i++) {
            List<String> record = records.get(i);
            insertQuery.append("( '").append(record.get(0)).append("', '").append(record.get(1)).append("', '")
                    .append(record.get(2)).append("', '").append(record.get(3)).append("', '").append(record.get(4))
                    .append("', '").append(record.get(5)).append("', '").append(record.get(6)).append("', '")
                    .append(record.get(7)).append("', '").append(record.get(8)).append("', '").append(record.get(9))
                    .append("')");
            if (i == count - 1)
                insertQuery.append(";");
            else
                insertQuery.append(",");
//            System.out.println(insertQuery);
        }

//        System.out.println(insertQuery);

        try {
            if(count > countInDatabase) {
                Connection conn = DatabaseConnection.getInstance().getConnection();
                PreparedStatement ps = conn.prepareStatement(insertQuery.toString());
                int result = ps.executeUpdate();
                System.out.println(result+ "records inserted in DB");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getCountFromDatabase() {
        String sqlSelectAllPersons = "SELECT count(1) as count FROM records";

        try {
            Connection conn = DatabaseConnection.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int resultCount = rs.getInt("count");
                return resultCount;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

public class CSVSync {
    public static String file = "";
    public static void main(String[] args) {

        if(args.length < 4)
            System.out.println("Incorrect arguments, 4 arguments url, username, password, file required");
        DatabaseConnection.init(args[0],args[1],args[2]);
        System.out.println("Args " +args[0] + " " + args[1] + " " + args[2] + " " + args[3]);

        file = args[3];
        System.out.println(file);
//        file = "src/test/resources/AssignmentSheet.csv";

        final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        System.out.println("Current time : " + java.time.LocalDateTime.now());

        // Scheduling the tasks
        scheduler.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.MINUTES);

        // remember to shutdown the scheduler
        // so that it no longer accepts
        // any new tasks
        // scheduler.shutdown();
    }
}
