package ee.icefire.modelgen.db;

import ee.icefire.modelgen.db.model.DatabaseModel;

import java.sql.Connection;

public interface DatabaseModelCustomizer {

    public void customize(DatabaseModel db, Connection connection);

}
