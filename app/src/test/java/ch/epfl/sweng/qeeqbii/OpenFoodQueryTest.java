package ch.epfl.sweng.qeeqbii;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by guillaume on 16.10.17.
 */


public class OpenFoodQueryTest {
    @Test
    public void QueryOfExistingProduct()
    {
        String[] barcode = new String[1];
        barcode[0] = "7610848337010";


        new OpenFoodQuery()
        {
            @Override public void onPostExecute(String result)
            {
                String true_result = "[\n" +
                        "    {\n" +
                        "      \"id\": 2030,\n" +
                        "      \"barcode\": \"7610848337010\",\n" +
                        "      \"display_name_translations\": {\n" +
                        "        \"de\": \"Mango : Scheiben\",\n" +
                        "        \"fr\": \"Mangue : en tranches\",\n" +
                        "        \"it\": \"Mango : A spicchi\",\n" +
                        "        \"en\": \"Mango : Scheiben\"\n" +
                        "      },\n" +
                        "      \"ingredients_translations\": {\n" +
                        "        \"de\": \"Mango (Thailand), Wasser, Zucker, Säuerungsmittel (E330)\",\n" +
                        "        \"fr\": \"mangue (Thaïlande), eau, sucre, acidifiant (E330)\",\n" +
                        "        \"it\": \"mango (Thailandia), acqua, zucchero, acidificante (E330)\"\n" +
                        "      },\n" +
                        "      \"origin_translations\": {\n" +
                        "        \"de\": \"[\\\"Thailand\\\"]\",\n" +
                        "        \"fr\": \"[\\\"Thaïlande\\\"]\",\n" +
                        "        \"it\": \"[\\\"Thailandia\\\"]\"\n" +
                        "      },\n" +
                        "      \"status\": \"complete\",\n" +
                        "      \"quantity\": 245,\n" +
                        "      \"unit\": \"g\",\n" +
                        "      \"portion_quantity\": 122,\n" +
                        "      \"portion_unit\": \"g\",\n" +
                        "      \"alcohol_by_volume\": 0,\n" +
                        "      \"nutrients\": {\n" +
                        "        \"salt\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Salz\",\n" +
                        "            \"en\": \"Salt\",\n" +
                        "            \"fr\": \"Sel\",\n" +
                        "            \"it\": \"Sale\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 0,\n" +
                        "          \"per_portion\": 0,\n" +
                        "          \"per_day\": 0\n" +
                        "        },\n" +
                        "        \"protein\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Eiweiss\",\n" +
                        "            \"en\": \"Protein\",\n" +
                        "            \"fr\": \"Protéines\",\n" +
                        "            \"it\": \"Proteine\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 0.5,\n" +
                        "          \"per_portion\": 0.6,\n" +
                        "          \"per_day\": 1\n" +
                        "        },\n" +
                        "        \"fiber\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Ballaststoffe\",\n" +
                        "            \"en\": \"Fiber\",\n" +
                        "            \"fr\": \"Fibres alimentaires\",\n" +
                        "            \"it\": \"Fibre\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 1.5,\n" +
                        "          \"per_portion\": 1.8,\n" +
                        "          \"per_day\": 7\n" +
                        "        },\n" +
                        "        \"sugars\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Zucker\",\n" +
                        "            \"en\": \"Sugars\",\n" +
                        "            \"fr\": \"Sucres\",\n" +
                        "            \"it\": \"Zuccheri\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 15,\n" +
                        "          \"per_portion\": 18.4,\n" +
                        "          \"per_day\": 20\n" +
                        "        },\n" +
                        "        \"carbohydrates\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Kohlenhydrate\",\n" +
                        "            \"en\": \"Carbohydrates\",\n" +
                        "            \"fr\": \"Glucides\",\n" +
                        "            \"it\": \"Carboidrati\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 15,\n" +
                        "          \"per_portion\": 18.4,\n" +
                        "          \"per_day\": 7\n" +
                        "        },\n" +
                        "        \"saturated_fat\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Gesättigte Fettsäuren\",\n" +
                        "            \"en\": \"Saturated fat\",\n" +
                        "            \"fr\": \"Acides gras saturées\",\n" +
                        "            \"it\": \"Acidi grassi saturi\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 0,\n" +
                        "          \"per_portion\": 0,\n" +
                        "          \"per_day\": 0\n" +
                        "        },\n" +
                        "        \"fat\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Fett\",\n" +
                        "            \"en\": \"Fat\",\n" +
                        "            \"fr\": \"Matières grasses\",\n" +
                        "            \"it\": \"Grassi\"\n" +
                        "          },\n" +
                        "          \"unit\": \"g\",\n" +
                        "          \"per_hundred\": 0,\n" +
                        "          \"per_portion\": 0,\n" +
                        "          \"per_day\": 0\n" +
                        "        },\n" +
                        "        \"energy_kcal\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Energie (kCal)\",\n" +
                        "            \"en\": \"Energy (kCal)\",\n" +
                        "            \"fr\": \"Énergie (kCal)\",\n" +
                        "            \"it\": \"Energia (kCal)\"\n" +
                        "          },\n" +
                        "          \"unit\": \"kCal\",\n" +
                        "          \"per_hundred\": 67,\n" +
                        "          \"per_portion\": 81,\n" +
                        "          \"per_day\": 4\n" +
                        "        },\n" +
                        "        \"energy\": {\n" +
                        "          \"name_translations\": {\n" +
                        "            \"de\": \"Energie\",\n" +
                        "            \"en\": \"Energy\",\n" +
                        "            \"fr\": \"Énergie\",\n" +
                        "            \"it\": \"Energia\"\n" +
                        "          },\n" +
                        "          \"unit\": \"kJ\",\n" +
                        "          \"per_hundred\": 280,\n" +
                        "          \"per_portion\": null,\n" +
                        "          \"per_day\": 4\n" +
                        "        }\n" +
                        "      },\n" +
                        "      \"created_at\": \"2016-06-28T06:39:07.707Z\",\n" +
                        "      \"updated_at\": \"2016-08-18T09:37:47.323Z\"\n" +
                        "    }\n" +
                        "  ]";
                result = result.substring(result.indexOf('['),result.indexOf(']'));
                assertEquals(result,true_result);

            }
        }.execute(barcode);



    }

}
