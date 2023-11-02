package com.nikozka.app.repositories;

import com.nikozka.app.exeptions.DatabaseException;
import com.nikozka.app.models.ProductDTO;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ProductRepositoryTest {
    Connection connection = mock(Connection.class);
    ProductRepository testObject = new ProductRepository(connection);

    @Test
    void testInsertProducts() throws SQLException {

        List<ProductDTO> products = new ArrayList<>();
        products.add(new ProductDTO("Product1", 1));
        products.add(new ProductDTO("Product2", 2));
        products.add(new ProductDTO("Product3", 3));

        PreparedStatement mockedPreparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenReturn(mockedPreparedStatement);
        doNothing().when(mockedPreparedStatement).setInt(anyInt(), anyInt());
        doNothing().when(mockedPreparedStatement).setString(anyInt(), anyString());
        doNothing().when(mockedPreparedStatement).setInt(anyInt(), anyInt());

        doNothing().when(mockedPreparedStatement).addBatch();
        when(mockedPreparedStatement.executeBatch()).thenReturn(new int[0]);

        testObject.insertProducts(products, 3);

        verify(connection, times(1)).prepareStatement(anyString());

        verify(mockedPreparedStatement, times(3)).setInt(eq(1), anyInt());
        verify(mockedPreparedStatement, times(3)).setString(eq(2), anyString());
        verify(mockedPreparedStatement, times(3)).setInt(eq(3), anyInt());
        verify(mockedPreparedStatement, times(3)).addBatch();
        verify(mockedPreparedStatement, times(1)).executeBatch();
    }
    @Test
    void testInsertProductsThrowsException() throws SQLException {
        List<ProductDTO> products = new ArrayList<>();
        PreparedStatement mockedPreparedStatement = mock(PreparedStatement.class);
        when(connection.prepareStatement(anyString())).thenThrow(SQLException.class);

        assertThrows(DatabaseException.class, () -> testObject.insertProducts(products, 3));

        verify(mockedPreparedStatement, times(0)).setInt(eq(1), anyInt());
        verify(mockedPreparedStatement, never()).setString(eq(2), anyString());
        verify(mockedPreparedStatement, never()).setInt(eq(3), anyInt());
        verify(mockedPreparedStatement, never()).addBatch();
        verify(mockedPreparedStatement, never()).executeBatch();

    }
}
