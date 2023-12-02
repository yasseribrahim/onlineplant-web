package com.online.plant.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.online.plant.constants.Constants;
import com.online.plant.models.FastSelling;
import com.online.plant.models.Plant;
import com.online.plant.models.PlantStock;
import com.online.plant.models.Transaction;
import com.online.plant.models.TransactionDetails;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DatasourceManager {

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void main(String[] args) throws IOException {
    }

    public static String convert(Object object) {
        return GSON.toJson(object);
    }

    public static List<Plant> loadPlants() {
        File file = new File(Constants.PATH_DATA_PLANTS);
        List<Plant> plants = GSON.fromJson(loadFile(file), new TypeToken<List<Plant>>() {
        }.getType());
        return plants;
    }

    public static String loadFile(File file) {
        String content = "";
        try {
            content = new String(Files.readAllBytes(Paths.get(file.toURI())));
        } catch (IOException e) {
        }

        return content;
    }

    public static void storeFile(File file, String content) {
        try {
            Files.write(Paths.get(file.toURI()), content.getBytes());
        } catch (IOException ex) {
        }
    }

    public static List<PlantStock> loadPlantStocks() {
        try {
            File file = new File(Constants.PATH_DATA_STOCKS);
            JsonReader reader = new JsonReader(new FileReader(file));
            List<PlantStock> stocks = GSON.fromJson(reader, new TypeToken<List<PlantStock>>() {
            }.getType());
            return stocks;
        } catch (FileNotFoundException ex) {
            return new ArrayList<>();
        }
    }

    public static List<PlantStock> loadPlantStocks(String json) {
        List<PlantStock> stocks = GSON.fromJson(json, new TypeToken<List<PlantStock>>() {
        }.getType());
        return stocks;
    }

    public static List<Transaction> loadTransactions() {
        try {
            File file = new File(Constants.PATH_DATA_TRANSACTIONS);
            JsonReader reader = new JsonReader(new FileReader(file));
            List<Transaction> transactions = GSON.fromJson(reader, new TypeToken<List<Transaction>>() {
            }.getType());
            return transactions;
        } catch (JsonIOException | JsonSyntaxException | FileNotFoundException ex) {
        }
        return new ArrayList<>();
    }

    public static List<Transaction> loadTransactions(String json) {
        List<Transaction> transactions = GSON.fromJson(json, new TypeToken<List<Transaction>>() {
        }.getType());
        return transactions;
    }

    public static List<Plant> loadPlants(String json) {
        List<Plant> plants = GSON.fromJson(json, new TypeToken<List<Plant>>() {
        }.getType());
        return plants;
    }

    public static Transaction loadTransaction(String json) {
        Transaction transaction = GSON.fromJson(json, new TypeToken<Transaction>() {
        }.getType());
        return transaction;
    }

    public static Plant loadPlant(String json) {
        Plant plant = GSON.fromJson(json, new TypeToken<Plant>() {
        }.getType());
        return plant;
    }

    public static TransactionDetails loadTransactionDetails(String json) {
        TransactionDetails details = GSON.fromJson(json, new TypeToken<TransactionDetails>() {
        }.getType());
        return details;
    }

    public static void updateTransactionPlant(int id, int plantId, int quantity) {
        List<Transaction> transactions = loadTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                for (TransactionDetails details : transaction.getDetailses()) {
                    if (details.getId() == plantId) {
                        details.setQuantity(quantity);
                    }
                }
            }
        }

        String json = convert(transactions);
        storeFile(new File(Constants.PATH_DATA_TRANSACTIONS), json);
    }

    public static boolean updatePlantStock(int id, int quantity) {
        List<PlantStock> plants = loadPlantStocks();
        boolean status = false;
        for (PlantStock plant : plants) {
            if (plant.getPlantId() == id) {
                plant.setQuantity(quantity);
                status = true;
                break;
            }
        }

        String json = convert(plants);
        storeFile(new File(Constants.PATH_DATA_STOCKS), json);
        return status;
    }

    public static void addTransactionPlant(int id, int plantId, int quantity) {
        List<Transaction> transactions = loadTransactions();
        Transaction selectedTransaction = null;
        TransactionDetails selectedDetails = null;
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                selectedTransaction = transaction;
                for (TransactionDetails details : transaction.getDetailses()) {
                    if (details.getId() == plantId) {
                        selectedDetails = details;
                        break;
                    }
                }
            }
        }
        if (selectedTransaction != null) {
            if (selectedDetails != null) {
                selectedDetails.setQuantity(selectedDetails.getQuantity() + quantity);
            } else {
                List<Plant> plants = DatasourceManager.loadPlants();
                for (Plant plant : plants) {
                    if (plant.getId() == plantId) {
                        TransactionDetails details = new TransactionDetails(plantId, plant.getName(), plant.getType(), plant.getAge(), plant.getPrice(), quantity);
                        selectedTransaction.getDetailses().add(details);
                    }
                }
            }

            String json = convert(transactions);
            storeFile(new File(Constants.PATH_DATA_TRANSACTIONS), json);
        }
    }

    public static void deleteTransactionPlant(int id, int plantId) {
        List<Transaction> transactions = loadTransactions();

        int index = 0, selected = -1;
        for (Transaction transaction : transactions) {
            if (transaction.getId() == id) {
                for (TransactionDetails details : transaction.getDetailses()) {
                    if (details.getId() == plantId) {
                        selected = index;
                        break;
                    }
                }

                if (selected >= 0) {
                    transaction.getDetailses().remove(selected);
                    String json = convert(transactions);
                    storeFile(new File(Constants.PATH_DATA_TRANSACTIONS), json);
                }
            }
            index++;
        }
    }

    public static List<FastSelling> loadFastSellings() throws IOException {
        File file = new File(Constants.PATH_DATA_FAST_SELLING);
        JsonReader reader = new JsonReader(new FileReader(file));
        List<FastSelling> fastSellings = GSON.fromJson(reader, new TypeToken<List<FastSelling>>() {
        }.getType());
        return fastSellings;
    }

    public static List<Plant> generatePlants() {
        List<Plant> plants = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            plants.add(new Plant(i, "Plant " + i, "Type " + i, i, i * 10));
        }
        return plants;
    }

    public static List<PlantStock> generatePlantStocks() {
        List<PlantStock> plants = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            plants.add(new PlantStock(i, "Plant " + i, i * 10));
        }
        return plants;
    }

    public static List<FastSelling> generateFastSellings() {
        List<FastSelling> plants = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            plants.add(new FastSelling(i, "Plant " + i, i * 10));
        }
        return plants;
    }

    public static List<Transaction> generateTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            List<TransactionDetails> detailses = new ArrayList<>();
            for (int j = 1; j <= i; j++) {
                detailses.add(new TransactionDetails(i, "Plant " + j, "Type " + j, j, i * 10, i));
            }

            Transaction transaction = new Transaction(i, "seller1", "buyer1", i + "/10/2023");
            transaction.setDetailses(detailses);
            transactions.add(transaction);
        }
        return transactions;
    }
}

