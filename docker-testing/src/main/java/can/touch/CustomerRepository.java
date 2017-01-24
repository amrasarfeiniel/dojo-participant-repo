package can.touch;/*
 * Copyright 2016 Palantir Technologies, Inc. All rights reserved.
 *
 * THIS SOFTWARE CONTAINS PROPRIETARY AND CONFIDENTIAL INFORMATION OWNED BY PALANTIR TECHNOLOGIES INC.
 * UNAUTHORIZED DISCLOSURE TO ANY THIRD PARTY IS STRICTLY PROHIBITED
 *
 * For good and valuable consideration, the receipt and adequacy of which is acknowledged by Palantir and recipient
 * of this file ("Recipient"), the parties agree as follows:
 *
 * This file is being provided subject to the non-disclosure terms by and between Palantir and the Recipient.
 *
 * Palantir solely shall own and hereby retains all rights, title and interest in and to this software (including,
 * without limitation, all patent, copyright, trademark, trade secret and other intellectual property rights) and
 * all copies, modifications and derivative works thereof.  Recipient shall and hereby does irrevocably transfer and
 * assign to Palantir all right, title and interest it may have in the foregoing to Palantir and Palantir hereby
 * accepts such transfer. In using this software, Recipient acknowledges that no ownership rights are being conveyed
 * to Recipient.  This software shall only be used in conjunction with properly licensed Palantir products or
 * services.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.util.List;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import cannot.touch.DataSourceFactory;
import cannot.touch.Retrying;

public interface CustomerRepository {
    static CustomerRepository createDefault(DataSource datasource) {
        DBI dbi = new DBI(datasource);
        try {
            return Retrying.withRetry( () -> dbi.open(CustomerRepository.class));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static CustomerRepository createPostgres() {
        return createDefault(DataSourceFactory.createPostgres());
    }

    static CustomerRepository createMysql() {
        return createDefault(DataSourceFactory.createMysql());
    }

    @SqlUpdate("create table customers (id int primary key, name varchar(100))")
    void createCustomerTable();

    @SqlUpdate("create table phonenumbers (phonenumber varchar(15), customer_id int)")
    void createPhoneNumberTable();

    @SqlUpdate("insert into customers (id, name) values (:id, :name)")
    void insertCustomer(@Bind("id") int id, @Bind("name") String name);

    @SqlUpdate("insert into phonenumbers (phonenumber, customer_id) values (:number, :customer_id)")
    void insertContactDetail(@Bind("customer_id") int customerId, @Bind("number") String phoneNumber);

    @SqlQuery("select * from customers where id = :id")
    @Mapper(CustomerMapper.class)
    Customer getCustomer(@Bind("id") int id);

    @SqlQuery("select * from customers")
    @Mapper(CustomerMapper.class)
    List<Customer> getAllCustomers();

    /*
    ---- ADD IMPLEMENTATION HERE ----
    | This method should join the   |
    | customer table with the       |
    | contact details table. Use    |
    | similar annotations to        |
    | `getCustomer` above.          |
    ---------------------------------
    */
    @SqlQuery("select phonenumber, name from phonenumbers, customers where id = customer_id")
    @Mapper(ContactListMapper.class)
    List<ContactDetail> getAllContactDetails();

    @SqlQuery("select * from phonenumbers")
    @Mapper(InternalContactMapper.class)
    List<InternalContact> getAllContactsInternal();

    /**
     * close with no args is used to close the connection
     */
    void close();

    @SqlUpdate("delete from customers;"
            + " delete from phonenumbers;")
    void clearTables();
}
