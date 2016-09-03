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
 * User model. For getting unique avatars used www.avatarpro.biz
 */
public class User implements BaseModel {
    public long id;
    public String name;
    public String avatar;

    public User(final String name) {
        this.name = name;
        this.id = name.hashCode();
        this.avatar = "http://www.avatarpro.biz/avatar/" + name.hashCode();
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
