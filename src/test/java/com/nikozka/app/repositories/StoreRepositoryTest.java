package com.nikozka.app.repositories;

import com.nikozka.app.exeptions.DatabaseException;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class StoreRepositoryTest {

    Connection connection = mock(Connection.class);
    Random random = mock(Random.class);
    StockRepository testObject = new StockRepository(connection, random);
    private final static int quantity = 3;

    @Test
    void testInsertStock() throws SQLException {
        PreparedStatement mockedPreparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        doNothing().when(mockedPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mockedPreparedStatement).addBatch();
        when(mockedPreparedStatement.executeBatch()).thenReturn(new int[0]);

        when(random.nextInt(anyInt())).thenReturn(10);

        testObject.insertStock(quantity);

        verify(connection, times(1)).prepareStatement(anyString());
        verify(mockedPreparedStatement, times(36)).setInt(eq(1), anyInt());
        verify(mockedPreparedStatement, times(36)).addBatch();
        verify(mockedPreparedStatement, times(1)).executeBatch();
    }

    @Test
    void testInsertStockThrowsException() throws SQLException {
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);
        assertThrows(DatabaseException.class, () -> testObject.insertStock(quantity));
    }
}
