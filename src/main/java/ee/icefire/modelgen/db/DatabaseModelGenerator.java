package ee.icefire.modelgen.db;

import ee.icefire.modelgen.db.model.DatabaseModel;

public interface DatabaseModelGenerator {

  DatabaseModel generate() throws Exception;

}
