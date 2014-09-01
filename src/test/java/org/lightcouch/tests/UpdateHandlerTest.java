/*
 * Copyright (C) 2011 lightcouch.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lightcouch.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.lightcouch.CouchDatabase;
import org.lightcouch.CouchDbClient;
import org.lightcouch.Params;
import org.lightcouch.Response;

public class UpdateHandlerTest {

	private static CouchDbClient dbClient;
	private static CouchDatabase db;
	

	@BeforeClass
	public static void setUpClass() {
		dbClient = new CouchDbClient();
		db = dbClient.database("lightcouch-db-test", true);
		db.syncDesignDocsWithDb();
	}

	@AfterClass
	public static void tearDownClass() {
		dbClient.shutdown();
	}

	@Test
	public void updateHandler_queryString() {
		final String oldValue = "foo";
		final String newValue = "foo bar";
		
		Response response = db.save(new Foo(null, oldValue));
		
		String query = "field=title&value=" + newValue;
		
		String output = db.invokeUpdateHandler("example/example_update", response.getId(), query);
		
		// retrieve from db to verify
		Foo foo = db.find(Foo.class, response.getId());
		
		assertNotNull(output);
		assertEquals(foo.getTitle(), newValue);
	}
	
	@Test
	public void updateHandler_queryParams() {
		final String oldValue = "foo";
		final String newValue = "foo bar";
		
		Response response = db.save(new Foo(null, oldValue));

		Params params = new Params()
					.addParam("field", "title")
					.addParam("value", newValue);
		String output = db.invokeUpdateHandler("example/example_update", response.getId(), params);
		
		// retrieve from db to verify
		Foo foo = db.find(Foo.class, response.getId());
		
		assertNotNull(output);
		assertEquals(foo.getTitle(), newValue);
	}
}