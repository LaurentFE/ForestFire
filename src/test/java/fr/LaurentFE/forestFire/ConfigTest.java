package fr.LaurentFE.forestFire;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigTest {

    @Test
    void readConfigFromFile() {
        Config config = Config.readConfig("./src/test/resources/testConfig.json");
         String expectedResult =
                 """
                         forestHeight:9
                         forestLength:9
                         fireSpreadProbability:0.6
                         ignitedTrees:(1,1)(2,2)""";
        assertEquals(expectedResult, config.toString());
    }
}