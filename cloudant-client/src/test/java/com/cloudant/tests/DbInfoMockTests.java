/*
 * Copyright © 2018 IBM Corp. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */

package com.cloudant.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.cloudant.client.api.CloudantClient;
import com.cloudant.client.api.Database;
import com.cloudant.tests.base.TestWithMockedServer;

import org.junit.jupiter.api.Test;

import okhttp3.mockwebserver.MockResponse;

/**
 * Created by samsmith on 13/12/2018.
 */

public class DbInfoMockTests extends TestWithMockedServer {

    @Test
    public void getDbInfoLongPurgeSeqAsLong() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"purge_seq\":123}");
        server.enqueue(response);

        assertEquals(123, db.info().getPurgeSeq());
    }

    @Test
    public void getDbInfoStringPurgeSeqAsLong() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        String mockPurgeSeq = "\"3-g1AAAABXeJzLYWBgYMpgTmEQTM4vTc5ISXLIyU9OzMnILy7JAUklMiTV____PyuRAY-iPBYgydAApP6D1TJnAQCZtRxv\"";
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"purge_seq\":" + mockPurgeSeq + "}");
        server.enqueue(response);

        assertEquals(0, db.info().getPurgeSeq());
    }

    @Test
    public void getDbInfoLongPurgeSeqAsString() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"purge_seq\":123}");
        server.enqueue(response);

        assertEquals("123", db.info().getStringPurgeSeq());
    }

    @Test
    public void getDbInfoStringPurgeSeqAsString() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        String mockPurgeSeq = "\"3-g1AAAABXeJzLYWBgYMpgTmEQTM4vTc5ISXLIyU9OzMnILy7JAUklMiTV____PyuRAY-iPBYgydAApP6D1TJnAQCZtRxv\"";
        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"purge_seq\":" + mockPurgeSeq + "}");
        server.enqueue(response);

        assertEquals(mockPurgeSeq, db.info().getStringPurgeSeq());
    }

    @Test
    public void getDbPartitionPartitionedIndexesCount() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"partitioned_indexes\":{\"count\":9}}");
        server.enqueue(response);

        assertEquals(9, db.info().getPartitionedIndexes().getCount());
    }

    @Test
    public void getDbPartitionPartitionedIndexesLimit() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"partitioned_indexes\":{\"limit\":10}}");
        server.enqueue(response);

        assertEquals(10, db.info().getPartitionedIndexes().getLimit());
    }

    @Test
    public void getDbPartitionPartitionedIndexesIndexesSearch() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"partitioned_indexes\":{\"indexes\":{\"search\":3}}}");
        server.enqueue(response);

        assertEquals(3, db.info().getPartitionedIndexes().getIndexes().getSearch());
    }

    @Test
    public void getDbPartitionPartitionedIndexesIndexesView() {
        CloudantClient c = CloudantClientHelper.newMockWebServerClientBuilder(server).build();
        Database db = c.database("animaldb", false);

        MockResponse response = new MockResponse()
                .setResponseCode(200)
                .setBody("{\"partitioned_indexes\":{\"indexes\":{\"view\":6}}}");
        server.enqueue(response);

        assertEquals(6, db.info().getPartitionedIndexes().getIndexes().getView());
    }

}