class ThrowableAdapterFactory implements TypeAdapterFactory {

    private ThrowableAdapterFactory() {
    }

    public static final ThrowableAdapterFactory INSTANCE = new ThrowableAdapterFactory();

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        // Only handles Throwable and subclasses; let other factories handle any other type
        if (!Throwable.class.isAssignableFrom(type.getRawType())) {
            return null;
        }

        @SuppressWarnings("unchecked")
        TypeAdapter<T> adapter = (TypeAdapter<T>) new TypeAdapter<Throwable>() {
            @Override
            public Throwable read(JsonReader in) throws IOException {
                throw new UnsupportedOperationException();
            }

            @Override
            public void write(JsonWriter out, Throwable value) throws IOException {
                if (value == null) {
                    out.nullValue();
                    return;
                }

                out.beginObject();
                // Include exception type name to give more context; for example NullPointerException might
                // not have a message
                out.name("type");
                out.value(value.getClass().getSimpleName());

                out.name("message");
                out.value(value.getMessage());

                Throwable cause = value.getCause();
                if (cause != null) {
                    out.name("cause");
                    write(out, cause);
                }

                Throwable[] suppressedArray = value.getSuppressed();
                if (suppressedArray.length > 0) {
                    out.name("suppressed");
                    out.beginArray();

                    for (Throwable suppressed : suppressedArray) {
                        write(out, suppressed);
                    }

                    out.endArray();
                }
                out.endObject();
            }
        };
        return adapter;
    }
}
