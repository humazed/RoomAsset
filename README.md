# RoomAsset

An Android helper class to manage database creation and version management using an application's raw asset files.

This library provides developers with a simple way to ship their Android app with an existing SQLite database (which may be pre-populated with data) and to manage its initial creation and any upgrades required with subsequent version releases.

It is implemented as an extension to `Room`, providing an easy way to use `Room` with an existing SQLite database.

---

# Gradle Dependency

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/b2a019a18e3a48e5b50ae4a5f1ed3135)](https://www.codacy.com/app/humazed/RoomAsset?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=humazed/RoomAsset&amp;utm_campaign=Badge_Grade)
[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square)](https://www.apache.org/licenses/LICENSE-2.0.html)


### Dependency

Add this to your module's `build.gradle` file (make sure the version matches the last [release](https://github.com/humazed/RoomAssety/releases/latest)):

Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
	repositories {
		...
		maven { url "https://jitpack.io" }
	}
}
```

Add the dependency
```gradle
dependencies {
    // ... other dependencies
    implementation 'com.github.humazed:RoomAsset:v1.0-beta5'
}
```
-----

`RoomAsset` is intended as a drop in alternative for the framework's [Room](https://developer.android.com/topic/libraries/architecture/room.html).

You can use `RoomAsset` as you use `Room` but with two changes:

1. Use `RoomAsset.databaseBuilder()` instead of `Room.databaseBuilder()` 
2. In `@Database` use `version = 2` instead of `version = 1`

```kotlin
  val db = RoomAsset.databaseBuilder(applicationContext, AppDatabase::class.java, "chinook.db").build()
  val employees = db.chinookDao().employees
```

`RoomAsset` relies upon asset file and folder naming conventions. Your `assets` folder will either be under your project root, or under `src/main` if you are using the default gradle project structure. At minimum, you must provide the following:

* A `databases` folder inside `assets`
* A SQLite database inside the `databases` folder whose file name matches the database name you provide in code (including the file extension, if any)

For the example above, the project would contain the following:

    assets/databases/chinook.db


The database will be extracted from the assets and copied into place within your application's private data directory. If you prefer to store the database file somewhere else (such as external storage) you can use the alternate constructor to specify a storage path. You must ensure that this path is available and writable whenever your application needs to access the database.

```kotlin
  val db = RoomAsset.databaseBuilder(applicationContext, AppDatabase::class.java, "chinook.db",
        applicationContext.getExternalFilesDir(null).absolutePath).build()
```

The library will throw a `SQLiteAssetHelperException` if you do not provide the appropriately named file.

Supported data types: `TEXT`, `INTEGER`, `REAL`, `BLOB`


The [samples](https://github.com/humazed/RoomAsset/tree/master/app) project demonstrates a simple database creation and usage example using the classic [Chinook database](http://www.sqlitetutorial.net/sqlite-sample-database).



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
