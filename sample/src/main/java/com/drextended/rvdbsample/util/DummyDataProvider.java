/*
 *  Copyright Roman Donchenko. All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.drextended.rvdbsample.util;

import com.drextended.rvdbsample.model.Advertisement;
import com.drextended.rvdbsample.model.Location;
import com.drextended.rvdbsample.model.User;

import java.util.Arrays;
import java.util.List;

/**
 * Provider of fake data
 */
public class DummyDataProvider {

    public static List<User> getUsers() {
        return Arrays.asList(
                new User("Tarik Dickerson"),
                new User("Hilel Hart"),
                new User("Dane Warner"),
                new User("Lamar Gross"),
                new User("Driscoll Lancaster"),
                new User("Finn Kelly"),
                new User("Quinlan Burt"),
                new User("Ryan Dotson"),
                new User("Zachary Benjamin"),
                new User("Connor Merrill"),
                new User(" Jeremy Alford"),
                new User("Demetrius Hodge"),
                new User("Troy Ware"),
                new User("Jared Villarreal"),
                new User("Slade Romero"),
                new User("Keane Franks")
        );
    }

    public static Advertisement getAdvertisment(int index) {
        return new Advertisement("This is Advertisment #" + index);
    }

    public static List<Location> getLocations() {
        return Arrays.asList(
                new Location("Amsterdam"),
                new Location("Paris"),
                new Location("Rome"),
                new Location("London"),
                new Location("New York"),
                new Location("Los Angeles"),
                new Location("Sydney"),
                new Location("Copenhagen"),
                new Location("Dubai"),
                new Location("Berlin"),
                new Location("Budapest"),
                new Location("Tokyo")
        );
    }
}