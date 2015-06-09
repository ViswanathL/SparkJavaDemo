package exp.web.java.spark.util;

import spark.ResponseTransformer;

import com.google.gson.Gson;

/**
 * SparkDemo - exp.web.java.spark
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 21, 2015
 */

public class JsonTransformer implements ResponseTransformer {

    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
