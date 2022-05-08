import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UpdateCSV {

    List<String> productFamilies = Arrays.asList("Shoes", "Clothes");
    List<String> countries = Arrays.asList("USA", "Canada", "GreatBritain");
    List<String> osList = Arrays.asList("iOS", "Android");
    String version = "ECOM_200";
    String comma = ",";
    String deviceType = "Mobile";

    public static void main(String[] args) throws IOException {
        String file = args[0];
        UpdateCSV updateCSV = new UpdateCSV();
        updateCSV.addRecordsToCSV(file);
    }

    private void addRecordsToCSV(String file) throws IOException {
        FileWriter csvWriter = new FileWriter(file,true);
        Random random = new Random();
        int randomNumber = random.nextInt(20);
        System.out.println(randomNumber);
        for(int i = 0;i < randomNumber;i++) {
            String date = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss").format(new Date());
            String productFamily = productFamilies.get(random.nextInt(2));
            String country = countries.get(random.nextInt(3));
            String os = osList.get(random.nextInt(2));
            String checkoutFailureCount = random.nextInt(10000) + "." + random.nextInt(10000);
            String paymentApiFailureCount = random.nextInt(10000) + "." + random.nextInt(10000);
            String purchaseCount = random.nextInt(10000) + "." + random.nextInt(10000);
            String revenue = random.nextInt(10000) + "." + random.nextInt(10000);
            String record = date + comma + version + comma +productFamily + comma +  country + comma + deviceType + comma + os + comma +
                    checkoutFailureCount + comma + paymentApiFailureCount + comma +
                    purchaseCount + comma + revenue + "\n";
            System.out.println(record);
            csvWriter.append(record);
        }
        csvWriter.close();
    }
}
