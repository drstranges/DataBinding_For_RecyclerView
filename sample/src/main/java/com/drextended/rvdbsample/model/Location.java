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
package com.drextended.rvdbsample.model;

/**
 * Location model. For getting unique images used loremflickr.com
 */

public class Location implements BaseModel {
    public long id;
    public String name;
    public String image;

    public Location(final String name) {
        this.name = name;
        this.id = name.hashCode();
        this.image = "http://loremflickr.com/100/100/" + name + "?random=" + this.id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
}
