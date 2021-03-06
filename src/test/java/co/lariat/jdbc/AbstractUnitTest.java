package co.lariat.jdbc;

/*-
 * #%L
 * jdbc-simple
 * %%
 * Copyright (C) 2013 - 2016 Lariat
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import org.junit.BeforeClass;

import java.sql.SQLException;

/**
 * @author <a href="mailto:john@lariat.co">John D. Dunlap</a>
 * @since 8/4/16 10:31 PM - Created with IntelliJ IDEA.
 */
public class AbstractUnitTest {
    protected static SimpleConnection connection = null;

    @BeforeClass
    public static void beforeClass() throws SQLException {
        // Drop the table so that it can be recreated in a known state,
        // if a connection already exists
        if (connection != null) {
            connection.execute("drop table users");
        }

        // Get a connection to the database
        connection = DB.getConnection("jdbc:hsqldb:mem:test", "sa", "");

        // Create a table
        connection.execute("create table users(\n" +
            "   id INTEGER not null,\n" +
            "   username char(25),\n" +
            "   password char(25),\n" +
            "   active BOOLEAN,\n" +
            "   last_active TIMESTAMP,\n" +
            "   balance NUMERIC(10, 2),\n" +
            "   PRIMARY KEY (id)\n" +
            ");"
        );

        // Add some data
        connection.execute(
            "insert into users(id, username, password, active, last_active, balance) values(?,?,?,?,?,?)",
            1,
            "admin",
            "password",
            true,
            "1970-01-01 00:00:00",
            1345.23
        );
        connection.execute(
            "insert into users(id, username, password, active, last_active, balance) values(?,?,?,?,?,?)",
            2,
            "bob.wiley",
            "password2",
            true,
            "1973-02-02 00:00:00",
            564.77
        );
    }
}
