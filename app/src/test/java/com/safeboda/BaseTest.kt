/*
 * Copyright 2020 Safeboda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.safeboda

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.safeboda.core.BaseKoinTest
import com.safeboda.data.local.Database
import com.safeboda.data.local.dao.FollowersDao
import com.safeboda.data.local.dao.FollowingDao
import com.safeboda.data.local.dao.UserDao
import org.junit.After
import org.junit.Before
import java.io.IOException

internal open class BaseTest : BaseKoinTest() {

    // database and dao
    private lateinit var database: Database
    protected lateinit var userDao: UserDao
    protected lateinit var followersDao: FollowersDao
    protected lateinit var followingDao: FollowingDao

    @Before
    open fun setup() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        database =
            Room.inMemoryDatabaseBuilder(context, Database::class.java).allowMainThreadQueries()
                .build()
        userDao = database.userDao()
        followersDao = database.followersDao()
        followingDao = database.followingDao()
    }

    @After
    @Throws(IOException::class)
    open fun tearDown() {
        database.close()
    }
}