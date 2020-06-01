/*
 * Copyright 2008 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.gwtproject.xml.client;

/**
 * <code>Attr</code> objects represent key-value pairs of attributes on <code>Element</code>
 * objects. <code>Attr</code> objects are immutable.
 */
public interface Attr extends Node {
  /**
   * This method retrieves the name.
   *
   * @return the name of this <code>Attr</code>
   */
  String getName();

  /**
   * This method determines whether the value of this <code>Attr</code> was specified here, or as a
   * default value in a DTD.
   *
   * @return <code>true</code> if the value of this <code>Attr</code> was specified locally.
   */
  boolean getSpecified();

  /**
   * This method retrieves the value.
   *
   * @return the value of this <code>Attr</code>
   */
  String getValue();
}
