# RoomForAsset
An Android helper class to manage database creation and version management using an application's raw asset files.

This library provides developers with a simple way to ship their Android app with an existing SQLite database (which may be pre-populated with data) and to manage its initial creation and any upgrades required with subsequent version releases.

It is implemented as an extension to `Room`, providing an easy way to use `Room` with an existing SQLite database.

Setup
-----

#### Gradle

If you are using the Gradle build system, simply add the following dependency in your `build.gradle` file:

```groovy
dependencies {
    compile 'com.humazed:room-for-asset:1.0.0'
}

-----

RoomForAsset is intended as a drop in alternative for the framework's [Room](https://developer.android.com/topic/libraries/architecture/room.html).

You can use `RoomForAsset` as you use `Room` but with two changes:

1. Use `RoomAsset.databaseBuilder()` instead of `Room.databaseBuilder()` 
2. In `@Database` use `version = 2` instead of `version = 1`

```kotlin
  val db = RoomAsset.databaseBuilder(applicationContext, AppDatabase::class.java, "chinook.db").build()
  val employees = db.chinookDao().employees
```

SQLiteAssetHelper relies upon asset file and folder naming conventions. Your `assets` folder will either be under your project root, or under `src/main` if you are using the default gradle project structure. At minimum, you must provide the following:

* A `databases` folder inside `assets`
* A SQLite database inside the `databases` folder whose file name matches the database name you provide in code (including the file extension, if any)

For the example above, the project would contain the following:

    assets/databases/northwind.db

Earlier versions of this library required the database asset to be compressed within a ZIP archive. This is no longer a requirement, but is still supported. Applications still targeting Gingerbread (API 10) or lower should continue to provide a compressed archive to ensure large database files are not corrupted during the packaging process. The more Linux friendly GZIP format is also supported. The naming conventions using the above example are as follows:

* ZIP: `assets/databases/northwind.db.zip` (a single SQLite database file must be the only file within the archive)
* GZIP: `assets/databases/northwind.db.gz`

The database will be extracted from the assets and copied into place within your application's private data directory. If you prefer to store the database file somewhere else (such as external storage) you can use the alternate constructor to specify a storage path. You must ensure that this path is available and writable whenever your application needs to access the database.

```java
super(context, DATABASE_NAME, context.getExternalFilesDir(null).getAbsolutePath(), null, DATABASE_VERSION);
```

The database is made available for use the first time either `getReadableDatabase()` or `getWritableDatabase()` is called.

The class will throw a `SQLiteAssetHelperException` if you do not provide the appropriately named file.

The SQLiteOpenHelper methods `onConfigure`, `onCreate` and `onDowngrade` are not supported by this implementation and have been declared `final`.

The [samples:database-v1](https://github.com/jgilfelt/android-sqlite-asset-helper/tree/master/samples/database-v1) project demonstrates a simple database creation and usage example using the classic Northwind database.


Credits
-------

####Author:

  * [Humazed](https://github.com/humazed)

License
-------

    Copyright (C) 2011 readyState Software Ltd
    Copyright (C) 2007 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

 [1]: https://search.maven.org/remote_content?g=com.readystatesoftware.sqliteasset&a=sqliteassethelper&v=LATEST
