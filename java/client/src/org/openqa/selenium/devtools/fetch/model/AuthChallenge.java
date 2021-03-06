// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package org.openqa.selenium.devtools.fetch.model;

import org.openqa.selenium.Beta;
import org.openqa.selenium.json.JsonInput;

import java.util.Objects;

/**
 * Authorization challenge for HTTP status code 401 or 407.EXPERIMENTAL
 */
@Beta
public class AuthChallenge {

  /**
   * Source of the authentication challenge.
   */
  private AuthChallengeSource source;
  /**
   * Origin of the challenger.
   */
  private String origin;
  /**
   * The authentication scheme used, such as basic or digest
   */
  private String scheme;
  /**
   * The realm of the challenge. May be empty.
   */
  private String realm;

  public AuthChallenge(AuthChallengeSource source, String origin, String scheme,
                       String realm) {
    this.source = source;
    this.origin = Objects.requireNonNull(origin, "origin is required");
    this.scheme = Objects.requireNonNull(scheme, "scheme is required");
    this.realm = Objects.requireNonNull(realm, "realm is required");
  }

  private static AuthChallenge fromJson(JsonInput input) {
    AuthChallengeSource source = null;
    String origin = null, scheme = null, realm = null;
    while (input.hasNext()) {
      switch (input.nextName()) {
        case "source":
          source = input.read(AuthChallengeSource.class);
          break;
        case "origin":
          origin = input.nextString();
          break;
        case "scheme":
          scheme = input.nextString();
          break;
        case "realm":
          realm = input.nextString();
          break;
        default:
          input.nextString();
          break;
      }
    }
    return new AuthChallenge(source, origin, scheme, realm);
  }

}
